package com.example.user.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="OrderDatabase")
public class Order {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private Long medicineId;
        private int quantity;
        private String status; 
        
        public Order() {
        	
        }
        public Order(Long id, Long medicineId, int quantity, String status) {
        	
        	this.id=id;
        	this.medicineId= medicineId;
        	this.quantity=quantity;
        	this.status=status;
        }
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public Long getMedicineId() {
			return medicineId;
		}
		public void setMedicineId(Long medicineId) {
			this.medicineId = medicineId;
		}
		public int getQuantity() {
			return quantity;
		}
		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
        
        
        
        
}
     