package com.yw.logcatformatlib.util;

/**
 * 格式化输出logcat
 * create by yangwei
 * on 2019-11-18 15:01
 */
public class FormatLogcatUtil {
    private FormatLogcatUtil() {
    }

    private static FormatLogcatUtil instance;

    public synchronized static FormatLogcatUtil getInstance() {
        if (instance == null) {
            instance = new FormatLogcatUtil();
        }
        return instance;
    }

    /**
     * 格式化输出默认tag值的日志
     *
     * @param log
     */
    public void formatLogcat(String log) {
        String json = LogcatJsonFormat.getInstance().format(log);
        DevLog.e(json);
    }

    /**
     * 格式化输出指定tag值的日志
     *
     * @param tag
     * @param log
     */
    public void formatLogcat(String tag, String log) {
        String json = LogcatJsonFormat.getInstance().format(log);
        DevLog.e(tag, json);
    }
}
