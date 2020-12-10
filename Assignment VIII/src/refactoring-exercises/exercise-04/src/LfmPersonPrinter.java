import java.io.IOException;
import java.io.Writer;

public class LfmPersonPrinter implements PersonPrinter {
    @Override
    public String print(Person person) {
        return person.last + ", " + person.first + ((person.middle == null) ? "" : " " + person.middle);
    }

    @Override
    public void printOut(Writer out, Person person) throws IOException {
        out.write(print(person));
    }
}
