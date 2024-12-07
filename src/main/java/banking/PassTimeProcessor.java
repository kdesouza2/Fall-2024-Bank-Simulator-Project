package banking;

public class PassTimeProcessor extends CommandProcessor {
	private String command;

	public PassTimeProcessor(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public void accrueAPR(Accounts account) {
		// calculate new APR
		double num = (account.getAprValue() / 100) / 12;
		double amount = account.getBalance() * num;
		account.deposit(amount);
	}

	public void accrueAPRCD(Accounts account) {
		for (int i = 0; i < 4; i++) {
			// calculate new APR
			double num = (account.getAprValue() / 100) / 12;
			double amount = account.getBalance() * num;
			account.deposit(amount);
		}
	}

	public void passTime(String command) {
		String[] parts = super.parseCommand(command);
		int time = Integer.parseInt(parts[1]);

		for (int start = 1; start <= time; start++) {
			// iterate through the all the accounts in bank
			for (Accounts account : bank.getAccounts().values()) {
				// if account is savings, set withdrawable to true
				if (account.getAccountType().equals("Savings")) {
					account.resetWithdrawable();
				}

				// check if the accounts balance is 0 -- if so close account
				if (account.getBalance() == 0) {
					bank.closeAccount(account.getId());
					// check if the balance is below 100 and deduct 25
				} else if (account.getBalance() < 100) {
					account.withdraw(25);
					if (account.getAccountType().equals("Cd")) {
						accrueAPRCD(account);
					} else {
						accrueAPR(account);
					}
					account.addTime(1);
				} else {
					if (account.getAccountType().equals("Cd")) {
						accrueAPRCD(account);
					} else {
						accrueAPR(account);
					}
					account.addTime(1);
				}
			}
		}
	}
}
