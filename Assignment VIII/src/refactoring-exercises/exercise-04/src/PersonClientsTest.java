// The Person class has multiple clients, but all of the clients are in 
// one file for convenience.  Imagine them as non-test methods in separate 
// client classes.

import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;

public class PersonClientsTest {

	@Test
	public void testClients() throws IOException {
		Person bobSmith = new Person("Smith", "Bob", null);
		Person jennyJJones = new Person("Jones", "Jenny", "J");

		PersonPrinter fmlPrinter = new FmlPersonPrinter();
		PersonPrinter lfmPrinter = new LfmPersonPrinter();

		StringWriter out = new StringWriter();
		fmlPrinter.printOut(out, bobSmith);
		assertEquals("Bob Smith", out.toString());

		out = new StringWriter();
		fmlPrinter.printOut(out, jennyJJones);
		assertEquals("Jenny J Jones", out.toString());

		assertEquals("Smith, Bob", lfmPrinter.print(bobSmith));
		assertEquals("Jones, Jenny J", lfmPrinter.print(jennyJJones));

		out = new StringWriter();
		lfmPrinter.printOut(out, bobSmith);
		assertEquals("Smith, Bob", out.toString());

		out = new StringWriter();
		lfmPrinter.printOut(out, jennyJJones);
		assertEquals("Jones, Jenny J", out.toString());

		assertEquals("Smith, Bob", lfmPrinter.print(bobSmith));
		assertEquals("Jones, Jenny J", lfmPrinter.print(jennyJJones));
	}
}
