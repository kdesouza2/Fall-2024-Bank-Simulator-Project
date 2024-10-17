import org.junit.jupiter.api.BeforeEach;

public abstract class AccountsTest {
	public static final int ID_CHECKING = 12345678;
	public static final int ID_SAVINGS = 234567891;
	public static final int ID_CD = 345678912;
	public static final double APR_VALUE = 9.8;
	public static final double INIT_BALANCE = 1000.00;
	Accounts testChecking;
	Accounts testSavings;
	Accounts testCD;
	Bank testBank;

	@BeforeEach
	public void setUp() {
		testChecking = new Checking(ID_CHECKING, APR_VALUE);
		testSavings = new Savings(ID_SAVINGS, APR_VALUE);
		testCD = new CD(ID_CD, APR_VALUE, INIT_BALANCE);
		testBank = new Bank();
	}

}
