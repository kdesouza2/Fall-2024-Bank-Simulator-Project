import org.junit.jupiter.api.BeforeEach;

public class CommandProcessorTest {

	CommandProcessor commandProcessor;
	Bank newBank;

	@BeforeEach
	void setUp() {
		newBank = new Bank();
		commandProcessor = new CommandProcessor(newBank);
	}
}
