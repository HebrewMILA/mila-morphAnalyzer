package sql;

import java.io.UnsupportedEncodingException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InflectionTable implements Iterable<Inflection> {
	final List<Inflection> table;
	
	public static List<Inflection> fetchHelper(String lexiconId) {
		try (final PreparedStatement statement = SqlDB.getGeneratorPoolConnection()
				.prepareStatement("SELECT * FROM inflections "
						+ " WHERE register='formal' AND spelling = 'standard' "
						+ " 	AND baseLexiconPointer =  ? "
						+ " ORDER BY id")) {
			statement.setObject(1, lexiconId);
			try (final ResultSet rs = statement.executeQuery()) {
				List<Inflection> res = new ArrayList<>();
				while (rs.next()) {
					res.add(new Inflection(rs));
				}
				return Collections.unmodifiableList(res);
			}
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
	
	public static InflectionTable fetch(String lexiconId) {
		return new InflectionTable(fetchHelper(lexiconId));
	}
	
	private InflectionTable(List<Inflection> table) {
		this.table = Collections.unmodifiableList(table);
	}
	
	@Override
	public Iterator<Inflection> iterator() {
		return table.iterator();
	}
	
	public Stream<Inflection> stream() {
		return table.stream();
	}
	
	Inflection.Where all(Inflection.Where... ps) {
		return x -> Arrays.asList(ps).stream().allMatch(p->p.test(x));
	}
	public InflectionTable where(Inflection.Where ... ps) {
		return new InflectionTable(stream().filter(all(ps)).collect(Collectors.toList()));
	}
	
	public String joinSurface() {
		return stream().map(x->x.surface).collect(Collectors.joining(", "));
	}
	public String joinSurface(Vector<String> outVec) {
		outVec.addAll(stream().map(x->x.surface).collect(Collectors.toList()));
		return outVec.stream().collect(Collectors.joining(", "));
	}
}
