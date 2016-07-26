package com.autoTestUI;

import java.io.IOException;

public class AutoTest {
	public static void main(String[] args) throws IOException {
		int i;
		
		// 创建对象的同时，创建 build.xml 并且编译 生成projectName.jar
		otoDisplayRun uiRun = new otoDisplayRun(env.projectName, env.androidTargetId);
		/*
		 * if (args.length != 2) { System.out.println(
		 * "usage: java -jar *.jar connect_dev_ip path_otoAutoTest_jarfile");
		 * return; } else {
		 * 
		 * }
		 */
		// adb connect + ip
		Runtime.getRuntime().exec("adb connect " + env.targetIp);
		
		// 将编译生成的jar push到 目标环境
		uiRun.pushTestJar(env.projectName + ".jar");

		System.out.println("**********************");
		System.out.println("----START TESTING----");
		System.out.println("**********************");

		// 循环执行需要的测试 class
		System.out.println("总测试数量： " + env.testClassFuncName.length);
		for (i = 0; i < env.testClassFuncName.length; i++) {
			uiRun.runTest(env.projectName + ".jar", env.testClassFuncName[i][0],
					env.testClassFuncName[i][1]);
		}

		System.out.println("**********************");
		System.out.println("----FINISH TESTING----");
		System.out.println("**********************");
	}
}
