package com.linkage.util;


public class ImageUtil
{

	public final static int ZOOMSCALE_BIG_HEIGHT = 800;// 大图标准高度
	public final static int ZOOMSCALE_MIDDLE_SIDE_MIN = 200;// 中图最小边标准尺寸
	public final static int ZOOMSCALE_SMALL_SIDE_MAX = 100;// 小图最大边标准尺寸

	public final static int VIEWSCALE_MIDDLE_SIDE = 371;// 371 中图显示标准尺寸

	public final static int VIEWSCALE_ML_WIDHT = 228;// 左中图显示标准尺寸：宽，（可视尺寸：228*171）
	public final static int VIEWSCALE_ML_HEIGHT = 171;// 左中图显示标准尺寸：高，（可视尺寸：228*171）

	public final static int VIEWSCALE_RS_WIDHT = 140;// 右小图显示标准尺寸：宽，（可视尺寸：140*85）
	public final static int VIEWSCALE_RS_HEIGHT = 85;// 右小图显示标准尺寸：高，（可视尺寸：140*85）

	public final static int VIEWSCALE_SMALL_WIDHT = 50;// 小图显示标准尺寸：宽，（可视尺寸：50*50）
	public final static int VIEWSCALE_SMALL_HEIGHT = 50;// 小图显示标准尺寸：高，（可视尺寸：50*50）

	/**
	 * 计算缩放后的大图尺寸,大图（如果原始图高度>800,则缩放到800的高度,否则原始高度，返回null）
	 * 
	 * @param orgWidth
	 *          图片原始宽度
	 * @param orgHeight
	 *          图片原始高度
	 * @return 返回缩放后的尺寸，如果是null则表示原始尺寸，不需要进行缩放
	 */
	public final static ImageScale calBigZoomScale(int orgWidth, int orgHeight)
	{
		if (orgHeight > ImageUtil.ZOOMSCALE_BIG_HEIGHT)
		{
			ImageScale s = new ImageScale();
			s.setHeight(ImageUtil.ZOOMSCALE_BIG_HEIGHT);
			double w = ((double) orgWidth / (double) orgHeight) * s.getHeight();
			// 四舍五入取整数
			String ws = Utility.getFormatedFloat(w, 0);
			s.setWidth(Integer.parseInt(ws));
			return s;
		}
		return null;
	}

	/**
	 * 计算缩放后的中图尺寸,中图：如果最小一边是大于200的，则缩放为200，另外一边按比例缩放
	 * 
	 * @param orgWidth
	 *          图片原始宽度
	 * @param orgHeight
	 *          图片原始高度
	 * @return 返回缩放后的尺寸，如果是null则表示原始尺寸，不需要进行缩放
	 */
	public final static ImageScale calMiddleZoomScale(int orgWidth, int orgHeight)
	{
		boolean widthIsMin = true;// 默认宽度是最小
		int smallSide = orgWidth;// 小边的尺寸
		int bigSide = orgHeight;// 大边的尺寸
		if (orgWidth > orgHeight)
		{
			widthIsMin = false;
			smallSide = orgHeight;
			bigSide = orgWidth;
		}
		// 小边大于200才缩放处理
		if (smallSide > ImageUtil.ZOOMSCALE_MIDDLE_SIDE_MIN)
		{
			// 大边缩放后的尺寸
			double b = ((double) bigSide / (double) smallSide) * ImageUtil.ZOOMSCALE_MIDDLE_SIDE_MIN;
			// 四舍五入取整数
			int bigSideZoom = Integer.parseInt(Utility.getFormatedFloat(b, 0));
			if (widthIsMin)
				return new ImageScale(ImageUtil.ZOOMSCALE_MIDDLE_SIDE_MIN, bigSideZoom);
			else
				return new ImageScale(bigSideZoom, ImageUtil.ZOOMSCALE_MIDDLE_SIDE_MIN);
		}
		return null;
	}

	/**
	 * 计算缩放后的小图尺寸,小图：如果最大一边是大于100的，则缩放为100，另外一边按比例缩放
	 * 
	 * @param orgWidth
	 *          图片原始宽度
	 * @param orgHeight
	 *          图片原始高度
	 * @return 返回缩放后的尺寸，如果是null则表示原始尺寸，不需要进行缩放
	 */
	public final static ImageScale calSmallZoomScale(int orgWidth, int orgHeight)
	{
		boolean heightIsMax = true;// 默认高度是最大
		int smallSide = orgWidth;// 小边的尺寸
		int bigSide = orgHeight;// 大边的尺寸
		if (orgWidth > orgHeight)
		{
			heightIsMax = false;
			smallSide = orgHeight;
			bigSide = orgWidth;
		}
		// 大边大于100才缩放处理
		if (bigSide > ImageUtil.ZOOMSCALE_SMALL_SIDE_MAX)
		{
			// 大边缩放后的尺寸
			double b = ((double) smallSide / (double) bigSide) * ImageUtil.ZOOMSCALE_SMALL_SIDE_MAX;
			// 四舍五入取整数
			int smallSideZoom = Integer.parseInt(Utility.getFormatedFloat(b, 0));
			if (heightIsMax)
				return new ImageScale(smallSideZoom, ImageUtil.ZOOMSCALE_SMALL_SIDE_MAX);
			else
				return new ImageScale(ImageUtil.ZOOMSCALE_SMALL_SIDE_MAX, smallSideZoom);
		}
		return null;
	}

