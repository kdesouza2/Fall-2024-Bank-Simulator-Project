public class DepositValidator extends CommandValidator {
	public DepositValidator(Bank newBank) {
		super(newBank);
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
