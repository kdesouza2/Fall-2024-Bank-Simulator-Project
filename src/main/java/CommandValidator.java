public class CommandValidator {
	Bank bank;
	String command;

	public CommandValidator(Bank newBank, String command) {
		this.bank = newBank;
		this.command = command;
	}

	public String[] parseCommand(String command) {
		return command.split(" ");
	}

	public boolean emptyCommand(String[] parts) {
		return parts.length == 0;
	}

	public boolean validAccountType(String accountType) {
		accountType = accountType.toLowerCase();

		return (accountType.equals("checking")) || (accountType.equals("savings")) || (accountType.equals("cd"));
	}

	public boolean validCommandType(String commandType) {
		commandType = commandType.toLowerCase();

		return (commandType.equals("create")) || (commandType.equals("deposit"));
	}

	public boolean validateCreate(String command) {
		String[] parts = parseCommand(command);

		if ((emptyCommand(parts)) || !(validAccountType(parts[1])) || !(validCommandType(parts[0]))) {
			return false;
		} else {
			String accountType = parts[1].toLowerCase();

			CreateValidator newCreateValidator = new CreateValidator(bank, command);
			if ((accountType.equals("checking")) || (accountType.equals("savings"))) {
				if (parts.length == 4) {
					return newCreateValidator.validateCreateCheckingOrSavings(parts[2], parts[3]);
				}
			} else if (accountType.equals("cd")) {
				if (parts.length == 5) {
					return newCreateValidator.validateCreateCD(parts[2], parts[3], parts[4]);
				}
			}
		}

		return false;
	}

	public boolean validateDeposit(String command) {
		String[] parts = parseCommand(command);

		if ((emptyCommand(parts)) || !(validCommandType(parts[0]))) {
			return false;

		} else {
			DepositValidator newDepositValidator = new DepositValidator(bank, command);
			if (parts.length == 3) {
				return newDepositValidator.validateDepositIntoAccount(command);

			}
		}

		return false;
	}

}
