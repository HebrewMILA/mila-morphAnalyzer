package lexicon.tools;

import java.util.*;
import java.net.*;
import lexicon.contents.EmptyContent;

/**
 * @author Danny Shacham
 */
public class LexiconUtils {
	protected static final String[] EXCEPTION_TYPES = { "adverb", "interjection", "interrogative", "noun", "preposition",
			"quantifier", "verb" };

	public static List searchForItems(String id, String dotted, String undotted, String transliterated) {
		try {
			dotted = URLEncoder.encode(dotted, "ISO-8859-1");

		} catch (Exception e) {
		}
		try {
			undotted = URLEncoder.encode(undotted, "ISO-8859-1");
		} catch (Exception e) {
		}
		try {
			transliterated = URLEncoder.encode(transliterated, "ISO-8859-1");
		} catch (Exception e) {
		}
		String sql = "SELECT id, dotted, undotted, transliterated, pos FROM item WHERE deleted=0";
		String cond = "";
		if (!id.equals("")) {
			cond += " id=" + id + " OR";
		} else {
			if (!dotted.equals("")) {
				cond += " dotted='" + dotted + "' OR";
			}
			if (!undotted.equals("")) {
				cond += " undotted='" + undotted + "' OR";
			}
			if (!transliterated.equals("")) {
				cond += " transliterated='" + transliterated + "' OR";
			}
		}
		if (cond.endsWith("OR")) {
			cond = cond.substring(0, cond.length() - 3);
		}
		if (!cond.equals("")) {
			sql = sql + " AND (" + cond + ")";
		}
		EmptyContent content = new EmptyContent();
		List result = content.getItems(sql);
		if (undotted != null && !undotted.equals("")) {
			for (int i = 0; i < EXCEPTION_TYPES.length; i++) {
				String exceptionType = EXCEPTION_TYPES[i];
				sql = "SELECT item.id, item.dotted, item.undotted, item.transliterated, pos";
				sql += " FROM item," + exceptionType + "_exception_type WHERE ";
				sql += exceptionType + "_exception_type.id=item.id AND action='add' AND ";
				sql += exceptionType + "_exception_type.undotted='" + undotted + "'";
				result.addAll(content.getItems(sql));
			}
		}
		return result;
	}

	/**
	 * Renders all the parameters from a ServletRequest object to a link or to
	 * hidden fields. The method can omit (skip) parameters from the output by using
	 * a list passed to it.
	 * <p>
	 * The link would be a string with the pattern of "&[name]=[value]..."<br>
	 * Hidden fields would take the pattern of:<br>
	 * "<input type="hidden" name="[name]" value="[value]">..."
	 * <p>
	 * An example of using this method would be:<br>
	 * <code>
	 * TreeSet omit = new TreeSet();<br>
	 * omit.add("username");<br>
	 * omit.add("password");<br>
	 * String queryString = QUtil.renderAllRequestParameters(request, omit, false);<br>
	 * </code>
	 * <p>
	 * 
	 * @param req
	 *           The request object.
	 * @param omitList
	 *           A TreeSet (simple unordered bag) which the skipped names of
	 *           parameters were inserted into.
	 * @param doHiddens
	 *           If set to <code>0</code>, the method would the result as
	 *           queryString If set to <code>1</code>, the method would produce
	 *           hidden If set to <code>2</code>, the method would produce the
	 *           result as jsp:forward parameters fields. otherwise, it woudl create
	 *           a link.
	 * @return
	 */
	// public static String renderAllRequestParameters(ServletRequest req, TreeSet
	// omitList, int doHiddens) {
	// if (req == null) {
	// return "";
	// }
	// Enumeration params = req.getParameterNames();
	// String hiddens = "";
	// String[] names = new String[] {""};
	// while (params.hasMoreElements()) {
	// String name = (String)params.nextElement();
	// names = req.getParameterValues(name);
	// for (int i=0; i< names.length; i++) {
	// try {
	// if (omitList == null || !omitList.contains(names[i])) {
	// String value = names[i];
	// if (value != null) {
	// value = value.replaceAll("\"", "&quot;");
	// }
	// if (doHiddens == 0) {
	// hiddens += "&";
	// hiddens += name +"="+ value;
	// }
	// if (doHiddens == 1) {
	// hiddens += "<input type=\"hidden\" name=\""+ name +"\"";
	// hiddens += " value=\""+ value +"\">\n";
	// }
	// if (doHiddens == 2) {
	// hiddens += "<jsp:param name=\""+name+"\" value=\""+value+"\"/>";
	// }
	// }
	// }
	// catch (Exception E) {}
	// }
	// }
	// return hiddens;
	// }
	public static String getTransliteration(String hebUTF) {
		if (hebUTF == null || hebUTF.length() == 0) {
			return "";
		}
		StringBuffer result = new StringBuffer();
		String[] heb = hebUTF.substring(1).split("%");
		for (int i = 0; i < heb.length; i++) {
			String eng = "";
			String extra = "";
			String hebToChange = "";
			if (heb[i].startsWith("D")) {
				if (heb[i + 1].length() == 2) {
					hebToChange = "%" + heb[i] + "%" + heb[++i];
					eng = Names.getHebToEng(hebToChange);
				} else {
					String temp = heb[i + 1].substring(0, 2);
					hebToChange = "%" + heb[i++] + "%" + temp;
					eng = Names.getHebToEng(hebToChange);
					extra = "-";
				}

			} else {
				eng = Names.getHebToEng("%" + heb[i]);
			}
			result.append(eng);
			result.append(extra);
		}
		return result.toString();
	}
}
