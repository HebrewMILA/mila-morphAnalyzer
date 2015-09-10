package utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Stream;

/**
 * This static class is used for a uniform file handling, and to dodge Java's
 * awkward checked exceptions.
 * <p>
 * Error handling is uniform: dump trace and exit.
 * </p>
 *
 * @author Elazar
 */
public final class IO {
	private static final String encoding = "UTF8";

	public static List<String> readLinesFromFile(String filename) {
		return readLinesFromPath(Paths.get(filename));
	}

	private static BufferedWriter allocateWriter(String filename, boolean append) {
		try {
			return new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filename, append), encoding));
		} catch (UnsupportedEncodingException | FileNotFoundException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void writeLines(Iterable<String> col) {
		for (String line : col)
			System.out.println(line);
	}

	public static void writeLines(Iterable<String> col, String filename) {
		try (PrintWriter out = new PrintWriter(IO.allocateWriter(filename, false))) {
			for (String line : col)
				out.println(line);
		}
	}
	public static void updateSet(Collection<String> col, String filename) {
		Set<String> set = new TreeSet<>();
		set.addAll(col);
		set.addAll(readLinesFromFile(filename));
		writeLines(set, filename);
	}
	private IO() {
	}

	public static List<String> stdinAsList() {
		try {
			List<String> res = new ArrayList<>();
			try (BufferedReader in = new BufferedReader(new InputStreamReader(System.in, encoding))) {
				for (String line; (line = in.readLine()) != null; )
					res.add(line);
			}
			return res;
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static List<String> readLinesFromPath(Path path) {
		try {
			return java.nio.file.Files.readAllLines(path);
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static Stream<Path> listDir(String dirname) {
		try {
			return Files.list(Paths.get(dirname));
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	public static void outputStream(String filename, Consumer<OutputStream> f) {
		try (OutputStream out = new FileOutputStream(filename)) {
			f.accept(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static <T> T inputStream(String filename, Function<InputStream, T> f) {
		try (InputStream in = new FileInputStream(filename)) {
			return f.apply(in);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
	public static FileOutputStream getOutputStream(String filename) {
		try {
			return new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			throw new RuntimeException();
		}
	}
}
