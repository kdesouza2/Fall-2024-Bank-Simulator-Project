package banking;

public class WithdrawalValidator extends CommandValidator {
	private String command;

	public WithdrawalValidator(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public boolean validateWithdrawalFromAccount(String command) {
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
				if (amount >= 0 && amount <= 400) {
					return true;
				}
			} else if (newAccount.getAccountType().equals("Savings")) {
				// check transaction history -- if account already withdrew in the month
				// invalid command
				if (newAccount.isWithdrawable()) {
					if (amount >= 0 && amount <= 1000) {
						return true;
					}
				}
			} else if (newAccount.getAccountType().equals("Cd")) {
				// check if the account was created less than a year ago --- if so invalid
				// command
				// once the year has passed only full withdrawal is allowed -- if amount is less
				// than balance invalid command
				if (newAccount.getTime() >= 12) {
					if (amount >= newAccount.getBalance()) {
						return true;
					}
				}
			}
		}

		return false;
	}

}
