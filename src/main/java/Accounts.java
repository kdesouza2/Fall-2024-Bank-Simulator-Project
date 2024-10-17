public abstract class Accounts {
	private final int id;
	private final double aprValue;
	double balance;

	public Accounts(int id, double aprValue) {
		this.id = id;
		this.aprValue = aprValue;
	}

	public double getBalance() {
		return balance;
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

	public int getId() {
		return id;
	}

}
