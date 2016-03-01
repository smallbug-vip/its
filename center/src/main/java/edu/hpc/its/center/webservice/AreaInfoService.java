
package edu.hpc.its.center.webservice;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "AreaInfoService", targetNamespace = "http://area.its.hpc.edu", wsdlLocation = "http://127.0.0.1:8888/area?wsdl")
public class AreaInfoService
    extends Service
{

    private final static URL AREAINFOSERVICE_WSDL_LOCATION;
    private final static WebServiceException AREAINFOSERVICE_EXCEPTION;
    private final static QName AREAINFOSERVICE_QNAME = new QName("http://area.its.hpc.edu", "AreaInfoService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://127.0.0.1:8888/area?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        AREAINFOSERVICE_WSDL_LOCATION = url;
        AREAINFOSERVICE_EXCEPTION = e;
    }

    public AreaInfoService() {
        super(__getWsdlLocation(), AREAINFOSERVICE_QNAME);
    }

    public AreaInfoService(WebServiceFeature... features) {
        super(__getWsdlLocation(), AREAINFOSERVICE_QNAME, features);
    }

    public AreaInfoService(URL wsdlLocation) {
        super(wsdlLocation, AREAINFOSERVICE_QNAME);
    }

    public AreaInfoService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, AREAINFOSERVICE_QNAME, features);
    }

    public AreaInfoService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public AreaInfoService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns AreaInfoServiceImpl
     */
    @WebEndpoint(name = "AreaInfoServicePort")
    public AreaInfoServiceImpl getAreaInfoServicePort() {
        return super.getPort(new QName("http://area.its.hpc.edu", "AreaInfoServicePort"), AreaInfoServiceImpl.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns AreaInfoServiceImpl
     */
    @WebEndpoint(name = "AreaInfoServicePort")
    public AreaInfoServiceImpl getAreaInfoServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://area.its.hpc.edu", "AreaInfoServicePort"), AreaInfoServiceImpl.class, features);
    }

    private static URL __getWsdlLocation() {
        if (AREAINFOSERVICE_EXCEPTION!= null) {
            throw AREAINFOSERVICE_EXCEPTION;
        }
        return AREAINFOSERVICE_WSDL_LOCATION;
    }

}