import java.io.IOException;
import java.io.Writer;

public class FmlPersonPrinter implements PersonPrinter {
    @Override
    public String print(Person person) {
        return person.first + " " + ((person.middle == null) ? "" : person.middle + " ") + person.last;
    }

    @Override
    public void printOut(Writer out, Person person) throws IOException {
        out.write(print(person));
    }
}
