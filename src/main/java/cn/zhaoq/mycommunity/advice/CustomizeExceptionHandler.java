package cn.zhaoq.mycommunity.advice;

import cn.zhaoq.mycommunity.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义异常处理
 */
@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handel(Throwable e, Model model){
        if(e instanceof CustomizeException){
            model.addAttribute("message",e.getMessage());

        }else {
            model.addAttribute("message","服务器冒烟了~，要不稍后试试！！！");

        }
        return new ModelAndView("error");
    }
}
