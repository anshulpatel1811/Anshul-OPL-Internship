package com.ems.employee.proxy;

import java.util.Date;

import com.ems.employee.entity.EmployeeInfo;
import com.ems.employee.utils.ErrorResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class EmployeeProfileImageProxy {

	private Long id;
	private String fileName;
	private String fileId;
	private Long fileSize;
	private String contentType;
	private byte[] fileData;
	
	private ErrorResponse response;
	
	private Date date;

	public EmployeeProfileImageProxy(ErrorResponse response) {
		super();
		this.response = response;
	}
	
	//private EmployeeInfo employeeInfo;

	
}
