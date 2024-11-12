public class Checking extends Accounts {

	public Checking(int id, double aprValue) {
		super(id, aprValue);
		accountType = "Checking";
		balance = 0;
	}

}
