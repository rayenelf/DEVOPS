package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Enrollment;
import tn.esprit.studentmanagement.repositories.EnrollmentRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentServiceTest {

    @Mock
    private EnrollmentRepository enrollmentRepository;

    @InjectMocks
    private EnrollmentService enrollmentService;

    @Test
    void getAllEnrollments_returnsList() {
        Enrollment e = new Enrollment();
        e.setIdEnrollment(1L);
        e.setEnrollmentDate(LocalDate.now());
        when(enrollmentRepository.findAll()).thenReturn(List.of(e));

        List<Enrollment> result = enrollmentService.getAllEnrollments();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getIdEnrollment());
    }

    @Test
    void getEnrollmentById_returnsEnrollment() {
        Enrollment e = new Enrollment();
        e.setIdEnrollment(2L);
        when(enrollmentRepository.findById(2L)).thenReturn(Optional.of(e));

        Enrollment result = enrollmentService.getEnrollmentById(2L);

        assertNotNull(result);
        assertEquals(2L, result.getIdEnrollment());
    }

    @Test
    void saveEnrollment_callsRepositoryAndReturns() {
        Enrollment e = new Enrollment();
        when(enrollmentRepository.save(e)).thenReturn(e);

        Enrollment saved = enrollmentService.saveEnrollment(e);

        assertSame(e, saved);
        verify(enrollmentRepository, times(1)).save(e);
    }

    @Test
    void deleteEnrollment_callsRepository() {
        doNothing().when(enrollmentRepository).deleteById(7L);

        enrollmentService.deleteEnrollment(7L);

        verify(enrollmentRepository).deleteById(7L);
    }
}
