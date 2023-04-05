package org.example;

import domain.Student;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import service.Service;
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

    public void testWhenGrupaIsNatural_thenOk()
    {
        var service = Service.getService();
        Student student = new Student("1", "name", 1, "email");

        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            fail("Expected no exception to be thrown");
        }
    }

    public void testWhenGrupaIsNegative_thenFail()
    {
        var service = Service.getService();
        Student student = new Student("1", "name", -1, "email");

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Grupa incorecta!", e.getMessage());
        }
    }

    public void testWhenIdIsNotNull_thenOk() {
        var service = Service.getService();
        Student student = new Student("1", "name", 1, "email");

        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            fail("Expected no exception to be thrown");
        }
    }

    public void testWhenIdIsNull_thenFail() {
        var service = Service.getService();
        Student student = new Student(null, "name", 1, "email");

        try {
            service.addStudent(student);
            fail("Expected an NullPointerException to be thrown");
        } catch (NullPointerException e) {
        }
    }

    public void testWhenIdIsEmpty_thenFail() {
        var service = Service.getService();
        Student student = new Student("", "name", 1, "email");

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Id incorect!", e.getMessage());
        }
    }

    public void testWhenEmailIsNull_thenFail() {
        var service = Service.getService();
        Student student = new Student("1", "name", 1, null);

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Email incorect!", e.getMessage());
        }
    }

    public void testWhenEmailIsEmpty_thenFail() {
        var service = Service.getService();
        Student student = new Student("2", "name", 1, "");

        try {
            service.addStudent(student);
            fail("Expected an ValidationException to be thrown");
        } catch (ValidationException e) {
            assertEquals("Email incorect!", e.getMessage());
        }
    }

    public void testWhenAllFieldsAreCorrect_thenOk() {
        var service = Service.getService();
        Student student = new Student("3", "name", 1, "email");

        try {
            service.addStudent(student);
        } catch (ValidationException e) {
            fail("Expected no exception to be thrown");
        }
    }
}
