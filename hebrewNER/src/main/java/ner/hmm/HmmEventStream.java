package ner.hmm;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import opennlp.tools.ml.model.Event;
import opennlp.tools.util.ObjectStream;
import tagging.Sentence;
import tagging.TokenEntity;

/**
 * Used by HmmTrainer for training HMM model from a file containing the training
 * set.<br>
 * The stream returns one Event at a time. The context of an event is generated
 * by HmmContextGenerator and part-of-speech tagger.
 */
class HmmEventStream implements ObjectStream<Event> {

	private final Iterator<Collection<TokenEntity>> input;
	private int curSentIter = 0;

	private List<TokenEntity> currList = new ArrayList<>();

	public HmmEventStream(Iterable<Collection<TokenEntity>> input) {
		this.input = input.iterator();
	}

	static class HmmEvent extends Event {

		final String entity;
		final String pos;
		final String context;

		private HmmEvent(String entity, String pos, String context) {
			super(entity, new String[] { context });
			this.entity = entity;
			this.pos = pos;
			this.context = context;
		}

	}

	/**
	 * @return The next event in the training file.
	 */
	@Override
	public HmmEvent read() {
		if (curSentIter == currList.size()) {
			currList = new ArrayList<>(input.next());
			curSentIter = 0;
		}
		String context = HmmContextGenerator.getContext(new Sentence(currList),
				curSentIter);
		TokenEntity wet = currList.get(curSentIter);
		curSentIter++;
		return new HmmEvent(wet.entity, wet.HMMPos, context);
	}

	/*
	@Override
	 */
	boolean hasNext() {
		return curSentIter < currList.size();
	}

	public boolean hasNextSentence() {
		return !hasNext() && input.hasNext();
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reset() throws IOException, UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
