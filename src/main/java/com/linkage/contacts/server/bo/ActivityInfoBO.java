package com.linkage.contacts.server.bo;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.linkage.contacts.server.dao.ActivityApplyDAO;
import com.linkage.contacts.server.dao.ActivityHisPicDAO;
import com.linkage.contacts.server.dao.ActivityInfoDAO;
import com.linkage.contacts.server.dao.ActivityPicDAO;
import com.linkage.contacts.server.entity.ActivityApply;
import com.linkage.contacts.server.entity.ActivityHisPic;
import com.linkage.contacts.server.entity.ActivityInfo;
import com.linkage.contacts.server.entity.ActivityPic;
import com.linkage.contacts.server.entity.ClassInfo;
import com.linkage.contacts.server.entity.SystemPushMessage;
import com.linkage.contacts.server.entity.UserInfo;
import com.linkage.contacts.server.mybatis.persistence.CommonMapper;
import com.linkage.contacts.server.vo.ActivityImage;
import com.linkage.contacts.server.vo.FormActivityInfo;
import com.linkage.contacts.server.vo.FormHisInfo;
import com.linkage.contacts.server.vo.FormPostActivityInfo;
import com.linkage.mybatis.util.CriteriaManager;
import com.linkage.mybatis.util.Restrictions;
import com.linkage.push.xinge.XingeAppPushUtil;
import com.linkage.util.DateBean;
import com.linkage.util.MybatisUtil;
import com.linkage.util.Utility;
import com.tencent.xinge.ClickAction;
import com.tencent.xinge.Message;
import com.tencent.xinge.MessageIOS;

@Service
public class ActivityInfoBO
{
	private static final Log log = LogFactory.getLog(ActivityInfoBO.class);
	
	@Autowired
	@Qualifier("mybatisActivityInfoDao")
	private ActivityInfoDAO activityInfoDAO;
	
	@Autowired
	@Qualifier("mybatisActivityPicDao")
	private ActivityPicDAO activityPicDAO;
	
	@Autowired
	@Qualifier("mybatisActivityHisPicDao")
	private ActivityHisPicDAO activityHisPicDAO;
	
	@Autowired
	private ActivityApplyDAO activityApplyDAO;
	
	@Autowired
	private CommonMapper commonMapper;
	
	@Autowired
	private CodeBO code;
	
	@Autowired
	private UserInfoBO userInfoBO;
	
	@Autowired
	private SystemPushMessageBO systemPushMessageBO;
	
	@Autowired
	private UserMessageMarkBO userMessageMarkBO;
	
	@Autowired
	@Qualifier("transactionManager")
	private PlatformTransactionManager transactionManager;
	
	
	public void save(ActivityInfo activityInfo)
  {
		if(0==activityInfo.getActivity_id())
		{
			activityInfo.setActivity_id((int)code.newCode("activity_info.activity_id", "活动信息", 1));
			activityInfoDAO.insert(activityInfo);
			log.info("活动创建成功。");
		}
		else
		{
			activityInfoDAO.update(activityInfo);
			log.info("活动更新成功。");
		}
  }
	
	public void deleteActivityPic(ActivityPic activityPic)
	{
		activityPicDAO.delete(activityPic);
	}
	
	public void deleteHisPic(ActivityHisPic ActivityHisPic)
	{
		activityHisPicDAO.delete(ActivityHisPic);
	}
	
	public void saveActivityPic(ActivityPic activityPic)
  {
		if(0==activityPic.getPic_id())
		{
			activityPic.setPic_id((int)code.newCode("activity_pic.pic_id", "活动图片信息", 1));
			//activityPic.setPic_id(1);
			activityPic.setOrder_no(activityPic.getPic_id());
			activityPicDAO.insert(activityPic);
			log.info("活动图片保存成功。");
		}
		else
		{
			activityPicDAO.update(activityPic);
			log.info("活动图片更新成功。");
		}
  }
	
