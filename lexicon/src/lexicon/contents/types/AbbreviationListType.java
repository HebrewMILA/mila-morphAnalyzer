package lexicon.contents.types;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import lexicon.contents.Connected;

public class AbbreviationListType extends Connected {

	int size = 0;
	private int id = 0;
	private String htmlLinkList = "";
	List<Integer> id_list = new ArrayList<Integer>(); // defines a growable
														// array for the list of
														// abbreviation
	private List<AbbreviationType> list = new ArrayList<AbbreviationType>();

	// --------------------------------------------------------------------------------------------------------

	public AbbreviationListType() {
	}

	// --------------------------------------------------------------------------------------------------------
	/*
	 * return the list
	 */
	public List<Integer> GetIdList() {
		return id_list;
	}

	// --------------------------------------------------------------------------------------------------------
	/*
	 * return the list size
	 */
	public int getSize() {
		return size;
	}

	// --------------------------------------------------------------------------------------------------------
	/*
	 * return a list item
	 */
	public int GetItem(int index) {
		return id_list.get(index);
	}

	// --------------------------------------------------------------------------------------------------------
	public void setId(int _id) {
		this.id = _id;
		if (id >= 0) {
			System.out.println("Loadin list for id = " + _id);
			String query = "SELECT * from abbreviation WHERE item_id = " + id;
			ResultSet rs = getData(query); // get the query results
			if (rs != null) {
				try {
					while (rs.next()) {
						int curr_id = rs.getInt("id");
						id_list.add(curr_id); // add the id
						System.out.println("Adding list item with id = "
								+ curr_id);
						AbbreviationType abbreviation = new AbbreviationType();
						abbreviation.Load(curr_id);
						getList().add(abbreviation);
						size++;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			releaseConnection();

			// Create the html link list
			// AbbreviationType abbreviation = new AbbreviationType();

			/*
			 * for (int id : id_list) { abbreviation.Load(id); // loads an
			 * abbreviation htmlLinkList +=
			 * "<a>"+abbreviation.GetSurface()+"</a><br/>"; }
			 */
		}
	}

	// --------------------------------------------------------------------------------------------------------
	public int getId() {
		return id;
	}

	// --------------------------------------------------------------------------------------------------------
	public void setHtmlLinkList(String htmlLinkList) {
		this.htmlLinkList = htmlLinkList;
	}

	// --------------------------------------------------------------------------------------------------------
	/*
	 * returns an html link representation of the list abbreviations
	 */
	public String getHtmlLinkList() {
		return htmlLinkList;
	}

	// --------------------------------------------------------------------------------------------------------
	public void setList(List<AbbreviationType> list) {
		this.list = list;
	}

	// --------------------------------------------------------------------------------------------------------
	public List<AbbreviationType> getList() {
		return list;
	}

	// --------------------------------------------------------------------------------------------------------
	/**
	 * used for testing
	 */
	public static void main(String[] args) {
		AbbreviationListType mylist = new AbbreviationListType();
		mylist.setId(100);

	}

}
