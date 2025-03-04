package banking;

public class CommandProcessor {
	public Bank bank;

	public CommandProcessor(Bank bank) {
		this.bank = bank;
	}

	public String[] parseCommand(String command) {
		return command.split(" ");
	}

	public void processCommand(String command) {
		String[] parts = parseCommand(command);
		String commandType = parts[0].toLowerCase();

		if (commandType.equals("create")) {
			CreateProcessor createProcessor = new CreateProcessor(bank, command);
			createProcessor.createAccount(command);
		} else if (commandType.equals("deposit")) {
			DepositProcessor depositProcessor = new DepositProcessor(bank, command);
			depositProcessor.depositIntoAccount(command);
		} else if (commandType.equals("pass")) {
			PassTimeProcessor passTimeProcessor = new PassTimeProcessor(bank, command);
			passTimeProcessor.passTime(command);
		} else if (commandType.equals("withdraw")) {
			WithdrawalProcessor withdrawalProcessor = new WithdrawalProcessor(bank, command);
			withdrawalProcessor.withdrawFromAccount(command);
		} else if (commandType.equals("transfer")) {
			TransferProcessor transferProcessor = new TransferProcessor(bank, command);
			transferProcessor.transferMoney(command);
		}
	}
}
