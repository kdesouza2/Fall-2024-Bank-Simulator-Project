package banking;

import java.util.ArrayList;
import java.util.List;

public class Accounts {
	private final int id;
	private final double aprValue;
	protected List<String> transactionHistory;
	protected double balance;
	protected String accountType;
	protected int time;

	public Accounts(int id, double aprValue) {
		this.id = id;
		this.aprValue = aprValue;
		this.transactionHistory = new ArrayList<>();
		this.time = 0; // time in months from opening account
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

	public List<String> getTransactionHistory() {
		return transactionHistory;
	}

	public int getTime() {
		return time;
	}

	public void deposit(double amount) {
		balance += amount;
		balance = Math.floor(balance * 100.0) / 100.0; // Round to 2 decimal places
	}

	public void withdraw(double amount) {
		if (amount > balance) {
			balance = 0;
		} else {
			balance -= amount;
		}
	}

	public void addToTransactionHistory(String command) {
		transactionHistory.add(command);
	}

	public void addTime(int timePassed) {
		time += timePassed;
	}
}
