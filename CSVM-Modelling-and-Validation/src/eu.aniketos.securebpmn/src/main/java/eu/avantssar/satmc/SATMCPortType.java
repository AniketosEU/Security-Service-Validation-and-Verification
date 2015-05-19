
package eu.avantssar.satmc;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.1.6 in JDK 6
 * Generated source version: 2.1
 * 
 */
@WebService(name = "SATMCPortType", targetNamespace = "http://avantssar.eu/satmc/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface SATMCPortType {


    /**
     * 
     * @param stepCompression
     * @param mutex
     * @param asLanSpec
     * @param max
     * @param outputFormat
     * @param otherOptions
     * @param exec
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://avantssar.eu/satmc/validate")
    @WebResult(name = "counterexample", partName = "counterexample")
    public String validate(
        @WebParam(name = "ASLanSpec", partName = "ASLanSpec")
        String asLanSpec,
        @WebParam(name = "max", partName = "max")
        int max,
        @WebParam(name = "mutex", partName = "mutex")
        boolean mutex,
        @WebParam(name = "stepCompression", partName = "stepCompression")
        boolean stepCompression,
        @WebParam(name = "exec", partName = "exec")
        boolean exec,
        @WebParam(name = "outputFormat", partName = "outputFormat")
        String outputFormat,
        @WebParam(name = "otherOptions", partName = "otherOptions")
        String otherOptions);

    /**
     * 
     * @param request
     * @return
     *     returns java.lang.String
     */
    @WebMethod(action = "http://avantssar.eu/satmc/version")
    @WebResult(name = "info", partName = "info")
    public String version(
        @WebParam(name = "request", partName = "request")
        String request);

}