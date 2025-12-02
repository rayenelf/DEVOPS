package tn.esprit.studentmanagement.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.studentmanagement.entities.Department;
import tn.esprit.studentmanagement.repositories.DepartmentRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentService departmentService;

    @Test
    void getAllDepartments_returnsList() {
        Department d = new Department();
        d.setIdDepartment(1L);
        d.setName("CS");
        when(departmentRepository.findAll()).thenReturn(List.of(d));

        List<Department> result = departmentService.getAllDepartments();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("CS", result.get(0).getName());
    }

    @Test
    void getDepartmentById_returnsDepartment() {
        Department d = new Department();
        d.setIdDepartment(2L);
        d.setName("Math");
        when(departmentRepository.findById(2L)).thenReturn(Optional.of(d));

        Department result = departmentService.getDepartmentById(2L);

        assertNotNull(result);
        assertEquals(2L, result.getIdDepartment());
        assertEquals("Math", result.getName());
    }

    @Test
    void saveDepartment_callsRepositoryAndReturns() {
        Department d = new Department();
        d.setName("Bio");
        when(departmentRepository.save(d)).thenReturn(d);

        Department saved = departmentService.saveDepartment(d);

        assertSame(d, saved);
        verify(departmentRepository, times(1)).save(d);
    }

    @Test
    void deleteDepartment_callsRepository() {
        doNothing().when(departmentRepository).deleteById(5L);

        departmentService.deleteDepartment(5L);

        verify(departmentRepository).deleteById(5L);
    }
}
