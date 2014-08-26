package com.linkage.contacts.core.exception;

/**
 * 自定义一般异常类
 * @author wangjie
 *
 */
public class ContactException extends RuntimeException
{

  private static final long serialVersionUID = -5281715299364121896L;

	public ContactException()
  {
	  super();
  }

	public ContactException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace)
  {
	  super(message, cause, enableSuppression, writableStackTrace);
  }

	public ContactException(String message, Throwable cause)
  {
	  super(message, cause);
  }

	public ContactException(String message)
  {
	  super(message);
  }

	public ContactException(Throwable cause)
  {
	  super(cause);
  }
  
  
}
