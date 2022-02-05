package controller.commands;

import converters.fromlatex.FromLatexConverter;
import converters.fromlatex.FromLatexConverterFactory;
import model.Document;
import model.VersionsManager;
import view.LatexEditorView;

public class SaveCommand implements Command {
	
	private LatexEditorView latexEditorView;
	private String filename;
	private Document document;
	
	public SaveCommand() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		latexEditorView = versionsManager.getLatexEditorView();
	}
	
	@Override
	public void execute() {
		initialize();
		convertContentsIfNeeded();
		saveToFile(filename, document);
	}
	
	private void initialize() {
		filename = latexEditorView.getController().getFilename();
		document = latexEditorView.getCurrentDocument().clone();
	}
	
	private void saveToFile(String filename, Document document) {
		document.save(filename);
	}
	
	private void convertContentsIfNeeded(){
		String extension = getExtension();
		
		if (!extension.equals("tex")) {
			FromLatexConverterFactory factory = new FromLatexConverterFactory();
			FromLatexConverter converter = factory.getConverter(extension);
			document.setContents(converter.convert(document.getContents()));
		}
	}
	
	private String getExtension() {
		return filename.substring(filename.indexOf(".") + 1);
	}
}