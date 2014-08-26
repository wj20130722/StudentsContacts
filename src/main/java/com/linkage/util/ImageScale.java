package com.linkage.util;

/**
 * 图片尺寸
 * <p>
 * 在图片缩放处理上面只使用宽度和高度两个属性
 * <p>
 * 在图片显示的时候使用宽度、高度、左边距、上边距
 * 
 * @author DHT SVN版本信息：$Id$
 */
public class ImageScale
{

	public ImageScale()
	{
	}

	public ImageScale(int width, int height, int margin_left, int margin_top)
	{
		this.width = width;
		this.height = height;
		this.margin_left = margin_left;
		this.margin_top = margin_top;
	}

	public ImageScale(int width, int height)
	{
		this.width = width;
		this.height = height;
	}

	private int width;// 宽度(Image.width)
	private int height;// 高度(Image.height)
	private int margin_left;// private int width;// margin-left
	private int margin_top;// 上边距 margin-top

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public int getHeight()
	{
		return height;
	}

	public void setHeight(int height)
	{
		this.height = height;
	}

	public int getMargin_left()
	{
		return margin_left;
	}

	public void setMargin_left(int margin_left)
	{
		this.margin_left = margin_left;
	}

	public int getMargin_top()
	{
		return margin_top;
	}

	public void setMargin_top(int margin_top)
	{
		this.margin_top = margin_top;
	}

	public String toString()
	{
		return "ImageScale [width=" + width + ", height=" + height + ", margin_left=" + margin_left + ", margin_top=" + margin_top + "]";
	}

}
