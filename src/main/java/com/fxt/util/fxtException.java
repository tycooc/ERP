package com.fxt.util;

/**
 * @title: 平台异常基类 
 * @description: 用于包装一些异常信息，打印日志等服务
 */
@SuppressWarnings("serial")
public class fxtException extends Exception
{
    public long errorCode = -1;

    public String message ;

    public fxtException()
    {
        super();
    }

    public fxtException(String message)
    {
        super(message);
        this.message = message;
    }

    public fxtException(String message, Throwable cause)
    {
        super(message, cause);
        this.message = message;
    }

    public fxtException(Throwable cause)
    {
        super(cause);
    }

    public fxtException(long errorCode)
    {
        super();
        this.errorCode = errorCode;
    }

    public fxtException(String message, long errorCode)
    {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }

    public fxtException(String message, long errorCode, Throwable cause)
    {
        super(message, cause);
        this.errorCode = errorCode;
        this.message = message;
    }

    public fxtException(long errorCode, Throwable cause)
    {
        super(cause);
        this.errorCode = errorCode;
    }

    public long getErrorCode()
    {
        return errorCode;
    }

    public String getMessage()
    {
        return message;
    }
}
