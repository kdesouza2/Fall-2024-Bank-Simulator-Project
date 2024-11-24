package banking;

public class WithdrawalProcessor extends CommandProcessor {
	private String command;

	public WithdrawalProcessor(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public void withdrawFromAccount(String command) {
		String[] parts = super.parseCommand(command);
		int id = Integer.parseInt(parts[1]);
		double amount = Double.parseDouble(parts[2]);

		Accounts target = bank.retrieve(id);
		target.withdraw(amount);
		target.addToTransactionHistory(command);
		target.setWithdrawableFalse();
	}
}
