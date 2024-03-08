package ca.uds.gestion_du_dossier_de_recherche.unittest;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class UnitTestExemple {

	int a;
	int b;
	
    
    @Before
    public void setUp() {
    	// chose executé avant chaque test
    	
        a = 42;
    }

    @Test
    public void testExemple() {
        b = a;
        assertTrue(b==42);
    }

    @Test
    public void testExemple_2() {
        b = 7;
        assertFalse(b==42);
    }

   

    @Test
    public void testExemple_3() {
    	b = a;
        assertEquals(a, b);
    }

}
