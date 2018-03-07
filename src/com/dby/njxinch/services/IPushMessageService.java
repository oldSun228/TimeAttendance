package com.dby.njxinch.services;

import java.util.Map;

public interface IPushMessageService {


	public void insertPushMessage(String bidno,int type, Map m);
	
	
	
	public void updateExePush();
	
}
