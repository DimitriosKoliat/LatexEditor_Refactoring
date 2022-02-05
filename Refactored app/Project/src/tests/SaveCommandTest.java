package test;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JEditorPane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.DocumentManager;
import model.VersionsManager;
import view.LatexEditorView;

public class SaveCommandTest {

	private VersionsManager versionsManager;
	private LatexEditorView latexEditorView;
	private JEditorPane jEditorPane;
	
	@BeforeEach
	private void setup() {
		latexEditorView = new LatexEditorView();
		versionsManager = VersionsManager.getInstance();
		versionsManager.setLatexEditorView(latexEditorView);
		LatexEditorController controller = new LatexEditorController();
		latexEditorView.setController(controller);
		jEditorPane = new JEditorPane();
		latexEditorView.setEditorPane(jEditorPane);
	}
	
	@Test
	@DisplayName("Save file as .tex")
	public void saveFile() {
		String filepath = "saveTest1.tex";
		String type = "letterTemplate";
		
		createDocument(type);
		saveDocument(filepath);
		
		String originalContents = new DocumentManager().getContents(type);
		String loadedFileContents = loadFile(filepath);
		assertEquals(originalContents, loadedFileContents);
	}
	
	
	
	@Test
	@DisplayName("Save file as .html")
	public void saveHtmlFile() {
		String filepath = "saveTest2.html";
		String type = "emptyTemplate";
		
		createDocument(type);
		addLatexContents();
		saveDocument(filepath);
		
		String latexAsHtml = getLatexAsHtml();
		String loadedFileContents = loadFile(filepath).trim();
		assertEquals(latexAsHtml, loadedFileContents);
	}
	
	private void createDocument(String type) {
		latexEditorView.getController().setType(type);
		latexEditorView.getController().enact("create");
		latexEditorView.getController().setText(
				new DocumentManager().createDocument(type).getContents());
		latexEditorView.getController().enact("edit");
	}
	
	private void saveDocument(String filepath) {
		latexEditorView.getController().setFilename(filepath);
		latexEditorView.getController().enact("save");
	}
	private void addLatexContents() {
		enactAddLatexCommandWithSpecificContent("chapter");
		enactAddLatexCommandWithSpecificContent("section");
		enactAddLatexCommandWithSpecificContent("subsection");
		enactAddLatexCommandWithSpecificContent("subsubsection");
		enactAddLatexCommandWithSpecificContent("itemize");
		enactAddLatexCommandWithSpecificContent("enumerate");
		enactAddLatexCommandWithSpecificContent("table");
		enactAddLatexCommandWithSpecificContent("figure");
		latexEditorView.getController().enact("edit");
	}
	
	private void enactAddLatexCommandWithSpecificContent(String type) {
		latexEditorView.getController().setContentType(type);
		latexEditorView.getController().enact("addLatex");
		jEditorPane.setCaretPosition(jEditorPane.getText().length());
	}
	
	private String loadFile(String filepath) {
		String fileContents = "";
		try {
			Scanner scanner = new Scanner(new FileInputStream(filepath));
			while(scanner.hasNextLine())
				fileContents = fileContents + scanner.nextLine() + "\n";
			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fileContents;
	}
	
	private String getLatexAsHtml() {
		return "<!DOCTYPE html>\n"
				+ "<html>\n"
				+ "<body>\n"
				+ "	<h1>...</h1>\n"
				+ "	<h2>...</h2>\n"
				+ "	<h3>...</h3>\n"
				+ "	<h4>...</h4>\n"
				+ "	<ul>\n"
				+ "		<li>...</li>\n"
				+ "		<li>...</li>\n"
				+ "	</ul>\n"
				+ "	<ol>\n"
				+ "		<li>...</li>\n"
				+ "		<li>...</li>\n"
				+ "	</ol>\n"
				+ "	<table>\n"
				+ "	<caption>....</caption><p>...</p>\n"
				+ "	<tr>\n"
				+ "		<td>... </td>\n"
				+ "		<td>...</td>\n"
				+ "		<td>...</td>\n"
				+ "	</tr>\n"
				+ "	<tr>\n"
				+ "		<td>... </td>\n"
				+ "		<td>...</td>\n"
				+ "		<td>...</td>\n"
				+ "	</tr>\n"
				+ "	<tr>\n"
				+ "		<td>... </td>\n"
				+ "		<td>...</td>\n"
				+ "		<td>...</td>\n"
				+ "	</tr>\n"
				+ "	</table>\n"
				+ "	<figure>\n"
				+ "	<img width='...' height='...' src='...'>\n"
				+ "	<figcaption>....</figcaption><p>...</p>\n"
				+ "	</figure>\n"
				+ "</body>\n"
				+ "</html>";
	}
}