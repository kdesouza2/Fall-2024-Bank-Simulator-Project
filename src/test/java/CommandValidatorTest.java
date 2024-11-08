import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	CommandValidator commandValidator;
	Bank newBank;
	Checking testChecking;
	Savings testSavings;
	CD testCD;

	@BeforeEach
	void setUp() {
		newBank = new Bank();
		commandValidator = new CommandValidator(newBank);
		testChecking = new Checking(12345678, 0.06);
		testSavings = new Savings(12345678, 0.06);
		testCD = new CD(12345678, 0.06, 1000);
	}

	@Test
	void valid_create_checking_command() {
		boolean actual = commandValidator.validateCreate("create checking 12345678 0.01");
		assertTrue(actual);
	}

	@Test
	void valid_create_savings_command() {
		boolean actual = commandValidator.validateCreate("create savings 12345678 0.01");
		assertTrue(actual);
	}

	@Test
	void valid_create_cd_command() {
		boolean actual = commandValidator.validateCreate("create cd 12345678 0.01 1001");
		assertTrue(actual);
	}

	@Test
	void create_account_with_duplicate_id_is_invalid() {
		newBank.addAccount(testChecking);
		boolean actual = commandValidator.validateCreate("create savings 12345678 0.06");
		assertFalse(actual);
	}

	@Test
	void create_command_without_account_type_is_invalid() {
		boolean actual = commandValidator.validateCreate("create 12345678 0.06");
		assertFalse(actual);
	}

	@Test
	void create_command_with_invalid_account_type_is_invalid() {
		boolean actual = commandValidator.validateCreate("create box 12345678 0.06");
		assertFalse(actual);
	}

	@Test
	void invalid_command_type_is_invalid() {
		boolean actual = commandValidator.validCommandType("make checking 12345678 0.06");
		assertFalse(actual);
	}

	@Test
	void empty_command_is_invalid() {
		boolean actual = commandValidator.validateCreate(" ");
		assertFalse(actual);
	}

//	 @Test void valid_deposit_into_checking_command() {
//		 newBank.addAccount(testChecking); boolean actual =
//		 commandValidator.validate("deposit 12345678 500"); assertTrue(actual);
//	}
//
//	 @Test void valid_deposit_into_savings_command() {
//		 newBank.addAccount(testSavings); boolean actual =
//		 commandValidator.validDepositIntoSavings("deposit 12345678 500");
//		 assertTrue(actual);
//	}
//
//	 @Test void deposit_into_cd_is_invalid() {
//		newBank.addAccount(testCD); boolean
//		 actual = commandValidator.validAccountTypeForDeposit("deposit 12345678 500");
//		 assertFalse(actual);
//	}
//

}
