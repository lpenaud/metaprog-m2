package utils;

public class StringUtils {

	public static String defaultString(final String str, final String d) {
		return str.isEmpty() || str.isBlank() ? d : str;
	}

	public static String toTitleCase(final String str) {
		return new StringBuilder(str.substring(0, 1).toUpperCase())
				.append(str.substring(1).toLowerCase())
				.toString();
	}

	private StringUtils() {
	}

}
