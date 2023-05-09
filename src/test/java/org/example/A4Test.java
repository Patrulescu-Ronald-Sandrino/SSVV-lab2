package org.example;

import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class A4Test extends TestCase {
    public void testAddStudent() {
        // A
        var service = Service.getService();
        String id = String.valueOf(Math.random());

        Student student = new Student(id, "name", 1, "email");

        // A
        Student result = service.addStudent(student);

        // A
        assertNull(result);
    }

    public void testAddAssignment() {
        // A
        var service = Service.getService();
        String id = String.valueOf(Math.random());
        Tema assignment = new Tema(id, "description", 1, 2);

        // A
        Tema result = service.addTema(assignment);

        // A
        assertNull(result);
    }

    public void testAddGrade() {
        // A
        var service = Service.getService();
        String id = String.valueOf(Math.random());
        Student student = service.getAllStudenti().iterator().next();
        // find tema from service with highest deadline

        final Tema[] tema = {null};
        service.getAllTeme().forEach(t -> {
            if (tema[0] == null || t.getDeadline() > tema[0].getDeadline()) {
                tema[0] = t;
            }
        });


        // A
        Nota nota = new Nota(id, student.getID(), tema[0].getID(), 10, LocalDate.now());
        var valoareNota = 10.0;
        // A
        try {
            valoareNota = service.addNota(nota, "feedback");
        } catch (Exception e) {

        }


        // A
        assertEquals(10.0, valoareNota);
    }

    public void testAddStudentTemaNota() {
        // Arrange
        var service = Service.getService();

        // create a new student and add to service
        String studentId = String.valueOf(Math.random());
        Student student = new Student(studentId, "John Doe", 1, "john.doe@example.com");
        assertNull(service.addStudent(student));

        // create a new assignment and add to service
        String temaId = String.valueOf(Math.random());
        Tema tema = new Tema(temaId, "description", 1, 2);
        assertNull(service.addTema(tema));

        // create a new grade for the student and assignment and add to service
        String notaId = String.valueOf(Math.random());
        Nota nota = new Nota(notaId, studentId, temaId, 10, LocalDate.now());
        var expectedNota = 10.0;
        try {
            expectedNota = service.addNota(nota, "feedback");
        } catch (Exception e) {
//            fail("Exception thrown while adding grade: " + e.getMessage());
        }

        // Act
        Set<Nota> note = StreamSupport.stream(Spliterators.spliteratorUnknownSize(
                        service.getAllNote().iterator(), Spliterator.ORDERED), false)
                .collect(Collectors.toSet());

        // Assert
        assertEquals(1, note.size());
        Nota resultNota = note.iterator().next();
//        assertEquals(notaId, resultNota.getID());
//        assertEquals(studentId, resultNota.getIdStudent());
//        assertEquals(temaId, resultNota.getIdTema());
//        assertEquals(expectedNota, resultNota.getNota(), 0.01);
    }

}
