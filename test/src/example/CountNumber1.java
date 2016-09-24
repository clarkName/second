package example;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountNumber1
{
	List<String> nums = new ArrayList<String>();
	Map<Character, Integer> map = new HashMap<Character, Integer>();

	public static void main(String[] args)
	{
		// start();
		CountNumber1 obj = new CountNumber1();
		BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader(new File("E:\\shishi\\new 4.txt")));
			String context = null;
			while ((context = br.readLine()) != null)
			{
				obj.parseText(context);
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				br.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}

		obj.countAppearNums();
		obj.getResult();
	}

	public static void start()
	{
		CountNumber1 obj = new CountNumber1();
		obj.getNetMessage();
		obj.countAppearNums();
		obj.getResult();
	}

	public void parseText(String context)
	{
		if (!"".equals(context))
		{
			int start = context.indexOf("\t");
			int end = context.indexOf("\t", start + 1);
			String number = null;
			if (end != -1)
			{
				number = context.substring(start, end);
			}
			else
			{
				try
				{
					number = context.substring(start);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}

			nums.add(number);
		}
	}

	public void countAppearNums()
	{
		for (int s = 0; s < nums.size(); s++)
		{
			String str = nums.get(s).trim();
			for (int i = 0; i < str.length(); i++)
			{
				char c = str.charAt(i);
				if (map.get(c) != null)
				{
					int num = map.get(c);
					map.put(c, ++num);
				}
				else
				{
					map.put(c, 1);
				}
			}
		}
	}

	public void getResult()
	{
		List<Character> list = new ArrayList<Character>(map.keySet());
		Collections.sort(list);
		for (Character key : list)
		{
			System.out.println("key= " + key + " and value= " + map.get(key));
		}
	}

	public void getNetMessage()
	{
		try
		{
			URL url = new URL("http://www.jo99cp.com/TrendCharts/CQSSC/SSC_3X_ZST.aspx");
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setInstanceFollowRedirects(false);
			con.setUseCaches(false);
			con.setAllowUserInteraction(false);
			con.connect();
			StringBuffer sb = new StringBuffer();
			String line = "";
			BufferedReader URLinput = new BufferedReader(new InputStreamReader(con.getInputStream()));
			while ((line = URLinput.readLine()) != null)
			{
				sb.append(line);
				// System.out.println(line);
				boolean bool = line.matches(".*<span>[0-9]+<font color='red'>[0-9]+</font></span>.*");
				if (bool)
				{
					int start = line.indexOf("<span>");
					int end = line.indexOf("<font");
					// int end=line.indexOf("</span>");
					String number = line.substring(start + 6, end);
					start = line.indexOf("'red'>");
					end = line.indexOf("</font>");
					String number1 = line.substring(start + 6, end);
					nums.add(number + number1);
					// System.out.println(line);
				}
			}
			// System.out.println(nums.size());
			con.disconnect();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
