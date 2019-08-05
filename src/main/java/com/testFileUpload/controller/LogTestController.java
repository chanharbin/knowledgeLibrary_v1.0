package com.testFileUpload.controller;

import com.testFileUpload.aop.LogAnnotation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class LogTestController {
    //protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @LogAnnotation()
    @RequestMapping(value = "/log")
    @ResponseBody
    public void log(HttpServletResponse response) throws IOException {
    }
}
