package peiliping.htmlparser;

import java.net.URLDecoder;
import java.net.URLEncoder;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);
	
	public static void main(String[] args) {

		int begin = 1, end = 1000;
		String website = "http://bbs.xxxxx.net/";
		String url = website + "forum.php?mod=forumdisplay&fid=" + "2" + "&orderby=dateline&page=";

		try {
			for (int i = begin; i <= end; i++) {
				Parser p = ConnectUtil.creatParser(url + i, "UTF-8");
				NodeList titlelist = HtmlHandleUtil.getNodeList(p,HtmlHandleUtil.GET_TITLE_FROM_LIST);				

				for (int j=0 ; j<titlelist.size() ; j++) {
					Node node = titlelist.elementAt(j);
					String title = node.toPlainTextString().replaceAll(" ", "");
					String href = website + HtmlHandleUtil.getAttributeValueFromNode("href",node);
					href = href.replaceAll("amp;", "");
					String result = title + "\t" + href ;
					logger.warn(result);
				}
//				Thread.sleep(800);
				System.out.println(i);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
}
