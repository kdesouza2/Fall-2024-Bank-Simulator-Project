package banking;

import java.util.ArrayList;
import java.util.List;

public class CommandStorage {
	public List<String> listOfInvalidCommands;
	public List<String> outputCommands;

	public CommandStorage() {
		listOfInvalidCommands = new ArrayList<>();
		outputCommands = new ArrayList<>();
	}

	public List<String> getInvalidCommands() {
		return listOfInvalidCommands;
	}

	public List<String> getOutputCommands() {
		return outputCommands;
	}

	public void storeInvalidCommand(String command) {
		listOfInvalidCommands.add(command);
	}

	public void storeOutputCommand(String command) {
		outputCommands.add(command);
	}

}
