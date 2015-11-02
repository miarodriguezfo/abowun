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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import matlabcontrol.*;
public class Classifier {
    
    MatlabProxy proxy;
    MatlabProxyFactory factory;
    public Classifier() throws MatlabConnectionException {
         // create proxy
        
        
    }
    
    public ArrayList<Integer> clasificar(){
        ArrayList<Integer> res = new ArrayList<Integer>();
        
        try {
             MatlabProxyFactoryOptions options =
            new MatlabProxyFactoryOptions.Builder()
                .setUsePreviouslyControlledSession(true)
                .build();
            factory = new MatlabProxyFactory(options);
            proxy = factory.getProxy();
            //proxy.eval("imshow('D:\\Unal\\TPI\\serverTest\\hello2.jpg')");
            Object[] d = proxy.returningEval("rand(10,1)",1);
            double[] a= (double[]) d[0];
            
            ArrayList<Double> index = new ArrayList<Double>();
            ArrayList<Double> b = new ArrayList<Double>();
            for(int i=0;i<a.length;i++){
                if(a[i]>0.8){
                    index.add(a[i]);
                }
                b.add(a[i]);
                System.out.printf("%f",a[i]);
                System.out.println();                
            }
            Collections.sort(index, Collections.reverseOrder());
            
            Iterator<Double> indexIterator = index.iterator();
		while (indexIterator.hasNext()) {
                        res.add(b.indexOf(indexIterator.next()));
			System.out.println();
		}            
            
            
            proxy.disconnect();
            
        } catch (MatlabInvocationException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MatlabConnectionException ex) {
            Logger.getLogger(Classifier.class.getName()).log(Level.SEVERE, null, ex);
        }
        return res;
    }
}
