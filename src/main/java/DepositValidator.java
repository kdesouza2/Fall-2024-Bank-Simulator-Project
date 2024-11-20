
public class DepositValidator extends CommandValidator {
	String command;

	public DepositValidator(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public boolean validateDepositIntoAccount(String command) {
		String[] parts = super.parseCommand(command);
		double amount = Double.parseDouble(parts[2]);
		int idInt = 0;

		try {
			idInt = Integer.parseInt(parts[1]);
		} catch (NumberFormatException e) { // if id contains non integers --> id = "keyra"
			return false;
		}

		if (bank.accountExistsByAccountID(idInt)) {
			Accounts newAccount = bank.retrieve(idInt);

			if (newAccount.getAccountType().equals("Checking")) {
				if (amount >= 0 && amount <= 1000) {
					return true;
				}
			} else if (newAccount.getAccountType().equals("Savings")) {
				if (amount >= 0 && amount <= 2500) {
					return true;
				}
			} else if (newAccount.getAccountType().equals("CD")) {
				return false;
			}
		}

		return false;
	}

}
