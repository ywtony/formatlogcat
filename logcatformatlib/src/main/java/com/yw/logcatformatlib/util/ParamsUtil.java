package com.yw.logcatformatlib.util;


import org.json.JSONException;
import org.json.JSONObject;

/**
 * 参数转换器
 * create by yangwei
 * on 2019-11-15 11:47
 */
public class ParamsUtil {
    private ParamsUtil() {
    }

    private static ParamsUtil instance;

    public static ParamsUtil getInstance() {
        if (instance == null) {
            instance = new ParamsUtil();
        }
        return instance;
    }

    /**
     * 格式化输出请求参数
     *
     * @param str
     * @return
     */
    public static String getFormatParams(String str) {
        String[] params = str.split("&");
        JSONObject obj = new JSONObject();
        for (int i = 0; i < params.length; i++) {
            String[] param = params[i].split("=");
            if (param.length >= 2) {
                String key = param[0];
                String value = param[1];
                for (int j = 2; j < param.length; j++) {
                    value += "=" + param[j];
                }
                try {
                    obj.put(key, value);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return LogcatJsonFormat.getInstance().format(obj.toString());
    }
}
