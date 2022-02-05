package controller.commands;

import java.util.HashMap;

import model.DocumentManager;

public class CommandFactory {
	private DocumentManager documentManager;
	private HashMap<String, Command> commands;
	
	public CommandFactory() {
		super();
		documentManager = new DocumentManager();
		commands = new HashMap<String, Command>();
		createAllContents();
	}
	
	private void createAllContents() {
		commands.put("addLatex", new AddLatexCommand());
		commands.put("changeVersionsStrategy", new ChangeVersionsStrategyCommand());
		commands.put("create", new CreateCommand(documentManager));
		commands.put("disableVersionsManagement", new DisableVersionsManagementCommand());
		commands.put("edit", new EditCommand());
		commands.put("enableVersionsManagement", new EnableVersionsManagementCommand());
		commands.put("load", new LoadCommand());
		commands.put("rollbackToPreviousVersion", new RollbackToPreviousVersionCommand());
		commands.put("save", new SaveCommand());
	}

	public Command createCommand(String type) {
		for (String currentComand: commands.keySet()) {
			if(currentComand==type){
				return commands.get(currentComand);
			}
		}
		return null;
	}
}
