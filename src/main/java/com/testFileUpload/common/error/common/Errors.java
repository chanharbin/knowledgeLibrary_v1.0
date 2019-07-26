package com.testFileUpload.common.error.common;


import com.testFileUpload.common.error.server.CommonError;

/**
 * 异常统一抛出格式化：Errors.wrap()
 * @author CAIFUCHENG3
 */
public class Errors {

	public static ServiceException wrap(ErrorCode code, Object... args) {
		return wrap(null, code, args);
	}

	public static ServiceException wrap(Throwable cause) {
		if (cause instanceof ServiceException) {
			/**
			 *  避免重复处理已经处理过的错误
			 */
			return (ServiceException) cause;
		}
		return wrap(cause, CommonError.UNEXPECTED, cause.toString());
	}

	public static ServiceException wrap(Throwable cause, ErrorCode code, Object... args) {
		ServiceException e = new ServiceException(cause, code, args);
		return e;
	}
}
