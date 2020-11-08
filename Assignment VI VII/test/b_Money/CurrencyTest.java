package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		assertEquals(SEK.getName(), "SEK");
		assertEquals(DKK.getName(), "DKK");
		assertEquals(EUR.getName(), "EUR");
	}
	
	@Test
	public void testGetRate() {
		assertEquals(SEK.getRate(), 0.15, 0.00001);
		assertEquals(DKK.getRate(), 0.20, 0.00001);
		assertEquals(EUR.getRate(), 1.5, 0.00001);
	}
	
	@Test
	public void testSetRate() {
		SEK.setRate(0.01);
		DKK.setRate(0.02);
		EUR.setRate(0.03);
		assertEquals(SEK.getRate(), 0.01, 0.00001);
		assertEquals(DKK.getRate(), 0.02, 0.00001);
		assertEquals(EUR.getRate(), 0.03, 0.00001);
	}
	
	@Test
	public void testGlobalValue() {
		assertEquals(100, (int) SEK.universalValue(15));
		assertEquals(100, (int) DKK.universalValue(20));
		assertEquals(100, (int) EUR.universalValue(150));
	}
	
	@Test
	public void testValueInThisCurrency() {
		assertEquals(15, (int) SEK.valueInThisCurrency(20, DKK));
		assertEquals(15, (int) SEK.valueInThisCurrency(150, EUR));
		assertEquals(20, (int) DKK.valueInThisCurrency(15, SEK));
		assertEquals(20, (int) DKK.valueInThisCurrency(150, EUR));
		assertEquals(150, (int) EUR.valueInThisCurrency(15, SEK));
		assertEquals(150, (int) EUR.valueInThisCurrency(20, DKK));
	}
}
