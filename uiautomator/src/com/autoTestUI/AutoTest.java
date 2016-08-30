package com.autoTestUI;

import java.io.IOException;

public class AutoTest {	
	public static void main(String[] args) throws IOException, InterruptedException {
		int i;
		int ret = 0;
		String objJarPath = "bin/";
		String objJarName = env.projectName + ".jar";
		// 创建对象
		otoDisplayRun uiRun = new otoDisplayRun();

		if (args.length > 0) {
			if (args.length < 3) {
				System.out.println("usage: java -jar *.jar targetIp otoAutoTest.jar " + args.length);
			}
			objJarPath = "";
			env.targetIp = args[0];
			objJarName = args[1];
		} else {
			//创建 build.xml 并且编译 生成projectName.jar
			uiRun.buildObjJarFile(env.projectName, env.androidTargetId);
			System.out.println("connect target ip is :" +  env.targetIp);
		}
		
		// adb connect + ip
		ret = otoDisplayRun.execCmd("adb connect " + env.targetIp);

		// 将编译生成的jar push到 目标环境
		ret = uiRun.pushTestJar(objJarName, objJarPath);
		if (ret != 0) {
			System.out.println("adb push  failed!");
			return;
		}

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
