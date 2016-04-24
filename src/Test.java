
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {

	@org.junit.Test
	public void test() {
		Document doc;
		String url = "https://www.youtube.com/watch?v=B2z5C_Mesx0";
		Map<String, List<String>> result = new HashMap<String,List<String>>();
		
		try {
			doc = Jsoup.connect(url).get();
			Elements ogElements = doc.select("meta");
			for (Element e : ogElements) {
				String target = null;
	            if (e.hasAttr("property")) target = "property";
	            else if (e.hasAttr("name"))target = "name";
				
				if(!result.containsKey(target)){
					result.put(target, new ArrayList<String>());
				}
				result.get(target).add(e.attr("content"));
			}
			
			for(String s : result.keySet())
			System.out.println(s + " : " + result.get(s));
			
		} catch (Exception e){
			e.printStackTrace();
		}

	}
	
	
	
	
	
	
	@org.junit.Test
	public void test2() {
		Document doc;
		String url = "http://zzong.net";
		Map<String, List<String>> result = new HashMap<String,List<String>>();
		String[] REQUIRED_META = new String[]{"og:title", "og:type", "og:image", "og:url" };
		
		try {
			doc = Jsoup.connect(url).get();
			Elements ogElements = doc.select("meta");
			for (Element e : ogElements) {
				String target = null;
	            if (e.hasAttr("property")) target = "property";
	            else if (e.hasAttr("name"))target = "name";

				if(!result.containsKey(target)){
					result.put(target, new ArrayList<String>());
				}
				result.get(target).add(e.attr("content"));
			}
			
			for(String s : REQUIRED_META){
				if (!(result.containsKey(s) && result.get(s).size() > 0)){
					if(s.equals(REQUIRED_META[0])){
						result.put(REQUIRED_META[0]
								, Arrays.asList(new String[]{doc.select("title").eq(0).text()}));
					} else if (s.equals(REQUIRED_META[1])){
						result.put(REQUIRED_META[1]
								, Arrays.asList(new String[]{"website"}));
					} else if (s.equals(REQUIRED_META[2])){
						result.put(REQUIRED_META[2]
								, Arrays.asList(new String[]{doc.select("img").eq(0).attr("abs:src")}));
					} else if (s.equals(REQUIRED_META[3])){
						result.put(REQUIRED_META[3]
								, Arrays.asList(new String[]{doc.baseUri()}));
					}
				}

			}
			
			for(String s : result.keySet())
			System.out.println(s + " : " + result.get(s));
			
	} catch (Exception e){
		e.printStackTrace();
	}

	}

}
