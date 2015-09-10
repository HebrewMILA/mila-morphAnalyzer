package ner.hmm;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import ner.Config;
import ner.Entity;
import utils.IO;

/**
 * Class for indexing the nodes of the HMM.<br>
 * Each state is a product of a part of speech tag and a named entity tag. <br>
 * Special nodes are start and end of sentence.<br>
 */
class HmmStateIndexer {

	private static final Map<State, Integer> stateToIndex = new Hashtable<>();
	private static final Map<Integer, State> indexToState = new Hashtable<>();

	/**
	 * Creates a new NodeIndexer instance by indexing the nodes
	 */
	static {
		// create start of sentence node
		addBidirectionalMapping(State.SOS, 0);

		// create end of sentence node
		addBidirectionalMapping(State.EOS, 1);

		int numOfNodes = 2;
		// creating the product nodes

		for (String pos : IO.readLinesFromFile(Config.POS_LIST)) {
			if (pos.trim().isEmpty())
				continue;
			for (Entity e : Entity.values())
				for (String pref : e.getPrefixes())
					addBidirectionalMapping(new State(pref + e, pos),
							numOfNodes++);
		}

	}

	private static void addBidirectionalMapping(State node, int numOfNodes) {
		stateToIndex.put(node, numOfNodes);
		indexToState.put(numOfNodes, node);
	}

	static int getNumOfStates() {
		return indexToState.size();
	}

	/**
	 * @return The index of the start of sentence node
	 */
	static int getSOSIndex() {
		return 0;
	}

	/**
	 * @return The index of the end of sentence node
	 */
	static int getEOSIndex() {
		return 1;
	}

	/**
	 * @param entity
	 *            the named entity tag
	 * @param pos
	 *            the part of speech tag in its long representation
	 * @return The index of the node created by their product
	 * @throws HmmUnexpectedAttribute
	 */
	static int getIndex(String entity, String pos) throws HmmUnexpectedAttribute {
		Integer node = stateToIndex.get(new State(entity, pos));
		if (node == null)
			node = stateToIndex.get(new State("O", pos));
		if (node != null)
			return node;
		throw new HmmUnexpectedAttribute(pos);
	}

	/**
	 * @param index
	 *            node index
	 * @return The named entity tag of this node
	 */
	private static String getNodeEntity(int index) {
		return indexToState.get(index).entity;
	}

	/**
	 * @param index
	 *            node index
	 * @return The pos tag of this node in its long representation
	 */
	private static String getNodePos(int index) {
		return indexToState.get(index).pos;
	}

	/**
	 * Checks if this node pos tag is wpos
	 */
	static boolean isPosNode(String wpos, int nodeNumber) {
		return nodeNumber == getSOSIndex() && wpos.isEmpty()
				|| getNodePos(nodeNumber).equals(wpos);
	}

	/**
	 * Checks if fromNode and toNode are a valid sequence
	 */
	static boolean validSequence(int fromNode, int toNode) {
		String toTag = getNodeEntity(toNode);
		if (toTag.startsWith(Config.MIDDLE)) {
			if (fromNode == getSOSIndex())
				return false;
			Entity fromEntity = Entity.fromPrefixed(getNodeEntity(fromNode));
			return fromEntity != Entity.O
					&& fromEntity == Entity.fromPrefixed(toTag);
		}
		return true;
	}

	private static class State {
		final String entity;
		final String pos;

		State(String entity, String pos) {
			this.entity = entity;
			this.pos = pos;
		}

		final static State SOS = new State("O", "SOS");
		final static State EOS = new State("O", "EOS");

		public boolean equals(State other) {
			return pos.equals(other.pos) && entity.equals(other.entity);
		}

		@Override
		public boolean equals(Object other) {
			return !(other == null || !(other instanceof State)) && equals((State) other);
		}

		@Override
		public int hashCode() {
			return pos.hashCode() ^ entity.hashCode();
		}
	}

	/**
	 * @param seq
	 * @return sequence of tags created by the path with the maximum probability
	 */
	static List<String> getSequenceTags(int[] seq) {
		List<String> tags = new ArrayList<>();
		for (int s : seq)
			tags.add(HmmStateIndexer.getNodeEntity(s));
		return tags;
	}
	
	public static void main(String[] argv) {
		//test Node:
		final Map<State, Integer> t = new Hashtable<>();
		t.put(new State("A", "B"), 3);
		t.put(new State("A", "BB"), 4);
		t.remove(new State("A", "BB"));
		System.out.println(t.get(new State("A", "B"))); //Expected: 3
		System.out.println(t.get(new State("A", "BB"))); //Expected: null
	}
}
