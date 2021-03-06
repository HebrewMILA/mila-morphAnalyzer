﻿﻿<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"
	import="lexicon.contents.pos.MultiWordNounAdjectiveLexiconType"
	import="lexicon.contents.types.ItemType" import="java.util.List"
	import="java.sql.*" import="java.util.StringTokenizer"%>
<jsp:useBean id="db" scope="request" class="autoFeed.AutoFeedBean" />

<jsp:setProperty name="db" property="*" />
<%
	MultiWordNounAdjectiveLexiconType multiWordNounAdjective = new MultiWordNounAdjectiveLexiconType();

	ItemType item = (ItemType) session.getAttribute("item");
	int id = Integer.parseInt((String) session.getAttribute("id"));
	boolean newItem = Boolean.valueOf((String) session
			.getAttribute("newItem"));
	List changeGroup = null;

	if (!newItem && item.getMultiWordNounAdjective() != null && id != 0) {
		multiWordNounAdjective.open(id);
		changeGroup = multiWordNounAdjective.getContents(
				"multiWordNounAdjective_exception_type", "id", id);
		session.setAttribute("changeGroup", changeGroup);

	}
	if (request.getParameter("pos") != null) {
		if (request.getParameter("pos")
				.equals("multiWordNounAdjective")) {
			if (request.getParameter("multiWordNounAdjective_pos") != null) {
				multiWordNounAdjective.setMwPos(request
						.getParameter("multiWordNounAdjective_pos"));
			}
			if (request.getParameter("multiWordNounAdjective_gender") != null) {
				multiWordNounAdjective.setGender(request
						.getParameter("multiWordNounAdjective_gender"));
			}
			if (request.getParameter("multiWordNounAdjective_number") != null) {
				multiWordNounAdjective.setNumber(request
						.getParameter("multiWordNounAdjective_number"));
			}

			if (request
					.getParameter("multiWordNounAdjective_feminine_noun") != null) {
				multiWordNounAdjective
						.setFeminineNoun(request
								.getParameter("multiWordNounAdjective_feminine_noun"));
			}

			if (request
					.getParameter("multiWordNounAdjective_feminine_adjective") != null) {
				multiWordNounAdjective
						.setFeminineAdjective(request
								.getParameter("multiWordNounAdjective_feminine_adjective"));
			}

			if (request
					.getParameter("multiWordNounAdjective_plural_male_noun") != null) {
				multiWordNounAdjective
						.setPluralMaleNoun(request
								.getParameter("multiWordNounAdjective_plural_male_noun"));
			}

			if (request
					.getParameter("multiWordNounAdjective_plural_male_adjective") != null) {
				multiWordNounAdjective
						.setPluralMaleAdjective(request
								.getParameter("multiWordNounAdjective_plural_male_adjective"));
			}

			if (request
					.getParameter("multiWordNounAdjective_plural_female_noun") != null) {
				multiWordNounAdjective
						.setPluralFemaleNoun(request
								.getParameter("multiWordNounAdjective_plural_female_noun"));
			}

			if (request
					.getParameter("multiWordNounAdjective_plural_female_adjective") != null) {
				multiWordNounAdjective
						.setPluralFemaleAdjective(request
								.getParameter("multiWordNounAdjective_plural_female_adjective"));
			}

			if (request.getParameter("multiWordNounAdjective_type") != null) {
				multiWordNounAdjective.setType(request
						.getParameter("multiWordNounAdjective_type"));
			}

			if (request
					.getParameter("multiWordNounAdjective_definiteness") != null) {
				multiWordNounAdjective
						.setDefiniteness(request
								.getParameter("multiWordNounAdjective_definiteness"));
			}

			if (request
					.getParameter("multiWordNounAdjective_consecutive") != null) {
				multiWordNounAdjective.setConsecutive(request
						.getParameter(
								"multiWordNounAdjective_consecutive")
						.equals("1"));
			}
			if (request
					.getParameter("multiWordNounAdjective_inflectPossessiveS") != null) {
				multiWordNounAdjective
						.setInflectPossessiveS(request
								.getParameter(
										"multiWordNounAdjective_inflectPossessiveS")
								.equals("1"));
			}
			if (request
					.getParameter("multiWordNounAdjective_inflectPossessiveP") != null) {
				multiWordNounAdjective
						.setInflectPossessiveP(request
								.getParameter(
										"multiWordNounAdjective_inflectPossessiveP")
								.equals("1"));
			}

			if (request
					.getParameter("multiWordNounAdjective_inflectionBase") != null) {
				multiWordNounAdjective
						.setInflectionBase(request
								.getParameter(
										"multiWordNounAdjective_inflectionBase")
								.trim());
			}

			if (request.getParameter("actionButton") != null) {
				String transliterated = item.getTransliterated();
				StringTokenizer st = new StringTokenizer(transliterated);
				String transliterated1 = st.nextToken();
				Connection connection = null;
				String feedId = "";
				String pos = multiWordNounAdjective.getMwPos();
				ResultSet rs = null;
				try {
					connection = db.connect();
					PreparedStatement pstatement = null;

					if (pos.equals("adjective")) {
						multiWordNounAdjective.setType("AN");
						multiWordNounAdjective
								.setDefiniteness("internal and external");
						pstatement = connection
								.prepareStatement("SELECT *  FROM item, adjective where item.id=adjective.id and transliterated=? and pos='adjective' ");
						pstatement.setObject(1, transliterated1);
					} else {
						pstatement = connection
								.prepareStatement("SELECT *  FROM item, noun where item.id=noun.id and transliterated=? and pos='noun' ");
						pstatement.setObject(1, transliterated1);
					}

					rs = pstatement.executeQuery();

					while (rs.next()) {
						String gender = rs.getString("gender");
						String number = rs.getString("number");
						String feminine_noun = rs
								.getString("feminine_noun");
						String feminine_adjective = rs
								.getString("feminine_adjective");
						String plural_male_noun = rs
								.getString("plural_male_noun");
						String plural_male_adjective = rs
								.getString("plural_male_adjective");
						String plural_female_noun = rs
								.getString("plural_female_noun");
						String plural_female_adjective = rs
								.getString("plural_female_adjective");
						feedId = rs.getString("id");

						multiWordNounAdjective.setGender(gender);
						multiWordNounAdjective.setNumber(number);
						multiWordNounAdjective
								.setFeminineNoun(feminine_noun);
						multiWordNounAdjective
								.setFeminineAdjective(feminine_adjective);
						multiWordNounAdjective
								.setPluralMaleNoun(plural_male_noun);
						multiWordNounAdjective
								.setPluralMaleAdjective(plural_male_adjective);
						multiWordNounAdjective
								.setPluralFemaleNoun(plural_female_noun);
						multiWordNounAdjective
								.setPluralFemaleAdjective(plural_female_adjective);
						//multiWordNoun.setInflectPossessiveS(rs.getInt("inflectPossessiveS")==1);
						//multiWordNoun.setInflectPossessiveP(rs.getInt("inflectPossessiveP")==1);							
					}

					pstatement = connection
							.prepareStatement("SELECT *  FROM noun_exception_type  where noun_exception_type.id=? and construct=0");
					pstatement.setObject(1, feedId);
					rs = pstatement.executeQuery();
					while (rs.next()) {
						String exceptionTransliterated = rs
								.getString("transliterated");
						String exceptionDotted = rs.getString("dotted");
						String exceptionGender = rs.getString("gender");
						String exceptionNumber = rs.getString("number");
						String exceptionPlural = rs.getString("plural");
						String exceptionFeminine = rs
								.getString("feminine");
						String exceptionAction = rs.getString("action");
						String exceptionUndotted = rs
								.getString("undotted");
						String exceptionRegister = rs
								.getString("register");
						String exceptionSpelling = rs
								.getString("spelling");

						pstatement = connection
								.prepareStatement("SELECT *  FROM   multiWordNounAdjective_exception_type where multiWordNounAdjective_exception_type.id=? and undotted=? and transliterated=? and dotted=? and register=? and spelling=? and gender=? and number=? and feminine=? and plural=? and action=?");
						pstatement.setInt(1, id);
						pstatement.setString(2, exceptionUndotted);
						pstatement
								.setString(3, exceptionTransliterated);
						pstatement.setString(4, exceptionDotted);
						pstatement.setString(5, exceptionRegister);
						pstatement.setString(6, exceptionSpelling);
						pstatement.setString(7, exceptionGender);
						pstatement.setString(8, exceptionNumber);
						pstatement.setString(9, exceptionFeminine);
						pstatement.setString(10, exceptionPlural);
						pstatement.setString(11, exceptionAction);

						rs = pstatement.executeQuery();
						if (!rs.next()) {
							pstatement = connection
									.prepareStatement("insert into multiWordNounAdjective_exception_type (aid, id, undotted , transliterated, dotted, register, spelling, gender, number, feminine, plural,action) values (?,? ,? ,?,?,?,?,?,?,?,?,?)");

							String sqlInsert = "insert into multiWordNounAdjective_exception_type (aid, id, undotted , transliterated, dotted, register, spelling, gender, number, feminine, plural,action) values (?,? ,? ,?,?,?,?,?,?,?,?,?) ";
							pstatement.setInt(1, 0);
							pstatement.setInt(2, id);
							pstatement.setString(3, exceptionUndotted);
							pstatement.setString(4,
									exceptionTransliterated);
							pstatement.setString(5, exceptionDotted);
							pstatement.setString(6, exceptionRegister);
							pstatement.setString(7, exceptionSpelling);
							pstatement.setString(8, exceptionGender);
							pstatement.setString(9, exceptionNumber);
							pstatement.setString(10, exceptionFeminine);
							pstatement.setString(11, exceptionPlural);
							pstatement.setString(12, exceptionAction);
							pstatement.executeUpdate();
						}
					}
					connection.close();

				} catch (Exception e) {
					System.out.println(e.getMessage());
				}

			}

			if (newItem) {
				multiWordNounAdjective.add(item.getID());
			} else {
				multiWordNounAdjective.update();
			}
			item.open(item.getID());
			multiWordNounAdjective.open(item.getID());
		}
	}
