package io.github.a2kaido.moshi;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.Log;
import android.view.View;

import org.junit.Test;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.atomic.AtomicInteger;

public class ScreenShotter2 {
    private static final String FILE_NAME_DELIMITER = "-";
    private static final String IMAGE_TYPE = "jpg";
    private static final String LOG_TAG = "cloud_screenshotter";
    private static final String SCREENSHOT_PATH = "/sdcard/screenshots/";
    private static String lastClassName = "";
    private static String lastMethodName = "";
    private static AtomicInteger counter = new AtomicInteger(0);

    public ScreenShotter2() {
    }

    public static void takeScreenshot(String name, Activity activity, View view) {
        if (activity == null) {
            Log.e("cloud_screenshotter", "Error taking screenshot: Activity cannot be null!");
        } else {
            String fileName = getScreenshotFileName(name);

            try {
                takeScreenshotInternal(fileName, activity, view);
            } catch (Exception var4) {

            }

        }
    }

    private static void takeScreenshotInternal(final String fileName, Activity activity, View view) {
        final View screenView = view;
        final View screenView2 = activity.getWindow().getDecorView().getRootView();
        if (screenView == null) {
            Log.e("cloud_screenshotter", "Error taking screenshot. Root view of given activity is null.");
        } else {
            activity.runOnUiThread(new Runnable() {
                public void run() {
                    screenView.setDrawingCacheEnabled(true);
                    Bitmap drawingCache = screenView.getDrawingCache();
                    screenView2.setDrawingCacheEnabled(true);
                    Bitmap drawingCache2 = screenView2.getDrawingCache();
                    if (drawingCache == null || drawingCache2 == null) {
                        Log.e("cloud_screenshotter", "Error taking screenshot. Calling getDrawingCache on the root view of the activity returned null.");
                    } else {
                        Bitmap bitmap = Bitmap.createBitmap(drawingCache);
                        Bitmap bitmap2 = Bitmap.createBitmap(drawingCache2);
                        screenView.setDrawingCacheEnabled(false);
                        screenView2.setDrawingCacheEnabled(false);
                        Bitmap bitmap3 = bitmapOverlayToCenter(bitmap2, bitmap);

                        File imageFolder = new File("/sdcard/screenshots/");
                        imageFolder.mkdirs();
                        String var5 = fileName;
                        File imageFile = new File(imageFolder, (new StringBuilder(4 + String.valueOf(var5).length())).append(var5).append(".").append("jpg").toString());
                        FileOutputStream out = null;

                        String var10001;
                        String var10002;
                        String var10003;
                        try {
                            out = new FileOutputStream(imageFile);
                            bitmap3.compress(Bitmap.CompressFormat.JPEG, 90, out);
                            out.flush();
                        } catch (IOException var15) {
                            if (var15.getLocalizedMessage() != null) {
                                Log.e("cloud_screenshotter", var15.getLocalizedMessage());
                            }
                        } finally {
                            try {
                                if (out != null) {
                                    out.close();
                                }
                            } catch (IOException var14) {

                            }
                        }

                    }
                }
            });
        }
    }

    private static String getScreenshotFileName(String screenshotName) {
        StackTraceElement[] var1 = Thread.currentThread().getStackTrace();
        int var2 = var1.length;

        int var3;
        for(var3 = 0; var3 < var2; ++var3) {
            StackTraceElement element = var1[var3];
            String elementClassName = element.getClassName();
            String elementMethodName = element.getMethodName();
            if (elementMethodName.startsWith("test") || isJUnit4Test(elementClassName, elementMethodName)) {
                if (!elementClassName.equals(lastClassName) || !elementMethodName.equals(lastMethodName)) {
                    counter = new AtomicInteger(0);
                }

                lastClassName = elementClassName;
                lastMethodName = elementMethodName;
                String var8 = String.valueOf("-");
                String var9 = String.valueOf("-");
                String var10 = String.valueOf("-");
                int var11 = counter.incrementAndGet();
                String filename = (new StringBuilder(11 + String.valueOf(elementClassName).length() + String.valueOf(var8).length() + String.valueOf(elementMethodName).length() + String.valueOf(var9).length() + String.valueOf(screenshotName).length() + String.valueOf(var10).length())).append(elementClassName).append(var8).append(elementMethodName).append(var9).append(screenshotName).append(var10).append(var11).toString();
                return filename;
            }
        }

        lastClassName = "";
        lastMethodName = "";
        String var12 = String.valueOf("UnknownTestClass-unknownTestMethod-");
        String var13 = String.valueOf("-");
        var3 = counter.incrementAndGet();
        return (new StringBuilder(11 + String.valueOf(var12).length() + String.valueOf(screenshotName).length() + String.valueOf(var13).length())).append(var12).append(screenshotName).append(var13).append(var3).toString();
    }

    private static boolean isJUnit4Test(String elementClassName, String elementMethodName) {
        try {
            Class<?> clazz = Class.forName(elementClassName);
            Method[] var3 = clazz.getMethods();
            int var4 = var3.length;

            int var5;
            Method method;
            for(var5 = 0; var5 < var4; ++var5) {
                method = var3[var5];
                if (method.getName().equals(elementMethodName) && method.getAnnotation(Test.class) != null) {
                    return true;
                }
            }

            var3 = clazz.getDeclaredMethods();
            var4 = var3.length;

            for(var5 = 0; var5 < var4; ++var5) {
                method = var3[var5];
                if (method.getName().equals(elementMethodName) && method.getAnnotation(Test.class) != null) {
                    return true;
                }
            }

            return false;
        } catch (ClassNotFoundException var7) {
            return false;
        }
    }

    private static Bitmap bitmapOverlayToCenter(Bitmap bitmap1, Bitmap overlayBitmap) {
        int bitmap1Width = bitmap1.getWidth();
        int bitmap1Height = bitmap1.getHeight();
        int bitmap2Width = overlayBitmap.getWidth();
        int bitmap2Height = overlayBitmap.getHeight();

        float marginLeft = (float) (bitmap1Width * 0.5 - bitmap2Width * 0.5);
        float marginTop = (float) (bitmap1Height * 0.5 - bitmap2Height * 0.5);

        Bitmap finalBitmap = Bitmap.createBitmap(bitmap1Width, bitmap1Height, bitmap1.getConfig());
        Canvas canvas = new Canvas(finalBitmap);
        canvas.drawBitmap(bitmap1, new Matrix(), null);
        canvas.drawBitmap(overlayBitmap, marginLeft, marginTop, null);
        return finalBitmap;
    }
}
