package banking;

public class TransferValidator extends CommandValidator {
	private String command;

	public TransferValidator(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public boolean validateTransfer(String command) {
		String[] parts = super.parseCommand(command);
		int srcId = 0;
		int destId = 0;
		double amount = Double.parseDouble(parts[3]);

		try {
			srcId = Integer.parseInt(parts[1]);
		} catch (NumberFormatException e) { // if id contains non integers --> id = "keyra"
			return false;
		}

		try {
			destId = Integer.parseInt(parts[2]);
		} catch (NumberFormatException e) { // if id contains non integers --> id = "keyra"
			return false;
		}

		if (bank.accountExistsByAccountID(srcId)) {
			Accounts srcAccount = bank.retrieve(srcId);

			if (bank.accountExistsByAccountID(destId)) {
				Accounts destAccount = bank.retrieve(destId);
				// check if dest is a CD account
				// check if src is a CD account
				if (destAccount.getAccountType().equals("Cd") || srcAccount.getAccountType().equals("Cd")) {
					return false;
				}

				String withdrawCommand = "withdraw " + srcId + " " + amount;
				String depositCommand = "deposit " + destId + " " + amount;
				if (super.validateCommand(withdrawCommand)) {
					if (super.validateCommand(depositCommand)) {
						return true;
					}
				}
			}
		}

		return false;
	}
}
