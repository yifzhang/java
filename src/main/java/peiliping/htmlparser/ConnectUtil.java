package peiliping.htmlparser;

import org.htmlparser.Parser;
import org.htmlparser.http.ConnectionManager;
import org.htmlparser.lexer.Page;
import org.htmlparser.util.ParserException;

public class ConnectUtil {

	public static Parser creatParser(String url, String code) {
		ConnectionManager manager = Page.getConnectionManager();
		try {
			Parser parser = new Parser(manager.openConnection(url));
			parser.setEncoding(code);
			return parser ;
		} catch (ParserException e) {
			System.out.println(e);
		}
		return null ;
	}

}
