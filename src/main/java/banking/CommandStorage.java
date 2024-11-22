package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {
	public List<String> listOfInvalidCommands;

	public CommandStorage() {
		listOfInvalidCommands = new ArrayList<>();
	}

	public List<String> getInvalidCommands() {
		return listOfInvalidCommands;
	}

	public void storeInvalidCommand(String command) {
		listOfInvalidCommands.add(command);
	}

}
