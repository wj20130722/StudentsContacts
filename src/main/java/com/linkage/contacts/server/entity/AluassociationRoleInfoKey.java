package com.linkage.contacts.server.entity;

public class AluassociationRoleInfoKey
{
	public AluassociationRoleInfoKey()
  {
		
  }

	public AluassociationRoleInfoKey(int aluassociation_id, int user_id)
	{
		this.aluassociation_id = aluassociation_id;
		this.user_id = user_id;
	}

	private int aluassociation_id;

	private int user_id;

	public int getAluassociation_id()
	{
		return aluassociation_id;
	}

	public void setAluassociation_id(int aluassociation_id)
	{
		this.aluassociation_id = aluassociation_id;
	}

	public int getUser_id()
	{
		return user_id;
	}

	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}
}