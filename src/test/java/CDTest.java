import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CDTest extends AccountsTest {
	@Test
	public void starting_balance_is_init_balance() {
		double actual = testCD.getBalance();
		assertEquals(1000.00, actual);
	}

	@Test
	public void account_created_with_specified_apr_value() {
		double actual = testCD.getAprValue();
		assertEquals(9.8, actual);
	}

	@Test
	public void deposit_amount_into_account() {
		testCD.deposit(20.00);
		double actual = testCD.getBalance();
		assertEquals(1020.00, actual);
	}

	@Test
	public void deposit_money_into_account_twice() {
		testCD.deposit(20.00);
		testCD.deposit(20.00);
		double actual = testCD.getBalance();
		assertEquals(1040.00, actual);
	}

	@Test
	public void withdraw_amount_from_account() {
		testCD.withdraw(20.00);
		double actual = testCD.getBalance();
		assertEquals(980.00, actual);
	}

	@Test
	public void withdraw_amount_from_account_twice() {
		testCD.withdraw(10.00);
		testCD.withdraw(5.00);
		double actual = testCD.getBalance();
		assertEquals(985.00, actual);
	}

	@Test
	public void withdraw_amount_from_account_with_insufficient_funds() {
		testCD.withdraw(1020.00);
		double actual = testCD.getBalance();
		assertEquals(1000.00, actual);
	}
}
