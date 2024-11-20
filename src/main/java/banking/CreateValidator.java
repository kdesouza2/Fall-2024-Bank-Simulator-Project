package banking;

public class CreateValidator extends CommandValidator {
	private String command;

	public CreateValidator(Bank bank, String command) {
		super(bank);
		this.command = command;
	}

	public boolean validateCreateCheckingOrSavings(String id, String aprValue) {
		int idInt = 0;

		try {
			idInt = Integer.parseInt(id);
		} catch (NumberFormatException e) { // if id contains non integers --> id = "keyra"
			return false;
		}

		double aprValueDouble = Double.parseDouble(aprValue);
		if (!bank.accountExistsByAccountID(idInt)) {
			if (id.length() == 8) {
				if (aprValueDouble >= 0 && aprValueDouble <= 10) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean validateCreateCD(String id, String aprValue, String balance) {
		double aprValueDouble = Double.parseDouble(aprValue);
		double balanceDouble = Double.parseDouble(balance);

		int idInt;
		try {
			idInt = Integer.parseInt(id);
		} catch (NumberFormatException e) { // if id contains non integers --> id = "keyra"
			return false;
		}

		if (!bank.accountExistsByAccountID(idInt)) {
			if (id.length() == 8) {
				if (aprValueDouble >= 0 && aprValueDouble <= 10) {
					if (balanceDouble >= 1000 && balanceDouble <= 10000) {
						return true;
					}
				}
			}
		}

		return false;
	}
}
