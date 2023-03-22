package org.example;

import domain.Student;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import service.Service;
import validation.StudentValidator;
import validation.ValidationException;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }

    public void testWhenNameIsNull_thenFail()
    {
        var service = Service.getService();
        Student student = new Student("1", null, 1, "email");

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Nume incorect!", e.getMessage());
        }
    }

    public void testWhenNameIsEmpty_thenFail()
    {
        var service = Service.getService();
        Student student = new Student("1", "", 1, "email");

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Nume incorect!", e.getMessage());
        }
    }
}
