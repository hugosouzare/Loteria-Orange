package com.loteriaorange.loteriaorange.services.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Error {

	private int status;
	private String msg;
	private Long timeStamp;
	
	
}
