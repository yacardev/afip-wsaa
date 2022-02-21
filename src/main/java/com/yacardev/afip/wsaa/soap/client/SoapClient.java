package com.yacardev.afip.wsaa.soap.client;

import com.yacardev.afip.wsaa.soap.wsdl.LoginCms;
import com.yacardev.afip.wsaa.soap.wsdl.LoginCmsResponse;
import com.yacardev.afip.wsaa.soap.wsdl.LoginFault;
import com.yacardev.afip.wsaa.soap.wsdl.LoginFault_Exception;
import org.dom4j.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapFaultException;

import java.io.Reader;
import java.io.StringReader;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;

@Service
public class SoapClient {

    @Autowired
    private Jaxb2Marshaller jaxb2Marshaller;

    private WebServiceTemplate webServiceTemplate;

    @Value("${AFIP.WSAA.URI}")
    private String AFIP_WSAA_URI;

    public LoginCmsResponse getCMS(LoginCms request) {
        webServiceTemplate = new WebServiceTemplate(jaxb2Marshaller);
        System.out.println("web service pruebas");
        System.out.println("request: "+request.getIn0());
        LoginCmsResponse response = null;
        response = (LoginCmsResponse) webServiceTemplate.marshalSendAndReceive(AFIP_WSAA_URI, request);

        //System.out.println("cms: "+cms.getIn0());
        // cms.getIn0()
        return response;
    }
}