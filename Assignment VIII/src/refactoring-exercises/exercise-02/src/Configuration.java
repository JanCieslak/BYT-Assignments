import java.util.*;

public class Configuration {
	public int interval;
	public int duration;
	public int departure;

	public void load(Properties props) throws ConfigurationException {
		interval = parseIntProperty(props, "interval");
		duration = parseIntIntervalProperty(props, "duration");
		departure = parseIntIntervalProperty(props, "departure");
	}

	private int parseIntProperty(Properties props, String name) throws ConfigurationException {
		String valueString = props.getProperty(name);

		if (valueString == null) {
			throw new ConfigurationException("Property " + name + " is null");
		}

		int value = Integer.parseInt(valueString);

		if (value <= 0) {
			throw new ConfigurationException("Property " + name + " should be > 0");
		}

		return value;
	}

	private int parseIntIntervalProperty(Properties props, String name) throws ConfigurationException {
		int value = parseIntProperty(props, name);

		if ((value % interval) != 0) {
			throw new ConfigurationException("Property: " + name + " % interval should be equal to 0");
		}

		return value;
	}
}
