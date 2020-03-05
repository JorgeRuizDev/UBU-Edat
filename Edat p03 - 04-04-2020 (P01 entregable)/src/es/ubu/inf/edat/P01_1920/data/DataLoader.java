package es.ubu.inf.edat.P01_1920.data;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Pattern;

public class DataLoader {

	// assume Unicode UTF-8 encoding
	private static final String CHARSET_NAME = "UTF-8";

	// assume language = English, country = US for consistency with System.out.
	private static final Locale LOCALE = Locale.US;

	// the default token separator; we maintain the invariant that this value 
	// is held by the scanner's delimiter between calls
	private static final Pattern WHITESPACE_PATTERN = Pattern.compile("\\p{javaWhitespace}+");

	// used to read the entire input. source:
	// http://weblogs.java.net/blog/pat/archive/2004/10/stupid_scanner_1.html
	private static final Pattern EVERYTHING_PATTERN = Pattern.compile("\\A");	

	private Scanner scanner;

	/**
	 * Initializes an input stream from standard input.
	 */
	public DataLoader() {
		scanner = new Scanner(new BufferedInputStream(System.in), CHARSET_NAME);
		scanner.useLocale(LOCALE);
	}


	public void LoadFile (String name) {

		if (name == null) throw new IllegalArgumentException("argument is null");
		try {
			// first try to read file from local file system
			File file = new File(name);
			if (file.exists()) {
				// for consistency with StdIn, wrap with BufferedInputStream instead of use
				// file as argument to Scanner
				FileInputStream fis = new FileInputStream(file);
				scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
				scanner.useLocale(LOCALE);
				return;
			} else {
				throw new FileNotFoundException();
			}

		}
		catch (IOException ioe) {
			throw new IllegalArgumentException("Could not open " + name, ioe);
		}
	}


	/**
	 * Reads and returns the remainder of this input stream, as a string.
	 *
	 * @return the remainder of this input stream, as a string
	 */
	public String readAll() {
		if (!scanner.hasNextLine())
			return "";

		String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
		// not that important to reset delimeter, since now scanner is empty
		scanner.useDelimiter(WHITESPACE_PATTERN); // but let's do it anyway
		return result;
	}

	/**
	 * Reads all remaining tokens from this input stream and returns them as
	 * an array of strings.
	 *
	 * @return all remaining tokens in this input stream, as an array of strings
	 */
	public String[] readAllStrings() {
		// we could use readAll.trim().split(), but that's not consistent
		// since trim() uses characters 0x00..0x20 as whitespace
		String[] tokens = WHITESPACE_PATTERN.split(readAll());
		if (tokens.length == 0 || tokens[0].length() > 0)
			return tokens;
		String[] decapitokens = new String[tokens.length-1];
		for (int i = 0; i < tokens.length-1; i++)
			decapitokens[i] = tokens[i+1];
		return decapitokens;
	}

	/**
	 * Reads all remaining tokens from this input stream, parses them as integers,
	 * and returns them as an array of integers.
	 *
	 * @return all remaining lines in this input stream, as an array of integers
	 */
	public int[] readAllInts() {
		String[] fields = readAllStrings();
		int[] vals = new int[fields.length];
		for (int i = 0; i < fields.length; i++)
			vals[i] = Integer.parseInt(fields[i]);
		return vals;
	}

	   /**
     * Closes this input stream.
     */
    public void close() {
        scanner.close();  
    }
	
}
