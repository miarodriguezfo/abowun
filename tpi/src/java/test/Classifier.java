/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

/**
 *
 * @author Miguel Rodriguez
 */

import java.util.logging.Level;
import java.util.logging.Logger;
import matlabcontrol.*;
public class Classifier {
    
    MatlabProxy proxy;
    MatlabProxyFactory factory;
    public Classifier() throws MatlabConnectionException {
         // create proxy
        
        
    }
    
    public void clasificar(){
        try {
             MatlabProxyFactoryOptions options =
            new MatlabProxyFactoryOptions.Builder()
                .setUsePreviouslyControlledSession(true)
                .build();
            factory = new MatlabProxyFactory(options);
            proxy = factory.getProxy();
            proxy.eval("imshow('D:\\Unal\\TPI\\serverTest\\hello2.jpg')");
            proxy.disconnect();
        } catch (MatlabInvocationException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MatlabConnectionException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
