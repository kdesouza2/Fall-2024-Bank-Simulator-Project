package banking;

public class CreateProcessor extends CommandProcessor {
	private String command;

	public CreateProcessor(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public void createAccount(String command) {
		String[] parts = super.parseCommand(command);
		String accountType = parts[1].toLowerCase();
		int id = Integer.parseInt(parts[2]);
		double aprValue = Double.parseDouble(parts[3]);
		double balance = 0;

		if (accountType.equals("cd")) {
			balance = Double.parseDouble(parts[4]);
		}

		if (accountType.equals("checking")) {
			Checking newChecking = new Checking(id, aprValue);
			bank.addAccount(newChecking);
			newChecking.addToTransactionHistory(command);
		} else if (accountType.equals("savings")) {
			Savings newSavings = new Savings(id, aprValue);
			bank.addAccount(newSavings);
			newSavings.addToTransactionHistory(command);
		} else {
			CD newCD = new CD(id, aprValue, balance);
			bank.addAccount(newCD);
			newCD.addToTransactionHistory(command);
		}
	}
}
