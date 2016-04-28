package com.walcron.util.common

import java.io.InputStream
import scala.io.Source
import java.util.Properties
import com.walcron.util.bean.PropertyBeanMapper
import java.io.FileNotFoundException

object PropertyLoaderUtil {
	val CLASS_LOCATION:String = this.getClass().getName()
	
	def loadProperty(propertyFileName:String, beanMapper:PropertyBeanMapper){
	  
	  if(beanMapper == null)
		  throw new IllegalAccessException("Bean has not been initialized")
	    
	  val property:Properties = new Properties()
	  
	  val inputStream:InputStream = loadFile(propertyFileName)
	  if(inputStream != null){
		  property.load(inputStream)
		  beanMapper.map(property)
		  inputStream.close()
	  }else{
	    throw new FileNotFoundException("File "+propertyFileName+" is not accessible")
	  }
	}
	
	def fileLoader(fileName:String):InputStream = {
		return loadFile(fileName);
	}
	
	def propertyLoader(fileName:String):Option[Properties] = {
	  var property:Properties = null
	  val inputStream:InputStream = loadFile(fileName)
	  if(inputStream != null){

	    property = new Properties()
		property.load(inputStream)
	  }
	  return Option.apply(property);
	}
	
	private def loadFile(propFileName:String):InputStream = {
	  var input:InputStream = this.getClass().getClassLoader().getResourceAsStream(propFileName)
	  
	  if(input == null){
	    input = this.getClass().getResourceAsStream(propFileName)
	  }
	  
	  input
	}
	
	def map(property:Properties, propertyMap:Array[String], ignorable:Boolean, scope:(Int,String) => Unit){
	  var i = 0;
	 
	  val setWithPropertyBound = setProperty(_:Int, _:String, property, ignorable)
	  
	  for(key <- propertyMap){
	    val value = setWithPropertyBound(i, key)
	    scope(i, value)
	    i+=1
	  }
	  
	}
	
	private def setProperty(propLoc:Int, key:String, property:Properties, ignorable:Boolean): String = {
	  val value = property.getProperty( key )
	  if(ignorable == false && ( value == null|| value.isEmpty() )){
	    throw new IllegalAccessException(String.format("Define %s:", key ))
	  }
	  value
	}

}