package peiliping.hivesql.udaf;

import java.math.BigDecimal;

public class KV {
	public long K;
	public long V;
	public double getRate(long total){
		return  (new BigDecimal(V * 100 /Double.valueOf(total))).setScale(2,BigDecimal.ROUND_HALF_UP).doubleValue();
	}	
}
