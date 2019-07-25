package com.testFileUpload.common;

import com.testFileUpload.common.error.common.Errors;
import com.testFileUpload.common.error.common.ServiceException;
import com.testFileUpload.common.error.server.CommonError;
import com.testFileUpload.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.rmi.NoSuchObjectException;

/**
 * 基础版Controller，后续Controller继承该类，统一返回和异常处理
 * @author CAIFUCHENG3
 */
@RestController
public class BaseController {
    private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
    /**
     * 统一异常处理类
     *
     * @param ex
     * @return
     */
    @ExceptionHandler({RuntimeException.class, Exception.class})
    public Object exp(HttpServletRequest request, Exception ex) {

        /**
         * 提示消息
         */
        String msg ;
        /**
         * 原始异常堆栈
         */
        String detailErrorMsgStack ;

        logger.error(ex.getMessage() , ex);

        /**
         * 异常分类处理：业务异常或其他异常
         */
        if (ex instanceof ServiceException) {

            msg = ex.getMessage();
            detailErrorMsgStack = StringUtil.printExceptionToStr(ex);
        }  else {
            CommonError code = this.handleSystemException(ex);
            Exception curEx ;
            curEx = Errors.wrap(code);
            msg = curEx.getMessage();
            detailErrorMsgStack = StringUtil.printExceptionToStr(curEx);
        }
        /**
         * 返回错误消息
         */
        ResultObject result = new ResultObject();
        result.setCode(ResultObject.FAILURE);
        result.setMsg(msg);
        result.setError(StringUtil.null2Str(detailErrorMsgStack));
        return result;
    }

    /**
     * 适配系统异常方法，根据异常类型来找到对应的错误码，
     *
     * @param throwable
     *            底层异常
     *
     * @return String 系统错误代码
     */
    private CommonError handleSystemException(Throwable throwable) {
        /**
         * 错误代码
         */
        CommonError errorCode = CommonError.UNRECOGNIZED_EXCEPTION;

        /**
         *  其它java不许捕获异常、框架异常等
         */
        if (throwable instanceof NullPointerException) {
            errorCode = CommonError.NULL_POINTER_EXCEPTION;
        } else if (throwable instanceof ClassCastException) {
            errorCode = CommonError.CLASS_CAST_EXCEPTION;
        } else if (throwable instanceof NumberFormatException) {
            errorCode = CommonError.NUMBER_FORMAT_EXCEPTION;
        } else if (throwable instanceof FileNotFoundException) {
            errorCode = CommonError.FILE_NOTFOUND_EXCEPTION;
        } else if (throwable instanceof IOException) {
            errorCode = CommonError.IOEXCEPTION;
        } else if (throwable instanceof ArrayIndexOutOfBoundsException) {
            errorCode = CommonError.ARRAY_INDEX_OUT_OF_BOUNDS_EXCEPTION;
        } else if (throwable instanceof IndexOutOfBoundsException) {
            errorCode = CommonError.INDEX_OUT_OF_BOUNDS_EXCEPTION;
        } else if (throwable instanceof ClassNotFoundException) {
            errorCode = CommonError.CLASS_NOT_FOUND_EXCEPTION;
        } else if (throwable instanceof NoSuchMethodException) {
            errorCode = CommonError.NO_SUCH_METHOD_EXCEPTION;
        } else if (throwable instanceof SecurityException) {
            errorCode = CommonError.SECURITY_EXCEPTION;
        } else if (throwable instanceof NoSuchObjectException) {
            errorCode = CommonError.NO_SUCH_OBJECT_EXCEPTION;
        } else if (throwable instanceof UnsupportedEncodingException) {
            errorCode = CommonError.UNSUPPORTED_ENCODING_EXCEPTION;
        } else if (throwable instanceof ConnectException) {
            errorCode = CommonError.CONNECT_EXCEPTION;
        }else if (throwable instanceof NoSuchBeanDefinitionException) {
            errorCode = CommonError.NO_SUCH_BEAN_DEFINITION;
        }

        return errorCode;
    }
}
