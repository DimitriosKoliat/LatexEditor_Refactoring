package controller.commands;

import model.VersionsManager;

public class DisableVersionsManagementCommand implements Command {
	
	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.disable();
	}
}