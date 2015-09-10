package features;

import ner.Entity;
import static ner.Entity.*;

import org.junit.*;

import features.Expression;
import tagging.Token;
import utils.Gram5;
import static org.junit.Assert.*;

@SuppressWarnings("all")
public class ExpressionTest {
	private Expression exp(String s) {
		String[] ss = s.split(" ");
		Gram5<Token> g = new Gram5<>(
				makeToken(1, ss[0]),
				makeToken(2, ss[1]),
				makeToken(3, ss[2]),
				makeToken(4, ss[3]),
				makeToken(5, ss[4]));
		return new Expression(g);
	}

	private Token makeToken(int id, String surface) {
		return new Token(id, surface, "", "", "", "", "", "", "");
	} 

	private void assertEntity(Entity ent, String s) {
		assertTrue("is " + ent + " " + s, exp(s).is(ent));
	}

	@Test
	public void isOrganization() {
		assertEntity(ORG, "החברה למשקאות קלים בע\"מ במבצע");
		assertEntity(ORG, "לטענת חברת מיקרוסופט אין רקע");
	}

	@Test
	public void isLocation() {
		assertEntity(LOC, "בסביבות העיר ירושלים נשמעה אזעקה");
	}

	@Test
	public void isQuarter() {
		assertEntity(DATE, "הרבעון השני של 2003 היה מוצלח");
		assertEntity(DATE, "הרבעון השני של שנת 2003 היה מוצלח");
	}

	@Test
	public void isYear() {
		assertEntity(DATE, "רק בשנת 2015 למניינם כאן");
	}

	@Test
	public void isDate() {
		assertEntity(DATE, "אתמול בסביבות חג החנוכה פה");
		assertEntity(DATE, "יגיע בסביבות אפריל אל החנות");
		assertEntity(DATE, "החמש עשרה באפריל למניינם כאן");
		assertEntity(DATE, "החמש עשרה לאפריל למניינם כאן");
		assertEntity(DATE, "אתמול החמישי בפברואר 2015 למניינם");
		assertEntity(DATE, "רק אתמול החמישי בפברואר 2015");
		assertEntity(DATE, "יגיע בפברואר 2004 אל החנות");
		assertEntity(DATE, "אתמול בסביבות חג חנוכה פה");
	}

	@Test
	public void isPercent() {
		assertEntity(PERCENT, "גבינה % 15 חלב טהור");
		assertEntity(PERCENT, "גבינה 15 אחוז חלב טהור");
		assertEntity(PERCENT, "גבינה 15 אחוזים חלב טהור");

		assertEntity(PERCENT, "שתי גבינה % 15 חלב");
		assertEntity(PERCENT, "שתי גבינה 15 אחוז חלב");
		assertEntity(PERCENT, "שתי גבינה 15 אחוזים חלב");
	}

	@Test
	public void isTime() {
		assertEntity(TIME, "אתמול בשעה 16:30 בלילה בדיוק");
		assertEntity(TIME, "ממש אתמול בשעה ארבע וחצי");
		assertEntity(TIME, "ממש אתמול בשעה ארבע וחצי");
		assertEntity(TIME, "אתמול בשעה ארבע וחצי בלילה");
	}

	@Test
	public void isMoney() {
		assertEntity(MONEY, "חמשת אלפים דולר זה יקר");
		assertEntity(MONEY, "חמשת אלפים שקל זה יקר");
		assertEntity(MONEY, "חמשת אלפים ש\"ח זה יקר");
	}

	@Test
	public void inQuotes() {
		String s = "כאילו \" אין \" דבר";
		assertTrue(s, Expression.inQuotes(s.split(" "), 2));
	}

	// @Test
	// public void testGetLongestMatch() {
	// fail("Not yet implemented");
	// }

}
