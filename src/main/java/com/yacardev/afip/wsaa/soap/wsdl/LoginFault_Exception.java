package com.yacardev.afip.wsaa.soap.wsdl;

import javax.xml.ws.WebFault;

@WebFault(name = "fault", targetNamespace = "https://wsaahomo.afip.gov.ar/ws/services/LoginCms")
public class LoginFault_Exception extends Exception {

    private LoginFault fault;

    public LoginFault_Exception() {
        super();
    }

    public LoginFault_Exception(String message) {
        super(message);
    }

    public LoginFault_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public LoginFault_Exception(String message, LoginFault fault) {
        super(message);
        this.fault = fault;
    }

    public LoginFault_Exception(String message, LoginFault fault, Throwable cause) {
        super(message, cause);
        this.fault = fault;
    }

    public LoginFault getFaultInfo() {
        return this.fault;
    }
}
