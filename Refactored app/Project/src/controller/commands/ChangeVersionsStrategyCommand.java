package controller.commands;

import model.VersionsManager;

public class ChangeVersionsStrategyCommand implements Command {

	@Override
	public void execute() {
		VersionsManager versionsManager = VersionsManager.getInstance();
		versionsManager.changeStrategy();
	}
}