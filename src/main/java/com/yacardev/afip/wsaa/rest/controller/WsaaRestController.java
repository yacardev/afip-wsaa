package com.yacardev.afip.wsaa.rest.controller;

import java.util.concurrent.atomic.AtomicLong;

import com.yacardev.afip.wsaa.rest.response.ResponseHandler;
import com.yacardev.afip.wsaa.soap.wsdl.LoginCms;
import com.yacardev.afip.wsaa.soap.wsdl.LoginCmsResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.yacardev.afip.wsaa.soap.client.SoapClient;


@RestController
public class WsaaRestController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    private SoapClient soapClient;

/*
    public WsaaRestController(SoapClient soapClient) {
        this.soapClient = soapClient;
    }*/

    @GetMapping(path = "/test", produces = "application/json")
    public ResponseEntity getToken() throws Exception{
        LoginCms cms = new LoginCms();
        String reqErr = null;
        LoginCmsResponse loginCmsResponse = null;
        ResponseEntity responseEntity;
        //try{
            cms.setIn0("<?xml version=\"1.0\" encoding=\"UTF\u00AD8\"?>\n" +
                    "<loginTicketRequest version=\"1.0\">\n" +
                    "    <header>\n" +
                    "        <source>cn=yacardevtest01,ou=facturacion,o=yacardevtest01,c=ar,serialNumber=CUIT 20312097916</source>\n" +
                    "        <destination>cn=wsaa,o=afip,c=ar,serialNumber=CUIT 20312097916</destination>\n" +
                    "        <uniqueId>4325399</uniqueId>\n" +
                    "        <generationTime>2001\u00AD12\u00AD31T12:00:00\u00AD03:00</generationTime>\n" +
                    "        <expirationTime>2001\u00AD12\u00AD31T12:10:00\u00AD03:00</expirationTime>\n" +
                    "    </header>\n" +
                    "    <service>wsfe</service>\n" +
                    "</loginTicketRequest>");
            try {
                loginCmsResponse = soapClient.getCMS(cms);
                //return ResponseEntity.status(200).build();
            }catch (Exception ex){
                //ex.printStackTrace();
                System.out.println("ex 1: "+ex.getMessage());
                reqErr = ex.getMessage();
                //return ResponseEntity.status(400).build();
            }

        if(loginCmsResponse!=null){
            //responseEntity = ResponseEntity.ok(loginCmsResponse);
            return ResponseHandler.generateResponse("OK", HttpStatus.OK,loginCmsResponse);
        }else{
            //responseEntity = ResponseEntity.status(400).body(reqErr);
            return ResponseHandler.generateResponse(reqErr,HttpStatus.EXPECTATION_FAILED,null);
        }
        //return responseEntity;
    }
}
