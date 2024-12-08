package banking;

import java.math.RoundingMode;
import java.text.DecimalFormat;
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
		// truncate balance and aprValue
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		decimalFormat.setRoundingMode(RoundingMode.FLOOR);

		// process all commands
		for (String command : input) {
			if (commandValidator.validateCommand(command)) {
				commandProcessor.processCommand(command);
			} else {
				commandStorage.storeInvalidCommand(command);
			}
		}

		// iterate through all accounts after commands
		for (Accounts account : commandProcessor.bank.getAccounts().values()) {
			String aprValue = decimalFormat.format(account.getAprValue());
			String balance = decimalFormat.format(account.getBalance());

			// add current states to outputCommands
			String inputString = account.getAccountType() + " " + account.getId() + " " + balance + " " + aprValue;
			commandStorage.storeOutputCommand(inputString);

			// add transaction history to outputCommands
			for (String transaction : account.getTransactionHistory()) {
				commandStorage.storeOutputCommand(transaction);
			}
		}

		// add invalid commands to outputCommands
		for (String invalidCommand : commandStorage.getInvalidCommands()) {
			commandStorage.storeOutputCommand(invalidCommand);
		}

		return commandStorage.getOutputCommands();
	}
}
