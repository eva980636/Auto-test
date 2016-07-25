package com.autoTestUI;

import java.io.IOException;

public class AutoTest {

	// 需要根据环境修改，如：查看5.1版本对应android stdio的id（ android list target )
	static String androidTargetId = "1";

	// project name 最后生成的jar包名字 projectName.jar
	static String projectName = "otoAutoTest";

	// {"包名.类名", "执行的函数名"}
	static String[][] testClassFuncName = {
		{"com.autoTestUI.calc", "testcalc"},
		{"com.autoTestUI.music", "testmusic"},
		{ "com.autoTestUI.terminal", "testterminal" },
		{ "com.autoTestUI.termuxl", "testtermux" },		
		{ "com.autoTestUI.wpspro", "testwpspro" },
		{ "com.autoTestUI.wpsemail", "testwpsemail" },
		{ "com.autoTestUI.firfefox", "testefirefox" },
		{ "com.autoTestUI.qq", "testeqq" },
		{ "com.autoTestUI.wechat", "testewechat" },
		{ "com.autoTestUI.wyiyunmusic", "testwyiyunmusic" },
		{"com.autoTestUI.fm", "testfm"},
		{"com.autoTestUI.toutiao", "testtoutiao"},
		{"com.autoTestUI.baiduy", "testbaiduy"},
		{"com.autoTestUI.seafile", "testseafile"},
		{"com.autoTestUI.taobao", "testtaobao"},
		{"com.autoTestUI.jd", "testjd"}		
	};

	public static void main(String[] args) throws IOException {
		int i;
		// 创建对象的同时，创建 build.xml 并且编译 生成projectName.jar
		otoDisplayRun uiRun = new otoDisplayRun(projectName, androidTargetId);
		/*
		 * if (args.length != 2) { System.out.println(
		 * "usage: java -jar *.jar connect_dev_ip path_otoAutoTest_jarfile");
		 * return; } else {
		 * 
		 * }
		 */
		// adb connect 192.168.0.105
		Runtime.getRuntime().exec("adb connect 192.168.0.105");
		
		// 将编译生成的jar push到 目标环境
		uiRun.pushTestJar(projectName + ".jar");

		System.out.println("**********************");
		System.out.println("----START TESTING----");
		System.out.println("**********************");

		// 循环执行需要的测试 class
		System.out.println("总测试数量： " + testClassFuncName.length);
		for (i = 0; i < testClassFuncName.length; i++) {
			uiRun.runTest(projectName + ".jar", testClassFuncName[i][0],
					testClassFuncName[i][1]);
		}

		System.out.println("**********************");
		System.out.println("----FINISH TESTING----");
		System.out.println("**********************");
	}
}
