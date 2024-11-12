package banking;

public class Accounts {
	private final int id;
	private final double aprValue;
	double balance;
	String accountType;

	public Accounts(int id, double aprValue) {
		this.id = id;
		this.aprValue = aprValue;
	}

	public int getId() {
		return id;
	}

	public double getBalance() {
		return balance;
	}

	public String getAccountType() {
		return accountType;
	}

	public double getAprValue() {
		return aprValue;
	}

	public void deposit(double amount) {
		balance += amount;
	}

	public void withdraw(double amount) {
		if (balance >= amount) {
			balance -= amount;
		}
	}

}
