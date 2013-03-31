package peiliping.producerAconsumer;

public class T {
	
	public static void main(String[] args) throws InterruptedException {
		
		Storage<String> S = new Storage<String>(100);
		Producers<String> PS = new Producers<String>(100, 10, 100, S);
		Consumers<String> CS = new Consumers<String>(100, 10, 100, S);

		PS.run(new ProducerThread<String>(){
			@Override
			public void run() {
				while(true)
				addL.add("a");
			}
		}
		);
		
		CS.run(new ConsumerThread<String>(){
			@Override
			public void run() {
				while(true)
				delL.del();
			}
		}	
		);
		while(true){
			System.out.println(S.size());
			Thread.sleep(1000);
		}
		
	}
}
