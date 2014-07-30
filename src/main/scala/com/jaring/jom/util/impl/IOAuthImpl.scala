package com.jaring.jom.util.impl

trait IOAuthImpl {
	def generateStateToken():String;
	def getGeneratedOAuthURL(stateToken:String):Option[String]
	def getUserInfoJson(authCode:String):Option[String]
	def getUserInfoJson(authCode:String, userId:String):Option[String]
	def convertJsonToObj(json:String):Option[_];
}