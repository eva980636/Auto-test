package com.autoTestUI;

import java.io.IOException;

public class AutoTest {

	// 需要根据环境修改，如：查看5.1版本对应android stdio的id（ android list target )
	static String androidTargetId = "4";

	// project name 最后生成的jar包名字 projectName.jar
	static String projectName = "otoAutoTest";

	// {"包名.类名", "执行的函数名"}
	static String[][] testClassFuncName = {
		/**/
		{"com.autoTestUI.calc", "testcalc"},
		{ "com.autoTestUI.clock", "testclock" },
		{ "com.autoTestUI.date", "testdate" },
		{ "com.autoTestUI.email", "testemail" },
		{ "com.autoTestUI.adobe_acrobat_dc", "testadobe_acrobat_dc" },
		{ "com.autoTestUI.gdmap", "testgdmap" },
		{ "com.autoTestUI.microsoft_execl", "testmicrosoft_execl" },
		{ "com.autoTestUI.microsoft_onenote", "microsoft_onenote" },
		{ "com.autoTestUI.microsoft_outlook", "testmicrosoft_outlook" },
		{ "com.autoTestUI.microsoft_powerpoint", "testmicrosoft_powerpoint" },
		{ "com.autoTestUI.microsoft_word", "testmicrosoft_word" },
		{ "com.autoTestUI.note", "testnote" },
		{ "com.autoTestUI.tencent_video", "testtencent_video" },
		{ "com.autoTestUI.VLC", "testVLC" },
		{ "com.autoTestUI.wps", "testwps" },
	    /**/
		{"com.autoTestUI.music", "testmusic"},
		{ "com.autoTestUI.terminal", "testterminal" },
		{ "com.autoTestUI.termux", "testtermux" },		
		{ "com.autoTestUI.wpspro", "testwpspro" },
		{ "com.autoTestUI.wpsemail", "testwpsemail" },
		{ "com.autoTestUI.firefox", "testfirefox" },
		{ "com.autoTestUI.qq", "testqq" },
		{ "com.autoTestUI.wechat", "testwechat" },
		{ "com.autoTestUI.wyiyunmusic", "testwyiyunmusic" },
		{"com.autoTestUI.fm", "testfm"},
		{"com.autoTestUI.toutiao", "testtoutiao"},
		{"com.autoTestUI.baiduy", "testbaiduy"},
		{"com.autoTestUI.seafile", "testseafile"},
		{"com.autoTestUI.taobao", "testtaobao"},
		{"com.autoTestUI.jd", "testjd"},
		/**/
		{"com.autoTestUI.angrybird", "testangrybird"},
		{"com.autoTestUI.appstore", "testappstore"},
		{"com.autoTestUI.czfilemanager", "testczfilemanager"},
		{"com.autoTestUI.esfilemanager", "testesfilemanager"},
		{"com.autoTestUI.gugepinyin", "testgugepinyin"},
		{"com.autoTestUI.meitu", "testmeitu"},
		{"com.autoTestUI.tuniu", "testtuniu"},
		{"com.autoTestUI.wandoujia", "testwandoujia"},
		{"com.autoTestUI.xiaoying", "testxiaoying"},
		{"com.autoTestUI.xiecheng", "xiecheng"},
		{"com.autoTestUI.xuetang", "testxuetang"},
		{"com.autoTestUI.yx_2048", "testyx"}
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
