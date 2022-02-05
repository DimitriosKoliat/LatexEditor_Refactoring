package controller.commands;

import model.ContentsManager;
import model.VersionsManager;
import view.LatexEditorView;

public class AddLatexCommand implements Command  {
	
	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		ContentsManager contentsManager = new ContentsManager();
		LatexEditorView latexEditorView = versionsManager.getLatexEditorView();
		
		contentsManager.addContent(latexEditorView.getController().getContentType());
		latexEditorView.getController().enact("edit");
	}
}