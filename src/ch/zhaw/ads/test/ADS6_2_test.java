/**
 * @(#)TreeTest.java
 *
 *
 * @author K Rege
 * @version 1.00 2018/3/17
 * @version 1.01 2021/8/1
 */

package ch.zhaw.ads.test;

import ch.zhaw.ads.AVLSearchTree;
import ch.zhaw.ads.Tree;
import ch.zhaw.ads.Visitor;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.io.*;

public class ADS6_2_test {
    
	Tree<String> tree;
	String fileToTest = "AVLSearchTree.java";
	
	@Test
    public void checkFileUpload() throws Exception {
        File f = new File(fileToTest);
        assertTrue("File uploaded "+fileToTest,f.exists());
    }
    
	@Before
	public void setUp() throws Exception {
		tree = new AVLSearchTree<String>();
		tree.add("B");
		tree.add("A");	
		tree.add("C");	
		tree.add("D");		
	}

	@Test
	public void testInorder() {
		Visitor<String> v = new MyVisitor<String>();
		tree.traversal().inorder(v);
		assertEquals("inorder", "ABCD", v.toString());
	}
	
	@Test
	public void testPreorder() {
		Visitor<String> v = new MyVisitor<String>();
		tree.traversal().preorder(v);
		assertEquals("preorder", "BACD", v.toString());	
	}	
	
	@Test	
	public void testPostorder() {	
		Visitor<String> v = new MyVisitor<String>();
		tree.traversal().postorder(v);
		assertEquals("postorder", "ADCB", v.toString());
	}	

	@Test
	public void testLevelorder() {			
		Visitor<String> v = new MyVisitor<String>();
		tree.traversal().levelorder(v);
		assertEquals("levelorder", "BACD", v.toString());	
	}
	
	/*
	@Test	
	public void testInterval() {
		char left = 'K';
        char right = 'O';
        	
        for (int i = 0; i < 200; i++) {
            Character c = (char) ('A' + (Math.random() * 26));
			tree.add(c.toString());
        }
        // get all elements with inorder
        Visitor<String> v = new MyVisitor<String>();
        tree.traversal().inorder(v);
        int count = 0;
        String s = v.toString();
        for (int i = 0; i < s.length(); i++) {
        	if (s.charAt(i) >= left && s.charAt(i) <= right) count++;
        }
        // now interval
        v = new MyVisitor<String>();
		tree.traversal().interval(((Character)left).toString(),((Character)right).toString(),v);
		s = v.toString();
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			assertTrue(c +" in interval "+left+" "+right,c >= left && c <= right);
		}
		assertEquals("size",count,s.length());
	}
	*/
}

class MyVisitor<T> implements Visitor<T> {
	StringBuilder output;
	
	MyVisitor() {
		output = new StringBuilder();
	}
	
	public void visit(T s) {
		output.append(s);
	}
	
	public String toString() {
		return output.toString();
	}
}