	public void saveHisPic(ActivityHisPic activityHisPic)
  {
		if(0==activityHisPic.getPic_id())
		{
			activityHisPic.setPic_id((int)code.newCode("activity_his_pic.pic_id", "活动花絮图片信息", 1));
			activityHisPic.setOrder_no(activityHisPic.getPic_id());
			activityHisPicDAO.insert(activityHisPic);
			log.info("活动花絮图片保存成功。");
		}
		else
		{
			activityHisPicDAO.update(activityHisPic);
			log.info("活动花絮图片更新成功。");
		}
  }

	
	//普通用户获取活动列表
	public List<FormActivityInfo> getActivityInfo(int university_id, int page, int pagesize) throws Exception
  {
		List<FormActivityInfo> list = new ArrayList<FormActivityInfo>();
		FormActivityInfo formActivity = null;
		int index = (page-1)*pagesize;
		List<ActivityInfo> activityList = activityInfoDAO.getActivityInfo(university_id,index,pagesize);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (final ActivityInfo activityInfo : activityList)
    {
	    formActivity = new FormActivityInfo();
	    formActivity.setActivity_id(activityInfo.getActivity_id());
	    formActivity.setActivity_name(activityInfo.getActivity_name());
	    formActivity.setActivity_pic(activityInfo.getActivity_pic());
	    formActivity.setAddress(activityInfo.getAddress());
	    formActivity.setApply_number(activityInfo.getApply_number());
	    formActivity.setView_times(activityInfo.getView_times());
	    formActivity.setUser_id(activityInfo.getUser_id());
	    if(null!=activityInfo.getBegin_time())
	    	formActivity.setBegin_time(activityInfo.getBegin_time().getTime());
	    if(null!=activityInfo.getEnd_time())
	    	formActivity.setEnd_time(activityInfo.getEnd_time().getTime());
	    if(null!=activityInfo.getApply_time())
	    	formActivity.setApply_time(activityInfo.getApply_time().getTime());
	    if(StringUtils.isBlank(activityInfo.getBegin_time2()))
	    {
	    	formActivity.setActivity_state(getActivityState(activityInfo.getActivity_state(),activityInfo.getApply_time(),activityInfo.getBegin_time(),activityInfo.getEnd_time()));
		    formActivity.setOffdate(getOffDate(formActivity.getActivity_state(),activityInfo.getApply_time()));
	    }
	    else
	    {
	    	formActivity.setActivity_state(1);
	    	formActivity.setOffdate(null);
	    }
	    formActivity.setCreate_org(activityInfo.getCreate_org());
	    formActivity.setActivity_type(activityInfo.getActivity_type());
	    formActivity.setActivity_url(activityInfo.getActivity_url());
	    formActivity.setGenerate_time(activityInfo.getGenerate_time().getTime());
	    formActivity.setBegin_time2(activityInfo.getBegin_time2());
	    list.add(formActivity);
    }
		
	  return list;
  }


	private static String getOffDate(int activity_state, Date apply_time) throws Exception
  {
		String offStr = "";
		Date now = new Date();
		if(1==activity_state) //可报名状态下显示报名截止天数
		{
			int diffDay = DateBean.getDiffDay(now, apply_time);
			if(diffDay>=1)
			{
				offStr = diffDay+"天";
			}
			else
			{
//				int diffHour = DateBean.getDiffHour(now, apply_time);
//				if(diffHour>=1)
//				{
//					offStr = diffHour+"小时";
//				}
//				else
//				{
//					offStr = DateBean.getDiffMin(now, apply_time)+"分钟";
//				}
				offStr = "今天";
			}
		}
		else
		{
			offStr = "0秒";
		}
	  return offStr;
  }
	
	/*public static void main(String[] args) throws Exception
  {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date apply_time = sdf.parse("2014-06-10 01:00:00");
		Date begin_time = sdf.parse("2014-06-10 08:30:00");
		Date end_time = sdf.parse("2014-06-10 10:30:00");
		System.out.println(getActivityState(1, apply_time, begin_time, end_time));
		System.out.println(getOffDate(1, apply_time));
  }*/


	private static int getActivityState(int activity_state, Date apply_time, Date begin_time, Date end_time)
  {
		int state = 0;
		Date now = new Date();
		if(1==activity_state)
		{
			if(now.before(apply_time))
				state = 1;
			else if(now.before(begin_time))
				state = 2;
			else if(now.before(end_time))
				state = 4;
			else 
				state = 5;
			
		}
		else if(0==activity_state)
		{
			if(now.compareTo(apply_time)==0 || now.after(apply_time))
				state = 6;
			else
				state = 0;
		}
		else
		{
			state = activity_state;
		}
	  return state;
  }