	/**
	 * 显示的大图尺寸，直接按照处理后的大图尺寸显示 原图尺寸应该是处理过后的尺寸，如：1200*800 450*800，如果不是，会自动处理的。
	 * 
	 * @param bigWidth
	 *          大图宽度
	 * @param bigHeight
	 *          大图高度
	 * @return 返回显示的尺寸
	 */
	public final static ImageScale viewBigImage(int bigWidth, int bigHeight)
	{
		ImageScale image = ImageUtil.calBigZoomScale(bigWidth, bigHeight);
		if (null == image)
			image = new ImageScale(bigWidth, bigHeight);
		return image;
	}

	/**
	 * 显示的中图尺寸，直接按照处理后的大图尺寸显示 原图尺寸应该是处理过后的尺寸，如：1200*800 450*800，如果不是，会自动处理的。
	 * <p>
	 * 中图(图片同大图)：只缩宽度为371，高度最高 371，如果宽<高，margin-top就是-（401-371）/2=-15 *
	 * 
	 * @param bigWidth
	 *          大图宽度
	 * @param bigHeight
	 *          大图高度
	 * @return 返回显示的尺寸
	 */
	public final static ImageScale viewMiddleImage(int bigWidth, int bigHeight)
	{
		ImageScale bigImage = ImageUtil.calBigZoomScale(bigWidth, bigHeight);
		if (null == bigImage)
			bigImage = new ImageScale(bigWidth, bigHeight);

		ImageScale image = new ImageScale();
		image.setWidth(ImageUtil.VIEWSCALE_MIDDLE_SIDE);

		// 计算图片高度
		// 大边缩放后的尺寸
		double b = ((double) bigHeight / (double) bigWidth) * image.getWidth();
		// 四舍五入取整数
		image.setHeight(Integer.parseInt(Utility.getFormatedFloat(b, 0)));
		// 如果宽<高，margin-top就是-（401-371）/2=-15 *
		if (image.getWidth() < image.getHeight())
		{
			// 相差才一个像素，不做四舍五入了
			image.setMargin_top(0 - (image.getHeight() - image.getWidth()) / 2);
		}
		return image;
	}

	/**
	 * 显示的指定尺寸图尺寸，直接按照处理后的大图尺寸显示 原图尺寸应该是处理过后的尺寸，如：1200*800 450*800，如果不是，会自动处理的。
	 * <p>
	 * 指定尺寸图： （可视尺寸：标准宽*标准高）
	 * <p>
	 * 1.宽高比=标准宽/标准高,就按此尺寸显示即可。
	 * <p>
	 * 2.宽高比>标准宽/标准高，margin-left=-(宽高比*标准高-标准宽)/2
	 * <p>
	 * 3.宽高比<标准宽/标准高，margin-top =-(高宽比*标准宽-标准高)/2
	 * 
	 * @param bigWidth
	 *          大图宽度
	 * @param bigHeight
	 *          大图高度
	 * @param standardWidth 标准宽度
	 * @param standardHeight 标准高度
	 * @return 返回显示的尺寸
	 */
	public final static ImageScale viewStandardImage(int bigWidth, int bigHeight, int standardWidth, int standardHeight)
	{
		ImageScale image = new ImageScale();
		double whBig = (double) bigWidth / (double) bigHeight;
		double whS = (double) standardWidth / (double) standardHeight;
		// 宽高比=标准宽/标准高,就按此尺寸显示即可。
		if (whBig == whS)
		{
			image.setWidth(standardWidth);
			image.setHeight(standardHeight);
		}
		else if (whBig > whS)
		{
			// 宽高比>标准宽/标准高，margin-left=-(宽高比*标准高-标准宽)/2
			image.setHeight(standardHeight);
			double w = whBig * standardHeight;
			image.setWidth(Integer.parseInt(Utility.getFormatedFloat(w, 0)));
			// double margin_left = 0 - (whBig * standardHeight -
			// standardWidth) / 2;
			image.setMargin_left(0 - (image.getWidth() - standardWidth) / 2);
		}
		else
		{
			// 宽高比<标准宽/标准高，margin-top =-(高宽比*标准宽-标准高)/2
			image.setWidth(standardWidth);
			double h = (double) bigHeight / (double) bigWidth * standardWidth;
			image.setHeight(Integer.parseInt(Utility.getFormatedFloat(h, 0)));
			image.setMargin_top(0 - (image.getHeight() - standardHeight) / 2);
		}
		return image;
	}

