import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class BankTest extends AccountsTest {

	@Test
	public void bank_created_with_no_accounts() {
		assertTrue(testBank.getAccounts().isEmpty());
	}

	@Test
	public void add_one_account_to_bank() {
		testBank.addAccount(testChecking);
		int actual = testBank.getAccounts().size();

		assertEquals(1, actual);
	}

	@Test
	public void add_two_accounts_to_bank() {
		testBank.addAccount(testChecking);
		testBank.addAccount(testSavings);
		int actual = testBank.getAccounts().size();

		assertEquals(2, actual);
	}

	@Test
	public void retrieve_the_correct_account() {
		testBank.addAccount(testSavings);
		Accounts foundAccount = testBank.retrieve(testSavings.getId());

		assertEquals(testSavings.getId(), foundAccount.getId());
	}

	@Test
	public void deposit_money_by_id_through_bank() {
		testBank.addAccount(testChecking);
		testBank.retrieve(testChecking.getId()).deposit(20.00);
		double actual = testChecking.getBalance();
		assertEquals(20.00, actual);
	}

	@Test
	public void deposit_money_twice_by_id_through_bank() {
		testBank.addAccount(testChecking);
		testBank.retrieve(testChecking.getId()).deposit(20.00);
		testBank.retrieve(testChecking.getId()).deposit(20.00);
		double actual = testChecking.getBalance();
		assertEquals(40.00, actual);
	}

	@Test
	public void withdraw_money_by_id_through_bank() {
		testBank.addAccount(testChecking);
		testChecking.deposit(40.00);
		testBank.retrieve(testChecking.getId()).withdraw(20.00);
		double actual = testChecking.getBalance();
		assertEquals(20.00, actual);
	}

	@Test
	public void withdraw_money__twice_by_id_through_bank() {
		testBank.addAccount(testChecking);
		testChecking.deposit(40.00);
		testBank.retrieve(testChecking.getId()).withdraw(20.00);
		testBank.retrieve(testChecking.getId()).withdraw(10.00);
		double actual = testChecking.getBalance();
		assertEquals(10.00, actual);
	}

}
