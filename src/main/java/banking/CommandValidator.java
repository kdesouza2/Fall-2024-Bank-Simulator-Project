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

		return (commandType.equals("create")) || (commandType.equals("deposit"));
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
			}
		}

		return false;
	}
//	public boolean validateCreate(String command) {
//		String[] parts = parseCommand(command);
//
//		if ((emptyCommand(parts)) || !(validAccountType(parts[1])) || !(validCommandType(parts[0]))) {
//			return false;
//		} else {
//			String accountType = parts[1].toLowerCase();
//
//			CreateValidator newCreateValidator = new CreateValidator(bank, command);
//			if ((accountType.equals("checking")) || (accountType.equals("savings"))) {
//				if (parts.length == 4) {
//					return newCreateValidator.validateCreateCheckingOrSavings(parts[2], parts[3]);
//				}
//			} else if (accountType.equals("cd")) {
//				if (parts.length == 5) {
//					return newCreateValidator.validateCreateCD(parts[2], parts[3], parts[4]);
//				}
//			}
//		}
//
//		return false;
//	}
//
//	public boolean validateDeposit(String command) {
//		String[] parts = parseCommand(command);
//
//		if ((emptyCommand(parts)) || !(validCommandType(parts[0]))) {
//			return false;
//
//		} else {
//			DepositValidator newDepositValidator = new DepositValidator(bank, command);
//			if (parts.length == 3) {
//				return newDepositValidator.validateDepositIntoAccount(command);
//
//			}
//		}
//
//		return false;
//	}

}
