public class CommandValidator {
	private Bank bank;

	public CommandValidator(Bank newBank) {
		this.bank = newBank;
	}

	public boolean validAccountIDForCreate(Accounts testAccount) {
		if (bank.accountExistsByAccountID(testAccount)) {
			return false;
		} else {
			return true;
		}
	}

	public boolean validCreateChecking(String command) {
		String[] parts = command.split(" ");
		String commandType = parts[0];
		String accountType = parts[1];
		String id = parts[2];
		double aprValue = Double.parseDouble(parts[3]);

		if (commandType.equalsIgnoreCase("create")) {
			if (accountType.equalsIgnoreCase("checking")) {
				if (id.length() == 8) {
					if (aprValue >= 0 && aprValue <= 10) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean validCreateSavings(String command) {
		String[] parts = command.split(" ");
		String commandType = parts[0];
		String accountType = parts[1];
		String id = parts[2];
		double aprValue = Double.parseDouble(parts[3]);

		if (commandType.equalsIgnoreCase("create")) {
			if (accountType.equalsIgnoreCase("savings")) {
				if (id.length() == 8) {
					if (aprValue >= 0 && aprValue <= 10) {
						return true;
					}
				}
			}
		}

		return false;
	}

	public boolean validCreateCD(String command) {
		String[] parts = command.split(" ");
		String commandType = parts[0];
		String accountType = parts[1];
		String id = parts[2];
		double aprValue = Double.parseDouble(parts[3]);
		double balance = Double.parseDouble(parts[4]);

		if (commandType.equalsIgnoreCase("create")) {
			if (accountType.equalsIgnoreCase("cd")) {
				if (id.length() == 8) {
					if (aprValue >= 0 && aprValue <= 10) {
						if (balance >= 1000 && balance <= 10000) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean validDepositIntoChecking(String command) {
		String[] parts = command.split(" ");
		String commandType = parts[0];
		int id = Integer.parseInt(parts[1]);
		double amount = Double.parseDouble(parts[2]);

		if (commandType.equalsIgnoreCase("deposit")) {
			if (bank.retrieve(id).getAccountType().equals("Checking")) {
				if (amount >= 0 && amount <= 1000) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean validDepositIntoSavings(String command) {
		String[] parts = command.split(" ");
		String commandType = parts[0];
		int id = Integer.parseInt(parts[1]);
		double amount = Double.parseDouble(parts[2]);

		if (commandType.equalsIgnoreCase("deposit")) {
			if (bank.retrieve(id).getAccountType().equals("Savings")) {
				if (amount >= 0 && amount <= 2500) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean validAccountTypeForDeposit(String command) {
		String[] parts = command.split(" ");
		String commandType = parts[0];
		int id = Integer.parseInt(parts[1]);
		double amount = Double.parseDouble(parts[2]);

		if (commandType.equalsIgnoreCase("deposit")) {
			if (bank.retrieve(id).getAccountType().equals("CD")) {
				return false;
			}
		}

		return true;
	}
}
