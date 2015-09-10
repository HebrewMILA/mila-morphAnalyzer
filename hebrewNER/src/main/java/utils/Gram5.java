package utils;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

public class Gram5<T> implements Iterable<T> {
	public final T ppw, pw, w, nw, nnw;

	public Gram5(T ppw, T pw, T w, T nw, T nnw) {
		this.ppw = ppw;
		this.pw = pw;
		this.w = w;
		this.nw = nw;
		this.nnw = nnw;
	}

	public <Q> Gram5<Q> map(Function<T, Q> map) {
		return new Gram5<>(map.apply(ppw), map.apply(pw), map.apply(w), map.apply(nw), map.apply(nnw));
	}
	
	public List<String> subtermsByLength() {
		String ppw=""+this.ppw, pw=""+this.pw, w=""+this.w,nw=""+this.nw,nnw=""+this.nnw; 
		return Arrays.asList(
			String.join(" ", ppw, pw, w),
			String.join(" ", pw, w, nw),
			String.join(" ", w, nw, nnw),
			String.join(" ", pw, w),
			String.join(" ", w, nw),
			String.join(" ", "", w)
		);
	}


	public List<List<T>> rawSubtermsByLength() {
		return Arrays.asList(
				Arrays.asList(ppw, pw, w),
				Arrays.asList(pw, w, nw),
				Arrays.asList(w, nw, nnw),
				Arrays.asList(pw, w),
				Arrays.asList(w, nw),
				Arrays.asList(w)
				);
	}

	@Override
	public Iterator<T> iterator() {
		return Arrays.asList(ppw, pw, w, nw, nnw).iterator();
	}	
}
