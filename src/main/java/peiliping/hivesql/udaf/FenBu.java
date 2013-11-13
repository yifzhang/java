package peiliping.hivesql.udaf;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map.Entry;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;

public class FenBu extends UDAF {

	public static double getNum(double num) {
		return new BigDecimal(num).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static class FenBuUDAFEvaluator implements UDAFEvaluator {

		private Result partial;

		// 初始化gedi
		public void init() {
			partial = new Result();
		}

		// 执行过程map
		public boolean iterate(String value, String unitstr, String ignore,String addupignore) {
			if (value == null) {
				return true;
			}
			if (partial == null) {
				partial = new Result();
				partial.unit = Long.valueOf(unitstr.trim());
				partial.ignore = Double.valueOf(ignore.trim());
				partial.addupignore = Double.valueOf(addupignore.trim());
			}
			long tk = Double.valueOf(value.trim()).longValue() / partial.unit;
			Long tv = partial.map.get(tk);
			partial.map.put(tk, tv == null ? 1L : ++tv);
			partial.total += 1;
			return true;
		}

		// map返回值
		public Result terminatePartial() {
			return partial;
		}

		// 合并map返回值 reduce
		public boolean merge(Result other) {
			if (other == null) {
				return true;
			}
			for (Entry<Long, Long> e : other.map.entrySet()) {
				Long tv = partial.map.get(e.getKey());
				partial.map.put(e.getKey(),(tv == null ? 0L : tv) + e.getValue());
			}
			partial.total += other.total;
			partial.unit = other.unit;
			partial.ignore = other.ignore;
			partial.addupignore = other.addupignore;
			return true;
		}

		// 最终结果
		public String terminate() {
			StringBuilder result = new StringBuilder();
			ArrayList<KV> list = new ArrayList<KV>();
			for (Entry<Long, Long> e : partial.map.entrySet()) {
				KV kv = new KV();
				kv.K = e.getKey();
				kv.V = e.getValue();
				list.add(kv);
			}
			Collections.sort(list, new Comparator<KV>() {
				@Override
				public int compare(KV o1, KV o2) {
					return o1.K < o2.K ? 0 : 1;
				}
			} );
			
			String start = null, end = null;
			double tmptotal = 0, adduptotal = 0;

			for (int i = 0; i < list.size(); i++) {
				KV kv = list.get(i);
				if (start == null) {
					start = kv.K * partial.unit + "";
				}

				tmptotal += kv.V;
				adduptotal += kv.V;
				if ((kv.getRate(partial.total) > partial.ignore) || (getNum(tmptotal * 100 / partial.total) > partial.addupignore) || (i== list.size()-1)) {
					end = (kv.K + 1) * partial.unit + "";
				} else {
					continue;
				}

				if (start != null && end != null) {
					result.append(String.format("%10s", start))
							.append("~")
							.append(String.format("%-10s", end))
							.append("\t")
							.append(String.format("%-10s","" + Double.valueOf(tmptotal).longValue()))
							.append("\t")
							.append(getNum(tmptotal * 100 / partial.total))
							.append("%").append("\t")
							.append(getNum(adduptotal * 100 / partial.total))
							.append("%").append("\n");
					start = null; end = null; tmptotal = 0;
				}
			}
			return result.toString();
		}
	}
}
