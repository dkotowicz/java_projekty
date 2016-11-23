package test.java.modele;

import static org.junit.Assert.*;

import org.junit.Test;

public class Tests {

	@Test
	public void test() {
		System.out.println("Dzia³a");
	}
	@Test
	 public void testConn() throws Exception {
	        Exception ex = null;
	        try {
	            System.out.println("polaczone");
	        } catch (Exception e) {
	            ex = e;
	        }
	        assertEquals(null,ex);
	    }

}
