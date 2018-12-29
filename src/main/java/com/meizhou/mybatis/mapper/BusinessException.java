package com.meizhou.mybatis.mapper;

/**
 * Created by meizhou on 2017/10/2.
 */
public class BusinessException extends RuntimeException {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8724437682527877749L;

	public BusinessException() {
        super();
    }

    public BusinessException(Exception e) {
        super(e);
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }
}
