package ner.hmm;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

import java.io.Serializable;
import java.util.*;
import static java.util.stream.Collectors.toList;
import utils.Serializer;

/**
 * Holds an HMM, serving basically as an input for the Viterbi algorithm.<br>
 * The model is serializable through @Serializer
 * 
 */

final class HmmModel implements Serializable {
	private static final long serialVersionUID = 1642164953287972051L;
	private Counter<Integer, Integer> transitions;
	private Counter<Integer, Integer> emissions;
	private double vocabularySize;

	private HmmModel(Counter<Integer, Integer> transitions, Counter<Integer, Integer> emissions, int vocabularySize) {
		this.transitions = transitions;
		this.emissions = emissions;
		this.vocabularySize = vocabularySize;
	}

	/**
	 * Get transition probability from nodeFrom to nodeTo.
	 *
	 * @return The probability or the Laplace correction if zero.
	 */
	public double getTransitionP(int nodeFrom, int nodeTo) {
		return transitions.get(nodeFrom, nodeTo) + 1.0 / HmmStateIndexer.getNumOfStates();
	}

	/**
	 * Get emission probability of context from node
	 *
	 * @return The probability or the Laplace correction if zero.
	 */
	public double getEmissionP(String context, int node) {
		return emissions.get(context.hashCode(), node) + 1.0 /  this.vocabularySize;
	}

	public static HmmModel create(HmmEventStream es) {
		// initialization here, otherwise kryo is exhausting the heap
		List<Point<Integer, Integer>> transitions = new ArrayList<>();
		List<Point<Integer, Integer>> emissions = new ArrayList<>();

		Set<String> vocabulary = new HashSet<>();
		do {
			int node1 = HmmStateIndexer.getSOSIndex();
			do {
				HmmEventStream.HmmEvent event = es.read();
				int node2 = HmmStateIndexer.getIndex(event.entity, event.pos);
				vocabulary.add(event.context);
				emissions.add(Point.create(event.context.hashCode(), node2));
				transitions.add(Point.create(node1, node2));
				node1 = node2;
			} while (es.hasNext());
			transitions.add(Point.create(node1, HmmStateIndexer.getEOSIndex()));
		} while (es.hasNextSentence());

		return new HmmModel(Counter.create(transitions), Counter.create(emissions), vocabulary.size());
	}

	/** Required by Kryo. Not intended to be used otherwise. */
	// @SuppressWarnings("WeakerAccess")
	public HmmModel() {
		// FOR KRYO
	}

	/**
	 * Persists the model.
	 *
	 * @param modelFile
	 *            the name of the model file
	 */
	public void writeToFile(String modelFile) {
		new Serializer().write(this, modelFile);
		/*
		try (FileOutputStream fileOut = new FileOutputStream(modelFile);
				ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
			out.writeObject(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
		*/
	}

	/**
	 * Load a model from file
	 *
	 * @param modelFile
	 *            the name of the model file
	 * @return a new instance of HmmModel
	 */
	public static HmmModel readFromFile(String modelFile) {
		return new Serializer().read(HmmModel.class, modelFile);
		/*
		try (FileInputStream fileIn = new FileInputStream(modelFile);
				ObjectInputStream in = new ObjectInputStream(fileIn)) {
			return (HmmModel) in.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		*/
	}

	public static void main(String[] argv) {
		//test Counter and Point:
		final Counter<String, Integer> c = Counter.create(Arrays.asList(Point.create("a", 1), Point.create("b", 2), Point.create("b", 1), Point.create("b", 2)));;
		System.out.println(c.get("a", 1)); //Expected: 0.5
		System.out.println(c.get("b", 2)); //Expected: 1.0
		System.out.println(c.get("c", 2)); //Expected: 0.0
		System.out.println(c.get("a", 2)); //Expected: 0.0
	}
}

final class Counter<From, To> implements Serializable {
	private static final long serialVersionUID = 5424615239376576708L;
	private Multiset<Point<From, To>> trans;
	private Multiset<To> total;

	Counter(List<Point<From, To>> points) {
		this.trans = HashMultiset.create(points);
		this.total = HashMultiset.create(points.stream().map(p -> p.y).collect(toList()));
	}

	static <X, Y> Counter<X, Y> create(List<Point<X, Y>> points) {
		return new Counter<X, Y>(points);
	}

	public double get(From x, To y) {
		int count = trans.count(Point.create(x, y));
		if (count == 0)
			return 0;
		return count / (double) total.count(y);
	}

	Counter() {
	}
}

final class Point<X, Y> implements Serializable {
	private static final long serialVersionUID = 1500162347488705862L;
	X x;
	Y y;

	static <X, Y> Point<X, Y> create(X from, Y to) {
		return new Point<>(from, to);
	}

	Point() {
	}

	Point(X from, Y to) {
		this.x = from;
		this.y = to;
	}

	@Override
	public int hashCode() {
		return x.hashCode() * 31 + y.hashCode();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public boolean equals(Object other) {
		return !(other == null || !(other instanceof Point)) && equals((Point) other);
	}

	boolean equals(Point<X, Y> other) {
		return x.equals(other.x) && y.equals(other.y);
	}
}
