package converters.tolatex;

import java.util.HashMap;

public class ToLatexConverterFactory {

	private HashMap<String, ToLatexConverter> readers;
	
	public ToLatexConverterFactory() {
		initializeHashMap();
	}
	
	private void initializeHashMap() {
		readers = new HashMap<String, ToLatexConverter>();
		
		readers.put("html", new FromHtmlConverter());
	}
	
	public ToLatexConverter getConverter(String type) {
		return readers.get(type);
	}
}
