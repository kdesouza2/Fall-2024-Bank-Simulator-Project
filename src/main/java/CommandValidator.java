public class CommandValidator {
	private Bank bank;

	public CommandValidator(Bank newBank) {
		this.bank = newBank;
	}

	public String[] parseCommand(String command) {
        return command.split(" ");
	}

	public boolean emptyCommand(String command) {
		String[] parts = parseCommand(command);

        return parts.length == 0;
    }

	public boolean 

}
