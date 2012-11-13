package org.mila.entities;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class ItemIdAdapter extends XmlAdapter<java.lang.String, Integer> {
    public static String printKey(Integer value) {
	return "I" + String.valueOf(value);
    }

    public static Integer parseKey(String value) {
	return Integer.valueOf(value);
    }

    public Integer unmarshal(java.lang.String value) {
	return (org.mila.entities.ItemIdAdapter.parseKey(value));
    }

    public java.lang.String marshal(Integer value) {
	return (org.mila.entities.ItemIdAdapter.printKey(value));
    }
}
