package com.testFileUpload.common.error.client;


import com.testFileUpload.common.error.common.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * 集成ErrorCode，定性为客户端校验性异常
 * @author CAIFUCHENG3
 */
public interface ClientError extends ErrorCode {

	@Override
	default HttpStatus httpStatus() {
		return HttpStatus.BAD_REQUEST;
	}
}
