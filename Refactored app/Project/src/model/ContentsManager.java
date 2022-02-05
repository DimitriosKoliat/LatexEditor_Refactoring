package model;

import java.util.HashMap;

import javax.swing.JEditorPane;

import view.LatexEditorView;

public class ContentsManager {
	private JEditorPane editorPane;
	private LatexEditorView latexEditorView;
	
	public ContentsManager() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		latexEditorView = versionsManager.getLatexEditorView();
		editorPane = latexEditorView.getEditorPane();
	}
	
	public HashMap<String, String> getAllContents() {
		HashMap<String, String> contents = new HashMap<String, String>();
		contents.put("chapter", "\n\\chapter{...}"+"\n");
		contents.put("section", "\n\\section{...}"+"\n");
		contents.put("subsection", "\n\\subsection{...}"+"\n");
		contents.put("subsubsection", "\n\\subsubsection{...}"+"\n");
		contents.put("enumerate","\\begin{enumerate}\n"+
				"\\item ...\n"+
				"\\item ...\n"+
				"\\end{enumerate}\n");
		contents.put("itemize","\\begin{itemize}\n"+
				"\\item ...\n"+
				"\\item ...\n"+
				"\\end{itemize}\n");
		contents.put("table", "\\begin{table}\n"+
				"\\caption{....}\\label{...}\n"+
				"\\begin{tabular}{|c|c|c|}\n"+
				"\\hline\n"+
				"... &...&...\\\\\n"+
				"... &...&...\\\\\n"+
				"... &...&...\\\\\n"+
				"\\hline\n"+
				"\\end{tabular}\n"+
				"\\end{table}\n");
		contents.put("figure","\\begin{figure}\n"+
				"\\includegraphics[width=...,height=...]{...}\n"+
				"\\caption{....}\\label{...}\n"+
				"\\end{figure}\n");
		return contents;
	}
	
	public void addContent(String type) {
		setTexts(addContentBetweenPreviousContent(type));
	}
	
	private String addContentBetweenPreviousContent(String type) {
		String contents = editorPane.getText();
		String before = contents.substring(0, editorPane.getCaretPosition());
		String after = contents.substring(editorPane.getCaretPosition());
		return before + getAllContents().get(type) + after;
	}
	
	private void setTexts(String contents) {
		latexEditorView.getController().setText(contents);
		editorPane.setText(contents);
	}
}
