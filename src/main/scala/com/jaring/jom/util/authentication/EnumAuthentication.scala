package com.jaring.jom.util.authentication

object EnumAuthentication extends Enumeration{
	type Authentication = Value
	val FACEBOOK_TOKEN = Value("facebook;")
	val GOOGLE_TOKEN = Value("google;")
}