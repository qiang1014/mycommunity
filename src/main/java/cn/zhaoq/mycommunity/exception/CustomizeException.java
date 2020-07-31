package cn.zhaoq.mycommunity.exception;

/**
 * 自定义异常
 */
public class CustomizeException extends RuntimeException{
    private String message;

    public CustomizeException(String message){
        this.message = message;
    }
}