	/**
	 * 显示的左中图尺寸，直接按照处理后的大图尺寸显示 原图尺寸应该是处理过后的尺寸，如：1200*800 450*800，如果不是，会自动处理的。
	 * <p>
	 * 左中图： （可视尺寸：228*171）
	 * <p>
	 * 1.宽高比=228/171,就按此尺寸显示即可。
	 * <p>
	 * 2.宽高比>228/171，margin-left=-(宽高比*171-228)/2
	 * <p>
	 * 3.宽高比<228/171，margin-top =-(高宽比*228-171)/2
	 * 
	 * @param bigWidth
	 *          大图宽度
	 * @param bigHeight
	 *          大图高度
	 * @return 返回显示的尺寸
	 */
	public final static ImageScale viewMLImage(int bigWidth, int bigHeight)
	{
		return ImageUtil.viewStandardImage(bigWidth, bigHeight, ImageUtil.VIEWSCALE_ML_WIDHT, ImageUtil.VIEWSCALE_ML_HEIGHT);
	}

	/**
	 * 显示的右小图尺寸，直接按照处理后的大图尺寸显示 原图尺寸应该是处理过后的尺寸，如：1200*800 450*800，如果不是，会自动处理的。
	 * <p>
	 * 右小图： （可视尺寸：140*85）
	 * <p>
	 * 1.宽高比=140/85,就按此尺寸显示即可。
	 * <p>
	 * 2.宽高比>140/85，margin-left=-(宽高比*85-140)/2
	 * <p>
	 * 3.宽高比<140/85，margin-top =-(高宽比*140-85)/2
	 * 
	 * @param bigWidth
	 *          大图宽度
	 * @param bigHeight
	 *          大图高度
	 * @return 返回显示的尺寸
	 */
	public final static ImageScale viewRSImage(int bigWidth, int bigHeight)
	{
		return ImageUtil.viewStandardImage(bigWidth, bigHeight, ImageUtil.VIEWSCALE_RS_WIDHT, ImageUtil.VIEWSCALE_RS_HEIGHT);
	}

	/**
	 * 显示的小图尺寸，直接按照处理后的大图尺寸显示 原图尺寸应该是处理过后的尺寸，如：1200*800 450*800，如果不是，会自动处理的。
	 * <p>
	 * 小图： （可视尺寸：50*50）
	 * <p>
	 * 1.宽高比=50/50,就按此尺寸显示即可。
	 * <p>
	 * 2.宽高比>50/50，margin-left=-(宽高比*50-50)/2
	 * <p>
	 * 3.宽高比<50/50，margin-top =-(高宽比*50-50)/2
	 * 
	 * @param bigWidth
	 *          大图宽度
	 * @param bigHeight
	 *          大图高度
	 * @return 返回显示的尺寸
	 */
	public final static ImageScale viewSmallImage(int bigWidth, int bigHeight)
	{
		return ImageUtil.viewStandardImage(bigWidth, bigHeight, ImageUtil.VIEWSCALE_SMALL_WIDHT, ImageUtil.VIEWSCALE_SMALL_HEIGHT);
	}

