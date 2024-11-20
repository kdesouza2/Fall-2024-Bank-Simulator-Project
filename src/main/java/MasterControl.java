import java.util.List;

public class MasterControl {
	private CommandValidator commandValidator;
	private CommandProcessor commandProcessor;
	private CommandStorage commandStorage;

	public MasterControl(CommandValidator commandValidator, CommandProcessor commandProcessor,
			CommandStorage commandStorage) {
		this.commandProcessor = commandProcessor;
		this.commandValidator = commandValidator;
		this.commandStorage = commandStorage;
	}

	public List<String> start(List<String> input) {
		for (String command : input) {
			if (commandValidator.validateCommand(command)) {
				commandProcessor.processCommand(command);
			} else {
				commandStorage.addInvalidCommand(command);
			}
		}

		return commandStorage.getInvalidCommands();
	}
}
