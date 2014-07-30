package com.bean

import scala.beans.BeanProperty
import java.text.SimpleDateFormat
import com.jaring.jom.util.common.CommonUtility
import java.util.Properties
import com.jaring.jom.util.bean.PropertyBeanMapper

class CustomEmailBean extends PropertyBeanMapper{
  
	@BeanProperty
	var toUser = ""
	@BeanProperty
	var fromUser = ""
	@BeanProperty
	var subject = ""
	@BeanProperty
	var userName = ""
	@BeanProperty
	var password = ""
	@BeanProperty
	var dateFormat:SimpleDateFormat = new SimpleDateFormat(CommonUtility.getCommonBeanValue.getDateFormat());
	@BeanProperty
	var property:Properties = null

	def map(property:Properties){
	  fromUser = property.getProperty("from.user");
	  toUser = property.getProperty("to.user");
	}
}