import java.io.IOException;
import java.io.Writer;

public interface PersonPrinter {
   String print(Person person);
   void printOut(Writer out, Person person) throws IOException;
}
