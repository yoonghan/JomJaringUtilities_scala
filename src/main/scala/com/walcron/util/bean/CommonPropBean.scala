package com.walcron.util.bean

import scala.beans.BeanProperty
import java.text.SimpleDateFormat

class CommonPropBean {

  private val defaultDateFormat:String ="dd/MM/yyyy hh:mm:ss";
  private val defaultWritableDirectory:String =System.getProperty("user.home")+"/misc";
  
  @BeanProperty
  var dateFormat:String = defaultDateFormat
  
  @BeanProperty
  var writableDirectory:String = defaultWritableDirectory
  
}