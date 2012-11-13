package org.mila;

import javax.persistence.EntityManager;

import org.mila.entities.generator.InflectionRule;

public class RulesLoader {
	public static void LoadRules(EntityManager generator) {
		InflectionRule rule = null;
		
		generator.getTransaction().begin();
		
		rule = new InflectionRule();
		rule.setId(0);
		rule.setInputPattern("");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("");
		rule.setInflectedPattern("");
		rule.setComment("");
		rule.setAction("");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(1);
		rule.setInputPattern("iih");
		rule.setInputSuffixLen(3);
		rule.setInputCondition("wt");
		rule.setInflectedPattern("ih");
		rule.setComment("iih->ih");
		rule.setAction("pluralWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(2);
		rule.setInputPattern("ait");
		rule.setInputSuffixLen(3);
		rule.setInputCondition("wt");
		rule.setInflectedPattern("ai");
		rule.setComment("ait->ai");
		rule.setAction("pluralWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(3);
		rule.setInputPattern("wt");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("wt");
		rule.setInflectedPattern("wi");
		rule.setComment("wt->wi");
		rule.setAction("pluralWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(4);
		rule.setInputPattern("it");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("wt");
		rule.setInflectedPattern("i");
		rule.setComment("it->i");
		rule.setAction("pluralWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(5);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("wt");
		rule.setInflectedPattern("");
		rule.setComment("h->");
		rule.setAction("pluralWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(6);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("wt");
		rule.setInflectedPattern("wt");
		rule.setComment("default->wt");
		rule.setAction("pluralWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(7);
		rule.setInputPattern("wt");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("iwt");
		rule.setInflectedPattern("wiwt");
		rule.setComment("wt->wiwt");
		rule.setAction("pluralIWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(8);
		rule.setInputPattern("it");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("iwt");
		rule.setInflectedPattern("wiwt");
		rule.setComment("it->");
		rule.setAction("pluralIWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(9);
		rule.setInputPattern("ait");
		rule.setInputSuffixLen(3);
		rule.setInputCondition("iwt");
		rule.setInflectedPattern("aiwt");
		rule.setComment("ait->aiwt");
		rule.setAction("pluralIWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(10);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("awt");
		rule.setInflectedPattern("awt");
		rule.setComment("default->awt");
		rule.setAction("pluralAWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(11);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("im");
		rule.setInflectedPattern("");
		rule.setComment("h->");
		rule.setAction("pluralIMnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(12);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("im");
		rule.setInflectedPattern("im");
		rule.setComment("default->im");
		rule.setAction("pluralIMnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(13);
		rule.setInputPattern("t");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("wt");
		rule.setInflectedPattern("");
		rule.setComment("rt->rwt");
		rule.setAction("pluralWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(14);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("awt");
		rule.setInflectedPattern("");
		rule.setComment("h->");
		rule.setAction("pluralAWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(17);
		rule.setInputPattern("im");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("");
		rule.setInflectedPattern("i");
		rule.setComment("im->i");
		rule.setAction("constructMasculinePluralnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(18);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("");
		rule.setInflectedPattern(",i,k,k,w,h,nw,km,kn,m,n");
		rule.setComment("default construct->i,k,w,h,nw,km,kn,m,n");
		rule.setAction("possessiveSingularnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(19);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("");
		rule.setInflectedPattern(",ii,ik,iik,iw,ih,inw,ikm,ikn,ihm,ihn");
		rule.setComment("default construct->i,ik,iw,ih,inw,ikm,ikn,ihm,ihn");
		rule.setAction("possessivePluralNoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(20);
		rule.setInputPattern("ai");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("");
		rule.setInflectedPattern("a");
		rule.setComment("i->");
		rule.setAction("possessivePluralNoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(21);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("");
		rule.setInflectedPattern("t");
		rule.setComment("h->t");
		rule.setAction("constructFeminineSingularnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(22);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("im");
		rule.setInflectedPattern("");
		rule.setComment("h->");
		rule.setAction("pluralIMadjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(23);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("wt");
		rule.setInflectedPattern("");
		rule.setComment("h->");
		rule.setAction("pluralWTadjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(24);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("im");
		rule.setInflectedPattern("im");
		rule.setComment("default -> im");
		rule.setAction("pluralIMadjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(25);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("wt");
		rule.setInflectedPattern("wt");
		rule.setComment("default -> wt");
		rule.setAction("pluralWTadjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(26);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("");
		rule.setInflectedPattern(",i,k,k,w,h,nw,km,kn,m,n");
		rule.setComment("default construct->i,k,w,h,nw,km,kn,m,n");
		rule.setAction("possessiveSingularadjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(27);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("");
		rule.setInflectedPattern(",i,ik,ik,iw,ih,inw,ikm,ikn,ihm,ihn");
		rule.setComment("default construct->i,ik,iw,ih,inw,ikm,ikn,ihm,ihn");
		rule.setAction("possessivePluraladjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(28);
		rule.setInputPattern("i");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("");
		rule.setInflectedPattern("");
		rule.setComment("i->");
		rule.setAction("possessivePluraladjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(29);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("");
		rule.setInflectedPattern("t");
		rule.setComment("h->t");
		rule.setAction("constructFeminineSingularadjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(30);
		rule.setInputPattern("im");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("");
		rule.setInflectedPattern("i");
		rule.setComment("im->i");
		rule.setAction("constructMasculinePluraladjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(71);
		rule.setInputPattern("iim");
		rule.setInputSuffixLen(3);
		rule.setInputCondition("");
		rule.setInflectedPattern("ii");
		rule.setComment("iim->ii");
		rule.setAction("constructMasculinePluralnoundalia");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(32);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("");
		rule.setInflectedPattern("basePos->noun");
		rule.setComment("basePos->noun");
		rule.setAction("possessiveSingularadjective-not in use");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(33);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("h");
		rule.setInflectedPattern("");
		rule.setComment("h->");
		rule.setAction("feminineHSingularadjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(34);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("h");
		rule.setInflectedPattern("h");
		rule.setComment("default->h");
		rule.setAction("feminineHSingularadjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(35);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("t");
		rule.setInflectedPattern("t");
		rule.setComment("deafult->t");
		rule.setAction("feminineTSingularadjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(36);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("it");
		rule.setInflectedPattern("it");
		rule.setComment("default->it");
		rule.setAction("feminineITSingularadjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(37);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("h");
		rule.setInflectedPattern("h");
		rule.setComment("default->h");
		rule.setAction("feminineHSingularnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(38);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("t");
		rule.setInflectedPattern("t");
		rule.setComment("default->t");
		rule.setAction("feminineTSingularnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(39);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("it");
		rule.setInflectedPattern("it");
		rule.setComment("default->it");
		rule.setAction("feminineITSingularnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(40);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("masculine");
		rule.setInflectedPattern("t");
		rule.setComment("h->t");
		rule.setAction("constructQuantifier");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(41);
		rule.setInputPattern("iim");
		rule.setInputSuffixLen(3);
		rule.setInputCondition("masculine");
		rule.setInflectedPattern("i");
		rule.setComment("iim->i");
		rule.setAction("constructQuantifier");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(42);
		rule.setInputPattern("im");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("masculine");
		rule.setInflectedPattern("i");
		rule.setComment("im->i");
		rule.setAction("constructQuantifier");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(43);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("");
		rule.setInflectedPattern(",i,k,k,w,h,nw,km,kn,m,n");
		rule.setComment("default->,i,k,k,w,h,nw,km,kn,m,n");
		rule.setAction("possessivePreposition");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(44);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("masculine and feminine");
		rule.setInflectedPattern("i,k,k,w,h,nw,km,kn,hm,hn");
		rule.setComment("default->i,k,k,w,h,nw,km,kn,hm,hn");
		rule.setAction("possessiveQuantifier");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(45);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("masculine");
		rule.setInflectedPattern("nw,km,m");
		rule.setComment("default->nw,km,m");
		rule.setAction("possessiveQuantifier");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(46);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("feminine");
		rule.setInflectedPattern("nw,kn,hn");
		rule.setComment("default->nw,kn,hn");
		rule.setAction("possessiveQuantifier");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(47);
		rule.setInputPattern("ii");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("inflectionBase");
		rule.setInflectedPattern("i");
		rule.setComment("ii->i");
		rule.setAction("possessiveSingularnounInflectionBase");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(48);
		rule.setInputPattern("im");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("inflectionBase");
		rule.setInflectedPattern("ihm");
		rule.setComment("im->ihm");
		rule.setAction("possessiveSingularnounInflectionBase");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(49);
		rule.setInputPattern("in");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("inflectionBase");
		rule.setInflectedPattern("ihn");
		rule.setComment("in->ihn");
		rule.setAction("possessiveSingularnounInflectionBase");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(50);
		rule.setInputPattern("ai");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("im");
		rule.setInflectedPattern("a");
		rule.setComment("i->");
		rule.setAction("pluralIMnoundalia");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(51);
		rule.setInputPattern("ai");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("");
		rule.setInflectedPattern("a");
		rule.setComment("i->");
		rule.setAction("possessiveSingularnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(52);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("plural");
		rule.setInflectedPattern(",-,k,k,w,h,nw,km,kn,hm,hn");
		rule.setComment("i,k,k,w,h,nw,km,kn,hm,hn");
		rule.setAction("possessiveVerbDalia");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(54);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("singular");
		rule.setInflectedPattern(",i,k,k,w,h,nw,km,kn,m,n");
		rule.setComment(",i,k,k,w,h,nw,km,kn,m,n");
		rule.setAction("possessiveVerbDalia");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(55);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("");
		rule.setInflectedPattern(",i,k,k,w,h,nw,km,kn,m,n");
		rule.setComment("i,k,k,w,h,nw,km,kn,hm,hn");
		rule.setAction("possessiveVerbDalia");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(57);
		rule.setInputPattern("t");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("plural");
		rule.setInflectedPattern("ti");
		rule.setComment("ti");
		rule.setAction("possessiveVerbDalia");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(58);
		rule.setInputPattern("in");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("");
		rule.setInflectedPattern("i");
		rule.setComment("in->i");
		rule.setAction("constructMasculinePluralnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(59);
		rule.setInputPattern("t");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("wt");
		rule.setInflectedPattern("");
		rule.setComment("it->i");
		rule.setAction("pluralWTadjective");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(60);
		rule.setInputPattern("ii");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("");
		rule.setInflectedPattern("i");
		rule.setComment("iim->i");
		rule.setAction("constructMasculinePluralnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(61);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("");
		rule.setInflectedPattern("");
		rule.setComment("h->");
		rule.setAction("possessiveSingularnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(62);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("");
		rule.setInflectedPattern(",i,k,k,w,h,nw,km,kn,m,n");
		rule.setComment(",i,k,k,w,h,nw,km,kn,m,n");
		rule.setAction("possessiveSingularnounInflectionBase");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(63);
		rule.setInputPattern("");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("");
		rule.setInflectedPattern("");
		rule.setComment("h->");
		rule.setAction("possessiveVerbDalia");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(64);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("ii");
		rule.setInflectedPattern(",-,k,k,w,h,nw,km,kn,hm,hn");
		rule.setComment("");
		rule.setAction("possessivePluralNoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(65);
		rule.setInputPattern("default");
		rule.setInputSuffixLen(0);
		rule.setInputCondition("iim");
		rule.setInflectedPattern("iim");
		rule.setComment("->iim");
		rule.setAction("pluralIIMnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(66);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("iim");
		rule.setInflectedPattern("");
		rule.setComment("h->");
		rule.setAction("pluralIIMnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(67);
		rule.setInputPattern("i");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("");
		rule.setInflectedPattern("");
		rule.setComment("->,ii,iik,ik,iw,ih,inw,ikm,ikn,ihm,ihn");
		rule.setAction("possessivePluralNoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(68);
		rule.setInputPattern("wh");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("wt");
		rule.setInflectedPattern("");
		rule.setComment("wh->");
		rule.setAction("pluralWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(69);
		rule.setInputPattern("th");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("th");
		rule.setInflectedPattern("twt");
		rule.setComment("th->twt");
		rule.setAction("pluralWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(70);
		rule.setInputPattern("i");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("m");
		rule.setInflectedPattern("im");
		rule.setComment("i->m");
		rule.setAction("pluralMnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(72);
		rule.setInputPattern("h");
		rule.setInputSuffixLen(1);
		rule.setInputCondition("iwt");
		rule.setInflectedPattern("iwt");
		rule.setComment("h->");
		rule.setAction("pluralIWTnoun");
		generator.persist(rule);

		rule = new InflectionRule();
		rule.setId(73);
		rule.setInputPattern("ih");
		rule.setInputSuffixLen(2);
		rule.setInputCondition("");
		rule.setInflectedPattern("iit");
		rule.setComment("ih->iit");
		rule.setAction("constructFeminineSingularnoun");
		generator.persist(rule);

		generator.getTransaction().commit();

	}
}
