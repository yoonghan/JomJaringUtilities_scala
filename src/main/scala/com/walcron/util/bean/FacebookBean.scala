package com.walcron.util.bean

import scala.beans.BeanProperty

class FacebookBean {
  @BeanProperty
  var clientId:String = null
  
  @BeanProperty
  var clientSecret:String = null
}