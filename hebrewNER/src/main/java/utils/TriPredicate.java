package utils;

@FunctionalInterface
public interface TriPredicate<T1, T2, T3>  {
	boolean test(T1 w, T2 nw, T3 nnw);
}