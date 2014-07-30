package com.bean

import scala.beans.BeanProperty
import java.util.Date
import java.util.Calendar

class SampleGsonBean(@BeanProperty
  var sampleDate:Date) {

  @BeanProperty
  var sampleString:String = new String("Test")

  
}
