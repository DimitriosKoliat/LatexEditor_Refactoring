package converters.fromlatex;

import java.util.HashMap;

public class FromLatexConverterFactory {
private HashMap<String, FromLatexConverter> converters;
	
	public FromLatexConverterFactory() {
		initializeHashMap();
	}
	
	private void initializeHashMap() {
		converters = new HashMap<String, FromLatexConverter>();
		
		converters.put("html", new ToHtmlConverter());
	}
	
	public FromLatexConverter getConverter(String type) {
		return converters.get(type);
	}
}