package lexicon.contents;

import java.util.Calendar;

/**
 * @author Danny Shacham
 */
public class Log extends EmptyContent {
	int iid = 0;
	int uid = 0;
	int aid = 0;
	String action = "";
	
	public Log(int uid, int iid, int aid, String action) {
		this.uid = uid;
		this.iid = iid;
		this.aid = aid;
		this.action = action;
		TABLE = "log_actions";
		IDNAME = "lid";
	} 
	public int add() {
		java.util.Date rightNow = new java.util.Date(); 
		String sql = "INSERT INTO "+ getTableName() + " VALUES (";
		sql += getID();
		sql += ", "+uid;
		sql += ", "+iid;
		sql += ", '"+action+"'";
		sql += ", "+aid;
		sql += ", '"+formatDateSQL(rightNow)+"')";
		int feedback = execute(sql);
		id = getCurrentID(getTableName(), getIDName()); 
		return id;
	}
	/**
	 * Format a Date object, and returns the formatted string which is suited for
	 * Databse INSERT statements. The following formatting is supported: "YYYY-MM-DD HH:MM:SS.
	 * The method takes the date, adds 1 to the month digit (in the Date object, the
	 * months start from 0), render the date according to the SQL scheme, and 
	 * returns it as a string.<br>
	 * Example:
	 * <br><code>QUtil.formatDate(new java.util.Date());</code>
	 * Notice that this method is static.
	 * @return	The string that represent the Date
	 * @param		date	-	The date we want to format
	 */
	public static String formatDateSQL(java.util.Date date)	{
		if (date==null)	{
			return "";
		}
		Calendar theDate = Calendar.getInstance();
		theDate.setTime(date);
		String output = "";
		output += theDate.get(Calendar.YEAR) + "-";
		int month = theDate.get(Calendar.MONTH) + 1;
		output += month +"-";
		output += theDate.get(Calendar.DATE) + " ";
		
		output += theDate.get(Calendar.HOUR_OF_DAY) + ":";
		output += theDate.get(Calendar.MINUTE) + ":";
		output += theDate.get(Calendar.SECOND) + "";
		return output;
	}
}