%>
<div id='multiWordNounAdjective_div' style="display: none;">
	<table>

		<tr>
			<td>
				<hr>
			</td>
		</tr>

		<tr>
			<td><input name="actionButton" type=Submit
				value="הזן תכונות אוטומטית"
				onClick="alert('יש להכנס שוב לפריט על מנת לראות את יוצאי הדופן במידה וקיימים'); return true;">
			</td>
		</tr>

		<tr>
			<td>חלק דיבר:</td>
			<td><select name='multiWordNounAdjective_pos'>
					<option value='noun'
						<%if (multiWordNounAdjective.getMwPos().equals("noun"))
				out.print("SELECTED");%>>noun</option>
					<!-- <option value='adjective' <%if (multiWordNounAdjective.getMwPos().equals("adjective"))
				out.print("SELECTED");%>>adjective</option> -->
			</select></td>
		</tr>

		<tr>
			<td>מין:</td>
			<td><select name='multiWordNounAdjective_gender'>
					<option value='masculine'
						<%if (multiWordNounAdjective.getGender().equals("masculine"))
				out.print("SELECTED");%>>זכר</option>
					<option value='feminine'
						<%if (multiWordNounAdjective.getGender().equals("feminine"))
				out.print("SELECTED");%>>נקבה</option>
					<option value='unspecified'
						<%if (multiWordNounAdjective.getGender().equals("unspecified"))
				out.print("SELECTED");%>>לא
						ידוע</option>
			</select></td>
		</tr>


		<tr>
			<td>מספר:</td>
			<td><select name='multiWordNounAdjective_number'>
					<option value='singular'
						<%if (multiWordNounAdjective.getNumber().equals("singular"))
				out.print("SELECTED");%>>יחיד</option>
					<option value='plural'
						<%if (multiWordNounAdjective.getNumber().equals("plural"))
				out.print("SELECTED");%>>רבים</option>
					<option value='unspecified'
						<%if (multiWordNounAdjective.getNumber().equals("unspecified"))
				out.print("SELECTED");%>>לא
						ידוע</option>
			</select></td>
		</tr>


		<tr>
			<td>צורן נקבה : (noun)</td>
			<td><select name='multiWordNounAdjective_feminine_noun'>
					<option value='h'
						<%if (multiWordNounAdjective.getFeminineNoun().equals("h"))
				out.print("SELECTED");%>>ה</option>
					<option value='it'
						<%if (multiWordNounAdjective.getFeminineNoun().equals("it"))
				out.print("SELECTED");%>>ית</option>
					<option value='t'
						<%if (multiWordNounAdjective.getFeminineNoun().equals("t"))
				out.print("SELECTED");%>>ת</option>
					<option value='unspecified'
						<%if (multiWordNounAdjective.getFeminineNoun().equals("unspecified"))
				out.print("SELECTED");%>>לא
						מוגדר</option>
			</select></td>
		</tr>

		<tr>
			<td>צורן נקבה: (adjective)</td>
			<td><select name='multiWordNounAdjective_feminine_adjective'>
					<option value='h'
						<%if (multiWordNounAdjective.getFeminineAdjective().equals("h"))
				out.print("SELECTED");%>>ה</option>
					<option value='it'
						<%if (multiWordNounAdjective.getFeminineAdjective().equals("it"))
				out.print("SELECTED");%>>ית</option>
					<option value='t'
						<%if (multiWordNounAdjective.getFeminineAdjective().equals("t"))
				out.print("SELECTED");%>>ת</option>
					<option value='unspecified'
						<%if (multiWordNounAdjective.getFeminineAdjective().equals(
					"unspecified"))
				out.print("SELECTED");%>>לא
						מוגדר</option>
			</select></td>
		</tr>

		<tr>
			<td>צורן ריבוי זכר: (noun)</td>
			<td><select name='multiWordNounAdjective_plural_male_noun'>
					<option value='m'
						<%if (multiWordNounAdjective.getPluralMaleNoun().equals("m"))
				out.print("SELECTED");%>>ם</option>
					<option value='im'
						<%if (multiWordNounAdjective.getPluralMaleNoun().equals("im"))
				out.print("SELECTED");%>>ים</option>
					<option value='iim'
						<%if (multiWordNounAdjective.getPluralMaleNoun().equals("iim"))
				out.print("SELECTED");%>>יים</option>
					<option value='wt'
						<%if (multiWordNounAdjective.getPluralMaleNoun().equals("wt"))
				out.print("SELECTED");%>>ות</option>
					<option value='awt'
						<%if (multiWordNounAdjective.getPluralMaleNoun().equals("awt"))
				out.print("SELECTED");%>>אות</option>
					<option value='iwt'
						<%if (multiWordNounAdjective.getPluralMaleNoun().equals("iwt"))
				out.print("SELECTED");%>>יות</option>
					<option value='im and wt'
						<%if (multiWordNounAdjective.getPluralMaleNoun().equals("im and wt"))
				out.print("SELECTED");%>>ים
						וגם ות</option>
					<option value='unspecified'
						<%if (multiWordNounAdjective.getPluralMaleNoun()
					.equals("unspecified"))
				out.print("SELECTED");%>>לא
						מוגדר</option>
			</select></td>
		</tr>

		<tr>
			<td>צורן ריבוי זכר: (adjective)</td>
			<td><select name='multiWordNounAdjective_plural_male_adjective'>
					<option value='m'
						<%if (multiWordNounAdjective.getPluralMaleAdjective().equals("m"))
				out.print("SELECTED");%>>ם</option>
					<option value='im'
						<%if (multiWordNounAdjective.getPluralMaleAdjective().equals("im"))
				out.print("SELECTED");%>>ים</option>
					<option value='iim'
						<%if (multiWordNounAdjective.getPluralMaleAdjective().equals("iim"))
				out.print("SELECTED");%>>יים</option>
					<option value='wt'
						<%if (multiWordNounAdjective.getPluralMaleAdjective().equals("wt"))
				out.print("SELECTED");%>>ות</option>
					<option value='awt'
						<%if (multiWordNounAdjective.getPluralMaleAdjective().equals("awt"))
				out.print("SELECTED");%>>אות</option>
					<option value='iwt'
						<%if (multiWordNounAdjective.getPluralMaleAdjective().equals("iwt"))
				out.print("SELECTED");%>>יות</option>
					<option value='im and wt'
						<%if (multiWordNounAdjective.getPluralMaleAdjective().equals(
					"im and wt"))
				out.print("SELECTED");%>>ים
						וגם ות</option>
					<option value='unspecified'
						<%if (multiWordNounAdjective.getPluralMaleAdjective().equals(
					"unspecified"))
				out.print("SELECTED");%>>לא
						מוגדר</option>
			</select></td>
		</tr>

		<tr>
			<td>צורן ריבוי נקבה: (noun)</td>
			<td><select name='multiWordNounAdjective_plural_female_noun'>
					<option value='m'
						<%if (multiWordNounAdjective.getPluralFemaleNoun().equals("m"))
				out.print("SELECTED");%>>ם</option>
					<option value='im'
						<%if (multiWordNounAdjective.getPluralFemaleNoun().equals("im"))
				out.print("SELECTED");%>>ים</option>
					<option value='iim'
						<%if (multiWordNounAdjective.getPluralFemaleNoun().equals("iim"))
				out.print("SELECTED");%>>יים</option>
					<option value='wt'
						<%if (multiWordNounAdjective.getPluralFemaleNoun().equals("wt"))
				out.print("SELECTED");%>>ות</option>
					<option value='awt'
						<%if (multiWordNounAdjective.getPluralFemaleNoun().equals("awt"))
				out.print("SELECTED");%>>אות</option>
					<option value='iwt'
						<%if (multiWordNounAdjective.getPluralFemaleNoun().equals("iwt"))
				out.print("SELECTED");%>>יות</option>
					<option value='im and wt'
						<%if (multiWordNounAdjective.getPluralFemaleNoun()
					.equals("im and wt"))
				out.print("SELECTED");%>>ים
						וגם ות</option>
					<option value='unspecified'
						<%if (multiWordNounAdjective.getPluralFemaleNoun().equals(
					"unspecified"))
				out.print("SELECTED");%>>לא
						מוגדר</option>
			</select></td>
		</tr>

		<tr>
			<td>צורן ריבוי נקבה: (adjective)</td>
			<td><select
				name='multiWordNounAdjective_plural_female_adjective'>
					<option value='m'
						<%if (multiWordNounAdjective.getPluralFemaleAdjective().equals("m"))
				out.print("SELECTED");%>>ם</option>
					<option value='im'
						<%if (multiWordNounAdjective.getPluralFemaleAdjective().equals("im"))
				out.print("SELECTED");%>>ים</option>
					<option value='iim'
						<%if (multiWordNounAdjective.getPluralFemaleAdjective().equals("iim"))
				out.print("SELECTED");%>>יים</option>
					<option value='wt'
						<%if (multiWordNounAdjective.getPluralFemaleAdjective().equals("wt"))
				out.print("SELECTED");%>>ות</option>
					<option value='awt'
						<%if (multiWordNounAdjective.getPluralFemaleAdjective().equals("awt"))
				out.print("SELECTED");%>>אות</option>
					<option value='iwt'
						<%if (multiWordNounAdjective.getPluralFemaleAdjective().equals("iwt"))
				out.print("SELECTED");%>>יות</option>
					<option value='im and wt'
						<%if (multiWordNounAdjective.getPluralFemaleAdjective().equals(
					"im and wt"))
				out.print("SELECTED");%>>ים
						וגם ות</option>
					<option value='unspecified'
						<%if (multiWordNounAdjective.getPluralFemaleAdjective().equals(
					"unspecified"))
				out.print("SELECTED");%>>לא
						מוגדר</option>
			</select></td>
		</tr>

		<tr>
			<td>
				<hr>
			</td>
		</tr>
		<tr>
			<td>סוג:</td>
			<td><select name='multiWordNounAdjective_type'>
					<!-- <option value='NN' <%if (multiWordNounAdjective.getType().equals("NN"))
				out.print("SELECTED");%>>NN-בית ספר</option> -->
					<option value='NA'
						<%if (multiWordNounAdjective.getType().equals("NA"))
				out.print("SELECTED");%>>NA-שוק
						שחור</option>
					<!-- <option value='NNN' <%if (multiWordNounAdjective.getType().equals("NNN"))
				out.print("SELECTED");%>>NNN-בית ספר שדה</option>
					<option value='NNA' <%if (multiWordNounAdjective.getType().equals("NNA"))
				out.print("SELECTED");%>>NNA-בית חולים ציבורי</option>
					<option value='AN' <%if (multiWordNounAdjective.getType().equals("AN"))
				out.print("SELECTED");%>>AN-רחב לב</option> -->
			</select></td>
		</tr>


		<tr>
			<td>יידוע:</td>
			<td><select name='multiWordNounAdjective_definiteness'>
					<!-- <option value='external' <%if (multiWordNounAdjective.getDefiniteness().equals("external"))
				out.print("SELECTED");%>>ייצר יידוע חיצוני</option> -->
					<option value='internal'
						<%if (multiWordNounAdjective.getDefiniteness().equals("internal"))
				out.print("SELECTED");%>>ייצר
						יידוע פנימי</option>
					<option value='internal and external'
						<%if (multiWordNounAdjective.getDefiniteness().equals(
					"internal and external"))
				out.print("SELECTED");%>>ייצר
						יידוע פנימי וגם חיצוני</option>
					<option value='none'
						<%if (multiWordNounAdjective.getDefiniteness().equals("none"))
				out.print("SELECTED");%>>אל
						תייצר שום יידוע</option>
					<!-- <option value='externallyDefinited' <%if (multiWordNounAdjective.getDefiniteness().equals(
					"externallyDefinited"))
				out.print("SELECTED");%>>קיימת הצורה החיצונית מיודעת בלבד</option> -->
			</select></td>
		</tr>

		<tr>
			<td>האם חלקי הביטוי חייבים להופיע באופן רציף?</td>
			<td><select name='multiWordNounAdjective_consecutive'>
					<option value='0'
						<%if (!multiWordNounAdjective.isConsecutive())
				out.print("SELECTED");%>>לא</option>
					<option value='1'
						<%if (multiWordNounAdjective.isConsecutive())
				out.print("SELECTED");%>>כן</option>
			</select></td>
		</tr>
		<%-- 
	 <tr>
        <td>
             האם לייצר קניין לצורת היחיד?
        </td>

	 	<td>
        	<select name='multiWordNounAdjective_inflectPossessiveS'>
                        <option value='0' <%if (!multiWordNounAdjective.isInflectPossessiveS()) out.print("SELECTED");%>>לא</option>                        
                        <option value='1' <%if (multiWordNounAdjective.isInflectPossessiveS()) out.print("SELECTED");%>>כן</option>                  
            </select>
        </td>
	</tr>
	
 	<tr>
        <td>
                        האם לייצר קניין לצורת הרבים?
                </td>
        <td>
                <select name='multiWordNounAdjective_inflectPossessiveP'>
                        <option value='0' <%if (!multiWordNounAdjective.isInflectPossessiveP()) out.print("SELECTED");%>>לא</option>
                        <option value='1' <%if (multiWordNounAdjective.isInflectPossessiveP()) out.print("SELECTED");%>>כן</option>                  
                </select>
        </td>
	</tr>
	 --%>

		<tr>
			<td>בסיס לנטיית גוף/מין/מספר אם שונה מהמילה:</td>
			<td><input type=text
				name='multiWordNounAdjective_inflectionBase' size=15
				value='<%=multiWordNounAdjective.getInflectionBase().trim()%>'>
			</td>
		</tr>

	</table>
</div>