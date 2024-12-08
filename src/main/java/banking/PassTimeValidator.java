package banking;

public class PassTimeValidator extends CommandValidator {
	private String command;

	public PassTimeValidator(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public boolean validatePassTime(String command) {
		String[] parts = super.parseCommand(command);
		int time = 0;

		try {
			time = Integer.parseInt(parts[1]);
		} catch (NumberFormatException e) { // if id contains non integers --> id = "keyra"
			return false;
		}

		if (time >= 1 && time <= 60) {
			return true;
		}

		return false;
	}
}
