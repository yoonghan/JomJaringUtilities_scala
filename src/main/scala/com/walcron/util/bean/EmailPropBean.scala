package com.walcron.util.bean

import scala.beans.BeanProperty
import java.text.SimpleDateFormat
import com.walcron.util.common.CommonUtility
import java.util.Properties

class EmailPropBean(){
  
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
}