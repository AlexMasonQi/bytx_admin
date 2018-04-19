package com.bytx.admin.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 统一异常处理
 */
@ControllerAdvice
class GlobalExceptionHandler
{

    public static final String DEFAULT_ERROR_VIEW = "error";

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception
    {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e.getMessage());
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        log.error(req.getRequestURI().toString(), e);
        return mav;
    }

}