package ner.maxent;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

import opennlp.tools.ml.model.Event;
import opennlp.tools.util.ObjectStream;
import tagging.Sentence;
import tagging.TokenEntity;

/**
 * This event stream is used by the train class for training maxent model from a
 * file containing the training set.<br>
 * The stream returns one Event at a time. The context of an event is generated
 * by MaxEntContextGenerator.
 */
class MaxentEventStream implements ObjectStream<Event> {

	private final Iterator<Collection<TokenEntity>> input;
	private int curSentIter = 0;
	private List<TokenEntity> currList = new ArrayList<>();

	public MaxentEventStream(Iterable<Collection<TokenEntity>> iterable) {
		input = iterable.iterator();
	}

	@Override
	public Event read() {
		// TODO: with token.id, this part might be much simpler
		if (curSentIter == currList.size()) {
			if (!input.hasNext())
				return null;
			currList = new ArrayList<>(input.next());
			curSentIter = 0;
		}
		TokenEntity wet = currList.get(curSentIter);

		String[] context = MaxentContextGenerator.getContext(curSentIter,
				extract(currList, x->x.surface),
				extract(currList, x->x.entity),
				new Sentence(currList), wet.id == 1);
		Event next = new Event(wet.entity, context);
		curSentIter++;
		return next;
	}

/*
	@Override
	public boolean hasNext() {
		return curSentIter < currList.size() || input.hasNext();
	}*/

	/** @return [we.entity for we in wees] */
	private static String[] extract(
			Collection<? extends TokenEntity> wees, Function<TokenEntity, String> f) {
		return wees.stream().map(f).toArray(size -> new String[size]);
	}

	@Override
	public void close() { 	}

	@Override
	public void reset() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}
}
