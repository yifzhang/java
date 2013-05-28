package peiliping.httpclient;

import java.io.IOException;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;

public class GoogleHttpClient {
	
	public static void main(String[] args) throws IOException {
		
		HttpTransport transport = new NetHttpTransport(); 
		
		HttpRequest request = transport.createRequestFactory().buildGetRequest(new GenericUrl("http://www.baidu.com"));
	    HttpResponse response = request.execute();
	    System.out.println(response.parseAsString());
	}

}
