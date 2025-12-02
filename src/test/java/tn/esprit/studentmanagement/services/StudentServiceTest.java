package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Student;
import tn.esprit.studentmanagement.repositories.StudentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void getAllStudents_returnsList() {
        Student s = new Student();
        s.setIdStudent(1L);
        s.setFirstName("John");
        when(studentRepository.findAll()).thenReturn(List.of(s));

        List<Student> result = studentService.getAllStudents();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void getStudentById_returnsStudent() {
        Student s = new Student();
        s.setIdStudent(2L);
        s.setFirstName("Jane");
        when(studentRepository.findById(2L)).thenReturn(Optional.of(s));

        Student result = studentService.getStudentById(2L);

        assertNotNull(result);
        assertEquals(2L, result.getIdStudent());
        assertEquals("Jane", result.getFirstName());
    }

    @Test
    void saveStudent_callsRepositoryAndReturns() {
        Student s = new Student();
        s.setFirstName("New");
        when(studentRepository.save(s)).thenReturn(s);

        Student saved = studentService.saveStudent(s);

        assertSame(s, saved);
        verify(studentRepository, times(1)).save(s);
    }

    @Test
    void deleteStudent_callsRepository() {
        doNothing().when(studentRepository).deleteById(3L);

        studentService.deleteStudent(3L);

        verify(studentRepository).deleteById(3L);
    }
}
