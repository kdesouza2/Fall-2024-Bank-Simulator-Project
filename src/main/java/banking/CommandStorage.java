package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {
	private List<String> listOfInvalidCommands;

	public CommandStorage() {
		listOfInvalidCommands = new ArrayList<>();
	}

	public List<String> getInvalidCommands() {
		return listOfInvalidCommands;
	}

	public void addInvalidCommand(String command) {
		listOfInvalidCommands.add(command);
	}

}
