package com.example.admin.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.admin.Model.Admin;
import com.example.admin.Service.Adminservice;

@RestController
@RequestMapping("/api/medicines")
public class AdminController {
	
	 @Autowired
	    private Adminservice adminService;
	 
	 @PostMapping
	    public ResponseEntity<Admin> addMedicine(@RequestBody Admin admin) {
	        return ResponseEntity.ok(adminService.addMedicine(admin));
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Admin> updateMedicine(@PathVariable Long id, @RequestBody Admin admin) {
	        return ResponseEntity.ok(adminService.updateMedicine(id, admin));
	    }
	    
	  

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deleteMedicine(@PathVariable Long id) {
	        adminService.deleteMedicine(id);
	        return ResponseEntity.ok("Medicine deleted successfully");
	    }

	    @GetMapping
	    public ResponseEntity<List<Admin>> getAllMedicines() {
	     return ResponseEntity.ok(adminService.getAllMedicines());
	    	
	    }

}
