package sql;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class SimpleQuerier implements AutoCloseable {
	private final String lexiconId;
	private final Connection connection;
	
	public SimpleQuerier(String lexiconId) {
		this.lexiconId = lexiconId;
		connection = SqlDB.getGeneratorPoolConnection();
	}	
	
	public List<Map<String, String>> queryFields(String query, String... fields) {
		try (final PreparedStatement statement = connection
				.prepareStatement(query)) {
			statement.setObject(1, lexiconId);
			List<Map<String, String>> res = new ArrayList<>();
			try (final ResultSet rs = statement.executeQuery()) {
				while (rs.next()) {
					Map<String, String> map = new HashMap<>();
					for (String f : fields)
						map.put(f, URLDecoder.decode(rs.getString(f), "UTF-8"));
					res.add(map);
				}
			}
			return res;
		} catch (SQLException | UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return new ArrayList<>();
	}
	
	public String querySurface(String query, Vector<String> outVec) {
		String res = "";
		for (Map<String, String> rs : queryFields(query, "surface")) {
			outVec.add(rs.get("surface"));
			if (!res.equals(""))
				res += ", ";
			res += rs.get("surface");
		}
		return res;
	}
	
	public String querySurface(String query) {
		return  querySurface(query, new Vector<>());
	}
	
	@Override
	public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
