package com.example.admin.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.admin.Model.Admin;
import com.example.admin.Repository.AdminRepository;

@Service
public class Adminservice {
	
	@Autowired
    private AdminRepository adminRepository;
	
	
	
	public Admin addMedicine(Admin admin) {
        return adminRepository.save(admin);
    }
	
	public Admin updateMedicine(Long id, Admin updatedMedicine) {
        Admin existingMedicine = adminRepository.findById(id).orElseThrow(() -> new RuntimeException("Medicine not found"));
        existingMedicine.setName(updatedMedicine.getName());
        existingMedicine.setdescription(updatedMedicine.getDescription());
        existingMedicine.setPrice(updatedMedicine.getPrice());
        return adminRepository.save(existingMedicine);
    }
	
	 public void deleteMedicine(Long id) {
	        adminRepository.deleteById(id);
	    }

	    public List<Admin> getAllMedicines() {
	        return adminRepository.findAll();
	    }



}
