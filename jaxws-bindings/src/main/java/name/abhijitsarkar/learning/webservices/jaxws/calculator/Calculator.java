
package name.abhijitsarkar.learning.webservices.jaxws.calculator;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "Calculator", targetNamespace = "http://abhijitsarkar.name/learning/webservices/jaxws/calculator/")
@HandlerChain(file = "Calculator_handler.xml")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Calculator {


    /**
     * 
     * @param parameters
     * @return
     *     returns name.abhijitsarkar.learning.webservices.jaxws.calculator.AddResponse
     */
    @WebMethod
    @WebResult(name = "addResponse", targetNamespace = "http://abhijitsarkar.name/learning/webservices/jaxws/calculator/", partName = "parameters")
    public AddResponse add(
        @WebParam(name = "addRequest", targetNamespace = "http://abhijitsarkar.name/learning/webservices/jaxws/calculator/", partName = "parameters")
        AddRequest parameters);

    /**
     * 
     * @param parameters
     * @return
     *     returns name.abhijitsarkar.learning.webservices.jaxws.calculator.SubtractResponse
     */
    @WebMethod
    @WebResult(name = "subtractResponse", targetNamespace = "http://abhijitsarkar.name/learning/webservices/jaxws/calculator/", partName = "parameters")
    public SubtractResponse subtract(
        @WebParam(name = "subtractRequest", targetNamespace = "http://abhijitsarkar.name/learning/webservices/jaxws/calculator/", partName = "parameters")
        SubtractRequest parameters);

}