	public static void main(String[] s)
	{
		System.out.println("大图");
		System.out.println("ImageUtil.calBigZoomScale(3200,1800)=" + ImageUtil.calBigZoomScale(3200, 1800));
		System.out.println("ImageUtil.calBigZoomScale(1200,300)=" + ImageUtil.calBigZoomScale(1200, 300));
		System.out.println("ImageUtil.calBigZoomScale(1200,800)=" + ImageUtil.calBigZoomScale(1200, 800));
		System.out.println("ImageUtil.calBigZoomScale(1800, 3200)=" + ImageUtil.calBigZoomScale(1800, 3200));
		System.out.println("ImageUtil.calBigZoomScale(120,80)=" + ImageUtil.calBigZoomScale(120, 80));
		System.out.println("ImageUtil.calBigZoomScale(920,780)=" + ImageUtil.calBigZoomScale(920, 780));

		System.out.println("中图");
		System.out.println("ImageUtil.calMiddleZoomScale(3200,1800)=" + ImageUtil.calMiddleZoomScale(3200, 1800));
		System.out.println("ImageUtil.calMiddleZoomScale(1200,300)=" + ImageUtil.calMiddleZoomScale(1200, 300));
		System.out.println("ImageUtil.calMiddleZoomScale(1200,800)=" + ImageUtil.calMiddleZoomScale(1200, 800));
		System.out.println("ImageUtil.calMiddleZoomScale(1800, 3200)=" + ImageUtil.calMiddleZoomScale(1800, 3200));
		System.out.println("ImageUtil.calMiddleZoomScale(180, 320)=" + ImageUtil.calMiddleZoomScale(180, 320));
		System.out.println("ImageUtil.calMiddleZoomScale(320,180)=" + ImageUtil.calMiddleZoomScale(320, 180));
		System.out.println("ImageUtil.calMiddleZoomScale(120,180)=" + ImageUtil.calMiddleZoomScale(120, 180));
		System.out.println("ImageUtil.calMiddleZoomScale(220,280)=" + ImageUtil.calMiddleZoomScale(220, 280));
		System.out.println("ImageUtil.calMiddleZoomScale(200,280)=" + ImageUtil.calMiddleZoomScale(200, 280));

		System.out.println("小图");
		System.out.println("ImageUtil.calSmallZoomScale(3200,1800)=" + ImageUtil.calSmallZoomScale(3200, 1800));
		System.out.println("ImageUtil.calSmallZoomScale(1200,300)=" + ImageUtil.calSmallZoomScale(1200, 300));
		System.out.println("ImageUtil.calSmallZoomScale(1200,800)=" + ImageUtil.calSmallZoomScale(1200, 800));
		System.out.println("ImageUtil.calSmallZoomScale(1800, 3200)=" + ImageUtil.calSmallZoomScale(1800, 3200));
		System.out.println("ImageUtil.calSmallZoomScale(80, 50)=" + ImageUtil.calSmallZoomScale(80, 50));
		System.out.println("ImageUtil.calSmallZoomScale(180, 50)=" + ImageUtil.calSmallZoomScale(180, 50));
		System.out.println("ImageUtil.calSmallZoomScale(90, 150)=" + ImageUtil.calSmallZoomScale(90, 150));

		System.out.println("看大图");
		System.out.println("ImageUtil.viewBigImage(1200, 800)=" + ImageUtil.viewBigImage(1200, 800));
		System.out.println("ImageUtil.viewBigImage(600, 800)=" + ImageUtil.viewBigImage(600, 800));
		System.out.println("ImageUtil.viewBigImage(900, 500)=" + ImageUtil.viewBigImage(900, 500));

		System.out.println("看中图");
		System.out.println("ImageUtil.viewMiddleImage(1200, 800)=" + ImageUtil.viewMiddleImage(1200, 800));
		System.out.println("ImageUtil.viewMiddleImage(450, 800)=" + ImageUtil.viewMiddleImage(450, 800));
		System.out.println("ImageUtil.viewMiddleImage(780, 800)=" + ImageUtil.viewMiddleImage(780, 800));
		System.out.println("ImageUtil.viewMiddleImage(900, 500)=" + ImageUtil.viewMiddleImage(900, 500));

		System.out.println("看左中图");
		System.out.println("ImageUtil.viewMLImage(1200, 800)=" + ImageUtil.viewMLImage(1200, 800));
		System.out.println("ImageUtil.viewMLImage(450, 800)=" + ImageUtil.viewMLImage(450, 800));
		System.out.println("ImageUtil.viewMLImage(780, 800)=" + ImageUtil.viewMLImage(780, 800));
		System.out.println("ImageUtil.viewMLImage(900, 500)=" + ImageUtil.viewMLImage(900, 500));

		System.out.println("看右中图");
		System.out.println("ImageUtil.viewRSImage(1200, 800)=" + ImageUtil.viewRSImage(1200, 800));
		System.out.println("ImageUtil.viewRSImage(450, 800)=" + ImageUtil.viewRSImage(450, 800));
		System.out.println("ImageUtil.viewRSImage(780, 800)=" + ImageUtil.viewRSImage(780, 800));
		System.out.println("ImageUtil.viewRSImage(900, 500)=" + ImageUtil.viewRSImage(900, 500));

		System.out.println("看小图");
		System.out.println("ImageUtil.viewSmallImage(1200, 800)=" + ImageUtil.viewSmallImage(1200, 800));
		System.out.println("ImageUtil.viewSmallImage(1200, 800)=" + ImageUtil.viewStandardImage(1200, 800, 50, 50));
		System.out.println("ImageUtil.viewSmallImage(450, 800)=" + ImageUtil.viewSmallImage(450, 800));
		System.out.println("ImageUtil.viewSmallImage(780, 800)=" + ImageUtil.viewSmallImage(780, 800));
		System.out.println("ImageUtil.viewSmallImage(900, 500)=" + ImageUtil.viewSmallImage(900, 500));
		System.out.println("ImageUtil.viewSmallImage(9, 5)=" + ImageUtil.viewSmallImage(9, 5));

	}

}
