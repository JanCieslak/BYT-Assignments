import expressions.Expression;
import expressions.NumberExpression;
import expressions.binary.AddExpression;
import expressions.binary.DivideExpression;
import expressions.binary.MultiplyExpression;
import expressions.binary.SubtractExpression;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class ExpressionTest {
	@Test
	public void testConstant() {
		Expression e = new NumberExpression(-43);
		assertEquals(e.evaluate(), -43);
	}

	@Test
	public void testAddition() {
		Expression e = new AddExpression(new NumberExpression(100), new NumberExpression(-100));
		assertEquals(e.evaluate(), 0);
	}

	@Test
	public void testSubtraction() {
		Expression e = new SubtractExpression(new NumberExpression(100), new NumberExpression(-100));
		assertEquals(e.evaluate(), 200);
	}

	@Test
	public void testMultiplication() {
		Expression e = new MultiplyExpression(new NumberExpression(100), new NumberExpression(-100));
		assertEquals(e.evaluate(), -10000);
	}

	@Test
	public void testDivision() {
		Expression e = new DivideExpression(new NumberExpression(100), new NumberExpression(-100));
		assertEquals(e.evaluate(), -1);
	}

	@Test
	public void testComplexExpression() {
		// ((1 + 2) - ((3 * 4) / 5)) = 1
		Expression e = new SubtractExpression(
							new AddExpression(new NumberExpression(1),
											  new NumberExpression(2)),
							new DivideExpression(
								new MultiplyExpression(new NumberExpression(3),
													   new NumberExpression(4)),
								new NumberExpression(5)));
		assertEquals(e.evaluate(), 1);
	}
}
