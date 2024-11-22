package banking;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CommandStorageTest {
	CommandStorage commandStorage;

	@BeforeEach
	void setUp() {
		commandStorage = new CommandStorage();
	}

	@Test
	void add_invalid_command_to_list() {
		commandStorage.storeInvalidCommand("creat checking 12345678 0.09");
		assertEquals(1, commandStorage.getInvalidCommands().size());
	}

	@Test
	void list_initially_is_empty() {
		assertEquals(commandStorage.getInvalidCommands().size(), 0);
	}

	@Test
	void add_two_invalid_commands_to_list() {
		commandStorage.storeInvalidCommand("creat checking 12345678 0.1");
		commandStorage.storeInvalidCommand("depositt 23456789 0.03");
		assertEquals(2, commandStorage.getInvalidCommands().size());
	}

}
