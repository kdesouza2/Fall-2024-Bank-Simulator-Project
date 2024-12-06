package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {

	private CommandProcessor commandProcessor;
	private Bank newBank;
	private Checking testChecking;
	private Savings testSavings;
	private CD testCD;

	@BeforeEach
	void setUp() {
		newBank = new Bank();
		commandProcessor = new CommandProcessor(newBank);
		testChecking = new Checking(12345678, 9.8);
		testSavings = new Savings(12345678, 9.8);
		testCD = new CD(12345678, 9.8, 1000);
	}

	///////////////////////////////////////////////////////////////////
	///////////////////// CREATE TESTS ////////////////////////////////
	///////////////////////////////////////////////////////////////////
	@Test
	void create_checking_account() {
		commandProcessor.processCommand("create checking 12345678 0.09");
		assertEquals(newBank.getAccounts().size(), 1);
	}

	@Test
	void new_checking_account_has_0_balance() {
		commandProcessor.processCommand("create checking 12345678 0.09");
		assertEquals(newBank.retrieve(12345678).getBalance(), 0);
	}

	@Test
	void create_savings_account() {
		commandProcessor.processCommand("create savings 12345678 0.09");
		assertEquals(newBank.getAccounts().size(), 1);
	}

	@Test
	void new_savings_account_has_0_balance() {
		commandProcessor.processCommand("create savings 12345678 0.09");
		assertEquals(newBank.retrieve(12345678).getBalance(), 0);
	}

	@Test
	void create_cd_account() {
		commandProcessor.processCommand("create cd 12345678 0.09 1000");
		assertEquals(newBank.getAccounts().size(), 1);
	}

	@Test
	void new_cd_account_has_specified_balance() {
		commandProcessor.processCommand("create cd 12345678 0.09 1000");
		assertEquals(newBank.retrieve(12345678).getBalance(), 1000);
	}

	@Test
	void new_checking_account_has_transaction_history_size_1() {
		commandProcessor.processCommand("create checking 12345678 0.09");
		assertEquals(newBank.retrieve(12345678).getTransactionHistory().size(), 1);
	}

	@Test
	void new_savings_account_has_transaction_history_size_1() {
		commandProcessor.processCommand("create savings 12345678 0.09");
		assertEquals(newBank.retrieve(12345678).getTransactionHistory().size(), 1);
	}

	@Test
	void new_cd_account_has_transaction_history_size_1() {
		commandProcessor.processCommand("create cd 12345678 0.09 1000");
		assertEquals(newBank.retrieve(12345678).getTransactionHistory().size(), 1);
	}

	@Test
	void new_checking_account_has_time_0() {
		commandProcessor.processCommand("create checking 12345678 0.09");
		assertEquals(newBank.retrieve(12345678).getTime(), 0);
	}

	@Test
	void new_savings_account_has_time_0() {
		commandProcessor.processCommand("create savings 12345678 0.09");
		assertEquals(newBank.retrieve(12345678).getTime(), 0);
	}

	@Test
	void new_cd_account_has_time_0() {
		commandProcessor.processCommand("create cd 12345678 0.09 1000");
		assertEquals(newBank.retrieve(12345678).getTime(), 0);
	}

	///////////////////////////////////////////////////////////////////
	///////////////////// DEPOSIT TESTS ///////////////////////////////
	///////////////////////////////////////////////////////////////////

	@Test
	void deposit_into_checking_account() {
		newBank.addAccount(testChecking);
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(testChecking.getBalance(), 100);
	}

	@Test
	void deposit_into_checking_account_has_transaction_history_2() {
		commandProcessor.processCommand("create checking 12345678 0.09");
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(newBank.retrieve(12345678).getTransactionHistory().size(), 2);
	}

	@Test
	void deposit_twice_into_checking_account() {
		newBank.addAccount(testChecking);
		commandProcessor.processCommand("deposit 12345678 100");
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(testChecking.getBalance(), 200);
	}

	@Test
	void deposit_into_checking_account_twice_has_transaction_history_3() {
		commandProcessor.processCommand("create checking 12345678 0.09");
		commandProcessor.processCommand("deposit 12345678 100");
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(newBank.retrieve(12345678).getTransactionHistory().size(), 3);
	}

	@Test
	void deposit_into_savings_account() {
		newBank.addAccount(testSavings);
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(testSavings.getBalance(), 100);
	}

	@Test
	void deposit_into_savings_account_has_transaction_history_2() {
		commandProcessor.processCommand("create savings 12345678 0.09");
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(newBank.retrieve(12345678).getTransactionHistory().size(), 2);
	}

	@Test
	void deposit_twice_into_savings_account() {
		newBank.addAccount(testSavings);
		commandProcessor.processCommand("deposit 12345678 100");
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(testSavings.getBalance(), 200);
	}

	@Test
	void deposit_into_savings_account_twice_has_transaction_history_3() {
		commandProcessor.processCommand("create savings 12345678 0.09");
		commandProcessor.processCommand("deposit 12345678 100");
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(newBank.retrieve(12345678).getTransactionHistory().size(), 3);
	}

	///////////////////////////////////////////////////////////////////
	///////////////////// PASS TIME TESTS /////////////////////////////
	///////////////////////////////////////////////////////////////////
	@Test
	void pass_command_with_savings_account() {
		newBank.addAccount(testSavings);
		testSavings.deposit(1000); // so pass won't close the account
		commandProcessor.processCommand("pass 1");
		assertEquals(testSavings.getTime(), 1);
	}

	@Test
	void pass_command_with_checking_account() {
		newBank.addAccount(testChecking);
		testChecking.deposit(1000); // so pass won't close the account
		commandProcessor.processCommand("pass 1");
		assertEquals(testChecking.getTime(), 1);
	}

	@Test
	void pass_command_with_cd_account() {
		newBank.addAccount(testCD);
		testCD.deposit(1000); // so pass won't close the account
		commandProcessor.processCommand("pass 1");
		assertEquals(testCD.getTime(), 1);
	}

	@Test
	void pass_command_twice_with_active_account() {
		newBank.addAccount(testChecking);
		testChecking.deposit(1000); // so pass won't close the account
		commandProcessor.processCommand("pass 2");
		commandProcessor.processCommand("pass 3");
		assertEquals(testChecking.getTime(), 5);
	}

	@Test
	void pass_command_updates_balance() {
		newBank.addAccount(testChecking);
		testChecking.deposit(1000); // so pass won't close the account
		commandProcessor.processCommand("pass 1");
		assertEquals(testChecking.getBalance(), 1008.16);
	}

	@Test
	void pass_command_will_close_empty_accounts() {
		newBank.addAccount(testChecking);
		commandProcessor.processCommand("pass 1");
		assertEquals(newBank.getAccounts().size(), 0);
	}

	@Test
	void pass_command_will_deduct_from_minimum_account() {
		newBank.addAccount(testSavings);
		testSavings.deposit(50);
		commandProcessor.processCommand("pass 1");
		assertEquals(testSavings.getBalance(), 25.2);
	}

	@Test
	void pass_command_will_accrue_apr_for_cd_accounts_4_times() {
		newBank.addAccount(testCD);
		commandProcessor.processCommand("pass 1");
		assertEquals(testCD.getBalance(), 1033.05);
	}

	@Test
	void pass_command_will_reset_withdrawable_status_for_savings() {
		newBank.addAccount(testSavings);
		testSavings.setWithdrawableFalse();
		commandProcessor.processCommand("pass 1");
		assertTrue(testSavings.isWithdrawable());
	}

	///////////////////////////////////////////////////////////////////
	///////////////////// WITHDRAWAL TESTS ////////////////////////////
	///////////////////////////////////////////////////////////////////
	@Test
	void withdraw_amount_less_than_balance_from_checking() {
		newBank.addAccount(testChecking);
		testChecking.deposit(100);
		commandProcessor.processCommand("withdraw 12345678 50");
		assertEquals(testChecking.getBalance(), 50);
	}

	@Test
	void withdraw_twice_from_checking() {
		newBank.addAccount(testChecking);
		testChecking.deposit(100);
		commandProcessor.processCommand("withdraw 12345678 50");
		commandProcessor.processCommand("withdraw 12345678 50");
		assertEquals(testChecking.getBalance(), 0);
	}

	@Test
	void withdraw_amount_greater_than_balance_from_checking() {
		newBank.addAccount(testChecking);
		testChecking.deposit(100);
		commandProcessor.processCommand("withdraw 12345678 150");
		assertEquals(testChecking.getBalance(), 0);
	}

	@Test
	void withdraw_amount_equal_to_balance_from_checking() {
		newBank.addAccount(testChecking);
		testChecking.deposit(100);
		commandProcessor.processCommand("withdraw 12345678 100");
		assertEquals(testChecking.getBalance(), 0);
	}

	@Test
	void create_and_valid_withdraw_from_checking_has_transaction_history_of_2() {
		commandProcessor.processCommand("create checking 12345678 0.09");
		commandProcessor.processCommand("withdraw 12345678 100");
		assertEquals(newBank.retrieve(12345678).getTransactionHistory().size(), 2);
	}

	@Test
	void create_and_valid_withdraw_from_checking_twice_has_transaction_history_of_3() {
		commandProcessor.processCommand("create checking 12345678 0.09");
		commandProcessor.processCommand("withdraw 12345678 100");
		commandProcessor.processCommand("withdraw 12345678 100");
		assertEquals(newBank.retrieve(12345678).getTransactionHistory().size(), 3);
	}

	@Test
	void withdraw_amount_less_than_balance_from_withdrawable_savings() {
		newBank.addAccount(testSavings);
		testSavings.deposit(100);
		commandProcessor.processCommand("withdraw 12345678 50");
		assertEquals(testSavings.getBalance(), 50);
	}

	@Test
	void withdraw_amount_greater_than_balance_from_withdrawable_savings() {
		newBank.addAccount(testSavings);
		testSavings.deposit(100);
		commandProcessor.processCommand("withdraw 12345678 150");
		assertEquals(testSavings.getBalance(), 0);
	}

	@Test
	void withdraw_amount_equal_to_balance_from_withdrawable_savings() {
		newBank.addAccount(testSavings);
		testSavings.deposit(100);
		commandProcessor.processCommand("withdraw 12345678 100");
		assertEquals(testSavings.getBalance(), 0);
	}

	@Test
	void create_and_valid_withdraw_from_savings_has_transaction_history_of_2() {
		commandProcessor.processCommand("create savings 12345678 0.09");
		commandProcessor.processCommand("withdraw 12345678 100");
		assertEquals(newBank.retrieve(12345678).getTransactionHistory().size(), 2);
	}

	///////////////////////////////////////////////////////////////////
	///////////////////// TRANSFER TESTS /////////////////////////////
	///////////////////////////////////////////////////////////////////

}
