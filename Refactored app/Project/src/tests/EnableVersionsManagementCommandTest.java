package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import javax.swing.JEditorPane;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import controller.LatexEditorController;
import model.VersionsManager;
import model.strategies.StableVersionsStrategy;
import model.strategies.VolatileVersionsStrategy;
import view.LatexEditorView;

public class EnableVersionsManagementCommandTest {

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
	@DisplayName("Enable volatile strategy")
	public void enableVolatileStrategy() {
		enableStrategy("volatileStrategy");
		assertTrue(versionsManager.isEnabled());
		assertEquals("volatileStrategy", versionsManager.getStrategyType());
		assertTrue(versionsManager.getStrategy() instanceof VolatileVersionsStrategy);
	}
	
	@Test
	@DisplayName("Enable stable strategy")
	public void enableStableStrategy() {
		enableStrategy("stableStrategy");
		assertTrue(versionsManager.isEnabled());
		assertEquals("stableStrategy", versionsManager.getStrategyType());
		assertTrue(versionsManager.getStrategy() instanceof StableVersionsStrategy);
	}
	
	private void enableStrategy(String strategy) {
		versionsManager.setStrategyType(strategy);
		latexEditorView.getController().enact("enableVersionsManagement");
	}
}
