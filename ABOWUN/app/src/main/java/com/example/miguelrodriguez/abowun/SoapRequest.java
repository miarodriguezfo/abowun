package com.example.miguelrodriguez.abowun;

/**
 * Created by Miguel Rodriguez on 17/10/2015.
 */

import android.util.Log;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.io.IOException;
import java.net.Proxy;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

public class SoapRequest {

    private static final boolean DEBUG_SOAP_REQUEST_RESPONSE = true;
    private static final String NAMESPACE = "http://test/";
    // private static final String MAIN_REQUEST_URL = "http://192.168.0.18:8080/tpi/test";
    private static final String MAIN_REQUEST_URL = "http://192.168.173.1:8080/tpi/test";
    private static final String SOAP_ACTION = "";
    private static String SESSION_ID;

    private final SoapSerializationEnvelope getSoapSerializationEnvelope(SoapObject request) {
        SoapSerializationEnvelope envelope =     new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = false;
        envelope.implicitTypes = true;
        envelope.setAddAdornments(false);
        envelope.setOutputSoapObject(request);

        return envelope;
    }


    public ArrayList<String> sendImage(String imData) {
        String methodname = "Analizarimagen";
        ArrayList<String> data = new ArrayList<String>();


        SoapObject request = new SoapObject(NAMESPACE, methodname);
        request.addProperty("imageByte",imData);

        SoapSerializationEnvelope envelope = getSoapSerializationEnvelope(request);

        HttpTransportSE ht = getHttpTransportSE();
        try {
            ht.setXmlVersionTag("<?xml version=\"1.0\" encoding= \"UTF-8\"?>");
            ht.call(SOAP_ACTION, envelope);
            testHttpResponse(ht);
            //SoapPrimitive resultsString = (SoapPrimitive)envelope.getResponse();

            SoapObject result = (SoapObject) envelope.bodyIn;
            int count = result.getPropertyCount();

            for (int i = 0; i < count; i++)
            {
                data.add(result.getPropertyAsString(i));
            }

            List<HeaderProperty> COOKIE_HEADER = (List<HeaderProperty>) ht.getServiceConnection().getResponseProperties();

            for (int i = 0; i < COOKIE_HEADER.size(); i++) {
                String key = COOKIE_HEADER.get(i).getKey();
                String value = COOKIE_HEADER.get(i).getValue();

                if (key != null && key.equalsIgnoreCase("set-cookie")) {
                    SoapRequest.SESSION_ID = value.trim();
                    Log.v("SOAP RETURN", "Cookie :" + SoapRequest.SESSION_ID);
                    break;
                }
            }

        } catch (SocketTimeoutException t) {
            t.printStackTrace();
        } catch (IOException i) {
            i.printStackTrace();
        } catch (Exception q) {
            q.printStackTrace();
        }

        System.out.println("AQUIIIIIIIIIIIIIIIIIIIII " + data);
        return data;
    }

    private final HttpTransportSE getHttpTransportSE() {
        HttpTransportSE ht = new HttpTransportSE(Proxy.NO_PROXY,MAIN_REQUEST_URL,60000);
        ht.debug = true;
        ht.setXmlVersionTag("<!--?xml version=\"1.0\" encoding= \"UTF-8\" ?-->");
        return ht;
    }

    private final void testHttpResponse(HttpTransportSE ht) {
        ht.debug = DEBUG_SOAP_REQUEST_RESPONSE;
        if (DEBUG_SOAP_REQUEST_RESPONSE) {
            Log.v("SOAP RETURN", "Request XML:\n" + ht.requestDump);
            Log.v("SOAP RETURN", "\n\n\nResponse XML:\n" + ht.responseDump);
        }
    }

    private final List<HeaderProperty> getHeader() {
        List<HeaderProperty> header = new ArrayList<HeaderProperty>();
        HeaderProperty headerPropertyObj = new HeaderProperty("cookie", SoapRequest.SESSION_ID);
        header.add(headerPropertyObj);
        return header;
    }
}
