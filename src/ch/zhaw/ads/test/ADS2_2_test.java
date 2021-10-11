package ch.zhaw.ads.test;

import ch.zhaw.ads.BracketServer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.Before;

import java.util.*;

public class ADS2_2_test extends Assert {
	BracketServer bs;

	@Before
	public void setUp() throws Exception {
		bs = new BracketServer();
	}
	private void test(String s, boolean b) {
		assertEquals(s,b,bs.checkBrackets(s));
	}
	
	@Test
	public void testBracket2() {
	    test("<(<>)>",true);
        test("<(<)>>",false);
		test("<",false);
		test(">",false);
	}
	
	@Test
	public void testBracket3() {
        test("/* hallo */",true);
        test("/*/* */",false);
        test("/*",false);

	}
	
	@Test
	public void testBracket() {
		test("()",true);
		test("(()]",false);
		test("((([([])])))",true);
		test("[(])",false);
		test("[(3 +3)* 35 +3]* {3 +2}",true);
		test("[({3 +3)* 35} +3]* {3 +2}",false);
		test("(",false);
		test(")",false);
	}	
}

