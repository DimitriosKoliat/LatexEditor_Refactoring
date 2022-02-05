package controller.commands;

import model.Document;
import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

public class CreateCommand implements Command {
	private DocumentManager documentManager;
	
	public CreateCommand(DocumentManager documentManager) {
		super();
		this.documentManager = documentManager;
	}

	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		LatexEditorView latexEditorView = versionsManager.getLatexEditorView();
		String type = latexEditorView.getController().getType();
		Document document = documentManager.createDocument(type);
		latexEditorView.setCurrentDocument(document);
	}
}