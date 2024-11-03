
import java.util.HashMap;
import java.util.Map;

public class Bank {
	private Map<Integer, Accounts> accountsInBank;

	public Bank() {
		accountsInBank = new HashMap<>();
	}

	public Map<Integer, Accounts> getAccounts() {
		return accountsInBank;
	}

	public void addAccount(Accounts newAccount) {
		accountsInBank.put(newAccount.getId(), newAccount);
	}

	public Accounts retrieve(int accountId) {
		return accountsInBank.get(accountId);
	}

	public boolean accountExistsByAccountID(Accounts newAccount) {
		if (retrieve(newAccount.getId()) != null) {
			return true;
		} else {
			return false;
		}
	}

}
