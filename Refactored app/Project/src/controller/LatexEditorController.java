package controller;

import java.util.HashMap;

import controller.commands.Command;
import controller.commands.CommandFactory;

public class LatexEditorController{
	private HashMap<String, Command> commands;
	private CommandFactory commandFactory;
	private String contentType;
	private String type;
	private String text;
	private String filename;
	
	public LatexEditorController() {
		commandFactory = new CommandFactory();
		commands = new HashMap<String, Command>(); 
		createAllCommands();
	}
	private void createAllCommands() {
		populateCommandsHashMap(initCommandsNames());
	}
	private String [] initCommandsNames() {
		String []  commandsNames = {"addLatex",
					"changeVersionsStrategy",
					"create",
					"disableVersionsManagement",
					"edit",
					"enableVersionsManagement",
					"load",
					"rollbackToPreviousVersion",
					"save"
					};
		return commandsNames;
	}
	private void populateCommandsHashMap(String [] commandsNames){
		for(int i=0; i<commandsNames.length; i++) {
			putCommandInHashMap(commandsNames[i]);
		}
	}
	private void putCommandInHashMap(String commandName) {
		commands.put(commandName,commandFactory.createCommand(commandName));
	}
	
	public void enact(String command) {
		commands.get(command).execute();
	}
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getContentType() {
		return contentType;
	}
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
}
