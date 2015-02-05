package com.carfinance.api;

/**
 * 
 * @author jiangyin
 *
 */
public class ServerException extends Exception {

	private static final long	serialVersionUID	= -1312685243776973542L;
	private int code;
    public ServerException(String msg) {
        super(msg);
    }

    public ServerException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }

}
