package com.testFileUpload.common.error.common;

import org.springframework.http.HttpStatus;

/**
 * 统一异常编码接口，后续拆分server  和 client异常码
 * @author CAIFUCHENG3
 */
public interface ErrorCode extends MessageKey {
	HttpStatus httpStatus();
}
