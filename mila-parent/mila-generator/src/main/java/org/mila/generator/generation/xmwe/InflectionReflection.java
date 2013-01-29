package org.mila.generator.generation.xmwe;

import org.apache.commons.lang.WordUtils;
import org.jvnet.jaxb2_commons.lang.EnumValue;
import org.mila.entities.inflections.Inflection;

public class InflectionReflection {

	@SuppressWarnings("unchecked")
	public static EnumValue<String> getFromInflection(Inflection inf, String prop) {
		try {
			return (EnumValue<String>) inf.getClass()
					.getMethod("get" + WordUtils.capitalize(prop))
					.invoke(inf);
		} catch (Exception e) {
			throw new RuntimeException("Failed to reflect get"
					+ WordUtils.capitalize(prop), e);
		}
	}

}
