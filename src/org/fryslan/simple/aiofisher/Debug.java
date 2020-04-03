package org.fryslan.simple.aiofisher;

public class Debug {
	public static void debug(String item, String value){
		if(FryAIOFisher.debugMap.containsKey(item)){
			FryAIOFisher.debugMap.replace(item,value);
		}else{
			FryAIOFisher.debugMap.put(item, value);
		}
	}
}
