public class CommandValidator {
	private Bank bank;

	public CommandValidator(Bank newBank) {
		this.bank = newBank;
	}

	public String[] parseCommand(String command) {
		return command.split(" ");
	}

	public boolean emptyCommand(String command) {
		String[] parts = parseCommand(command);

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

	public boolean validAccountID(Accounts testAccount) {
		if (bank.accountExistsByAccountID(testAccount)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validateCreate(String command) {
		String[] parts = parseCommand(command);

		if ((emptyCommand(command)) || !(validAccountType(parts[1])) || !(validCommandType(parts[0]))) {
			return false;
		} else {
			String accountType = parts[1].toLowerCase();

			if (accountType.equals("checking")) {
				return CreateValidator.validateCreateChecking(command);
			} else if (accountType.equals("savings")) {
				return CreateValidator.validateCreateSavings(command);
			} else if (accountType.equals("cd")) {
				return CreateValidator.validateCreateCD(command);
			}
		}
	}

}
