import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandValidatorTest {
	CommandValidator commandValidator;
	Bank newBank;

	@BeforeEach
	void setUp() {
		newBank = new Bank();
		commandValidator = new CommandValidator(newBank);
	}

	@Test
	void valid_create_checking_command() {
		boolean actual = commandValidator.validCreateChecking("create checking 12345678 0.01");
		assertTrue(actual);
	}

	@Test
	void valid_create_savings_command() {
		boolean actual = commandValidator.validCreateSavings("create savings 12345678 0.01");
		assertTrue(actual);
	}

	@Test
	void valid_create_cd_command() {
		boolean actual = commandValidator.validCreateCD("create cd 12345678 0.01 1001");
		assertTrue(actual);
	}

	@Test
	void valid_deposit_into_checking_command() {
		Checking testChecking = new Checking(12345678, 0.06);
		newBank.addAccount(testChecking);
		boolean actual = commandValidator.validDepositIntoChecking("deposit 12345678 500");
		assertTrue(actual);
	}

	@Test
	void valid_deposit_into_savings_command() {
		Savings testSavings = new Savings(12345678, 0.06);
		newBank.addAccount(testSavings);
		boolean actual = commandValidator.validDepositIntoSavings("deposit 12345678 500");
		assertTrue(actual);
	}

	@Test
	void deposit_into_cd_is_invalid() {
		CD testCD = new CD(12345678, 0.07, 1000);
		newBank.addAccount(testCD);
		boolean actual = commandValidator.validAccountTypeForDeposit("deposit 12345678 500");
		assertFalse(actual);
	}

	@Test
	void duplicate_account_id_is_invalid() {
		Checking testChecking = new Checking(12345678, 0.06);
		newBank.addAccount(testChecking);
		boolean actual = commandValidator.validAccountIDForCreate(testChecking);
		assertFalse(actual);
	}

}
