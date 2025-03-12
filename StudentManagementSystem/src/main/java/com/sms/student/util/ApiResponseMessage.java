package com.sms.student.util;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class ApiResponseMessage {

	private String message;
	private Boolean success;
	private HttpStatus status;
}
