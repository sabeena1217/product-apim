package org.wso2.carbon.apimgt.rest.integration.tests.microgateway;

/**
 * This error handler interface must use in all exceptions class, for example please see APIManagementException class.
 */
public interface ErrorHandler {
    /**
     * Get error code that defined in the enum
     * @return error code
     */
    long getErrorCode();

    /**
     * Get error message that defined in the enum
     * @return  error message
     */
    String getErrorMessage();

    /**
     * Get error description that defined in the enum
     *
     * @return  error description.
     */
    default String getErrorDescription() {
        return null;
    }

    /**
     * Get Http status code that defined in the enum
     *
     * @return  error code.
     */
    default int getHttpStatusCode() {
        return 500;
    }
}
