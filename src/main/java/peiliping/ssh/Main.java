package peiliping.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.atomic.AtomicReference;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class Main {

	private static String ip = "127.0.0.1";
	private static String username = "AAA";
	private static String password = "BBB";
	private static long SLEEP = 1000;
	private static int TOTAL = 100;
	private static Stat S = new Stat() ;

	public static void main(String[] args) throws InterruptedException {
		
		new Thread(new Runnable(){@Override public void run(){getload(TOTAL);}}).start();
		new Thread(new Runnable(){@Override public void run(){getdstat(TOTAL);}}).start();
		
		while(true){
			System.out.println(S.load1.get() + "|" + S.load5.get() + "|" + S.load15.get() + "|" +
					S.cpuusr.get() + "|" + S.cpusys.get() + "|" + S.cpuidle.get() + "|" +
					S.read.get() + "|" + S.write.get() + "|" +
					S.recv.get() + "|" + S.send.get() + "|" +
					S.interrupt.get() + "|" + S.contextswitch.get() + "|" +
					""
			);
			Thread.sleep(1000);
		}
	}

	private static void getdstat(int total) {
		Connection conn = new Connection(ip);
		try {
			conn.connect();
			conn.authenticateWithPassword(username, password);
			Session sess = conn.openSession();
			sess.execCommand("dstat");
			InputStream in = new StreamGobbler(sess.getStdout());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			br.readLine();
			br.readLine();
			br.readLine();
			while (total-- > 0) {
				String dstat = br.readLine();
				S.cpuusr.compareAndSet(S.cpuusr.get(),dstat.substring(0,4));
				S.cpusys.compareAndSet(S.cpusys.get(),dstat.substring(5,8));
				S.cpuidle.compareAndSet(S.cpuidle.get(),dstat.substring(9,12));
				
				S.read.compareAndSet(S.read.get(), dstat.substring(25,30));
				S.write.compareAndSet(S.write.get(), dstat.substring(31,35));
				
				S.recv.compareAndSet(S.recv.get(),dstat.substring(36,42));
				S.send.compareAndSet(S.send.get(),dstat.substring(43,48));
				
				S.interrupt.compareAndSet(S.interrupt.get(),dstat.substring(60,65));
				S.contextswitch.compareAndSet(S.contextswitch.get(),dstat.substring(66,70));
				Thread.sleep(SLEEP);
			}
			sess.close();
		} catch (IOException e) {
		} catch (InterruptedException e) {
		}
		conn.close();
	}
	
	private static void getload(int total) {
		Connection conn = new Connection(ip);
		try {
			conn.connect();
			conn.authenticateWithPassword(username, password);
			while (total-- > 0) {
				Session sess = conn.openSession();
				sess.execCommand("uptime");
				InputStream in = new StreamGobbler(sess.getStdout());
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				String load = br.readLine();
				load = load.split(": ")[1];
				String[] loads = load.split(", ");
				S.load1.compareAndSet(S.load1.get(),loads[0]);
				S.load5.compareAndSet(S.load5.get(),loads[1]);
				S.load15.compareAndSet(S.load15.get(),loads[2]);
				sess.close();
				Thread.sleep(SLEEP);
			}
		} catch (IOException e) {
		} catch (InterruptedException e) {
		}
		conn.close();
	}

	static class Stat {
		
		/**
		 * Load
		 */
		public AtomicReference<String> load1 = new AtomicReference<String>();
		public AtomicReference<String> load5 = new AtomicReference<String> ();
		public AtomicReference<String> load15 = new AtomicReference<String>();

		/**
		 * cpu
		 */
		public AtomicReference<String> cpuusr = new AtomicReference<String>();
		public AtomicReference<String> cpusys = new AtomicReference<String>();
		public AtomicReference<String> cpuidle = new AtomicReference<String>();
		
		/**
		 * system
		 */
		public AtomicReference<String> interrupt = new AtomicReference<String>();
		public AtomicReference<String> contextswitch = new AtomicReference<String>();
		
		/**
		 * disk
		 */
		public AtomicReference<String> read = new AtomicReference<String>();
		public AtomicReference<String> write = new AtomicReference<String>();

		/**
		 * net
		 */
		public AtomicReference<String> recv = new AtomicReference<String>();
		public AtomicReference<String> send = new AtomicReference<String>();
		
		/**
		 * nginx
		 */
		public AtomicReference<String> qps = new AtomicReference<String>();
		public AtomicReference<String> rt = new AtomicReference<String>();
		
		/**
		 * jvm
		 */
		public AtomicReference<String> threadnum = new AtomicReference<String>();
		public AtomicReference<String> S0 = new AtomicReference<String>();
		public AtomicReference<String> S1 = new AtomicReference<String>();
		public AtomicReference<String> Eden = new AtomicReference<String>();
		public AtomicReference<String> Old = new AtomicReference<String>();
		public AtomicReference<String> YGC = new AtomicReference<String>();
		public AtomicReference<String> CMSGC = new AtomicReference<String>();
		
	}
	
}
