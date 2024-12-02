package banking;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	private CommandValidator commandValidator;
	private Bank newBank;
	private Checking testChecking;
	private Savings testSavings;
	private CD testCD;

	@BeforeEach
	void setUp() {
		newBank = new Bank();
		commandValidator = new CommandValidator(newBank);
		testChecking = new Checking(12345678, 0.06);
		testSavings = new Savings(12345678, 0.06);
		testCD = new CD(12345678, 0.06, 1000);
	}

	///////////////////////////////////////////////////////////////////
	///////////////////// CREATE TESTS ////////////////////////////////
	///////////////////////////////////////////////////////////////////
	@Test
	void valid_create_checking_command() {
		boolean actual = commandValidator.validateCommand("create checking 12345678 0.01");
		assertTrue(actual);
	}

	@Test
	void valid_create_savings_command() {
		boolean actual = commandValidator.validateCommand("create savings 12345678 0.01");
		assertTrue(actual);
	}

	@Test
	void valid_create_cd_command() {
		boolean actual = commandValidator.validateCommand("create cd 12345678 0.01 1001");
		assertTrue(actual);
	}

	@Test
	void create_account_with_duplicate_id_is_invalid() {
		newBank.addAccount(testChecking);
		boolean actual = commandValidator.validateCommand("create savings 12345678 0.06");
		assertFalse(actual);
	}

	@Test
	void create_account_with_negative_apr_is_invalid() {
		boolean actual = commandValidator.validateCommand("create checking 12345678 -0.9");
		assertFalse(actual);
	}

	@Test
	void create_account_with_more_than_10_apr_is_invalid() {
		boolean actual = commandValidator.validateCommand("create checking 12345678 90");
		assertFalse(actual);
	}

	@Test
	void create_cd_without_balance_is_invalid() {
		boolean actual = commandValidator.validateCommand("create cd 12345678 0.06");
		assertFalse(actual);
	}

	@Test
	void create_account_with_id_more_than_8_digits_is_invalid() {
		boolean actual = commandValidator.validateCommand("create savings 123456789 0.06");
		assertFalse(actual);
	}

	@Test
	void create_cd_account_with_negative_balance_is_invalid() {
		boolean actual = commandValidator.validateCommand("create cd 12345678 0.06 -10");
		assertFalse(actual);
	}

	@Test
	void create_without_command_is_invalid() {
		boolean actual = commandValidator.validateCommand("cd 12345678 0.08");
		assertFalse(actual);
	}

	@Test
	void create_cd_account_with_balance_more_than_10000_is_invalid() {
		boolean actual = commandValidator.validateCommand("create cd 12345678 0.09 20000");
		assertFalse(actual);
	}

	@Test
	void create_checking_with_specified_balance_is_invalid() {
		boolean actual = commandValidator.validateCommand("create checking 12345678 0.06 1000");
		assertFalse(actual);
	}

	@Test
	void create_savings_with_specified_balance_is_invalid() {
		boolean actual = commandValidator.validateCommand("create savings 12345678 0.06 1000");
		assertFalse(actual);
	}

	@Test
	void create_command_without_account_type_is_invalid() {
		boolean actual = commandValidator.validateCommand("create 12345678 0.06");
		assertFalse(actual);
	}

	@Test
	void create_checking_without_id_is_invalid() {
		boolean actual = commandValidator.validateCommand("create checking 0.09");
		assertFalse(actual);
	}

	@Test
	void create_savings_without_id_is_invalid() {
		boolean actual = commandValidator.validateCommand("create savings 0.09");
		assertFalse(actual);
	}

	@Test
	void create_cd_without_id_is_invalid() {
		boolean actual = commandValidator.validateCommand("create cd 0.09");
		assertFalse(actual);
	}

	@Test
	void create_checking_without_apr_is_invalid() {
		boolean actual = commandValidator.validateCommand("create checking 12345678");
		assertFalse(actual);
	}

	@Test
	void create_savings_without_apr_is_invalid() {
		boolean actual = commandValidator.validateCommand("create savings 12345678");
		assertFalse(actual);
	}

	@Test
	void create_cd_without_apr_is_invalid() {
		boolean actual = commandValidator.validateCommand("create cd 12345678");
		assertFalse(actual);
	}

	@Test
	void create_command_with_invalid_account_type_is_invalid() {
		boolean actual = commandValidator.validateCommand("create box 12345678 0.06");
		assertFalse(actual);
	}

	@Test
	void create_with_non_int_id_is_invalid() {
		boolean actual = commandValidator.validateCommand("create checking keyra 0.06");
		assertFalse(actual);
	}

	@Test
	void create_with_0_apr_is_valid() {
		boolean actual = commandValidator.validateCommand("create checking 12345678 0");
		assertTrue(actual);
	}

	@Test
	void create_with_10_apr_is_valid() {
		boolean actual = commandValidator.validateCommand("create checking 12345678 10");
		assertTrue(actual);
	}

	@Test
	void create_cd_with_1000_balance_is_valid() {
		boolean actual = commandValidator.validateCommand("create cd 12345678 0.09 1000");
		assertTrue(actual);
	}

	@Test
	void create_cd_with_10000_balance_is_valid() {
		boolean actual = commandValidator.validateCommand("create cd 12345678 0.09 10000");
		assertTrue(actual);
	}

	///////////////////////////////////////////////////////////////////
	///////////////////// DEPOSIT TESTS ///////////////////////////////
	///////////////////////////////////////////////////////////////////
	@Test
	void valid_deposit_into_checking_command() {
		newBank.addAccount(testChecking);
		boolean actual = commandValidator.validateCommand("deposit 12345678 500");
		assertTrue(actual);
	}

	@Test
	void valid_deposit_into_savings_command() {
		newBank.addAccount(testSavings);
		boolean actual = commandValidator.validateCommand("deposit 12345678 1500");
		assertTrue(actual);
	}

	@Test
	void deposit_into_cd_is_invalid() {
		newBank.addAccount(testCD);
		boolean actual = commandValidator.validateCommand("deposit 12345678 500");
		assertFalse(actual);
	}

	@Test
	void deposit_more_than_2500_into_savings_is_invalid() {
		newBank.addAccount(testSavings);
		boolean actual = commandValidator.validateCommand("deposit 12345678 3000");
		assertFalse(actual);
	}

	@Test
	void deposit_without_command_is_invalid() {
		boolean actual = commandValidator.validateCommand("12345678 0.09");
		assertFalse(actual);
	}

	@Test
	void deposit_negative_number_into_savings_is_invalid() {
		newBank.addAccount(testSavings);
		boolean actual = commandValidator.validateCommand("deposit 12345678 -1000");
		assertFalse(actual);
	}

	@Test
	void deposit_negative_number_into_checking_is_invalid() {
		newBank.addAccount(testChecking);
		boolean actual = commandValidator.validateCommand("deposit 12345678 -1000");
		assertFalse(actual);
	}

	@Test
	void deposit_more_than_1000_into_checking_is_invalid() {
		newBank.addAccount(testChecking);
		boolean actual = commandValidator.validateCommand("deposit 12345678 2000");
		assertFalse(actual);
	}

	@Test
	void deposit_with_non_int_id_is_invalid() {
		boolean actual = commandValidator.validateCommand("deposit keyra 300");
		assertFalse(actual);
	}

	@Test
	void deposit_without_id_is_invalid() {
		boolean actual = commandValidator.validateCommand("deposit 345");
		assertFalse(actual);
	}

	@Test
	void deposit_without_amount_is_invalid() {
		boolean actual = commandValidator.validateCommand("deposit 12345678");
		assertFalse(actual);
	}

	@Test
	void deposit_into_nonexistent_account_is_invalid() {
		newBank.addAccount(testChecking);
		boolean actual = commandValidator.validateCommand("deposit 23456789 450");
		assertFalse(actual);
	}

	///////////////////////////////////////////////////////////////////
	///////////////////// WITHDRAWAL TESTS ////////////////////////////
	///////////////////////////////////////////////////////////////////
	@Test
	void withdraw_negative_amount_from_checking_is_invalid() {
		newBank.addAccount(testChecking);
		boolean actual = commandValidator.validateCommand("withdraw 12345678 -10");
		assertFalse(actual);
	}

	@Test
	void withdraw_greater_than_400_from_checking_is_invalid() {
		newBank.addAccount(testChecking);
		boolean actual = commandValidator.validateCommand("withdraw 12345678 500");
		assertFalse(actual);
	}

	@Test
	void withdraw_amount_from_checking_less_than_balance_is_valid() {
		newBank.addAccount(testChecking);
		testChecking.deposit(300);
		boolean actual = commandValidator.validateCommand("withdraw 12345678 30");
		assertTrue(actual);
	}

	@Test
	void withdraw_amount_from_checking_greater_than_balance_is_valid() {
		newBank.addAccount(testChecking);
		testChecking.deposit(100);
		boolean actual = commandValidator.validateCommand("withdraw 12345678 150");
		assertTrue(actual);
	}

	@Test
	void withdraw_negative_amount_from_savings_is_invalid() {
		newBank.addAccount(testSavings);
		boolean actual = commandValidator.validateCommand("withdraw 12345678 -10");
		assertFalse(actual);
	}

	@Test
	void withdraw_greater_than_1000_from_savings_is_invalid() {
		newBank.addAccount(testSavings);
		boolean actual = commandValidator.validateCommand("withdraw 12345678 10000");
		assertFalse(actual);
	}

	@Test
	void withdraw_amount_from_savings_less_than_balance_is_valid() {
		newBank.addAccount(testSavings);
		testSavings.deposit(300);
		boolean actual = commandValidator.validateCommand("withdraw 12345678 30");
		assertTrue(actual);
	}

	@Test
	void withdraw_amount_from_savings_greater_than_balance_is_valid() {
		newBank.addAccount(testSavings);
		testSavings.deposit(100);
		boolean actual = commandValidator.validateCommand("withdraw 12345678 150");
		assertTrue(actual);
	}

	@Test
	void withdraw_from_unwithdrawable_savings_account_is_invalid() {
		newBank.addAccount(testSavings);
		testSavings.setWithdrawableFalse();
		boolean actual = commandValidator.validateCommand("withdraw 12345678 100");
		assertFalse(actual);
	}

	@Test
	void withdraw_command_without_command_is_invalid() {
		boolean actual = commandValidator.validateCommand("12345678 100");
		assertFalse(actual);
	}

	@Test
	void withdraw_without_account_id_is_invalid() {
		boolean actual = commandValidator.validateCommand("withdraw 100");
		assertFalse(actual);
	}

	@Test
	void withdraw_without_amount_is_invalid() {
		boolean actual = commandValidator.validateCommand("withdraw 12345678");
		assertFalse(actual);
	}

	@Test
	void withdraw_from_nonexistent_account_is_invalid() {
		boolean actual = commandValidator.validateCommand("withdraw 2345678 100");
		assertFalse(actual);
	}

	///////////////////////////////////////////////////////////////////
	///////////////////// PASS TIME TESTS /////////////////////////////
	///////////////////////////////////////////////////////////////////
	@Test
	void pass_1_month_command_is_valid() {
		boolean actual = commandValidator.validateCommand("pass 1");
		assertTrue(actual);
	}

	@Test
	void pass_negative_month_is_invalid() {
		boolean actual = commandValidator.validateCommand("pass -1");
		assertFalse(actual);
	}

	@Test
	void pass_0_is_invalid() {
		boolean actual = commandValidator.validateCommand("pass 0");
		assertFalse(actual);
	}

	@Test
	void pass_60_is_valid() {
		boolean actual = commandValidator.validateCommand("pass 60");
		assertTrue(actual);
	}

	@Test
	void pass_more_than_60_is_invalid() {
		boolean actual = commandValidator.validateCommand("pass 100");
		assertFalse(actual);
	}

	///////////////////////////////////////////////////////////////////
	///////////////////// GENERAL TESTS ///////////////////////////////
	///////////////////////////////////////////////////////////////////
	@Test
	void invalid_command_type_is_invalid() {
		boolean actual = commandValidator.validCommandType("make checking 12345678 0.06");
		assertFalse(actual);
	}

	@Test
	void empty_command_is_invalid() {
		boolean actual = commandValidator.validateCommand(" ");
		assertFalse(actual);
	}

}
