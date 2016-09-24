package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.omg.CORBA.TIMEOUT;

public class Test
{
	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException
	{
		List list = getList();
		List list1 = getList();
		ExecutorService es = Executors.newCachedThreadPool();
		Future future = es.submit(new FutureTask<String>(new Callable<String>()
		{

			@Override
			public String call() throws Exception
			{
				Thread.sleep(5000);
				System.out.println(Thread.currentThread().getName());

				return "hello";
			}

		}));
		Future future1 = es.submit(new FutureTask<String>(new Callable<String>()
		{

			@Override
			public String call() throws Exception
			{
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName());

				return "hello";
			}

		}));

		list.clear();
		long start = System.currentTimeMillis();

		while (System.currentTimeMillis() - start < 1000)
		{
			try
			{
				System.out.println(System.currentTimeMillis() - start);
				List<Future> lists = new ArrayList<Future>();
				lists.add(future);
				lists.add(future1);
				for (Future l : lists)
				{
					list.add(l.get());
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
			System.out.println(System.currentTimeMillis() - start);
		}
		System.out.println(Thread.currentThread().getName());
		System.out.println(list.get(0));
		// System.out.println(list.get(1));
	}

	public static List getList()
	{
		ArrayList list = new ArrayList();
		list.add(1);
		return list;
	}
}
