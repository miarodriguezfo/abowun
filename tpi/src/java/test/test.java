/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceException;
import matlabcontrol.MatlabConnectionException;

/**
 *
 * @author Miguel Rodriguez
 */
@WebService(serviceName = "test")
public class test {

    
    Classifier clasificador;

    
    @WebMethod(operationName = "inicializar")
    public void hello() {
        try {
            clasificador = new Classifier();
        } catch (MatlabConnectionException ex) {
            Logger.getLogger(test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Web service operation
     */
    @WebMethod(operationName = "Analizarimagen")
    public ArrayList<Integer> Analizarimagen(@WebParam(name = "imageByte") String imageByte) {
        //TODO write your implementation code here:
        String filePath = "D:/Unal/TPI/serverTest/hello2.jpg";
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            BufferedOutputStream outputStream = new BufferedOutputStream(fos);
                
            byte [] decoded = Base64.getDecoder().decode(imageByte); 
            
            outputStream.write(decoded);
            outputStream.close();
             
            System.out.println("Received file: " + filePath);
             
            return clasificador.clasificar();
        } catch (IOException ex) {
            
            //System.err.println(ex);
            throw new WebServiceException(ex);
        }
    }

}
