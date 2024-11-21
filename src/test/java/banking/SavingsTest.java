package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class SavingsTest extends AccountsTest {

	@Test
	public void initial_balance_is_zero() {
		double actual = testSavings.getBalance();

		assertEquals(0, actual);
	}

	@Test
	public void account_created_with_specified_apr_value() {
		double actual = testSavings.getAprValue();
		assertEquals(9.8, actual);
	}

	@Test
	public void deposit_amount_into_account() {
		testSavings.deposit(20.00);
		double actual = testSavings.getBalance();
		assertEquals(20.00, actual);
	}

	@Test
	public void deposit_money_into_account_twice() {
		testSavings.deposit(20.00);
		testSavings.deposit(20.00);
		double actual = testSavings.getBalance();
		assertEquals(40.00, actual);
	}

	@Test
	public void withdraw_amount_from_account() {
		testSavings.deposit(21.00);
		testSavings.withdraw(20.00);
		double actual = testSavings.getBalance();
		assertEquals(1.00, actual);
	}

	@Test
	public void withdraw_amount_from_account_twice() {
		testSavings.deposit(20.00);
		testSavings.withdraw(10.00);
		testSavings.withdraw(5.00);
		double actual = testSavings.getBalance();
		assertEquals(5.00, actual);
	}

	@Test
	public void withdraw_amount_from_account_with_insufficient_funds() {
		testSavings.withdraw(20.00);
		double actual = testSavings.getBalance();
		assertEquals(0.00, actual);
	}

	@Test
	public void new_savings_account_has_empty_transaction_history() {
		assertEquals(testSavings.getTransactionHistory().size(), 0);
	}

	@Test
	public void new_savings_account_has_time_0() {
		assertEquals(testSavings.getTime(), 0);
	}
}
