package banking;

import java.util.HashMap;
import java.util.Map;

public class Accounts {
	private final int id;
	private final double aprValue;
	protected Map<String, Integer> transactionHistory;
	protected double balance;
	protected String accountType;
	protected int time;

	public Accounts(int id, double aprValue) {
		this.id = id;
		this.aprValue = aprValue;
		this.transactionHistory = new HashMap<>();
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

	public Map<String, Integer> getTransactionHistory() {
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

	public void addToTransactionHistory(String command, int time) {
		transactionHistory.put(command, time);
	}

	public void addTime(int timePassed) {
		time += timePassed;
	}
}
