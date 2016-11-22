package ru.regionsgroup.inventory.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Mbedritskiy
 */
public class PrinterConnectionTest {
    final PrinterConnection printer1 = new PrinterConnection();
    final PrinterConnection printer2 = new PrinterConnection();
    final PrinterConnection printer3 = new PrinterConnection();
    final PrinterConnection printer4 = new PrinterConnection();

    @Before
    public void setUp() throws Exception {
        printer1.setId(1L);
        printer1.setName("PRINTER1");
        printer2.setId(2L);
        printer2.setName("PRINTER2");
        printer3.setId(3L);
        printer3.setName("PRINTER2");
        printer4.setId(1L);
        printer4.setName("PRINTER4");
    }

    @Test
    public void equalsTest() throws Exception {
        assertTrue("self equals", printer1.equals(printer1));
        assertFalse("not the same type", printer1.equals(new Object()));
        assertFalse("same ids, different info", printer1.equals(printer4));
        assertTrue("different ids, same info", printer2.equals(printer3));
    }

    @Test
    public void hashCodeTest() throws Exception {
        assertFalse("same ids, different info", printer1.hashCode() == printer4.hashCode());
        assertTrue("different ids, same info", printer2.hashCode() == printer3.hashCode());
    }
}