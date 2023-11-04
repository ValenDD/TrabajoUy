
package logica.webservices;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import jakarta.xml.ws.Service;
import jakarta.xml.ws.WebEndpoint;
import jakarta.xml.ws.WebServiceClient;
import jakarta.xml.ws.WebServiceException;
import jakarta.xml.ws.WebServiceFeature;


/**
 * This class was generated by the XML-WS Tools.
 * XML-WS Tools 4.0.0
 * Generated source version: 3.0
 * 
 */
@WebServiceClient(name = "PublicadorService", targetNamespace = "http://webservices.logica/", wsdlLocation = "http://localhost:8085/webservices?wsdl")
public class PublicadorService
    extends Service
{

    private final static URL PUBLICADORSERVICE_WSDL_LOCATION;
    private final static WebServiceException PUBLICADORSERVICE_EXCEPTION;
    private final static QName PUBLICADORSERVICE_QNAME = new QName("http://webservices.logica/", "PublicadorService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8085/webservices?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        PUBLICADORSERVICE_WSDL_LOCATION = url;
        PUBLICADORSERVICE_EXCEPTION = e;
    }

    public PublicadorService() {
        super(__getWsdlLocation(), PUBLICADORSERVICE_QNAME);
    }

    public PublicadorService(WebServiceFeature... features) {
        super(__getWsdlLocation(), PUBLICADORSERVICE_QNAME, features);
    }

    public PublicadorService(URL wsdlLocation) {
        super(wsdlLocation, PUBLICADORSERVICE_QNAME);
    }

    public PublicadorService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, PUBLICADORSERVICE_QNAME, features);
    }

    public PublicadorService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public PublicadorService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns Publicador
     */
    @WebEndpoint(name = "PublicadorPort")
    public Publicador getPublicadorPort() {
        return super.getPort(new QName("http://webservices.logica/", "PublicadorPort"), Publicador.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link jakarta.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns Publicador
     */
    @WebEndpoint(name = "PublicadorPort")
    public Publicador getPublicadorPort(WebServiceFeature... features) {
        return super.getPort(new QName("http://webservices.logica/", "PublicadorPort"), Publicador.class, features);
    }

    private static URL __getWsdlLocation() {
        if (PUBLICADORSERVICE_EXCEPTION!= null) {
            throw PUBLICADORSERVICE_EXCEPTION;
        }
        return PUBLICADORSERVICE_WSDL_LOCATION;
    }

}