	public List<FormActivityInfo> getActivityInfo2(int university_id, int user_id,int page, int pagesize) throws Exception
  {
		List<FormActivityInfo> list = new ArrayList<FormActivityInfo>();
		FormActivityInfo formActivity = null;
		int index = (page-1)*pagesize;
		List<ActivityInfo> activityList = activityInfoDAO.getActivityInfo2(university_id,user_id,index,pagesize);
	//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (final ActivityInfo activityInfo : activityList)
    {
	    formActivity = new FormActivityInfo();
	    formActivity.setActivity_id(activityInfo.getActivity_id());
	    formActivity.setActivity_name(activityInfo.getActivity_name());
	    formActivity.setActivity_pic(activityInfo.getActivity_pic());
	    formActivity.setAddress(activityInfo.getAddress());
	    formActivity.setApply_number(activityInfo.getApply_number());
	    formActivity.setView_times(activityInfo.getView_times());
	    formActivity.setUser_id(activityInfo.getUser_id());
	    if(null!=activityInfo.getBegin_time())
	    	formActivity.setBegin_time(activityInfo.getBegin_time().getTime());
	    if(null!=activityInfo.getEnd_time())
	    	formActivity.setEnd_time(activityInfo.getEnd_time().getTime());
	    if(null!=activityInfo.getApply_time())
	    	formActivity.setApply_time(activityInfo.getApply_time().getTime());
	    if(StringUtils.isBlank(activityInfo.getBegin_time2()))
	    {
	    	formActivity.setActivity_state(getActivityState(activityInfo.getActivity_state(),activityInfo.getApply_time(),activityInfo.getBegin_time(),activityInfo.getEnd_time()));
		    formActivity.setOffdate(getOffDate(formActivity.getActivity_state(),activityInfo.getApply_time()));
	    }
	    else
	    {
	    	formActivity.setActivity_state(1);
	    	formActivity.setOffdate(null);
	    }
	    formActivity.setCreate_org(activityInfo.getCreate_org());
	    formActivity.setActivity_type(activityInfo.getActivity_type());
	    formActivity.setActivity_url(activityInfo.getActivity_url());
	    formActivity.setGenerate_time(activityInfo.getGenerate_time().getTime());
	    formActivity.setBegin_time2(activityInfo.getBegin_time2());
	    list.add(formActivity);
    }
		
	  return list;
  }


	public List<FormActivityInfo> getActivityInfo3(int university_id, int page, int pagesize) throws Exception
  {
		List<FormActivityInfo> list = new ArrayList<FormActivityInfo>();
		FormActivityInfo formActivity = null;
		int index = (page-1)*pagesize;
		List<ActivityInfo> activityList = activityInfoDAO.getActivityInfo3(university_id,index,pagesize);
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (final ActivityInfo activityInfo : activityList)
    {
	    formActivity = new FormActivityInfo();
	    formActivity.setActivity_id(activityInfo.getActivity_id());
	    formActivity.setActivity_name(activityInfo.getActivity_name());
	    formActivity.setActivity_pic(activityInfo.getActivity_pic());
	    formActivity.setAddress(activityInfo.getAddress());
	    formActivity.setApply_number(activityInfo.getApply_number());
	    formActivity.setView_times(activityInfo.getView_times());
	    formActivity.setUser_id(activityInfo.getUser_id());
	    if(null!=activityInfo.getBegin_time())
	    	formActivity.setBegin_time(activityInfo.getBegin_time().getTime());
	    if(null!=activityInfo.getEnd_time())
	    	formActivity.setEnd_time(activityInfo.getEnd_time().getTime());
	    if(null!=activityInfo.getApply_time())
	    	formActivity.setApply_time(activityInfo.getApply_time().getTime());
	    if(StringUtils.isBlank(activityInfo.getBegin_time2()))
	    {
	    	formActivity.setActivity_state(getActivityState(activityInfo.getActivity_state(),activityInfo.getApply_time(),activityInfo.getBegin_time(),activityInfo.getEnd_time()));
		    formActivity.setOffdate(getOffDate(formActivity.getActivity_state(),activityInfo.getApply_time()));
	    }
	    else
	    {
	    	formActivity.setActivity_state(1);
	    	formActivity.setOffdate(null);
	    }
	    formActivity.setCreate_org(activityInfo.getCreate_org());
	    formActivity.setActivity_type(activityInfo.getActivity_type());
	    formActivity.setActivity_url(activityInfo.getActivity_url());
	    formActivity.setGenerate_time(activityInfo.getGenerate_time().getTime());
	    formActivity.setBegin_time2(activityInfo.getBegin_time2());
	    list.add(formActivity);
    }
		
	  return list;
  }


	public List<FormActivityInfo> getActivityInfo4(int university_id, int page, int pagesize) throws Exception
  {
		List<FormActivityInfo> list = new ArrayList<FormActivityInfo>();
		FormActivityInfo formActivity = null;
		int index = (page-1)*pagesize;
		List<ActivityInfo> activityList = activityInfoDAO.getActivityInfo4(university_id,index,pagesize);
	//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		for (final ActivityInfo activityInfo : activityList)
    {
	    formActivity = new FormActivityInfo();
	    formActivity.setActivity_id(activityInfo.getActivity_id());
	    formActivity.setActivity_name(activityInfo.getActivity_name());
	    formActivity.setActivity_pic(activityInfo.getActivity_pic());
	    formActivity.setAddress(activityInfo.getAddress());
	    formActivity.setApply_number(activityInfo.getApply_number());
	    formActivity.setView_times(activityInfo.getView_times());
	    formActivity.setUser_id(activityInfo.getUser_id());
	    if(null!=activityInfo.getBegin_time())
	    	formActivity.setBegin_time(activityInfo.getBegin_time().getTime());
	    if(null!=activityInfo.getEnd_time())
	    	formActivity.setEnd_time(activityInfo.getEnd_time().getTime());
	    if(null!=activityInfo.getApply_time())
	    	formActivity.setApply_time(activityInfo.getApply_time().getTime());
	    if(StringUtils.isBlank(activityInfo.getBegin_time2()))
	    {
	    	formActivity.setActivity_state(getActivityState(activityInfo.getActivity_state(),activityInfo.getApply_time(),activityInfo.getBegin_time(),activityInfo.getEnd_time()));
		    formActivity.setOffdate(getOffDate(formActivity.getActivity_state(),activityInfo.getApply_time()));
	    }
	    else
	    {
	    	formActivity.setActivity_state(1);
	    	formActivity.setOffdate(null);
	    }
	    formActivity.setCreate_org(activityInfo.getCreate_org());
	    formActivity.setActivity_type(activityInfo.getActivity_type());
	    formActivity.setActivity_url(activityInfo.getActivity_url());
	    formActivity.setGenerate_time(activityInfo.getGenerate_time().getTime());
	    formActivity.setBegin_time2(activityInfo.getBegin_time2());
	    list.add(formActivity);
    }
		
	  return list;
  }


