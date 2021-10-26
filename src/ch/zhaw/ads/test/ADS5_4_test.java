/**
 * @(#)TreeTest.java
 *
 *
 * @author K Rege
 * @version 1.00 2018/3/17
 * @version 1.01 2021/8/1
 */

package ch.zhaw.ads.test;

import ch.zhaw.ads.SortedBinaryTree;
import ch.zhaw.ads.Tree;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.util.Collections;
import java.util.List;
import java.util.LinkedList;
import java.io.*;

public class ADS5_4_test {
	Tree<String> tree;
	
	String fileToTest = "SortedBinaryTree.java";
	
	@Test
    public void checkFileUpload() throws Exception {
        File f = new File(fileToTest);
        assertTrue("File uploaded "+fileToTest,f.exists());
    }

	@Before
	public void setUp() throws Exception {
		tree = new SortedBinaryTree<String>();
		tree.add("B");
		tree.add("A");	
		tree.add("C");	
		tree.add("D");		
	}

	@Test
	public void testHeight() {			

		assertEquals("height", 3, tree.height());	
	}
	
	@Test
	public void testSize() {			
		assertEquals("size", 4, tree.size());	
	}
	
	@Test
	public void testSizeMixed() {	
	    Tree<String> tree = new SortedBinaryTree<String>();
    	List<String> list = new LinkedList<>();
        for (int i = 0; i < 1000; i++) {
            Character c = (char) ('A' + (Math.random() * 26));
            int op = (int) (Math.random() * 2);
            switch (op) {
                case 0:
                	list.add(c.toString());
                    tree.add(c.toString());
                    break;
                case 1:
                	list.remove(c.toString());
                    tree.remove(c.toString());
                    break;
            }
        }
		assertEquals(list.size(),tree.size());
	}
	
	
}
