package controller.commands;

import model.Document;
import model.VersionsManager;
import view.LatexEditorView;

public class EditCommand implements Command {
	
	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView;
	
	public EditCommand() {
		versionsManager = VersionsManager.getInstance();
		latexEditorView = versionsManager.getLatexEditorView();
	}
	
	@Override
	public void execute() {
		saveContents();
	}
	
	private void saveContents() {
		Document document = latexEditorView.getCurrentDocument();

		document.setContents(latexEditorView.getController().getText());
		changeDocumentVersion(document);
	}
	
	private void changeDocumentVersion(Document document) {
		if(versionsManager.isEnabled()) {
			document.changeVersion();
			versionsManager.putVersion(document);
		}
	}
}