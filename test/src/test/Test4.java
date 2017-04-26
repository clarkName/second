package test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;

public class Test4 {
	private static final  SimpleDateFormat sdf = 
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args)
	{
		
		HashMap<String, String> sortMap=new HashMap<>();
		sortMap.put("a", "b");
		sortMap.put("c", "d");
		System.out.println(sortMap.toString());
	//	sortMap.forEach((a,b)->{a.compareTo(b);System.out.println(a);});
	}
	public static String getDateString(Date date)
	{
		return sdf.format(date);
	}
}
