
package edu.hpc.its.center.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the edu.hpc.its.center.webservice package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetLight2TimeResponse_QNAME = new QName("http://area.its.hpc.edu", "getLight2TimeResponse");
    private final static QName _GetLight2Time_QNAME = new QName("http://area.its.hpc.edu", "getLight2Time");
    private final static QName _GetAllExpInfoResponse_QNAME = new QName("http://area.its.hpc.edu", "getAllExpInfoResponse");
    private final static QName _GetAllExpInfo_QNAME = new QName("http://area.its.hpc.edu", "getAllExpInfo");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: edu.hpc.its.center.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetAllExpInfoResponse }
     * 
     */
    public GetAllExpInfoResponse createGetAllExpInfoResponse() {
        return new GetAllExpInfoResponse();
    }

    /**
     * Create an instance of {@link GetAllExpInfo }
     * 
     */
    public GetAllExpInfo createGetAllExpInfo() {
        return new GetAllExpInfo();
    }

    /**
     * Create an instance of {@link GetLight2Time }
     * 
     */
    public GetLight2Time createGetLight2Time() {
        return new GetLight2Time();
    }

    /**
     * Create an instance of {@link GetLight2TimeResponse }
     * 
     */
    public GetLight2TimeResponse createGetLight2TimeResponse() {
        return new GetLight2TimeResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLight2TimeResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://area.its.hpc.edu", name = "getLight2TimeResponse")
    public JAXBElement<GetLight2TimeResponse> createGetLight2TimeResponse(GetLight2TimeResponse value) {
        return new JAXBElement<GetLight2TimeResponse>(_GetLight2TimeResponse_QNAME, GetLight2TimeResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetLight2Time }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://area.its.hpc.edu", name = "getLight2Time")
    public JAXBElement<GetLight2Time> createGetLight2Time(GetLight2Time value) {
        return new JAXBElement<GetLight2Time>(_GetLight2Time_QNAME, GetLight2Time.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllExpInfoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://area.its.hpc.edu", name = "getAllExpInfoResponse")
    public JAXBElement<GetAllExpInfoResponse> createGetAllExpInfoResponse(GetAllExpInfoResponse value) {
        return new JAXBElement<GetAllExpInfoResponse>(_GetAllExpInfoResponse_QNAME, GetAllExpInfoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetAllExpInfo }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://area.its.hpc.edu", name = "getAllExpInfo")
    public JAXBElement<GetAllExpInfo> createGetAllExpInfo(GetAllExpInfo value) {
        return new JAXBElement<GetAllExpInfo>(_GetAllExpInfo_QNAME, GetAllExpInfo.class, null, value);
    }

}
