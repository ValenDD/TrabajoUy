
package logica.webservices;

import jakarta.xml.ws.WebFault;

/**
 * This class was generated by the XML-WS Tools. XML-WS Tools 4.0.0 Generated
 * source version: 3.0
 * 
 */
@WebFault(name = "TipoDePublicacionYaFueIngresado", targetNamespace = "http://webservices.logica/")
public class TipoDePublicacionYaFueIngresado_Exception extends Exception
{

	/**
	 * Java type that goes as soapenv:Fault detail element.
	 * 
	 */
	private TipoDePublicacionYaFueIngresado faultInfo;

	/**
	 * 
	 * @param faultInfo
	 * @param message
	 */
	public TipoDePublicacionYaFueIngresado_Exception(String message, TipoDePublicacionYaFueIngresado faultInfo)
	{
		super(message);
		this.faultInfo = faultInfo;
	}

	/**
	 * 
	 * @param cause
	 * @param faultInfo
	 * @param message
	 */
	public TipoDePublicacionYaFueIngresado_Exception(String message, TipoDePublicacionYaFueIngresado faultInfo,
			Throwable cause)
	{
		super(message, cause);
		this.faultInfo = faultInfo;
	}

	/**
	 * 
	 * @return returns fault bean:
	 *         logica.webservices.TipoDePublicacionYaFueIngresado
	 */
	public TipoDePublicacionYaFueIngresado getFaultInfo()
	{
		return faultInfo;
	}

}
