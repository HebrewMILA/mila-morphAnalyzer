package lexicon.contents;

import java.net.URLDecoder;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The base class for all the content items in the system, including ItemType,
 * MetadataType, User etc. The class enables the following functionality:
 * <p>
 * <ui>
 * <li>Basic content management functionality such as <code>open, update and
 * delete</code>.
 * <li>Get / Set operations for the content fields, which are cached in the
 * object after the <code>open()</code> operation.
 * <li>Database connection functionality, such as <code>getData(sql)</code> and
 * <code>execute</code>. Furthermore, the class handles such DB conenction
 * functionality as connection pooling management, direct connection management,
 * and connection configuration using <code>.ini</code> file. </ui>
 * <p>
 * All Content objects must supprot a specific DB structure:<br>
 * <ui>
 * <li>ID - an unsigned int that uniquely defines a content object.
 * <li>owner_id - an unsigned int that holds the ID of the owner of the content
 * object. The owner might be the user who created the object. </ui>
 * 
 * @author Danny Shacham
 * @version 1.0
 */
public abstract class Content extends Connected {

	// when ading a content from a utf8 file the ADD_ENCODING = "UTF-8";
	public static String ADD_ENCODING = "ISO-8859-1"; // "UTF-8"; //
	public static String UPDATE_ENCODING = "ISO-8859-1";
	/**
	 * Represent the ID of the content. The id name in the DB is different for
	 * different types of content, and it may be iid for item or eid for event
	 */
	public int id = 0;;

	/**
	 * Holds the content data from the DB. Each data field is accesible using
	 * set() and getString() / getInt() methods, using the same name as in the
	 * DB record.
	 */
	protected HashMap<String, Object> info = new HashMap<String, Object>();

	/**
	 * The name of the table of the current content, such as items, events etc.
	 * The field is set by the concrete class constructor.
	 */
	protected String TABLE = ""; // Name of the DB table
	/**
	 * The name of the id of the current content, such as iid, eid etc. The
	 * field is set by the concrete class constructor.
	 */
	protected String IDNAME = ""; // Name of the id column in DB

	/**
	 * A list of constants that refer to the SQL code of the different datatypes
	 * used by the Content item.
	 */
	protected final int STRINGCODE = 12;
	protected final int INTCODE = 4;
	protected final int DATECODE = 93;
	protected final int FLOATCODE = 6;
	protected final int BLOBCODE = -4;
	protected final int CHARCODE = 1;
	protected final int BIGINT = -5;
	protected final int DOUBLECODE = 8;
	protected final int LONGCODE = -5;
	protected final int REALCODE = 7;

	/**
	 * An empty implementation to the method add() methods in the different
	 * subclasses of Content. Connects to the DB, commits the different SQL
	 * statements and return feedback.
	 * 
	 * @param sql
	 *            - The SQL statement to be executed.
	 * @return Number of rows affected (0, if nothing happened, 1 if one row
	 *         added).
	 */
	public abstract int add();

	/**
	 * An empty implementation to the method add() methods in the different
	 * subclasses of Content. Connects to the DB, commits the different SQL
	 * statements and return feedback.
	 * 
	 * @param sql
	 *            - The SQL statement to be executed.
	 * @return Number of rows affected (0, if nothing happened, 1 if one row
	 *         added).
	 */
	public abstract int add(int id);

	/**
	 * The method compares another Content object's ID to the ID of itself.
	 * method.
	 * 
	 * @retuen -1 if the ID of self less than other,<br>
	 *         0 if same,<br>
	 *         1 if self greater than other.<br>
	 */
	public int compareID(Content other) {
		if (this.getID() >= other.getID()) {
			if (this.getID() == other.getID())
				return 0;
			return 1;
		} else
			return -1;
	}

	/**
	 * Checks to see if a content object contains a certain data field. Checks
	 * if the <code>info</code> variable holds the key.
	 * 
	 * @return <code>true</code> if the object contains this key.
	 *         <code>false</code> otherwise.
	 * @param key
	 *            The name of the field (should correspond with the DB name).
	 */
	public boolean contains(String fieldName) {
		return this.info.containsKey(fieldName);
	}

