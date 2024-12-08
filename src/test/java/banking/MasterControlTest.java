package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MasterControlTest {
	MasterControl masterControl;
	Bank bank;
	List<String> input;

	@BeforeEach
	void setUp() {
		input = new ArrayList<>();
		bank = new Bank();
		masterControl = new MasterControl(new CommandValidator(bank), new CommandProcessor(bank), new CommandStorage());
	}

	@Test
	void sample_make_sure_this_passes_unchanged_or_you_will_fail() {
		input.add("Create savings 12345678 0.6"); // valid
		input.add("Deposit 12345678 700"); // valid
		input.add("Deposit 12345678 5000"); // invalid
		input.add("creAte cHecKing 98765432 0.01"); // valid
		input.add("Deposit 98765432 300"); // valid
		input.add("Transfer 98765432 12345678 300"); // valid
		input.add("Pass 1"); // valid -- checking account closes
		input.add("Create cd 23456789 1.2 2000"); // valid
		List<String> actual = masterControl.start(input);

		assertEquals(5, actual.size());
		assertEquals("Savings 12345678 1000.50 0.60", actual.get(0));
		assertEquals("Deposit 12345678 700", actual.get(1));
		assertEquals("Transfer 98765432 12345678 300", actual.get(2));
		assertEquals("Cd 23456789 2000.00 1.20", actual.get(3));
		assertEquals("Deposit 12345678 5000", actual.get(4));
	}

}
