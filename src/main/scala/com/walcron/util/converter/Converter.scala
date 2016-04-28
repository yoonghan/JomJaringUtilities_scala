package com.walcron.util.converter

import scala.language.implicitConversions

object NumberConverter{
  implicit def intToNumberConverter(decimal:Int) = new NumberConverter(decimal)
}

class NumberConverter(val decimal:Int) {

	private def splitIntoDivision(value:Int, division:Int):List[Int]={
	  if(value == 0){
		  Nil
	  }else{
		  value :: (splitIntoDivision(value/division, division))
	  }
	}

	def toDivisionList(division:Int):List[Int] = {
	  splitIntoDivision(decimal, division);
	}

	def toTernaryString():String = {
		val range = splitIntoDivision(decimal, 3);

		val ternaryStr = for( loop <- range) yield {
			(loop % 3);
		}

		ternaryStr.reverse.mkString
	}
}
