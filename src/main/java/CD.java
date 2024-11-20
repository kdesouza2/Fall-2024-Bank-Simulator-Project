public class CD extends Accounts {

	public CD(int id, double aprValue, double balance) {
		super(id, aprValue);
		this.balance = balance;
		accountType = "CD";
	}

}
