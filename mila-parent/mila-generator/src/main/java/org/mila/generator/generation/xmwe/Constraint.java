package org.mila.generator.generation.xmwe;

import java.util.List;

import javax.xml.bind.annotation.XmlType;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.WordUtils;
import org.jvnet.jaxb2_commons.lang.EnumValue;
import org.mila.entities.inflections.Inflection;
import org.mila.entities.lexicon.Pos;
import org.mila.entities.lexicon.XMWEConstraint;

import com.google.common.collect.ImmutableSet;

public class Constraint {

	private List<XMWEConstraint> constraints;
	static private ImmutableSet<String> props = ImmutableSet
			.<String> copyOf(XMWEConstraint.class.getAnnotation(XmlType.class)
					.propOrder());

	public Constraint(List<XMWEConstraint> c) {
		constraints = c;
	}

	public boolean match(Inflection inf) {
		if (constraints.isEmpty())
			return true; /* Vacuous truth */
		for (XMWEConstraint constraint : constraints) {
			boolean ret = true;

			for (String prop : props) {
				if (!chkIsSet(constraint, prop))
					continue;
				if (prop.equals("partOfSpeech")) {
					/* pos special case */
					if (!constraint.getPartOfSpeech().contains(
							Pos.fromValue(StringUtils.uncapitalize(inf
									.getBasePos())))) {
						ret = false;
						break;
					}
					continue;
				}
				if (!chkContains(constraint, prop,
						InflectionReflection.getFromInflection(inf, prop))) {
					ret = false;
					break;
				}
			}
			if (ret)
				return true;
		}
		return false;
	}

	private Boolean chkIsSet(XMWEConstraint constraint, String prop) {
		try {
			return (Boolean) constraint.getClass()
					.getMethod("isSet" + WordUtils.capitalize(prop))
					.invoke(constraint);
		} catch (Exception e) {
			throw new RuntimeException("Failed to reflect isSet"
					+ WordUtils.capitalize(prop), e);
		}
	}

	@SuppressWarnings("unchecked")
	private <S extends EnumValue<String>> Boolean chkContains(
			XMWEConstraint constraint, String prop, S value) {
		List<EnumValue<String>> l;
		try {
			l = (List<EnumValue<String>>) constraint.getClass()
					.getMethod("get" + WordUtils.capitalize(prop))
					.invoke(constraint);
		} catch (Exception e) {
			throw new RuntimeException("Failed to reflect get"
					+ WordUtils.capitalize(prop), e);
		}
		for (EnumValue<String> t : l) {
			if (chkEq(t, value))
				return true;
		}
		return false;
	}

	private <T extends EnumValue<String>, S extends EnumValue<String>> boolean chkEq(
			T e1, S e2) {
		return e1.enumValue().equals(e2.enumValue());
	}
}
