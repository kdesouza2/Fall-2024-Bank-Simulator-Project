package banking;

public class Savings extends Accounts {

	public Savings(int id, double aprValue) {
		super(id, aprValue);
		balance = 0;
		accountType = "Savings";
		withdrawable = true;
	}

}
