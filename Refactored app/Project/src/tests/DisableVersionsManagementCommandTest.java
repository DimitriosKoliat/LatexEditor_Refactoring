package test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JEditorPane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.VersionsManager;
import view.LatexEditorView;

public class DisableVersionsManagementCommandTest {

	private LatexEditorView latexEditorView;
	private VersionsManager versionsManager;
	
	@BeforeEach
	private void setup() { 
		latexEditorView = new LatexEditorView();
		versionsManager = VersionsManager.getInstance();
		versionsManager.setLatexEditorView(latexEditorView);
		LatexEditorController controller = new LatexEditorController();
		latexEditorView.setController(controller);
		latexEditorView.setEditorPane(new JEditorPane());
	}
	
	@Test
	@DisplayName("Disable from volatile")
	public void disableVolatile() {
		enableStrategy("volatileStrategy");
		disableStrategy();
		assertTrue(!versionsManager.isEnabled());
	}
	
	@Test
	@DisplayName("Disable from stable")
	public void disableStable() {
		enableStrategy("stableStrategy");
		disableStrategy();
		assertTrue(!versionsManager.isEnabled());
	}
	
	private void enableStrategy(String strategy) {
		
	}
	
	private void disableStrategy() {
		latexEditorView.getController().enact("disableVersionsManagement");
	}
}
