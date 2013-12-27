package peiliping.paiallel;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class PaiallelPool<R> {

	private List<Callable<R>> listCallable;

	private long timeout = 500;

	private long atleast = 2;

	private ExecutorService sharePool;

	/**
	 * 独立线程池的
	 * 
	 * @param listCallable
	 * @param timeout
	 */
	public PaiallelPool(List<Callable<R>> listCallable, long timeout) {
		this.listCallable = listCallable;
		this.timeout = timeout;
	}

	public PaiallelPool(List<Callable<R>> listCallable) {
		this.listCallable = listCallable;
	}

	/**
	 * 共用线程池的
	 * 
	 * @param timeout
	 * @param threadsNum
	 */
	public PaiallelPool(long timeout, int threadsNum) {
		this.timeout = timeout;
		this.sharePool = Executors.newFixedThreadPool(threadsNum);
	}

	public List<R> runWithNewPool() {

		if (listCallable == null || listCallable.size() == 0) {
			throw new IllegalArgumentException("params invalid");
		}

		int pieceNum = listCallable.size();

		ExecutorService pool = Executors.newFixedThreadPool(pieceNum);
		List<FutureTask<R>> l = new ArrayList<FutureTask<R>>();
		List<R> r = new ArrayList<R>();

		try {
			for (int i = 0; i < pieceNum; i++) {
				FutureTask<R> dbtask = new FutureTask<R>(listCallable.get(i));
				l.add(dbtask);
				pool.submit(dbtask);
			}

			FutureTask<R> dbtask;
			long remaintime = timeout;
			long start, end;
			R t = null;
			for (int i = 0; i < pieceNum; i++) {
				dbtask = l.get(i);
				start = System.currentTimeMillis();
				if (remaintime > 0) {
					try {
						t = dbtask.get(remaintime, TimeUnit.MILLISECONDS);
					} catch (Exception e) {
					}
				}
				end = System.currentTimeMillis();
				r.add(t);
				remaintime = remaintime - (end - start);
				if (remaintime <= 0) {
					remaintime = atleast;
				}
			}
		} catch (Exception e) {
			r = null;
		} finally {
			pool.shutdownNow();
		}
		return r;
	}

	public List<R> runWithSharePool(List<Callable<R>> callable_list) {

		if (callable_list == null || callable_list.size() == 0) {
			throw new IllegalArgumentException("params invalid");
		}

		int pieceNum = callable_list.size();
		List<FutureTask<R>> l = new ArrayList<FutureTask<R>>();
		List<R> r = new ArrayList<R>();

		try {
			for (int i = 0; i < pieceNum; i++) {
				FutureTask<R> dbtask = new FutureTask<R>(callable_list.get(i));
				l.add(dbtask);
				sharePool.submit(dbtask);
			}

			FutureTask<R> dbtask;
			long remaintime = timeout;
			long start, end;
			R t = null;
			for (int i = 0; i < pieceNum; i++) {
				dbtask = l.get(i);
				start = System.currentTimeMillis();
				if (remaintime > 0) {
					try {
						t = dbtask.get(remaintime, TimeUnit.MILLISECONDS);
					} catch (Exception e) {
					}
				}
				end = System.currentTimeMillis();
				r.add(t);
				remaintime = remaintime - (end - start);
				if (remaintime <= 0) {
					remaintime = atleast;
				}
			}
		} catch (Exception e) {
			r = null;
		}
		return r;
	}

}
