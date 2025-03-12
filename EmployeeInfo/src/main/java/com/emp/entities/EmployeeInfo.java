package com.emp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class EmployeeInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String empId;
    private String name;
   // private String dob;
    @Temporal(TemporalType.DATE)  // Use @Temporal to map to a SQL DATE column
    private Date dob;
    
    private String mobNumber;
    private Boolean available;
    
    @Lob
    private byte[] profileImageData;  // The file data will be stored here

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdAt;

//    @PrePersist
//    public void prePersist() {
//        this.createdAt = new Date();
//    }
}