	public HashMap<String, Object> getActivityInfoById(UserInfo userInfo, int activity_id, int type) throws Exception
  {
		HashMap<String, Object> result = new LinkedHashMap<String, Object>();
		commonMapper.updateDataBySql("update activity_info set view_times=ifnull(view_times,0)+1 where activity_id="+activity_id);
		//封装数据信息
		if(null!=userInfo)
			result.put("super_admin", userInfo.getSuper_admin());
		else
			result.put("super_admin", 0);
		ActivityInfo activityInfo = activityInfoDAO.selectByPrimaryKey(activity_id);
		result.put("activity_id", activityInfo.getActivity_id());
		result.put("activity_name", activityInfo.getActivity_name());
		result.put("activity_pic", activityInfo.getActivity_pic());
		result.put("address", activityInfo.getAddress());
		result.put("apply_number", activityInfo.getApply_number());
		result.put("view_times", activityInfo.getView_times());
		result.put("user_id", activityInfo.getUser_id());
		result.put("create_org", activityInfo.getCreate_org());
		result.put("begin_time", activityInfo.getBegin_time()!=null?activityInfo.getBegin_time().getTime():null);
		result.put("end_time", activityInfo.getEnd_time()!=null?activityInfo.getEnd_time().getTime():null);
		result.put("apply_time", activityInfo.getApply_time()!=null?activityInfo.getApply_time().getTime():null);
		result.put("activity_state", getActivityState(activityInfo.getActivity_state(),activityInfo.getApply_time(),activityInfo.getBegin_time(),activityInfo.getEnd_time()));
		result.put("offdate", getOffDate(Integer.parseInt(Utility.chgZero(result.get("activity_state"))),activityInfo.getApply_time()));
		
		if(1==Integer.parseInt(Utility.chgZero(result.get("activity_state")))&&null!=userInfo)
		{
			CriteriaManager cri = new CriteriaManager();
			cri.or().add(Restrictions.equalTo("user_id", userInfo.getUser_id())).add(Restrictions.equalTo("activity_id", activity_id));
			int count = commonMapper.countByWhereCondition("activity_apply", cri);
			if(count==0)
				result.put("is_apply", 0);
			else
				result.put("is_apply", 1);
			
		}
		else if(1==Integer.parseInt(Utility.chgZero(result.get("activity_state")))&&null==userInfo)
		{
			result.put("is_apply", 0);
		}
		else
		{
			result.put("is_apply", 2);
		}
		if(1==type)
		{
			result.put("activity_desc", activityInfo.getActivity_desc());
			String sql = "select pic_id,pic_url,pic_desc,1 pic_state from activity_pic where activity_id="+activity_id+" order by order_no";
			List<HashMap<String, Object>> maps = commonMapper.getTableRowBySql(sql);
			maps = MybatisUtil.converColumnNullList(maps, new String[]{"pic_url","pic_desc"});
			result.put("picinfo", maps);
		}
		else
		{
			result.put("his_desc", activityInfo.getHis_desc());
			String sql = "select pic_id,pic_url,pic_desc,1 pic_state from activity_his_pic where activity_id="+activity_id+" order by order_no";
			List<HashMap<String, Object>> maps = commonMapper.getTableRowBySql(sql);
			maps = MybatisUtil.converColumnNullList(maps, new String[]{"pic_url","pic_desc"});
			result.put("picinfo", maps);
		}
	  return result;
  }


