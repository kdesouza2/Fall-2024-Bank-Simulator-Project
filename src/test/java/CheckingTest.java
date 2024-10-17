import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CheckingTest extends AccountsTest {

	@Test
	public void initial_balance_is_zero() {
		double actual = testChecking.getBalance();
		assertEquals(0, actual);
	}

	@Test
	public void account_created_with_specified_apr_value() {
		double actual = testChecking.getAprValue();
		assertEquals(9.8, actual);
	}

	@Test
	public void deposit_amount_into_account() {
		testChecking.deposit(20.00);
		double actual = testChecking.getBalance();
		assertEquals(20.00, actual);
	}

	@Test
	public void deposit_money_into_account_twice() {
		testChecking.deposit(20.00);
		testChecking.deposit(20.00);
		double actual = testChecking.getBalance();
		assertEquals(40.00, actual);
	}

	@Test
	public void withdraw_amount_from_account() {
		testChecking.deposit(21.00);
		testChecking.withdraw(20.00);
		double actual = testChecking.getBalance();
		assertEquals(1.00, actual);
	}

	@Test
	public void withdraw_amount_from_account_twice() {
		testChecking.deposit(20.00);
		testChecking.withdraw(10.00);
		testChecking.withdraw(5.00);
		double actual = testChecking.getBalance();
		assertEquals(5.00, actual);
	}

	@Test
	public void withdraw_amount_from_account_with_insufficient_funds() {
		testChecking.withdraw(20.00);
		double actual = testChecking.getBalance();
		assertEquals(0.00, actual);
	}
}
