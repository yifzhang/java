package peiliping.htmlparser;

import java.util.HashMap;
import java.util.Map;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.nodes.TagNode;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;

public class HtmlHandleUtil {
	
	private static Map<Integer, NodeFilter > filterMap = new HashMap<Integer,NodeFilter >();
	
	public static final int GET_TITLE_FROM_LIST = 1 ;
	
	private static NodeFilter getNodeFilter(int key){
		return filterMap.get(key);
	}

	public static NodeList getNodeList(Parser parser,int key){
		NodeFilter filter = getNodeFilter(key);
		NodeList nodelist=null;
		
		if(parser == null) {
			return  new NodeList();
		}
		
		try {
			 nodelist=parser.parse(filter);
		} catch (ParserException e) { 
		}
		return nodelist;
	}
	
	public static String getAttributeValueFromNode(String attributeName,Node n){
		TagNode tagNode = new TagNode();
		tagNode.setText(n.toHtml());
		return tagNode.getAttribute(attributeName);
	}
	
	static{
		filterMap.put(GET_TITLE_FROM_LIST,new AndFilter(new TagNameFilter("a"),new HasParentFilter(
				new AndFilter(new TagNameFilter("span"),new HasAttributeFilter("class","xst")))));
	}
//		filterMap.put(1,new AndFilter(new TagNameFilter("a"),new HasParentFilter(new TagNameFilter("dt"))));
//		filterMap.put(2,new AndFilter(new TagNameFilter("label"),new HasAttributeFilter("id","ctl00_AndroidMaster_Content_Apk_Download")));
//		filterMap.put(3,new AndFilter(new TagNameFilter("a"),new HasAttributeFilter("class","d4")));
//		filterMap.put(4,new AndFilter(new TagNameFilter("label"),new HasAttributeFilter("id","ctl00_AndroidMaster_Content_Apk_SoftPublishTime")));
//		filterMap.put(5,new AndFilter(new TagNameFilter("li"),new HasAttributeFilter("name","li_ApkOtherVersion")));
//		filterMap.put(6,new AndFilter(new TagNameFilter("dd"),new HasAttributeFilter("class","color9")));
//		filterMap.put(7,new AndFilter(new TagNameFilter("label"),new HasAttributeFilter("id","Apk_SoftSize")));
//		filterMap.put(8,new AndFilter(new TagNameFilter("label"),new HasAttributeFilter("id","Apk_Description")));
//		filterMap.put(9,new AndFilter(new TagNameFilter("img"),new HasParentFilter(new AndFilter(new TagNameFilter("a"), 
//				new HasParentFilter(new AndFilter(new TagNameFilter("div"), new HasAttributeFilter("class","soft_img")))))));
//		filterMap.put(10,new AndFilter(new TagNameFilter("label"),new HasAttributeFilter("id","Apk_SoftLanguage")));
//		filterMap.put(11,new AndFilter(new TagNameFilter("a"),
//				new HasParentFilter(new AndFilter(new TagNameFilter("span"),new HasAttributeFilter("class","name l")))));
//		filterMap.put(12,new AndFilter(new TagNameFilter("span"),
//				new HasParentFilter(new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","titleline")))));
//		filterMap.put(13,new AndFilter(new TagNameFilter("img"),
//				new HasParentFilter(new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","d_img l")))));
//		filterMap.put(14,new AndFilter(new TagNameFilter("h1"),
//				new HasParentFilter(new AndFilter(new TagNameFilter("div"),new HasAttributeFilter("class","titleline")))));
//		filterMap.put(15, new AndFilter(new TagNameFilter("p"),new HasParentFilter(new AndFilter(new TagNameFilter("div"), 
//				new HasAttributeFilter("class","des")))));
//		filterMap.put(16, new AndFilter(new TagNameFilter("img"),new HasParentFilter(new AndFilter(new TagNameFilter("li"),
//				new HasParentFilter(new AndFilter(new TagNameFilter("ul"),new HasParentFilter(new AndFilter(new TagNameFilter("div"),
//					new HasAttributeFilter("class","imgoutbox")))))))));
//		filterMap.put(17,new AndFilter(new TagNameFilter("span"),new HasAttributeFilter("class","num")));
}