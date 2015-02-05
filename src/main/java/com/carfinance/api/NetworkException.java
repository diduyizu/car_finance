package com.carfinance.api;

/**
 * 
 * @author jiangyin
 *
 */
public class NetworkException extends Exception {
    /**
	 * 
	 */
	private static final long	serialVersionUID	= -1781185364762048223L;

	public NetworkException(String msg) {
        super(msg);
    }

    public NetworkException(Throwable cause) {
        super(cause);
    }

    public NetworkException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
