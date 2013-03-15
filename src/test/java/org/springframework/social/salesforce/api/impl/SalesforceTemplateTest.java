/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.springframework.social.salesforce.api.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author os
 */
public class SalesforceTemplateTest {
    
    public SalesforceTemplateTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

     @Test
     public void testEscape() {
         assertNull("Null", SalesforceTemplate.escape(null));
         assertEquals("Empty", "", SalesforceTemplate.escape(""));
         assertEquals("Without", "Hello World!", SalesforceTemplate.escape("Hello World!"));
         assertEquals("First", "\\' first", SalesforceTemplate.escape("' first"));
         assertEquals("Last", "last \\'", SalesforceTemplate.escape("last '"));
         assertEquals("Middle", "Mid\\'dle", SalesforceTemplate.escape("Mid'dle"));
         assertEquals("Multi", "\\'M\\'ul\\'ti\\'", SalesforceTemplate.escape("'M'ul'ti'"));
     }
}