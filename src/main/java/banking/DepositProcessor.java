package banking;

public class DepositProcessor extends CommandProcessor {
	private String command;

	public DepositProcessor(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public void depositIntoAccount(String command) {
		String[] parts = super.parseCommand(command);
		int id = Integer.parseInt(parts[1]);
		double amount = Double.parseDouble(parts[2]);

		Accounts target = bank.retrieve(id);
		target.deposit(amount);
		target.addToTransactionHistory(command);
	}
}
