package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);

		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);

		SweBank.openAccount("Ulrika");
		SweBank.openAccount("Bob");
		Nordea.openAccount("Bob");
		DanskeBank.openAccount("Gertrud");
	}

	@Test
	public void testGetName() {
		assertEquals("SweBank", SweBank.getName());
		assertEquals("Nordea", Nordea.getName());
		assertEquals("DanskeBank", DanskeBank.getName());
	}

	@Test
	public void testGetCurrency() {
		assertSame(SEK, SweBank.getCurrency());
		assertSame(SEK, Nordea.getCurrency());
		assertSame(DKK, DanskeBank.getCurrency());
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		boolean[] openAccounts = { false, false, false, false };
		String[] names = { "Ulrika", "Bob", "Bob", "Gertrud" };
		Bank[] banks = { SweBank, SweBank, Nordea, DanskeBank };

		for (int i = 0; i < openAccounts.length; i++) {
			try {
				banks[i].openAccount(names[i]);
			} catch (Exception e) {
				openAccounts[i] = true;
			}

			assertTrue(openAccounts[i]);
		}
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(100, SEK));
		SweBank.deposit("Bob", new Money(100, SEK));
		Nordea.deposit("Bob", new Money(100, SEK));
		DanskeBank.deposit("Gertrud", new Money(100, DKK));

		assertEquals(1.0, SweBank.getBalance("Ulrika"), 0.1);
		assertEquals(1.0, SweBank.getBalance("Bob"), 0.1);
		assertEquals(1.0, Nordea.getBalance("Bob"), 0.1);
		assertEquals(1.0, DanskeBank.getBalance("Gertrud"), 0.1);
	}

	@Test
	public void testWithdraw() throws AccountDoesNotExistException {
		SweBank.withdraw("Ulrika", new Money(100, SEK));

		assertEquals(-1.0, SweBank.getBalance("Ulrika"), 0.1);
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		SweBank.deposit("Ulrika", new Money(10000, SEK));

		assertEquals(100.0, SweBank.getBalance("Ulrika"), 0.1);
	}
	
	@Test
	public void testTransfer() throws AccountDoesNotExistException {

	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		
	}
}
