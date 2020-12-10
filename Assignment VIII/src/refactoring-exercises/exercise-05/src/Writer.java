import java.io.OutputStream;

public abstract class Writer {
    protected OutputStream out;

    public Writer(OutputStream out) {
        this.out = out;
    }
}