	public List<HashMap<String, Object>> getApplyerById(int activity_id, int activity_state, UserInfo userInfo)
  {
		List<HashMap<String, Object>> maps = new ArrayList<HashMap<String, Object>>();
		String sql = "select u.user_id,u.user_name,u.avatar_large," +
				"u.city_name,(convert(CONCAT(u.city_secret,''),UNSIGNED)) city_secret," +
				"u.industry,(convert(CONCAT(u.industry_secret,''),UNSIGNED)) industry_secret," +
				"u.companyname,(convert(CONCAT(u.company_secret,''),UNSIGNED)) company_secret," +
				"(convert(CONCAT(u.is_authentication,''),UNSIGNED)) is_authentication  " +
				"from activity_apply a join user_info u on u.user_id=a.user_id " +
				"where a.activity_id="+activity_id+" order by convert(u.user_name USING gbk) COLLATE gbk_chinese_ci asc";
		String sql2 = "";
		if(activity_state!=7)//新活动
		{
			maps = commonMapper.getTableRowBySql(sql);
			//TODO WJ 增加班级信息
			for (final HashMap<String, Object> hashMap : maps)
      {
				//CONCAT((select college_name from college_config where college_id=c.college_id),c.class_name) class_name
	      sql2 = "select c.year,c.class_name "
	      		+" from class_role_info cc join class_info c on c.class_id = cc.class_id and c.class_state=1 join user_info u on u.user_id = cc.user_id " +
	      		" where cc.user_id="+Utility.chgZero(hashMap.get("user_id"))+" and c.university_id=1 and cc.class_role_state=1 ";
	      List<HashMap<String, Object>> maps2 = commonMapper.getTableRowBySql(sql2);
	      if(null!=maps2 && maps2.size()>0)
	      {
	      	hashMap.put("year", maps2.get(0).get("year"));
	      	hashMap.put("class_name", maps2.get(0).get("class_name"));
	      }
	      else
	      {
	      	hashMap.put("year", null);
	      	hashMap.put("class_name", null);
	      }
      }
		}
		else
		{
			if(null!=userInfo)
			{
				CriteriaManager cri = new CriteriaManager();
				cri.or().add(Restrictions.equalTo("user_id", userInfo.getUser_id())).add(Restrictions.equalTo("activity_id", activity_id));
				int count = commonMapper.countByWhereCondition("activity_apply", cri);
				if(count>0)
				{
					maps = commonMapper.getTableRowBySql(sql);
					//TODO WJ 增加班级信息
					for (final HashMap<String, Object> hashMap : maps)
		      {
			      sql2 = "select c.year,c.class_name "
			      		+" from class_role_info cc join class_info c on c.class_id = cc.class_id and c.class_state=1 join user_info u on u.user_id = cc.user_id " +
			      		" where cc.user_id="+Utility.chgZero(hashMap.get("user_id"))+" and c.university_id=1 and cc.class_role_state=1 ";
			      List<HashMap<String, Object>> maps2 = commonMapper.getTableRowBySql(sql2);
			      if(null!=maps2 && maps2.size()>0)
			      {
			      	hashMap.put("year", maps2.get(0).get("year"));
			      	hashMap.put("class_name", maps2.get(0).get("class_name"));
			      }
			      else
			      {
			      	hashMap.put("year", null);
			      	hashMap.put("class_name", null);
			      }
		      }
				}
			}
			
		}
		maps = MybatisUtil.converColumnNullList(maps, new String[]{"user_name","avatar_large"});
	  return maps;
  }

	
	//@Transactional(readOnly=false,propagation=Propagation.REQUIRED)
	//@Transactional
	public void saveActivityInfo(UserInfo userInfo, FormPostActivityInfo formActivityInfo)
  {
		//TransactionDefinition def = new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
		//def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		//TransactionStatus status = transactionManager.getTransaction(def);
		//try
		//{
			//保存活动信息
			ActivityInfo activityInfo = new ActivityInfo();
			activityInfo.setActivity_id(formActivityInfo.getActivity_id());
			activityInfo.setActivity_name(formActivityInfo.getActivity_name());
			activityInfo.setActivity_state(0);
			activityInfo.setAddress(formActivityInfo.getAddress());
			activityInfo.setApply_number(0);
			activityInfo.setView_times(0);
			activityInfo.setCreate_org(formActivityInfo.getCreate_org());
			activityInfo.setUniversity_id(userInfo.getUniversity_id());
			activityInfo.setUser_id(userInfo.getUser_id());
			activityInfo.setApply_time(new Date(formActivityInfo.getApply_time()));
			activityInfo.setBegin_time(new Date(formActivityInfo.getBegin_time()));
			activityInfo.setEnd_time(new Date(formActivityInfo.getEnd_time()));
			activityInfo.setActivity_desc(formActivityInfo.getActivity_desc());
			if(null!=formActivityInfo.getImages() && formActivityInfo.getImages().size()>0)
				activityInfo.setActivity_pic(formActivityInfo.getImages().get(0).getPic_url());
			//新增字段
			activityInfo.setGenerate_time(new Date());
			activityInfo.setActivity_type(1);
			activityInfo.setActivity_url(null);
			activityInfo.setBegin_time2(null);
			//保存活动信息
			//throw new RuntimeException("this is my exception!");
			this.save(activityInfo);
			
			//保存图片信息
			List<ActivityImage> images = formActivityInfo.getImages();
			ActivityPic activityPic = null;
			for (final ActivityImage activityImage : images)
	    {
		    if(0==activityImage.getPic_state()&&StringUtils.isNotBlank(activityImage.getPic_url())) //新增图片
		    {
		    	activityPic = new ActivityPic();
		    	activityPic.setPic_url(activityImage.getPic_url());
		    	activityPic.setPic_desc(activityImage.getPic_desc());
		    	activityPic.setActivity_id(activityInfo.getActivity_id());
		    	this.saveActivityPic(activityPic);//保存图片信息
		    }
		    else if(2==activityImage.getPic_state()&&StringUtils.isNotBlank(activityImage.getPic_url())) //修改图片
		    {
		    	activityPic = new ActivityPic();
		    	activityPic.setPic_id(activityImage.getPic_id());
		    	activityPic.setPic_url(activityImage.getPic_url());
		    	activityPic.setPic_desc(activityImage.getPic_desc());
		    	activityPic.setActivity_id(activityInfo.getActivity_id());
		    	this.saveActivityPic(activityPic);//保存图片信息
		    }
		    else if(3==activityImage.getPic_state()) //删除图片
		    {
		    	activityPic = new ActivityPic();
		    	activityPic.setPic_id(activityImage.getPic_id());
		    	activityPic.setPic_url(activityImage.getPic_url());
		    	activityPic.setPic_desc(activityImage.getPic_desc());
		    	activityPic.setActivity_id(activityInfo.getActivity_id());
		    	this.deleteActivityPic(activityPic);
		    }
	    }
			
			//活动发布推送消息---->系统管理员
			if(formActivityInfo.getActivity_id()==0 || formActivityInfo.getActivity_id()==-1) //新活动发布时需要推送系统消息给管理员
			{
			//推送系统消息---->提醒管理员审核用户活动发布
				int nid = 30000+activityInfo.getActivity_id();
				String content = "您有一条待审核信息：用户"+userInfo.getUser_name()+"申请创建活动"+activityInfo.getActivity_name()+"请您尽快审核！";
				List<UserInfo> admins = userInfoBO.getSysAdmin(userInfo.getUniversity_id());
				SystemPushMessage systemMessage = null;
			//发送用户信息给对方用户
				Date now = new Date();
				for (final UserInfo admin : admins)
	      {
					Map<String, Object> custom = new HashMap<String, Object>();
					custom.put("type", "2");
					custom.put("content", content);
					custom.put("time", now.getTime());
					//Andriod推送
					Message message = XingeAppPushUtil.constructMessageAndriod(Message.TYPE_NOTIFICATION, nid, ClickAction.TYPE_ACTIVITY, "活动发布申请", content,null, custom);
					JSONObject jsonObject = XingeAppPushUtil.pushSingleAccountAndriod(message, admin.getAccess_token());
					int push_state = jsonObject.getInt("ret_code");
					//IOS推送
					int push_state2 = 0;
					if(1 == admin.getOnline_status())
					{
						Map<String, Object> custom2 = new HashMap<String, Object>();
						custom2.put("type", "2");
						custom2.put("time", now.getTime());
						MessageIOS message2 = XingeAppPushUtil.constructMessageIOS(1, "【活动发布申请】"+content, custom2);
						JSONObject jsonObject2 = XingeAppPushUtil.pushSingleAccountIOS(message2, admin.getAccess_token());
						push_state2 = jsonObject2.getInt("ret_code");
					}
					else
					{
						push_state2 = 77;//用户不在线
					}
					if(0 == (push_state & push_state2))
					{
						log.info("活动发布审核消息发送给管理员"+admin.getUser_name()+"成功！");
						userMessageMarkBO.addUserSysMessageMark(admin.getUser_id());
					}
					else
						log.info("活动发布审核消息发送给管理员"+admin.getUser_name()+"失败！");
					//保存系统发送的消息
					systemMessage = new SystemPushMessage();
					systemMessage.setMessage_type_id(4);//发布活动的系统消息
					systemMessage.setFrom_user_id(userInfo.getUser_id());
					systemMessage.setTo_user_id(admin.getUser_id());
					systemMessage.setMessage(content);
					systemMessage.setPush_time(now);
					systemMessage.setPush_state(push_state & push_state2);
					this.systemPushMessageBO.save(systemMessage);
	      }
			}
			
			//提交事务
		//	transactionManager.commit(status);
		//}
		//catch(Exception e)
		//{
		//回滚事务
		//	transactionManager.rollback(status);
		//	e.printStackTrace();
		//}
  }

