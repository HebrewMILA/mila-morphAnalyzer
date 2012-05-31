package org.mila;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ XMLImportTest.class, XMLRoundtripTest.class })
public class AllTests {

}
