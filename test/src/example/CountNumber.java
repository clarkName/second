package example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CountNumber
{
	private List<String> nums = new ArrayList<String>();
	private List<String> nums1 = new ArrayList<String>();
	private Map<Character, Integer> map = new HashMap<Character, Integer>();
	private List<String> preResult = new ArrayList<String>();
	private static boolean flag = false;
	private volatile List<Integer> finallyResult = new ArrayList<Integer>();
	private final String APPEAR_COMBINATION_3 = "0";
	private final String APPEAR_COMBINATION_4 = "1";

	public static void main(String[] args)
	{
		start();
	}

	public static void start()
	{
		CountNumber obj = new CountNumber();
		obj.nums = obj.getNetMessage();
		obj.countAppearNums();
	//	obj.appearCombination3();
	//	obj.appearCombination4();
//		FutureTask<String> future = new FutureTask<String>(new Callable<String>()
//		{
//
//			@Override
//			public String call() throws Exception
//			{
//				obj.getCurrentNumber();
//				return "";
//			}
//
//		});
//		ExecutorService es = Executors.newFixedThreadPool(5);
//		es.submit(future);
	//	obj.countAppearNums();
		// obj.getResult();
	}

	public void parseText(String context)
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		nums.add(number);
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
		getResult();
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

	public List<String> getNetMessage()
	{
		List<String> numbers = new ArrayList<String>();
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
					numbers.add(number + number1);
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
		return numbers;
	}

	public void appearCombination3()
	{
		int size = nums.size();
		String one = nums.get(size - 4);
		String two = nums.get(size - 3);
		String three = nums.get(size - 2);
		String four = nums.get(size - 1);

		Character num3_1 = one.charAt(2);
		Character num3_2 = two.charAt(2);
		Character num3_3 = three.charAt(2);
		Character num3_4 = four.charAt(2);
		Character num1_1 = one.charAt(0);
		Character num1_2 = two.charAt(0);
		Character num1_3 = three.charAt(0);
		Character num1_4 = four.charAt(0);
		Character num2_1 = one.charAt(1);
		Character num2_2 = two.charAt(1);
		Character num2_3 = three.charAt(1);
		Character num2_4 = four.charAt(1);
		Character num4_1 = one.charAt(3);
		Character num4_2 = two.charAt(3);
		Character num4_3 = three.charAt(3);
		Character num4_4 = four.charAt(3);

		Set<Object> set = new HashSet<Object>();
		set.add(num3_1);
		set.add(num3_2);
		set.add(num3_3);
		set.add(num3_4);
		if (set.size() != 4)
		{
			preResult.add(APPEAR_COMBINATION_3);
		}
		int result1 = Integer.parseInt(num1_1 + "") + Integer.parseInt(num2_1 + "") + Integer.parseInt(num3_1 + "")
				+ Integer.parseInt(num4_1 + "");
		int result2 = Integer.parseInt(num1_2 + "") + Integer.parseInt(num2_2 + "") + Integer.parseInt(num3_2 + "")
				+ Integer.parseInt(num4_2 + "");
		int result3 = Integer.parseInt(num1_3 + "") + Integer.parseInt(num2_3 + "") + Integer.parseInt(num3_3 + "")
				+ Integer.parseInt(num4_3 + "");
		int result4 = Integer.parseInt(num1_4 + "") + Integer.parseInt(num2_4 + "") + Integer.parseInt(num3_4 + "")
				+ Integer.parseInt(num4_4 + "");

		result1 = result1 % 10;
		result2 = result2 % 10;
		result3 = result3 % 10;
		result4 = result4 % 10;

		set.clear();
		set.add(result1);
		set.add(result2);
		set.add(result3);
		set.add(result4);
		if (set.size() != 4)
		{
			preResult.add(APPEAR_COMBINATION_3);
		}
	}

	public void appearCombination4()
	{
		preResult.add(APPEAR_COMBINATION_4);
		List<Integer> list = new ArrayList<Integer>(map.values());
		Collections.sort(list);
		String preResult1 = "";
		String preResult2 = "";
		String preResult3 = "";
		String preResult4 = "";
		String preResult5 = "";
		Map<String, Integer> preResult1Map = new HashMap<String, Integer>();
		Map<String, Integer> preResult2Map = new HashMap<String, Integer>();
		for (Character key : map.keySet())
		{
			if (map.get(key) == list.get(0))
			{
				preResult1 = (key + "");
			}
			if (map.get(key) == list.get(1))
			{
				preResult2 = (key + "");
			}
			if (map.get(key) == list.get(2))
			{
				preResult3 = (key + "");
			}
			if (map.get(key) == list.get(3))
			{
				preResult4 = (key + "");
			}
			if (map.get(key) == list.get(4))
			{
				preResult5 = (key + "");
			}
		}

		int size = nums.size();
		String one = nums.get(size - 4);
		String two = nums.get(size - 3);
		String three = nums.get(size - 2);
		String four = nums.get(size - 1);
		String value = one + two + three + four;
		int zeroCounts = value.split("0").length;
		int oneCounts = value.split("1").length;
		int twoCounts = value.split("2").length;
		int threeCounts = value.split("3").length;
		int fourCounts = value.split("4").length;
		int fiveCounts = value.split("5").length;
		int sixCounts = value.split("6").length;
		int sevenCounts = value.split("7").length;
		int eightCounts = value.split("8").length;
		int nineCounts = value.split("9").length;
		preResult1Map.put("0", zeroCounts);
		preResult1Map.put("1", oneCounts);
		preResult1Map.put("2", twoCounts);
		preResult1Map.put("3", threeCounts);
		preResult1Map.put("4", fourCounts);
		preResult1Map.put("5", fiveCounts);
		preResult1Map.put("6", sixCounts);
		preResult1Map.put("7", sevenCounts);
		preResult1Map.put("8", eightCounts);
		preResult1Map.put("9", nineCounts);

		int c1 = preResult1Map.get(preResult1);
		int c2 = preResult1Map.get(preResult2);
		int c3 = preResult1Map.get(preResult3);
		int c4 = preResult1Map.get(preResult4);
		int c5 = preResult1Map.get(preResult5);
		getPreResult(c1, c2, c3, c4, c5);
		getPreResult(c1, c2, c3, c4, c5);
	}

	private void getPreResult(int c1, int c2, int c3, int c4, int c5)
	{
		if (c1 < 3 && preResult.contains(c1))
		{
			preResult.add(c1 + "");
		}
		else if (c2 < 3 && !preResult.contains(c2))
		{
			preResult.add(c2 + "");
		}
		else if (c3 < 3 && !preResult.contains(c3))
		{
			preResult.add(c3 + "");
		}
		else if (c4 < 3 && !preResult.contains(c4))
		{
			preResult.add(c4 + "");
		}
		else if (c5 < 3 && !preResult.contains(c5))
		{
			preResult.add(c5 + "");
		}
	}

	private void getCurrentNumber()
	{
		while (true)
		{
			try
			{
//				if (flag)
//				{
//					Thread.sleep(600000);
//					flag = true;
//				}

			//	nums1 = getNetMessage();
				String curNum = nums1.get(nums1.size() - 1);
				String lastNum = nums1.get(nums1.size() - 1);
				if (!curNum.equals(lastNum))
				{
					curNum = curNum.substring(2);
					if (preResult.get(0).indexOf(curNum) != -1)
					{
						finallyResult.add(0);
					}
					else if (preResult.get(1).indexOf(curNum) != -1)
					{
						finallyResult.add(0);
					}
					else
					{
						finallyResult.add(1);
					}
					nums = nums1;
				}
				//getFinalResult();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}

	public void getFinalResult()
	{
		double count = 0;
		for (int i : finallyResult)
		{
			if (i == 1)
			{
				count++;
			}
		}
		if (finallyResult.size() != 0)
		{
			double rate = count / finallyResult.size();
			System.out.println("final result" + rate);
		}
	}

}