	public boolean applyActivity(UserInfo userInfo,int activity_id, int type, int create_user_id)
  {
		boolean result = false;
		String sql = "";
		if(1 == type) //同意用户发布活动
		{
			sql = "update activity_info set activity_state=1 where activity_id="+activity_id+" and university_id="+userInfo.getUniversity_id();
		}
		else if(0 == type) //拒绝用户发布活动
		{
			sql = "update activity_info set activity_state=3 where activity_id="+activity_id+" and university_id="+userInfo.getUniversity_id();
		}
		else
		{
			return false;
		}
		int count = commonMapper.updateDataBySql(sql);
		if (count==1)
			result = true;
		else
			result = false;
		//TODO WJ 同意用户发布活动之后 活动的发布者默认加入该活动
		//推送系统消息---->活动创建者
			UserInfo toUserInfo = userInfoBO.selectByPrimaryKey(create_user_id);
			int nid = 30000+activity_id;
			ActivityInfo activityInfo = activityInfoDAO.selectByPrimaryKey(activity_id);
			String content = "";
			Date now = new Date();
			SystemPushMessage systemMessage = null;
		if(result)
		{
			if(1==type)
			{
				ActivityApply activityApply = new ActivityApply();
				activityApply.setActivity_id(activity_id);
				activityApply.setUser_id(create_user_id);
				activityApply.setApply_time(new Date());
				activityApplyDAO.insert(activityApply);
				log.info("活动发布成功之后活动发布者加入成功。");
				//将活动发布者加入活动数量中
				commonMapper.updateDataBySql("update activity_info set apply_number=ifnull(apply_number,0)+1 where activity_id="+activity_id);
			//推送系统消息
				content = "您有一条审核通过的信息：系统管理员"+userInfo.getUser_name()+"同意您创建活动"+activityInfo.getActivity_name()+"！";
			}
			else
			{
			//推送系统消息
				content = "您有一条审核不通过的信息：系统管理员"+userInfo.getUser_name()+"不同意您创建活动"+activityInfo.getActivity_name()+"！";
			}
			Map<String, Object> custom = new HashMap<String, Object>();
			custom.put("type", "2");
			custom.put("content", content);
			custom.put("time", now.getTime());
			//Andriod推送
			Message message = XingeAppPushUtil.constructMessageAndriod(Message.TYPE_NOTIFICATION, nid, ClickAction.TYPE_ACTIVITY, "活动创建审核反馈信息", content,null, custom);
			JSONObject jsonObject =  XingeAppPushUtil.pushSingleAccountAndriod(message, toUserInfo.getAccess_token());
			int push_state = jsonObject.getInt("ret_code");
			//IOS推送
			int push_state2 = 0;
			if(1 == toUserInfo.getOnline_status())
			{
				Map<String, Object> custom2 = new HashMap<String, Object>();
				custom2.put("type", "2");
				custom2.put("time", now.getTime());
				MessageIOS message2 = XingeAppPushUtil.constructMessageIOS(1, "【活动创建审核反馈信息】"+content, custom2);
				JSONObject jsonObject2 = XingeAppPushUtil.pushSingleAccountIOS(message2, toUserInfo.getAccess_token());
				push_state2 = jsonObject2.getInt("ret_code");
			}
			else
			{
				push_state2 = 77;//用户不在线
			}
			if(0 == (push_state & push_state2))
			{
				log.info("活动创建审核反馈信息发送给"+toUserInfo.getUser_name()+"成功！");
				userMessageMarkBO.addUserSysMessageMark(toUserInfo.getUser_id());
			}
			else
				log.info("活动创建审核反馈信息发送给"+toUserInfo.getUser_name()+"失败！");
			//保存系统消息
			systemMessage = new SystemPushMessage();
			systemMessage.setMessage_type_id(4);//发布活动的系统消息
			systemMessage.setFrom_user_id(userInfo.getUser_id());
			systemMessage.setTo_user_id(create_user_id);
			systemMessage.setMessage(content);
			systemMessage.setPush_time(now);
			systemMessage.setPush_state(push_state & push_state2);
			this.systemPushMessageBO.save(systemMessage);
		}
	  return result;
  }

