package banking;

import java.util.List;

public class MasterControl {
	private final CommandValidator commandValidator;
	private final CommandProcessor commandProcessor;
	private final CommandStorage commandStorage;

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
				commandStorage.storeInvalidCommand(command);
			}
		}

		for (Accounts account : commandProcessor.bank.getAccounts().values()) {
			String inputString = account.getAccountType() + " " + account.getId() + " " + account.getBalance() + " "
					+ account.getAprValue();
			commandStorage.storeOutputCommand(inputString);
			for (String transaction : account.getTransactionHistory()) {
				commandStorage.storeOutputCommand(transaction);
			}
		}

		for (String invalidCommand : commandStorage.getInvalidCommands()) {
			commandStorage.storeOutputCommand(invalidCommand);
		}

		return commandStorage.getOutputCommands();
	}
}
