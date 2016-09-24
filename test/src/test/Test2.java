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

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.omg.CORBA.TIMEOUT;

public class Test2
{
	public static void main(String[] args)
	{
		String str="11234343";
		System.out.println(str.matches("[0-9]{1,12}"));
		
		
		
	}
}
