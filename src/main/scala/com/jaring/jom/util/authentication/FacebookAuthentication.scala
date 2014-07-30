package com.jaring.jom.util.authentication

import com.jaring.jom.util.impl.IOAuthImpl
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport
import com.google.api.client.json.jackson2.JacksonFactory
import java.util.Arrays
import java.security.SecureRandom
import com.jaring.jom.util.bean.FacebookBean
import com.jaring.jom.util.common.PropertyLoaderUtil
import java.util.Properties
import com.google.api.client.http.GenericUrl
import com.google.api.client.auth.oauth2.ClientParametersAuthentication
import com.google.api.client.auth.oauth2.AuthorizationCodeFlow
import com.google.api.client.auth.oauth2.BearerToken
import java.util.HashMap
import com.google.api.client.http.HttpResponse
import java.io.IOException
import com.jaring.jom.util.gson.CustomGson

object FacebookAuthentication{
  	def getUserInfo(json:String) : FacebookUserInfoEntity = {
		val facebookObject:FacebookUserInfoEntity = CustomGson.fromGson(json, classOf[FacebookUserInfoEntity])
		return facebookObject;
	}
}

class FacebookAuthentication(val callBackURI:String)
	extends IOAuthImpl{
  
    private val clientSecretFile = "/facebook.properties";
    val propertyMap = Array("client.id","client.secret")
    private val fbBean = new FacebookBean 
  
    private val accessTokenKey = "access_token";
    
	private val facebookUrl = "https://www.facebook.com/dialog/oauth";
	private val facebookTokenUrl = "https://graph.facebook.com/oauth/access_token";

	private val user = "user"
	private val httpTransport = GoogleNetHttpTransport.newTrustedTransport()
	private val jsonFactory = JacksonFactory.getDefaultInstance();
	private val userInfoUrl = "https://graph.facebook.com/me";;
	private val scopes:java.util.List[String] = Arrays.asList("public_profile", "email");
	
	private val profileInfo = "id,name,email,first_name,gender,link";	
	
	private val clientSecrets = loadClientSecrets();
	
	private val facebookFlow = new AuthorizationCodeFlow.Builder(
	    		BearerToken.authorizationHeaderAccessMethod(),
	    		httpTransport, jsonFactory, new GenericUrl(facebookTokenUrl),
	    		new ClientParametersAuthentication(fbBean.getClientId(), fbBean.getClientSecret()), fbBean.getClientId(), 
	    		facebookUrl)
	    .setScopes(scopes)
	    .build();
	
    val genUrl = new GenericUrl(facebookTokenUrl);
    	genUrl.put("client_id", fbBean.getClientId());
    	genUrl.put("client_secret", fbBean.getClientSecret());
    	genUrl.put("redirect_uri", callBackURI);
      
    val userUrl = new GenericUrl(userInfoUrl);
    	userUrl.put("fields", profileInfo);


	def isConnectable():Boolean = {
		if(facebookFlow == null || httpTransport == null)
			return false;
		return true;
	}
	
	override def generateStateToken():String = {
		val sr1 = new SecureRandom();
		return EnumAuthentication.FACEBOOK_TOKEN.toString() +sr1.nextInt();
	}

	override def getGeneratedOAuthURL(stateToken:String):Option[String] = {
		if (isConnectable() == false || stateToken==null || stateToken.isEmpty())
			return None;

		val url = facebookFlow.newAuthorizationUrl();
		
		return Option.apply(url.setRedirectUri(callBackURI).setState(stateToken).build());
	}

	def loadClientSecrets() {
		val property:Properties = PropertyLoaderUtil.propertyLoader(clientSecretFile).get
		PropertyLoaderUtil.map(property, propertyMap, false,
	    {(i,value)=>i match{
		    case 0 => fbBean.setClientId(value)
		    case 1 => fbBean.setClientSecret(value)
	    }})
	}
	
	override def getUserInfoJson(authCode:String):Option[String] = {
		return getUserInfoJson(authCode, user);
	}
	
	def getAccessToken(response:HttpResponse):Map[String, String]  = {
		var hashMap:Map[String, String] = Map();
		
		val authJsonIdentity = response.parseAsString();
		
		if(response.getStatusCode() == 200){
			val keyValues = authJsonIdentity.split("&");
			for(keyValue <- keyValues){
				val splitKey = keyValue.split("=")
				hashMap += (splitKey(0) -> splitKey(1))
			}
		}else{
			throw new IOException("Error:"+authJsonIdentity)
		}
		
		return hashMap
	}
	
	override def getUserInfoJson(authCode:String, userId:String) : Option[String] = {
		
		if(isConnectable() == false || authCode == null || authCode.isEmpty())
			return None;
		
		var jsonIdentity:String = null;
		
		val requestFactory = httpTransport.createRequestFactory();
		genUrl.put("code", authCode);
		
		val request = requestFactory.buildGetRequest(genUrl);
		val response = request.execute();
		
		val hashMap = getAccessToken(response);

		if(hashMap.contains(accessTokenKey)){
			userUrl.put(accessTokenKey , hashMap(accessTokenKey));
			val userInfoRequest = requestFactory.buildGetRequest(userUrl);
			request.getHeaders().setContentType("application/json");
			jsonIdentity = userInfoRequest.execute().parseAsString();
		}
		
		return Option.apply(jsonIdentity);
	}
	
	override def convertJsonToObj(json:String):Option[FacebookUserInfoEntity] = {
		if(json == null || json.isEmpty())
			return None
		val entity:FacebookUserInfoEntity = new FacebookUserInfoEntity()
		val facebookInfoEntity =  CustomGson.fromGson(json, entity.getClass())
		return Option.apply(facebookInfoEntity);
	}
	
}