	// ------------------------------------------------------------------------------------------------
	/**
	 * preform an sql query
	 * 
	 * @return
	 */
	public ResultSet DoQuery(String sql) {
		ResultSet rs = null;
		rs = this.getData(sql);
		// releaseConnection();
		return rs;
	}

	/**
	 * get a list of actions from a given sql statment
	 * 
	 * @param sql
	 * @return
	 */
	public List<ActionType> getActions(String sql) {
		final ArrayList<ActionType> result = new ArrayList<ActionType>();
		try {
			ResultSet rs = null;
			rs = this.getData(sql);
			if (rs != null) {
				while (rs.next()) {
					final ActionType action = new ActionType(rs.getInt("aid"));
					String dotted = rs.getString("dotted");
					String undotted = rs.getString("undotted");
					try {
						if (dotted != null) {
							dotted = URLDecoder.decode(dotted, "UTF-8");
						}
						if (undotted != null) {
							undotted = URLDecoder.decode(undotted, "UTF-8");
						}
					} catch (final Exception e) {
					}
					action.setDotted(dotted);
					action.setUndotted(undotted);
					action.setTransliterated(rs.getString("transliterated"));
					action.setAction(rs.getString("action"));
					result.add(action);
				}
				rs.close();
			}
		} catch (final SQLException E) {
			E.printStackTrace();
		} finally {
			this.releaseConnection();
		}
		return result;
	}

