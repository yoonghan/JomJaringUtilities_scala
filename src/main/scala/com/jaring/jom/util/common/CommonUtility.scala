package com.jaring.jom.util.common

import com.jaring.jom.util.bean.CommonPropBean
import java.util.Properties

object CommonUtility {
	val commonPropBean = new CommonPropBean()
	val propertyFileName = "common.properties"
	val propertyMap = Array("date.format", "writable.directory")
	initVal()
	
	private def initVal():Unit={
	   val prop:Option[Properties] = PropertyLoaderUtil.propertyLoader(propertyFileName)

	   if(prop.isDefined){
		   val property:Properties = prop.get

		   PropertyLoaderUtil.map(property, propertyMap, true,
			    {(i,value)=>i match{
				    case 0 => commonPropBean.setDateFormat(value)
				    case 1 => commonPropBean.setWritableDirectory(value)
			}})
	   }
	}
	
	def getCommonBeanValue():CommonPropBean = {
	  commonPropBean
	}
}