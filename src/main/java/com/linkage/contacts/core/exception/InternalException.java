package com.linkage.contacts.core.exception;

/**
 * 自定义系统内部异常
 * @author wangjie
 *
 */
public class InternalException extends RuntimeException
{
  private static final long serialVersionUID = -4850917996517754131L;

  public InternalException()
	{
		super();
	}

	public InternalException(String message)
	{
		super(message);
	}

	public InternalException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public InternalException(Throwable cause)
	{
		super(cause);
	}
	
}
