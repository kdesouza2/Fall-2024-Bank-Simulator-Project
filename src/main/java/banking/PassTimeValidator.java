package banking;

public class PassTimeValidator extends CommandValidator {
	private String command;

	public PassTimeValidator(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public boolean validatePassTime(String command) {
		String[] parts = super.parseCommand(command);
		int time = Integer.parseInt(parts[1]);

		if (time >= 1 && time <= 60) {
			return true;
		}

		return false;
	}
}
