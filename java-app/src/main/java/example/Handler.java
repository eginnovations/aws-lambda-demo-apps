package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.HashMap;

public class Handler implements RequestHandler<Map<String,String>, String>{
  private static final Logger logger = LoggerFactory.getLogger(Handler.class);
  public Handler(){
    
  }
  @Override
  public String handleRequest(Map<String,String> event, Context context)
  {
    String response = new String();
	
	HashMap responseData = new HashMap();
	long millis=System.currentTimeMillis();  
	java.util.Date date=new java.util.Date(millis);  
	responseData.put("reqTime",date);
	
	try {
			logger.info("eG EVENT: data {}", event);
			if(event != null){
				responseData.put("input",event);
				String str1 = event.get("str_1");
				String str2 = event.get("str_2");
				String sleep = event.get("sleep_secs");
				
				if( str1 == null ){
					str1 = "NULL";
				}
				if( str2 == null ){
					str2 = "NULL";
				}
				
				String concatStr = str1+str2;
				responseData.put("OUTPUT", concatStr);
				
				if(sleep != null){
					try{
						long sleepTime = Long.parseLong(sleep);
						sleep(sleepTime*1000);
					}catch(Exception e){
						e.printStackTrace();
					}
				}
			}
	}
	catch(Exception e){
		 e.printStackTrace();
	}
	response = responseData.toString();
    return response;
  }
  
  private void sleep(long millis){
	  try{
		Thread.sleep(millis);
	  }
	  catch(Exception e){
		   e.printStackTrace();
	  }
  }
}