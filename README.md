# Auto-test
1. 修改auto-test.java文件 androidTargetId
   根据android list查看本地id.如我本地为4  androidTargetId = "4";
2. 修改auto-test.java文件 IP地址（被测机的IP）
    eg:	// adb connect 192.168.0.105
		Runtime.getRuntime().exec("adb connect 192.168.0.105");
