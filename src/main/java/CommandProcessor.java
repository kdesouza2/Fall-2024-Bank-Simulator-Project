public class CommandProcessor {
	Bank bank;

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
			createProcessor.createAccount(parts);
		}
	}
}
