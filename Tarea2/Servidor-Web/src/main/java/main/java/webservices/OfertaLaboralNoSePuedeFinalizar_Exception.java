
package main.java.webservices;

import jakarta.xml.ws.WebFault;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebFault(name = "OfertaLaboralNoSePuedeFinalizar", targetNamespace = "http://webservices.java.main/")
public class OfertaLaboralNoSePuedeFinalizar_Exception
    extends Exception
{

    /**
     * Java type that goes as soapenv:Fault detail element.
     * 
     */
    private OfertaLaboralNoSePuedeFinalizar faultInfo;

    /**
     * 
     * @param faultInfo
     * @param message
     */
    public OfertaLaboralNoSePuedeFinalizar_Exception(String message, OfertaLaboralNoSePuedeFinalizar faultInfo) {
        super(message);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @param cause
     * @param faultInfo
     * @param message
     */
    public OfertaLaboralNoSePuedeFinalizar_Exception(String message, OfertaLaboralNoSePuedeFinalizar faultInfo, Throwable cause) {
        super(message, cause);
        this.faultInfo = faultInfo;
    }

    /**
     * 
     * @return
     *     returns fault bean: main.java.webservices.OfertaLaboralNoSePuedeFinalizar
     */
    public OfertaLaboralNoSePuedeFinalizar getFaultInfo() {
        return faultInfo;
    }

}
