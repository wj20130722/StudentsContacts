package com.linkage.contacts.server.entity;

/**
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: linkage.com</p>
 * @author not attributable
 * @version 1.0
 */

public  class Codepool
{private static final org.apache.commons.logging.Log log = org.apache.commons.logging.LogFactory.getLog(Codepool.class);
  private static String vssHeader = "$Header: /webapp/BasicPlan/src/com/eidolon/db/Codepool.java 3     08-05-06 14:54 Dht $";

  public final static  int  POOLNUM_DEFAULT = 100;

  private String codeid = null; //字段编码
  private long curid = 0; //当前的id，此id已经被使用，下一个是未使用的。注意这个意义和code表的nextnbr的意义有区别。
  private int poolnum = 100; //编码池大小
  private long maxid = 0; //编码池中可用最大编码
  public Codepool(String codeid)
  {
    this.codeid = codeid;
  }

  public String getCodeid()
  {
    return codeid;
  }

  public void setCodeid(String codeid)
  {
    this.codeid = codeid;
  }

  public long getCurid()
  {
    return curid;
  }

  public void setCurid(long curid)
  {
    this.curid = curid;
  }

  public int getPoolnum()
  {
    return poolnum;
  }

  public void setPoolnum(int poolnum)
  {
    this.poolnum = poolnum;
  }

  public long getMaxid()
  {
    return maxid;
  }

  public void setMaxid(long maxid)
  {
    this.maxid = maxid;
  }

  public static void main(String[] args)
  {
    log.info("class header:"+vssHeader);
  }

}