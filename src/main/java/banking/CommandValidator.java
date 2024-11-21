package banking;

public class CommandValidator {
	protected Bank bank;

	public CommandValidator(Bank newBank) {
		this.bank = newBank;
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

		return (commandType.equals("create")) || (commandType.equals("deposit")) || (commandType.equals("pass"))
				|| (commandType.equals("transfer")) || (commandType.equals("withdraw"));
	}

	public boolean validateCommand(String command) {
		String[] parts = parseCommand(command);

		if (emptyCommand(parts) || !(validCommandType(parts[0]))) {
			return false;
		} else {
			String commandType = parts[0].toLowerCase();
			String accountType = parts[1].toLowerCase();

			if (commandType.equals("create") && validAccountType(parts[1])) {
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
			} else if (commandType.equals("deposit")) {
				DepositValidator newDepositValidator = new DepositValidator(bank, command);
				if (parts.length == 3) {
					return newDepositValidator.validateDepositIntoAccount(command);
				}
			} else if (commandType.equals("withdrawal")) {
				WithdrawalValidator newWithdrawalValidator = new WithdrawalValidator(bank, command);
				if (parts.length == 3) {
					return newWithdrawalValidator.validateWithdrawalFromAccount(command);
				}
			} else if (commandType.equals("pass")) {
				PassTimeValidator newPassTimeValidator = new PassTimeValidator(bank, command);
				if (parts.length == 2) {
					return newPassTimeValidator.validatePassTime(command);
				}
			} else if (commandType.equals("withdraw")) {
				return true;
			} else if (commandType.equals("transfer")) {
				return true;
			}
		}

		return false;
	}
}
