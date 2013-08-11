package peiliping.htmlparser;

import org.htmlparser.Node;
import org.htmlparser.Parser;
import org.htmlparser.util.NodeList;

public class Main {
	
	public static void main(String[] args) {

		int begin = 1, end = 100;
		String website = "http://bbs.xxxxxxxxx.net/";
		String url = website + "forum.php?mod=forumdisplay&fid=" + "XXXXXX" + "&page=";

		try {
			for (int i = begin; i <= end; i++) {
				Parser p = ConnectUtil.creatParser(url + i, "UTF-8");
				NodeList titlelist = HtmlHandleUtil.getNodeList(p,HtmlHandleUtil.GET_TITLE_FROM_LIST);				

				for (int j=0 ; j<titlelist.size() ; j++) {
					Node node = titlelist.elementAt(j);
					String title = node.toPlainTextString().replaceAll(" ", "");
					String href = website + HtmlHandleUtil.getAttributeValueFromNode("href",node);
					String result = title + "\t" + href ;
					HtmlHandleUtil.writefile(result,"/home/peiliping/下载/log");
				}
				Thread.sleep(800);
				System.out.println(i);
			}
		} catch (Throwable e) {
			e.printStackTrace();
		}

	}
}
