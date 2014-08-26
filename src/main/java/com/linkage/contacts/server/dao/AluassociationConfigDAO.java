package com.linkage.contacts.server.dao;

import java.util.List;

import com.linkage.contacts.server.entity.AluassociationConfig;
import com.linkage.contacts.server.vo.FormAluassociation;

public interface AluassociationConfigDAO
{
	public abstract void insert(AluassociationConfig aluassociationConfig);
	
	public abstract void update(AluassociationConfig aluassociationConfig);
	
	public abstract void delete(AluassociationConfig aluassociationConfig);
	
	public abstract AluassociationConfig selectByPrimaryKey(int aluassociation_id);
	
	public abstract List<FormAluassociation> getAluInfosByUser(int user_id,int university_id);
  
	public abstract List<FormAluassociation> getAluInfosByAdmin(int university_id);
}