	//@Transactional
	public void saveHisInfo(UserInfo userInfo, FormHisInfo formHisInfo)
  {
		//保存活动花絮信息
	  commonMapper.updateDataBySql("update activity_info set activity_state=7,his_desc='"+formHisInfo.getHis_desc()+"' where activity_id="+formHisInfo.getActivity_id());
		//保存图片信息
		List<ActivityImage> images = formHisInfo.getImages();
		ActivityHisPic activityHisPic = null;
		for (final ActivityImage activityImage : images)
    {
	    if(0==activityImage.getPic_state()&&StringUtils.isNotBlank(activityImage.getPic_url())) //新增图片
	    {
	    	activityHisPic = new ActivityHisPic();
	    	activityHisPic.setPic_url(activityImage.getPic_url());
	    	activityHisPic.setPic_desc(activityImage.getPic_desc());
	    	activityHisPic.setActivity_id(formHisInfo.getActivity_id());
	    	this.saveHisPic(activityHisPic);//保存图片信息
	    }
	    else  if(2==activityImage.getPic_state()&&StringUtils.isNotBlank(activityImage.getPic_url())) //修改图片
	    {
	    	activityHisPic = new ActivityHisPic();
	    	activityHisPic.setPic_id(activityImage.getPic_id());
	    	activityHisPic.setPic_url(activityImage.getPic_url());
	    	activityHisPic.setPic_desc(activityImage.getPic_desc());
	    	activityHisPic.setActivity_id(formHisInfo.getActivity_id());
	    	this.saveHisPic(activityHisPic);//保存图片信息
	    }
	    else if(3==activityImage.getPic_state()) //删除图片
	    {
	    	activityHisPic = new ActivityHisPic();
	    	activityHisPic.setPic_id(activityImage.getPic_id());
	    	activityHisPic.setPic_url(activityImage.getPic_url());
	    	activityHisPic.setPic_desc(activityImage.getPic_desc());
	    	activityHisPic.setActivity_id(formHisInfo.getActivity_id());
	    	this.deleteHisPic(activityHisPic);
	    }
    }
  }

