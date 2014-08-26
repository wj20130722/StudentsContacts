package com.linkage.contacts.server.dao;

import com.linkage.contacts.server.entity.AluassociationRoleInfo;
import com.linkage.contacts.server.entity.AluassociationRoleInfoKey;

public interface AluassociationRoleInfoDAO
{
	public abstract void insert(AluassociationRoleInfo aluassociationRoleInfo);
	
	public abstract void update(AluassociationRoleInfo aluassociationRoleInfo);
	
	public abstract void delete(AluassociationRoleInfo aluassociationRoleInfo);
	
	public abstract AluassociationRoleInfo selectByPrimaryKey(AluassociationRoleInfoKey aluassociationRoleInfoKey);
	
	public abstract int countByUserAlu(int user_id,int aluassociation_id);
	
	public abstract int deleteByUserAlu(int user_id,int aluassociation_id);
}
