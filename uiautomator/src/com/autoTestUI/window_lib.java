package com.autoTestUI;

import java.io.IOException;

import android.os.RemoteException;

import com.android.uiautomator.core.UiDevice;
import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;

public class window_lib extends UiAutomatorTestCase{
	public static void windowtest(UiDevice device, String appName)
			throws UiObjectNotFoundException, RemoteException, IOException, InterruptedException {

		Runtime.getRuntime().exec("am start -n " + appName);
		Thread.sleep(3000);

		// 改变窗体大小 左上拉动 改变大小
		UiObject objectSide = new UiObject(
				new UiSelector().resourceId("android:id/mwOuterBorder"));
		android.graphics.Rect myAppSide = objectSide.getVisibleBounds();
		device.drag(myAppSide.left, myAppSide.top, myAppSide.left + 100,
				myAppSide.top + 100, 2);
		Thread.sleep(2000);

		// 重新通过resourceId 获取窗口边界坐标，与预计的坐标不想等……
		UiObject objectSide1 = new UiObject(
				new UiSelector().resourceId("android:id/mwOuterBorder"));
		android.graphics.Rect myAppSide1 = objectSide1.getVisibleBounds();
		// 验证上一次拖动是否成功：10 pixcel 的误差
		if (myAppSide1.left < (myAppSide.left + 90)
				|| myAppSide1.top < (myAppSide1.top + 90)
				|| myAppSide1.left > (myAppSide.left + 110)
				|| myAppSide1.top > (myAppSide1.top + 110)) {
			System.out.println("左上 向左下改变窗口大小失败！");
		}
		device.drag(myAppSide1.left, myAppSide1.top, myAppSide1.left - 100,
				myAppSide1.top - 100, 2);
		Thread.sleep(2000);

		// 改变窗体大小 右下拉动 改变大小
		UiObject objectSide2 = new UiObject(
				new UiSelector().resourceId("android:id/mwOuterBorder"));
		android.graphics.Rect myAppSide2 = objectSide2.getVisibleBounds();
		// 验证上一次拖动是否成功：
		if (myAppSide2.left < (myAppSide.left - 10)
				|| myAppSide2.top < (myAppSide2.top - 10)
				|| myAppSide2.left > (myAppSide.left + 10)
				|| myAppSide.top > (myAppSide2.top + 10)) {

			System.out.println("左上 向左上改变窗口大小失败！");
		}
		device.drag(myAppSide2.right - 2, myAppSide2.bottom - 2,
				myAppSide2.right + 100, myAppSide2.bottom + 100, 2);
		Thread.sleep(2000);

		// 重新通过resourceId 获取窗口边界坐标 下面 拖动时，差几个像素点到边界 所以-2
		UiObject objectSide3 = new UiObject(
				new UiSelector().resourceId("android:id/mwOuterBorder"));
		android.graphics.Rect myAppSide3 = objectSide3.getVisibleBounds();
		device.drag(myAppSide3.right - 2, myAppSide3.bottom - 2,
				myAppSide3.right - 100, myAppSide3.bottom - 100, 2);
		Thread.sleep(2000);

		// 最大化
		UiObject objectMax = new UiObject(
				new UiSelector().resourceId("android:id/mwMaximizeBtn"));
		objectMax.click();
		Thread.sleep(1000);
		objectMax.click();
		Thread.sleep(1000);
		// 最小化
		// UiObject objectMin=new UiObject(new
		// UiSelector().resourceId("android:id/mwMinimizeBtn"));
		// objectMin.click();
		// sleep(1000);

		// 关闭程序
		UiObject objectClose = new UiObject(
				new UiSelector().resourceId("android:id/mwCloseBtn"));
		objectClose.click();
		Thread.sleep(1000);
		// 重新启动程序
		Runtime.getRuntime().exec("am start -n " + appName);
		Thread.sleep(3000);

		// 拖动程序 拖动程序后， 窗口最大化/最小化等位置将无法通过resourceId获取到
		UiObject objectHead = new UiObject(
				new UiSelector().resourceId("android:id/mw_decor_header"));
		objectHead.dragTo(1000, 500, 10);
		Thread.sleep(1000);
		// 强制关闭程序
		Runtime.getRuntime().exec("am force-stop " + appName);
		device.pressHome();
	}
}
