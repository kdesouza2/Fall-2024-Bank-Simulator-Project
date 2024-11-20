package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandProcessorTest {

	private CommandProcessor commandProcessor;
	private Bank newBank;
	private Checking testChecking;
	private Savings testSavings;

	@BeforeEach
	void setUp() {
		newBank = new Bank();
		commandProcessor = new CommandProcessor(newBank);
		testChecking = new Checking(12345678, 0.06);
		testSavings = new Savings(12345678, 0.06);
	}

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
	void deposit_into_checking_account() {
		newBank.addAccount(testChecking);
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(newBank.retrieve(12345678).getBalance(), 100);
	}

	@Test
	void deposit_twice_into_checking_account() {
		newBank.addAccount(testChecking);
		commandProcessor.processCommand("deposit 12345678 100");
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(newBank.retrieve(12345678).getBalance(), 200);
	}

	@Test
	void deposit_into_savings_account() {
		newBank.addAccount(testSavings);
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(newBank.retrieve(12345678).getBalance(), 100);
	}

	@Test
	void deposit_twice_into_savings_account() {
		newBank.addAccount(testSavings);
		commandProcessor.processCommand("deposit 12345678 100");
		commandProcessor.processCommand("deposit 12345678 100");
		assertEquals(newBank.retrieve(12345678).getBalance(), 200);
	}

}
