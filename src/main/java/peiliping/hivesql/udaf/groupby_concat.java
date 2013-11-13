package peiliping.hivesql.udaf;

import org.apache.hadoop.hive.ql.exec.UDAF;
import org.apache.hadoop.hive.ql.exec.UDAFEvaluator;


public class groupby_concat extends UDAF {

	public static class ConcatUDAFEvaluator implements UDAFEvaluator {
		
		public static class PartialResult {
			String result;
			String delimiter;
		}

		private PartialResult partial;

		
		//初始化
		
		public void init() {
			partial = null;
		}

		//执行过程map
		
		public boolean iterate(String value, String deli) {

			if (value == null) {
				return true;
			}
			if (partial == null) {
				partial = new PartialResult();
				partial.result = new String("");
				if (deli == null || deli.equals("")) {
					partial.delimiter = new String(",");
				} else {
					partial.delimiter = new String(deli);
				}

			}
			if (partial.result.length() > 0) {
				partial.result = partial.result.concat(partial.delimiter);
			}

			partial.result = partial.result.concat(value);

			return true;
		}
		
		//map返回值

		public PartialResult terminatePartial() {
			return partial;
		}

		//合并map返回值 reduce
		
		public boolean merge(PartialResult other) {
			if (other == null) {
				return true;
			}
			if (partial == null) {
				partial = new PartialResult();
				partial.result = new String(other.result);
				partial.delimiter = new String(other.delimiter);
			} else {
				if (partial.result.length() > 0) {
					partial.result = partial.result.concat(partial.delimiter);
				}
				partial.result = partial.result.concat(other.result);
			}
			return true;
		}
		
		//最终结果

		public String terminate() {
			return new String(partial.result);
		}
	}

}