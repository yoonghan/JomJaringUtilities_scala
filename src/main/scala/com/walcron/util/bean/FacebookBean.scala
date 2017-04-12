package com.walcron.util.bean

import scala.beans.BeanProperty

class FacebookBean {
  @BeanProperty
  var clientId:String = null
  
  @BeanProperty
  var clientSecret:String = null
}

class FacebookTokenBean (var access_token:String, var token_type: String, var expires_in: String)