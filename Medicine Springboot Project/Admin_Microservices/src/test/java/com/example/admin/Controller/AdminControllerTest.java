package com.example.admin.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.example.admin.Model.Admin;
import com.example.admin.Service.Adminservice;
import static org.springframework.http.HttpStatus.OK;

import java.util.Arrays;
import java.util.List;
class AdminControllerTest {
	
	@Mock
    private Adminservice adminService;

    @InjectMocks
    private AdminController adminController;
    
   
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testAddMedicine() {
        Admin admin = new Admin(1L, "Paracetamol", "Dolo650", 10.5);
        when(adminService.addMedicine(any(Admin.class))).thenReturn(admin);

        ResponseEntity<Admin> response = adminController.addMedicine(admin);

        assertEquals(OK, response.getStatusCode());
        assertEquals(admin, response.getBody());
    }

    @Test
    void testUpdateMedicine() {
    	 Long id = 1L;
        Admin updatedAdmin = new Admin(id, "Ibuprofen", "Pain reliever", 12.0);

        when(adminService.updateMedicine(eq(id), any(Admin.class))).thenReturn(updatedAdmin);

        ResponseEntity<Admin> response = adminController.updateMedicine(id, updatedAdmin);

        assertEquals(OK, response.getStatusCode());
        assertEquals(updatedAdmin, response.getBody());
       
    }
    @Test
    void testDeleteMedicine() {
        Long id = 1L;
        doNothing().when(adminService).deleteMedicine(id);

        ResponseEntity<String> response = adminController.deleteMedicine(id);

        assertEquals(OK, response.getStatusCode());
        assertEquals("Medicine deleted successfully", response.getBody());
       
    }
    @Test
    void testGetAllMedicines() {
        List<Admin> adminList = Arrays.asList(
                new Admin(1L, "Paracetamol", "Dolo650", 10.5),
                new Admin(2L, "Ibuprofen", "Pain reliever", 12.0)
        );

        when(adminService.getAllMedicines()).thenReturn(adminList);

        ResponseEntity<List<Admin>> response = adminController.getAllMedicines();

        assertEquals(OK, response.getStatusCode());
        assertEquals(adminList, response.getBody());
     
    }

}
