package b_Money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000, SEK);
		SEK200 = new Money(20000, SEK);
		SEKn100 = new Money(-10000, SEK);
		SEK0 = new Money(0, SEK);
		EUR20 = new Money(2000, EUR);
		EUR10 = new Money(1000, EUR);
		EUR0 = new Money(0, EUR);
	}

	@Test
	public void testGetAmount() {
		assertEquals(100.0, SEK100.getAmount(), 0.001);
		assertEquals(200.0, SEK200.getAmount(), 0.001);
		assertEquals(-100.0, SEKn100.getAmount(), 0.001);
		assertEquals(0.0, SEK0.getAmount(), 0.001);
		assertEquals(20.0, EUR20.getAmount(), 0.001);
		assertEquals(10.0, EUR10.getAmount(), 0.001);
		assertEquals(0.0, EUR0.getAmount(), 0.001);
	}

	@Test
	public void testGetCurrency() {
		assertSame(SEK, SEK100.getCurrency());
		assertSame(SEK, SEK200.getCurrency());
		assertSame(SEK, SEKn100.getCurrency());
		assertSame(SEK, SEK0.getCurrency());
		assertSame(EUR, EUR20.getCurrency());
		assertSame(EUR, EUR10.getCurrency());
		assertSame(EUR, EUR0.getCurrency());
	}

	@Test
	public void testToString() {
		assertEquals("(100.00) (SEK)", SEK100.toString());
		assertEquals("(200.00) (SEK)", SEK200.toString());
		assertEquals("(-100.00) (SEK)", SEKn100.toString());
		assertEquals("(0.00) (SEK)", SEK0.toString());
		assertEquals("(20.00) (EUR)", EUR20.toString());
		assertEquals("(10.00) (EUR)", EUR10.toString());
		assertEquals("(0.00) (EUR)", EUR0.toString());
	}

	@Test
	public void testGlobalValue() {
		assertEquals(SEK.universalValue(100), SEK100.universalValue());
		assertEquals(SEK.universalValue(200), SEK200.universalValue());
		assertEquals(SEK.universalValue(-100), SEKn100.universalValue());
		assertEquals(SEK.universalValue(0), SEK0.universalValue());
		assertEquals(EUR.universalValue(20), EUR20.universalValue());
		assertEquals(EUR.universalValue(10), EUR10.universalValue());
		assertEquals(EUR.universalValue(0), EUR0.universalValue());
	}

	@Test
	public void testEqualsMoney() {
		assertTrue(new Money(10000, SEK).equals(SEK100));
		assertTrue(new Money(20000, SEK).equals(SEK200));
		assertTrue(new Money(-10000, SEK).equals(SEKn100));
		assertTrue(new Money(0, SEK).equals(SEK0));
		assertTrue(new Money(2000, EUR).equals(EUR20));
		assertTrue(new Money(1000, EUR).equals(EUR10));
		assertTrue(new Money(0, EUR).equals(EUR0));
	}

	@Test
	public void testAdd() {
		final Money SEK_ADD = new Money(10000, SEK);
		final Money EUR_ADD = new Money(10000, EUR);

		assertEquals(200.0, SEK100.add(SEK_ADD).getAmount(), 0.1);
		assertEquals(300.0, SEK200.add(SEK_ADD).getAmount(), 0.1);
		assertEquals(0.0, SEKn100.add(SEK_ADD).getAmount(), 0.1);
		assertEquals(100.0, SEK0.add(SEK_ADD).getAmount(), 0.1);
		assertEquals(120.0, EUR20.add(EUR_ADD).getAmount(), 0.1);
		assertEquals(110.0, EUR10.add(EUR_ADD).getAmount(), 0.1);
		assertEquals(100.0, EUR0.add(EUR_ADD).getAmount(), 0.1);
	}

	@Test
	public void testSub() {
		final Money SEK_SUB = new Money(10000, SEK);
		final Money EUR_SUB = new Money(10000, EUR);

		assertEquals(0.0f, SEK100.sub(SEK_SUB).getAmount(), 0.1);
		assertEquals(100.0, SEK200.sub(SEK_SUB).getAmount(), 0.1);
		assertEquals(-200.0, SEKn100.sub(SEK_SUB).getAmount(), 0.1);
		assertEquals(-100.0, SEK0.sub(SEK_SUB).getAmount(), 0.1);
		assertEquals(-80.0, EUR20.sub(EUR_SUB).getAmount(), 0.1);
		assertEquals(-90.0, EUR10.sub(EUR_SUB).getAmount(), 0.1);
		assertEquals(-100.0, EUR0.sub(EUR_SUB).getAmount(), 0.1);
	}

	@Test
	public void testIsZero() {
		assertFalse(SEK100.isZero());
		assertFalse(SEK200.isZero());
		assertFalse(SEKn100.isZero());
		assertTrue(SEK0.isZero());
		assertFalse(EUR20.isZero());
		assertFalse(EUR10.isZero());
		assertTrue(EUR0.isZero());
	}

	@Test
	public void testNegate() {
		assertEquals(-100.0, SEK100.negate().getAmount(), 0.1);
		assertEquals(-200.0, SEK200.negate().getAmount(), 0.1);
		assertEquals(100.0, SEKn100.negate().getAmount(), 0.1);
		assertEquals(0.0, SEK0.negate().getAmount(), 0.1);
		assertEquals(-20.0, EUR20.negate().getAmount(), 0.1);
		assertEquals(-10.0, EUR10.negate().getAmount(), 0.1);
		assertEquals(0.0, EUR0.negate().getAmount(), 0.1);
	}

	@Test
	public void testCompareTo() {
		final Money SEK_COMP = new Money(10000, SEK);
		final Money EUR_COMP = new Money(1000, EUR);

		assertEquals(0, SEK100.compareTo(SEK_COMP));
		assertTrue(SEK200.compareTo(SEK_COMP) > 0);
		assertTrue(SEKn100.compareTo(SEK_COMP) < 0);
		assertTrue(SEK0.compareTo(SEK_COMP) < 0);
		assertTrue(EUR20.compareTo(EUR_COMP) > 0);
		assertEquals(0, EUR10.compareTo(EUR_COMP));
		assertTrue(EUR0.compareTo(EUR_COMP) < 0);
	}
}