	public void deleteActivity(UserInfo userInfo, int activity_id)
  {
		ActivityInfo activityInfo = new ActivityInfo();
		activityInfo.setActivity_id(activity_id);
	  //删除活动相关的图片信息
		this.activityPicDAO.deleteByActivityId(activity_id);
		//删除活动信息
		this.activityInfoDAO.delete(activityInfo);
  }

	public void joinActivity(UserInfo userInfo, int activity_id, int type)
  {
		ActivityApply activityApply = null;
		if(1==type)
		{
			activityApply = new ActivityApply();
			activityApply.setActivity_id(activity_id);
			activityApply.setUser_id(userInfo.getUser_id());
			activityApply.setApply_time(new Date());
			activityApplyDAO.insert(activityApply);
			log.info("用户加入活动成功。");
			//活动加入用户数加1
			commonMapper.updateDataBySql("update activity_info set apply_number=ifnull(apply_number,0)+1 where activity_id="+activity_id);
		}
		else
		{
			activityApplyDAO.deleteByUserId(activity_id, userInfo.getUser_id());
		//活动加入用户数减1
			commonMapper.updateDataBySql("update activity_info set apply_number=ifnull(apply_number,0)-1 where activity_id="+activity_id);
		}
  }

	public List<FormActivityInfo> getMyActivity(int university_id, int user_id, int page, int pagesize) throws Exception
  {
		List<FormActivityInfo> list = new ArrayList<FormActivityInfo>();
		FormActivityInfo formActivity = null;
		int index = (page-1)*pagesize;
		List<ActivityInfo> activityList = activityInfoDAO.getMyActivity(university_id,user_id,index,pagesize);
		for (final ActivityInfo activityInfo : activityList)
    {
	    formActivity = new FormActivityInfo();
	    formActivity.setActivity_id(activityInfo.getActivity_id());
	    formActivity.setActivity_name(activityInfo.getActivity_name());
	    formActivity.setActivity_pic(activityInfo.getActivity_pic());
	    formActivity.setAddress(activityInfo.getAddress());
	    formActivity.setApply_number(activityInfo.getApply_number());
	    formActivity.setView_times(activityInfo.getView_times());
	    formActivity.setUser_id(activityInfo.getUser_id());
	    if(null!=activityInfo.getBegin_time())
	    	formActivity.setBegin_time(activityInfo.getBegin_time().getTime());
	    if(null!=activityInfo.getEnd_time())
	    	formActivity.setEnd_time(activityInfo.getEnd_time().getTime());
	    if(null!=activityInfo.getApply_time())
	    	formActivity.setApply_time(activityInfo.getApply_time().getTime());
	    if(StringUtils.isBlank(activityInfo.getBegin_time2()))
	    {
	    	formActivity.setActivity_state(getActivityState(activityInfo.getActivity_state(),activityInfo.getApply_time(),activityInfo.getBegin_time(),activityInfo.getEnd_time()));
		    formActivity.setOffdate(getOffDate(formActivity.getActivity_state(),activityInfo.getApply_time()));
	    }
	    else
	    {
	    	formActivity.setActivity_state(1);
	    	formActivity.setOffdate(null);
	    }
	    formActivity.setCreate_org(activityInfo.getCreate_org());
	    formActivity.setActivity_type(activityInfo.getActivity_type());
	    formActivity.setActivity_url(activityInfo.getActivity_url());
	    formActivity.setGenerate_time(activityInfo.getGenerate_time().getTime());
	    formActivity.setBegin_time2(activityInfo.getBegin_time2());
	    list.add(formActivity);
    }
	  return list;
  }
	
}
