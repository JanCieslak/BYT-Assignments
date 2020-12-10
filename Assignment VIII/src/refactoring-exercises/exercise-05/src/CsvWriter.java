import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class CsvWriter extends Writer {
	public CsvWriter(OutputStream out) {
		super(out);
	}

	public void write(String[][] lines) {
		for (String[] line : lines) {
			try {
				writeLine(line);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void writeLine(String[] fields) throws IOException {
		if (fields.length == 0) {
			out.write("\n".getBytes(StandardCharsets.UTF_8));
		} else {
			writeField(fields[0]);

			for (int i = 1; i < fields.length; i++) {
				out.write(",".getBytes(StandardCharsets.UTF_8));
				writeField(fields[i]);
			}
			out.write("\n".getBytes(StandardCharsets.UTF_8));
		}
	}

	private void writeField(String field) throws IOException {
		if (field.indexOf(',') != -1 || field.indexOf('\"') != -1)
			writeQuoted(field);
		else
		    out.write(field.getBytes(StandardCharsets.UTF_8));
	}

	private void writeQuoted(String field) throws IOException {
		out.write("\"".getBytes(StandardCharsets.UTF_8));
		for (int i = 0; i < field.length(); i++) {
			char c = field.charAt(i);
			if (c == '\"')
			    out.write("\"\"".getBytes(StandardCharsets.UTF_8));
			else
			    out.write(String.valueOf(c).getBytes(StandardCharsets.UTF_8));
		}
        out.write("\"".getBytes(StandardCharsets.UTF_8));
	}
}