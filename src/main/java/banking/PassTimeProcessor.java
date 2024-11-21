package banking;

public class PassTimeProcessor extends CommandProcessor {
	private String command;

	public PassTimeProcessor(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public void passTime(String command) {
		// iterate through the all the accounts in bank
		for (Accounts account : bank.getAccounts().values()) {

		}

	}
}
