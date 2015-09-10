package ner.baseline;

import ner.Entity;
import static ner.Entity.*;

import utils.Gram5;
import org.junit.*;
import static org.junit.Assert.*;

public class DictionaryTest {
	public static Gram5<String> g(String s) {
		String[] ss = s.split(" ");
		return new Gram5<>(ss[0], ss[1], ss[2], ss[3], ss[4]);
	}

	private void lookupLongest(Entity e, String s) {
		assertEquals(e, Dictionary.dictionary.getLongestMatch(g(s)));
	}

	@Test
	public void testGetLongestMatch() {
		lookupLongest(LOC, "אנשים ליד קרית מלאכי אולי");
		lookupLongest(LOC, "אנשים ליד ירושלים אולי זקנים");
		lookupLongest(LOC, "ליד ריו דה ז'נירו אולי");
	}

	private void lookup(Entity e, String word) {
		assertEquals(e, Dictionary.dictionary.get(word));
	}

	@Test
	public void testPers() {
		lookup(PERS, "שלמה");
	}

	@Test
	public void testMoney() {
		lookup(MONEY, "דולר");
	}

	@Test
	public void testDate() {
		lookup(DATE, "הושענא רבא");
	}

	@Test
	public void testLocation() {
		lookup(LOC, "אבו גוש");
	}

	@Test
	public void testOrganization() {
		lookup(ORG, "נטוויז'ן");
		lookup(ORG, "מיקרוסופט");
	}

	@Test
	public void testContainsPhrase() {
		assertTrue(Dictionary.dictionary.contains(LOC, "קרית","ביאליק"));
	}

	@Test
	public void testContains() {
		assertTrue(Dictionary.dictionary.contains(LOC, "קראקוב"));
	}

}
