package org.tbwork.anole.hub.exceptions;

import org.tbwork.anole.common.ConfigType;
 
public class AuthenticationNotReadyException extends RuntimeException {

	private static String errorMessage = "This anole client has not passed the identity verification yet, please verify first";
	
	public AuthenticationNotReadyException()
    {
    	super(errorMessage);
    }
	 
	
}