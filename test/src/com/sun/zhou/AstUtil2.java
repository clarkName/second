package com.sun.zhou;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Statement;
import org.eclipse.jdt.core.dom.TypeDeclaration;

public class AstUtil2
{
	private static List<String> result1 = new ArrayList<String>();

	/**
	 * get compilation unit of source code
	 * 
	 * @param javaFilePath
	 * @return CompilationUnit
	 */
	public static void getCompilationUnit(String javaFilePath)
	{
		byte[] input = null;
		try
		{
			BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(javaFilePath));
			input = new byte[bufferedInputStream.available()];
			bufferedInputStream.read(input);
			bufferedInputStream.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		ASTParser astParser = ASTParser.newParser(AST.JLS3);
		char[] charArray = new String(input).toCharArray();
		astParser.setSource(charArray);
		astParser.setKind(ASTParser.K_COMPILATION_UNIT);
		CompilationUnit result = (CompilationUnit) (astParser.createAST(null));
		TypeDeclaration type = (TypeDeclaration) result.types().get(0);

		List<Comment> commentList = result.getCommentList();
		List<Position> positionList = new ArrayList<Position>();
		Iterator<Comment> ite1 = commentList.iterator();
		Position position = null;
		while (ite1.hasNext())
		{
			Comment comment = ite1.next();
			if (comment.isLineComment() || comment.isBlockComment())
			{
				position = new Position();
				int startPosition = comment.getStartPosition();
				int end = comment.getLength() + startPosition;
				// System.out.println(startPosition+"....."+end);
				position.setStart(startPosition);
				position.setEnd(end);
				positionList.add(position);

			}

		}
		MethodDeclaration[] methods = type.getMethods();
		for (int i = 0; i < methods.length; i++)
		{

			MethodDeclaration method = methods[i];
			SimpleName methodName = method.getName();
			int methodStart = method.getBody().getStartPosition() + 1;
			// int line = result.getLineNumber(methodStart);
			// System.out.println(line);
			int length = method.getBody().getLength();
			int methodEnd = methodStart + length - 2;
			String methodContext = new String(input).toString().substring(methodStart, methodEnd);
			String[] context = methodContext.split("\n\t");
			String wordContext = "";
			String annotationWord = "";
			//标识是否可以截为一段
			Boolean flag = null;
			for (int k = 0; k < context.length; k++)
			{
				String temp = context[k];
				//判断代码后面里面是否有//
				if (isLinewithBackslash(temp))
				{
					flag = null;
					annotationWord += wordContext;
					System.out.println(annotationWord);
					wordContext = "";
					wordContext += temp;
					System.out.println(wordContext);
					wordContext = "";
					continue;
				}
				else if (temp.indexOf("//") != -1 && wordContext.length() < 1)
				{
					flag = true;
				}
				else if (temp.indexOf("//") != -1 && wordContext.length() > 1)
				{
					flag = false;
				}else if((temp.indexOf("/*") != -1||temp.indexOf("*") != -1||temp.indexOf("*/") != -1)&& wordContext.length() < 1){
					flag = true;
				}else if((temp.indexOf("/*") != -1||temp.indexOf("*") != -1||temp.indexOf("*/") != -1)&& wordContext.length() >1){
					flag = false;
				}
				//添加代码
				if (flag == null)
				{
					wordContext += temp;

				}
				//添加注释代码
				else if (flag)
				{
					annotationWord += temp;
					flag = null;
				}
				//打印输出
				else
				{
					annotationWord += wordContext;
					System.out.println(annotationWord);
					result1.add(annotationWord);
					annotationWord = "";
					wordContext = "";
					annotationWord += temp;
					flag = true;
				}
			}
			// 方法结束时剩下的字符串
			if (!wordContext.equals(""))
			{
				System.out.println(wordContext);
			}
		}

	}

	public static boolean isLinewithBackslash(String word)
	{
		if (word.indexOf("//") != -1)
		{
			String[] t1 = word.split("//");
			if (t1[0].matches(""))
			{
				return true;
			}
		}
		return false;
	}

	public static void main(String[] args)
	{
		getCompilationUnit("d://CommandLineExecutor1.java");
		// System.out.println(result1);
	}
}
