package com.autoTestUI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class otoDisplayRun {

	private static String workspace_path;
	
	public  otoDisplayRun(String projectName, String androidTargetId) {
		System.out.println("-----------build--uiautomator--obj--jar--file----------");
		workspace_path = getWorkSpase();
		System.out.println("----工作空间：\t\n" + workspace_path);

		// 1 创建 build.xml
		execCmd("android create uitest-project -n " + projectName + " -t "
				+ androidTargetId + " -p " + workspace_path);
		
		// 2 ant build 编译 生成projectName.jar 
		execCmd("ant build");
		
		System.out.println("**********************");
		System.out.println("---FINISH BUILDING----");
		System.out.println("**********************");
	}
	
	//获取工作环境目录
	public String getWorkSpase() {
		File directory = new File("");
		String abPath = directory.getAbsolutePath();
		return abPath;
	}
	
	/**
	 * 执行cmd命令，且输出信息到控制台
	 */
	public void execCmd(String cmd) {
		System.out.println("----execCmd:  " + cmd);
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			//正确输出流
			InputStream input = p.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					input));
			String line = "";
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
                saveToFile(line, "runlog.log", false);
			}
			//错误输出流
			InputStream errorInput = p.getErrorStream();
			BufferedReader errorReader = new BufferedReader(new InputStreamReader(
					errorInput));
			String eline = "";
			while ((eline = errorReader.readLine()) != null) {
				System.out.println(eline);
                saveToFile(eline, "runlog.log", false);
			}       
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 将运行结果保存至文件中
    public void saveToFile(String text,String path,boolean isClose) {
    	File file=new File("runlog.log");   	
		BufferedWriter bf=null;
		try {
		    FileOutputStream outputStream=new FileOutputStream(file,true);
		    OutputStreamWriter outWriter=new OutputStreamWriter(outputStream);
		    bf=new BufferedWriter(outWriter);
			bf.append(text);
			bf.newLine();
			bf.flush();
			
			if(isClose){
				bf.close();
			}
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	// 3---push jar
	public void pushTestJar(String jarName) {
		String jarFile = workspace_path + "/bin/" + jarName;
		String objPath = "/data/local/tmp/";
		String pushCmd = "adb push " + jarFile + " " + objPath;
		
		System.out.println("----jar包路径： " +  jarFile);
		System.out.println("----" + pushCmd);
		execCmd(pushCmd);
	}
	
	// 4 run test
	public void runTest(String jarName, String className, String testFuncName) {
		String testCmd = "adb shell uiautomator runtest " + jarName + " --nohup -c " + className + "#" + testFuncName;
		System.out.println("----runTest:  " + testCmd);
		execCmd(testCmd);
	}
}
