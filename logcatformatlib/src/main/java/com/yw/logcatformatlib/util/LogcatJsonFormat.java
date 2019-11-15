package com.yw.logcatformatlib.util;

/**
 * Logcat中格式化输出json的工具
 * create by yangwei
 * on 2019-11-14 20:57
 */
public class LogcatJsonFormat {
    private static final String EMPTY = "  ";//每次缩进两个空格

    private LogcatJsonFormat() {
    }

    private static LogcatJsonFormat instance;

    public static synchronized LogcatJsonFormat getInstance() {
        if (instance == null) {
            instance = new LogcatJsonFormat();
        }
        return instance;
    }

    /**
     * 格式化输出json字符串
     *
     * @param json 被输出的字符串
     * @return
     */
    public String format(String json) {
        try {
            int empty = 0;
            char[] chs = json.toCharArray();
            StringBuilder stringBuilder = new StringBuilder();
            for (int i = 0; i < chs.length; ) {
                //若是双引号，则为字符串，下面if语句会处理该字符串
                if (chs[i] == '\"') {

                    stringBuilder.append(chs[i]);
                    i++;
                    //查找字符串结束位置
                    for (; i < chs.length; ) {
                        //如果当前字符是双引号，且前面有连续的偶数个\，说明字符串结束
                        if (chs[i] == '\"' && isDoubleSerialBackslash(chs, i - 1)) {
                            stringBuilder.append(chs[i]);
                            i++;
                            break;
                        } else {
                            stringBuilder.append(chs[i]);
                            i++;
                        }

                    }
                } else if (chs[i] == ',') {
                    stringBuilder.append(',').append('\n').append(setEmpty(empty));

                    i++;
                } else if (chs[i] == '{' || chs[i] == '[') {
                    empty++;
                    stringBuilder.append(chs[i]).append('\n').append(setEmpty(empty));

                    i++;
                } else if (chs[i] == '}' || chs[i] == ']') {
                    empty--;
                    stringBuilder.append('\n').append(setEmpty(empty)).append(chs[i]);

                    i++;
                } else {
                    stringBuilder.append(chs[i]);
                    i++;
                }


            }


            return stringBuilder.toString();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return json;
        }

    }

    private static boolean isDoubleSerialBackslash(char[] chs, int i) {
        int count = 0;
        for (int j = i; j > -1; j--) {
            if (chs[j] == '\\') {
                count++;
            } else {
                return count % 2 == 0;
            }
        }

        return count % 2 == 0;
    }

    /**
     * 格式化缩进空格
     *
     * @param count 每次缩进的个数
     */
    private String setEmpty(int count) {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < count; i++) {
            stringBuffer.append(EMPTY);
        }
        return stringBuffer.toString();
    }
}
