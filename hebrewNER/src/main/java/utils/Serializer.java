package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.KryoException;

/**
 * A simple wrapper around Kryo's serializer that handles exceptions uniformly,
 * in keeping with <u>Utils</u>
 *
 * @see IO IO
 * @see com.esotericsoftware.kryo.Kryo Kryo
 */

public class Serializer {
	private final Kryo k = new Kryo();

	public void write(Object t, String filename) {
		try (Output output = new Output(new FileOutputStream(filename))) {
			k.writeObject(output, t);
		} catch (KryoException | IOException e) {
			e.printStackTrace();
			System.exit(2);
		}
	}

	public <T> T read(Class<T> cls, String filename) {
		try (Input input = new Input(new FileInputStream(filename))) {
			return k.readObject(input, cls);
		} catch (KryoException | FileNotFoundException e) {
			e.printStackTrace();
			assert false;
		}
		throw new AssertionError("Could not read object from "+ filename);
	}
}