	// ------------------------------------------------------------------------------------------------
	/**
	 * returns a list of Integers. Each elements is the id of the desiered
	 * Content
	 * 
	 * @param sql
	 * @param field
	 * @return
	 */
	public List<Integer> getContents(String sql, String field) {
		final ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			ResultSet rs = null;
			rs = this.getData(sql);
			if (rs != null) {
				while (rs.next()) {
					final Integer integer = new Integer(rs.getInt(field));
					result.add(integer);
				}
				rs.close();
			}

		} catch (final SQLException E) {
			E.printStackTrace();
		} finally {
			this.releaseConnection();
		}
		return result;
	}

	/**
	 * Gets a list on EmptyContents with the info populated according of the
	 * table's fields.
	 * 
	 * @param tableName
	 *            - the table from which we get the infrmation
	 * @param idName
	 *            - the name of the field using fot the "WHERE" clause
	 * @param id
	 *            the id to compare the idName to.
	 * @return
	 */
	public List<EmptyContent> getContents(String tableName, String idName, int id) {
		final ArrayList<EmptyContent> result = new ArrayList<EmptyContent>();
		final String sql = "SELECT * FROM " + tableName + " WHERE " + idName
				+ "=" + id;
		try {
			ResultSet rs = null;
			rs = this.getData(sql);
			if (rs != null) {
				while (rs.next()) {
					final EmptyContent content = new EmptyContent();
					content.openRS(rs);
					result.add(content);
				}
				rs.close();
			}
		} catch (final SQLException E) {
			E.printStackTrace();
		} finally {
			this.releaseConnection();
		}
		return result;
	}

	/**
	 * Returns last id in the DB. The temporary ID is used to request the object
	 * after an INSERT action happened. The temporary ID is the max ID in the
	 * table. In the furure we may want to make this method 'synchronized'
	 * 
	 * @param table
	 *            - the name of the requestred table
	 * @param idName
	 *            - the name of the coulumn title (id, sid...)
	 * @return The temporary ID of the object
	 */
	public synchronized int getCurrentID(String table, String idName) {
		int maxID = 0;
		final String sql = "select max(" + idName + ") as maxid from " + table;
		ResultSet rs = null;
		try {
			rs = this.getData(sql);
			if (rs == null)
				return 1;
			if (rs.next()) {
				maxID = rs.getInt("maxid");
			}
			rs.close();
			// In the future, we need to change that to adding a random int
		} catch (final SQLException E) {
			E.printStackTrace();
		} finally {
			this.releaseConnection();
		}
		return maxID;
	}

	/**
	 * Retreived a date object from the <code>info</code> HashMap object. If the
	 * method does not find a value by the passed key, then it returns null.
	 * 
	 * @param key
	 *            The key to retrieved the value by.
	 * @return A java.util.Date object.
	 * @see #info
	 */
	public java.util.Date getDate(String key) {
		java.util.Date date;
		date = (java.util.Date) this.info.get(key);
		return date;
	}

	/**
	 * Retreived a double value from the <code>info</code> HashMap object. If
	 * the method does not find a value by the passed key, then it returns 0.
	 * 
	 * @param key
	 *            The key to retrieved the value by.
	 * @return A double value.
	 * @see #info
	 */
	public double getDouble(String key) {
		double output;
		try {
			output = ((Double) this.info.get(key)).doubleValue();
		} catch (final NullPointerException E) {
			return 0;
		}
		return output;
	}

	public String GetExceptionHtml() {
		// TODO Auto-generated method stub
		return "empty";
	}

	/**
	 * Retreived a float value from the <code>info</code> HashMap object. If the
	 * method does not find a value by the passed key, then it returns 0.
	 * 
	 * @param key
	 *            The key to retrieved the value by.
	 * @return A float value.
	 * @see #info
	 */
	public float getFloat(String key) {
		float output;
		try {
			output = ((Float) this.info.get(key)).floatValue();
		} catch (final NullPointerException E) {
			return 0;
		}
		return output;
	}

	/********************** IContent Implemetations *********************/
	/**
	 * Returns the object ID. It is advised to use the method in order to
	 * retrieve the object's id, and to call directly to <code>content.id</code>
	 * .
	 * 
	 * @return The <code>id</code> field.
	 */
	public int getID() {
		return this.id;
	}

	/**
	 * Return the id name for the current content object. For example, returns
	 * "iid" for for the Item class.
	 * 
	 * @return The value of teh field IDNAME.
	 */
	public String getIDName() {
		return this.IDNAME;
	}

	/**
	 * Retreived an int value from the <code>info</code> HashMap object. If the
	 * method does not find a value by the passed key, then it returns 0.
	 * 
	 * @param key
	 *            The key to retrieved the value by.
	 * @return An int value.
	 * @see #info
	 */
	public int getInt(String key) {
		int output;
		try {
			output = ((Integer) this.info.get(key)).intValue();
		} catch (final NullPointerException E) {
			return 0;
		} catch (final ClassCastException E) {
			System.out
					.println("Error in Content.getInt() - field was not initialized as int");
			System.out.println("Error in: " + key);
			E.printStackTrace();
			return 0;
		}
		return output;
	}

	/**
	 * gets a list of ItemTypes from a given sql statment
	 * 
	 * @param sql
	 * @return
	 */
	public List<SearchItem> getItems(String sql) {
		final ArrayList<SearchItem> result = new ArrayList<SearchItem>();
		try {
			ResultSet rs = null;
			rs = this.getData(sql);
			if (rs != null) {
				while (rs.next()) {
					final SearchItem searched = new SearchItem(rs.getInt("id"));
					String dotted = rs.getString("dotted");
					String undotted = rs.getString("undotted");
					try {
						if (dotted != null) {
							dotted = URLDecoder.decode(dotted, "UTF-8");
						}
						if (undotted != null) {
							undotted = URLDecoder.decode(undotted, "UTF-8");
						}
					} catch (final Exception e) {
					}
					searched.setDotted(dotted);
					searched.setUndotted(undotted);
					searched.setTransliterated(rs.getString("transliterated"));
					searched.setPos(rs.getString("pos"));
					result.add(searched);
				}
				rs.close();
			}
		} catch (final SQLException E) {
			E.printStackTrace();
		} finally {
			this.releaseConnection();
		}
		return result;
	}

	/**
	 * Retreived an Object value from the <code>info</code> HashMap object.
	 * 
	 * @param key
	 *            The key to retrieved the value by.
	 * @return An Object object. The calling client should overide the class.
	 * @see #info
	 */
	public Object getObject(String key) {
		return this.info.get(key);
	}

	// ------------------------------------------------------------------------------------------------
	/**
	 * preform an sql query
	 * 
	 * @return
	 */
	public int GetQueryCount(String sql) {
		int count = -1;
		try {
			ResultSet rs = null;
			rs = this.getData(sql);
			if (rs != null) {
				rs.last();
				count = rs.getRow();
				rs.close();
			}

		} catch (final SQLException E) {
			E.printStackTrace();
		} finally {
			this.releaseConnection();
		}
		return count;
	}

	// ------------------------------------------------------------------------------------------------
	/**
	 * preform an sql query
	 * 
	 * @return
	 */
	public int GetQueryInt(String sql, String field) {
		int value = -1;
		try {
			ResultSet rs = null;
			rs = this.getData(sql);
			if (rs != null) {
				while (rs.next()) {
					value = rs.getInt(field);
				}
				rs.close();
			}

		} catch (final SQLException E) {
			E.printStackTrace();
		} finally {
			this.releaseConnection();
		}
		return value;
	}

	// ------------------------------------------------------------------------------------------------
	/**
	 * preform an sql query
	 * 
	 * @return
	 */
	public String GetQueryString(String sql, String field) {
		String value = "";
		try {
			ResultSet rs = null;
			rs = this.getData(sql);
			if (rs != null) {
				while (rs.next()) {
					value = rs.getString(field);
				}
				rs.close();
			}

		} catch (final SQLException E) {
			E.printStackTrace();
		} finally {
			this.releaseConnection();
		}
		return value;
	}

	/**
	 * Retreived a String value from the <code>info</code> HashMap object. If
	 * the method does not find a value by the passed key, then it returns an
	 * empty string ("").
	 * 
	 * @param key
	 *            The key to retrieved the value by.
	 * @return A String object.
	 * @see #info
	 */
	public String getString(String key) {
		String output = "";
		try {
			output = (String) this.info.get(key);
			if (output == null) {
				output = "";
			}
			output = URLDecoder.decode(output, "UTF-8");
			output = output.replaceAll("'", "&#39;");
			// output = output.replaceAll("\\\"", "&#34;");
		} catch (final NullPointerException E) {
			return null;
		} catch (final Exception e) {
		}
		return output;
	}

	/**
	 * Return the table name for the current content object. For example,
	 * returns "items" for for the Item class.
	 * 
	 * @return The value of teh field TABLENAME.
	 */
	public String getTableName() {
		return this.TABLE;
	}

	/**
	 * Returns true if the current object has a specific field in it's record.
	 * 
	 * @param key
	 *            The name of the field.
	 * @return <code>true</code> if the object has the field. <code>false</code>
	 *         otherwise.
	 */
	public boolean hasField(String key) {
		return this.info.containsKey(key);
	}

	public void load() {

	}

	/**
	 * The method opens a DB record using a passed ID, and populates the current
	 * object with the data from that record. The method uses
	 * <code>openRS</code> to populate the <code>info</code> and <code>id</code>
	 * variables with the data. Note that this method is highly "expensive"
	 * comparing to the <code>openRS</code> method.
	 * 
	 * @param requestedID
	 *            The id of the record to be opened.
	 * @see #openRS
	 */
	public void open(int requestedID) {
		if (requestedID == 0)
			return;
		final String sql = "SELECT * FROM " + this.getTableName() + " WHERE "
				+ this.getIDName() + " = " + requestedID;
		try {
			ResultSet rs = null;
			rs = this.getData(sql);
			if (rs != null) {
				if (rs.next()) {
					this.openRS(rs);
				}
				rs.close();
				this.id = requestedID;
			} else {
				// need to throw exception
				System.out.println("Lexicon: Could not open item no. "
						+ requestedID);
			}
		} catch (final SQLException E) {
			E.printStackTrace();
		} finally {
			this.releaseConnection();
			this.load();
		}
		return;
	}

	/**
	 * Populates the current object variables with the variables from the DB
	 * record. Translate the ResultSet record into a the HashMap
	 * <code>info</code> state variable. The HashMap saves the content object
	 * data fields, are gives access to them using the set, and get* (getInt,
	 * for example) methods.<br>
	 * The method populates the fields according to the field types. Up to now,
	 * the following fields are supported: String, blob (treated the same way as
	 * String), int, float, date. In order to support more field types, an
	 * update to the method is needed.
	 * 
	 * @param rs
	 *            The record set of the current object.
	 * @see #open
	 * @see #set
	 * @see java.sql.ResultSet#getMetaData
	 * @see java.sql.ResultSet#getTimestamp
	 */
	protected void openRS(ResultSet rs) {
		if (rs == null)
			return;
		this.info = new HashMap<String, Object>();
		int cType;
		try {
			final ResultSetMetaData meta = rs.getMetaData();
			if (!this.getIDName().equals("no_id")) {
				this.id = rs.getInt(this.getIDName());
			}
			for (int i = 1; i <= meta.getColumnCount(); i++) {
				cType = meta.getColumnType(i);
				if (cType == this.INTCODE) {
					this.set(meta.getColumnName(i), rs.getInt(i));
				} else if (cType == this.LONGCODE) {
					this.set(meta.getColumnName(i), rs.getLong(i));
				} else if (cType == this.DOUBLECODE) {
					this.set(meta.getColumnName(i), rs.getDouble(i));
				}
				// We treat Strings and Blobs the same way
				else if ((cType == this.STRINGCODE) || (cType == this.CHARCODE)) {
					String blobText = "";
					if (rs.getBytes(i) != null) {
						blobText = new String(rs.getBytes(i));
					}
					try {
						// blobText = URLEncoder.encode(blobText, "ISO-8859-1");
						// blobText = URLDecoder.decode(blobText, "ISO-8859-8");
						// blobText = URLDecoder.decode(blobText, "ISO-8859-8");
					} catch (final Exception e1) {
					}
					this.set(meta.getColumnName(i), blobText);
				} else if (cType == this.BLOBCODE) {
					String blobText = "";
					if (rs.getBytes(i) != null) {
						blobText = new String(rs.getBytes(i));
					}
					try {
						// blobText = URLDecoder.decode(blobText, "ISO-8859-8");
					} catch (final Exception e1) {
					}
					this.set(meta.getColumnName(i), blobText);
				}
				// Handling dates with the getTimestamp method
				else if (cType == this.DATECODE) {
					this.set(meta.getColumnName(i), rs.getTimestamp(i));
				} else if ((cType == this.FLOATCODE)
						|| (cType == this.REALCODE)) {
					this.set(meta.getColumnName(i), rs.getFloat(i));
				} else {
					System.out.println("ERROR in cTYPE (Content.OpenRS) "
							+ cType);
				}
				// System.out.println(meta.getColumnName(i) + " = " +
				// rs.getObject(i) + " ("+ cType +")");
			}
		} catch (final SQLException E) {
			E.printStackTrace();
		}
		return;
	}

	/**
	 * Delete from the DB all the records that are connected with the current
	 * object. Any overide should take care of deleting any sub tables such as
	 * exception_type, english, etc.
	 * 
	 * @return The accumalated number of rows that were affected from the
	 *         action. If 0, then nothing happened
	 */
	public int remove() {
		int feedback = 0;
		// Removing record from the content's table
		final String sql = "DELETE FROM " + this.getTableName() + " WHERE "
				+ this.getIDName() + " = " + this.getID();
		System.out.println("sql =" + sql);
		feedback = feedback + this.execute(sql);
		this.info = new HashMap<String, Object>();
		this.id = 0;
		return feedback;
	}

	// ------------------------------------------------------------------------------------------------
	/**
	 * removes a list of contents sharing the same idName1=id, and their own
	 * idNAme=idNaem2
	 * 
	 * @param tableName
	 * @param idName1
	 *            - the common field of the list
	 * @param idName2
	 *            - the idname of the the row to be deleted
	 * @param id
	 * @return
	 */
	protected int removeContents(String tableName, String idName1,
			String idName2, int id) {
		final int result = 0;
		final List<EmptyContent> list = this.getContents(tableName, idName1, id);
		for (int i = 0; i < list.size(); i++) {
			final Content content = list.get(i);
			content.id = content.getInt(idName2);
			content.TABLE = tableName;
			content.IDNAME = idName2;
			content.remove();
		}
		return result;
	}

	/**
	 * Sets a double value in the <code>info</code> HashMap object. If the
	 * insert operation on the HashMap throws a <code>
	 * NullPointerException</code> exception, then an error message is printed
	 * to System.out.
	 * 
	 * @param key
	 *            The key to to set the value by.
	 * @param value
	 *            The value to be set.
	 * @see #info
	 */
	public void set(String key, double value) {
		final Double insert = new Double(value);
		try {
			this.info.put(key, insert);
		} catch (final NullPointerException E) {
			System.out.println("Exception: " + E.getMessage());
			System.out.println("key:       " + key);
			System.out.println("value:     " + value);
		}
		return;
	}

	/**
	 * Sets a float value in the <code>info</code> HashMap object. If the insert
	 * operation on the HashMap throws a <code>
	 * NullPointerException</code> exception, then an error message is printed
	 * to System.out.
	 * 
	 * @param key
	 *            The key to to set the value by.
	 * @see #info
	 */
	public void set(String key, float value) {
		final Float insert = new Float(value);
		try {
			this.info.put(key, insert);
		} catch (final NullPointerException E) {
			System.out.println("Exception: " + E.getMessage());
			System.out.println("key:       " + key);
			System.out.println("value:     " + value);
		}
		return;
	}

	/**
	 * Sets an int value in the <code>info</code> HashMap object. If the insert
	 * operation on the HashMap throws a <code>
	 * NullPointerException</code> exception, then an error message is printed
	 * to System.out.
	 * 
	 * @param key
	 *            The key to to set the value by.
	 * @param value
	 *            The value to be set.
	 * @see #info
	 */
	public void set(String key, int value) {
		final Integer insert = new Integer(value);
		try {
			this.info.put(key, insert);
		} catch (final NullPointerException E) {
			System.out.println("Exception: " + E.getMessage());
			System.out.println("key:       " + key);
			System.out.println("value:     " + value);
		}
		return;
	}

	/**
	 * Sets a Date value in the <code>info</code> HashMap object. If the insert
	 * operation on the HashMap throws a <code>
	 * NullPointerException</code> exception, then an error message is printed
	 * to System.out.
	 * 
	 * @param value
	 *            The value to be set.
	 * @param key
	 *            The key to to set the value by.
	 * @see #info
	 */
	public void set(String key, java.util.Date value) {
		try {
			java.util.Date insert = new java.util.Date();
			if (value != null) {
				insert = value;
			}
			this.info.put(key, insert);
		} catch (final NullPointerException E) {
			System.out.println("Exception: " + E.getMessage());
			System.out.println("key:       " + key);
			System.out.println("value:     " + value);
		}
		return;
	}

	/**
	 * Sets a String value in the <code>info</code> HashMap object. If the
	 * passed <code>value</code> is null, then the empty string is inserted. If
	 * the insert operation on the HashMap throws a <code>
	 * NullPointerException</code> exception, then an error message is printed
	 * to System.out.
	 * 
	 * @param key
	 *            The key to to set the value by.
	 * @param value
	 *            The value to be set.
	 * @see #info
	 */
	public void set(String key, String value) {
		String insert = "";
		if (value != null) {
			insert = value;
		}
		try {
			// value = URLEncoder.encode(value, Content.ADD_ENCODING);
			this.info.put(key, insert);
		} catch (final NullPointerException E) {
			System.out.println("Exception: " + E.getMessage());
			System.out.println("key:       " + key);
			System.out.println("value:     " + value);
		} catch (final Exception e) {
		}
		return;
	}

	/**
	 * Updates the current record in the DB, so it would resemble the current
	 * object state. The method updates all the fields of the Content.
	 * 
	 * @see #info
	 * @see ResultSet#updateRow
	 * @see #openRS
	 * @return The number of rows that were affected from the action. If 0, then
	 *         nothing happened
	 */
	public abstract int update();

}