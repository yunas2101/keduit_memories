package com.kedu.factories;

import com.kedu.beans.LGTv;
import com.kedu.beans.SamsungTv;
import com.kedu.interfaces.Tv;

public class TvFactory {

	public static Tv getTv(String type) {
		
		if(type.equals("samsung")) {
			return new SamsungTv();
			
		} else if(type.equals("lg")) {
			return new LGTv();
	
		} else {
			return null;
		}
			
	}
}
