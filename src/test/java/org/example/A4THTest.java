package org.example;

import curent.Curent;
import domain.Nota;
import domain.Student;
import domain.Tema;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;

import java.time.LocalDate;

import static junit.framework.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class A4THTest extends TestCase {
    @Mock private StudentXMLRepo studentXMLRepo;
    @Mock private TemaXMLRepo temaXMLRepo;
    @Mock private NotaXMLRepo notaXMLRepo;

    @BeforeEach
    public void setUp() {
        studentXMLRepo = mock(StudentXMLRepo.class);
        temaXMLRepo = mock(TemaXMLRepo.class);
        notaXMLRepo = mock(NotaXMLRepo.class);
    }

    @Test
    public void testAddStudentMockito() {
        // A
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        var service = new Service(studentXMLRepo, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("1", "name", 1, "email");

        when(studentXMLRepo.save(student)).thenReturn(null);

        // A
        Student result = service.addStudent(student);

        // A
        assertNull(result);
    }

    @Test
    public void testAddAssignmentMockito() {
        // A
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();

        String filenameNota = "fisiere/Note.xml";

        NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        var service = new Service(studentXMLRepo, studentValidator, temaXMLRepo, temaValidator, notaXMLRepository, notaValidator);

        Student student = new Student("1", "name", 1, "email");
        Tema tema = new Tema("1", "description", 1, 1);

        when(studentXMLRepo.save(student)).thenReturn(null);
        when(temaXMLRepo.save(tema)).thenReturn(null);

        // A
        Student result = service.addStudent(student);
        Tema temaResult = service.addTema(tema);

        // A
        assertNull(temaResult);
    }

    @Test
    public void testAddGradeMockito() {
        // A
        NotaValidator notaValidator = new NotaValidator(studentXMLRepo, temaXMLRepo);
        var service = new Service(studentXMLRepo, new StudentValidator(), temaXMLRepo, new TemaValidator(), notaXMLRepo, notaValidator);

        Student student = new Student("1", "name", 1, "email");
        Tema tema = new Tema("1", "description", 14, 14);
        Nota nota = new Nota("1", "1", "1", 1, Curent.getStartDate().plusDays(98));

        when(studentXMLRepo.save(student)).thenReturn(null);
        when(temaXMLRepo.save(tema)).thenReturn(null);
        when(notaXMLRepo.save(nota)).thenReturn(null);
        when(studentXMLRepo.findOne("1")).thenReturn(student);
        when(temaXMLRepo.findOne("1")).thenReturn(tema);

        // A
        Student result = service.addStudent(student);
        Tema temaResult = service.addTema(tema);
        double notaResult = service.addNota(nota, "ok");

        // A
        assertEquals(1.0, notaResult);
    }
}
