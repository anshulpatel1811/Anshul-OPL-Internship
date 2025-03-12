package com.emp.security.proxy;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageProxy {

    private String id;
    private String fileName;
    private String fileId;
    private Long fileSize;
    private String contantType;
    @Lob 	
    private byte[] fileData;
}
