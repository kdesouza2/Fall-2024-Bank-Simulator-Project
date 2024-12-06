package banking;

public class TransferProcessor extends CommandProcessor {
	private String command;

	public TransferProcessor(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public void transferMoney(String command) {
		String[] parts = super.parseCommand(command);
		int srcId = Integer.parseInt(parts[1]);
		int destId = Integer.parseInt(parts[2]);
		double amount = Double.parseDouble(parts[3]);

		Accounts srcAcc = bank.retrieve(srcId);
		Accounts destAcc = bank.retrieve(destId);

		if (amount >= srcAcc.getBalance()) {
			destAcc.deposit(srcAcc.getBalance());
		} else {
			destAcc.deposit(amount);
		}

		srcAcc.withdraw(amount);

		if (srcAcc.getAccountType().equals("Savings")) {
			srcAcc.setWithdrawableFalse();
		}

		srcAcc.addToTransactionHistory(command);
		destAcc.addToTransactionHistory(command);
	}
}
