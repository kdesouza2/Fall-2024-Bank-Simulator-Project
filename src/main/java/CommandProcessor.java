public class CommandProcessor {
	Bank bank;
	String command;

	public CommandProcessor(Bank bank, String command) {
		this.bank = bank;
		this.command = command;
	}

	public String[] parseCommand(String command) {
		return command.split(" ");
	}

	public void processCommand(String command) {
		String[] parts = parseCommand(command);
		String commandType = parts[0].toLowerCase();

		if (commandType.equals("create")) {
			CreateProcessor createProcessor = new CreateProcessor(bank, command);
			createProcessor.createAccount(parts);
		} else if (commandType.equals("deposit")) {
			DepositProcessor depositProcessor = new DepositProcessor(bank, command);
			depositProcessor.depositIntoAccount(parts);
		}
	}
}
