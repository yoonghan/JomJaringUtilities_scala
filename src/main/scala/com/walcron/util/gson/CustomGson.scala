package com.walcron.util.gson

import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.walcron.util.common.CommonUtility

object CustomGson {
	val gson:Gson = new GsonBuilder().setDateFormat(CommonUtility.getCommonBeanValue.getDateFormat()).create();
	
	def getGson():Gson = {
		gson;
	}
	
	def toGson[A](obj:A):String = {
		gson.toJson(obj)
	}
	
	def fromGson[A](jsonValue:String, objectType: Class[A]): A ={
		gson.fromJson(jsonValue, objectType)
	}
}