package com.linkage.mybatis.util;

import java.util.List;

public class Criterion {
	/**
	 * 查询条件
	 */
	private String condition;
	
	/**
	 * 参数值
	 */
	private Object value;
	
	/**
	 * 第二个参数值
	 */
	private Object secondValue;
	
	/**
	 * 判断是否有值.如：a is not null
	 */
	private boolean noValue;
	
	/**
	 * 是否单一条件.如：a=?
	 */
	private boolean singleValue;
	
	/**
	 * 是否是between...and...如：a between 1 and 3
	 */
	private boolean betweenValue;
	
	/**
	 * 是否含有一组值in/not in.如:a in (1,2,3,4,5)
	 */
	private boolean listValue;
	
	/**
	 * 类型转化器,根据实际需求自己定义.
	 */
	private String typeHandler;

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Object getSecondValue() {
		return secondValue;
	}

	public void setSecondValue(Object secondValue) {
		this.secondValue = secondValue;
	}

	public boolean isNoValue() {
		return noValue;
	}

	public void setNoValue(boolean noValue) {
		this.noValue = noValue;
	}

	public boolean isSingleValue() {
		return singleValue;
	}

	public void setSingleValue(boolean singleValue) {
		this.singleValue = singleValue;
	}

	public boolean isBetweenValue() {
		return betweenValue;
	}

	public void setBetweenValue(boolean betweenValue) {
		this.betweenValue = betweenValue;
	}

	public boolean isListValue() {
		return listValue;
	}

	public void setListValue(boolean listValue) {
		this.listValue = listValue;
	}

	public String getTypeHandler() {
		return typeHandler;
	}

	public void setTypeHandler(String typeHandler) {
		this.typeHandler = typeHandler;
	}
	
	 protected Criterion(String condition) {
         super();
         this.condition = condition;
         this.typeHandler = null;
         this.noValue = true;
     }

     protected Criterion(String condition, Object value, String typeHandler) {
         super();
         this.condition = condition;
         this.value = value;
         this.typeHandler = typeHandler;
         if (value instanceof List<?>) {
             this.listValue = true;
         } else {
             this.singleValue = true;
         }
     }

     protected Criterion(String condition, Object value) {
         this(condition, value, null);
     }

     protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
         super();
         this.condition = condition;
         this.value = value;
         this.secondValue = secondValue;
         this.typeHandler = typeHandler;
         this.betweenValue = true;
     }

     protected Criterion(String condition, Object value, Object secondValue) {
         this(condition, value, secondValue, null);
     }
     
     
     
     @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((condition == null) ? 0 : condition.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Criterion other = (Criterion) obj;
		if (condition == null) {
			if (other.condition != null)
				return false;
		} else if (!condition.equals(other.condition))
			return false;
		return true;
	}

	@Override
    public String toString() {
    	 StringBuffer sb = new StringBuffer();
    	 sb.append("Criterion:\n");
    	 sb.append("condition="+this.condition+"\n");
    	 sb.append("value="+this.value+"\n");
    	 sb.append("secondValue="+this.secondValue+"\n");
    	 sb.append("typeHandler="+this.typeHandler+"\n");
    	return sb.toString();
    }

}
