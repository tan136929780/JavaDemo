package com.util;

import java.util.Enumeration;
import java.util.Properties;

/**
 * <p>运行环境工具类</p>
 *
 * @version 1.0.0, 2013-5-9
 * @see
 * @since
 */

public class SystemUtils {

    private static Properties properties = null;

    static {
        properties = System.getProperties();
    }

    /**
     * <p>返回格式化后的所有系统属性信息</p>
     *
     * @return
     * @since
     */
    @SuppressWarnings("unchecked")
    public static String formatSystemProperties() {
        StringBuilder formatResult = new StringBuilder();
        Enumeration<String> names = (Enumeration<String>) properties.propertyNames();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            formatResult.append(name).append("=")
                    .append(properties.getProperty(name)).append("\n");
        }
        int length = 0;
        return (length = formatResult.length()) > 0 ?
                formatResult.substring(0, length - 1) : "";
    }

    /**
     * <p>返回格式化后的所有指定的系统属性信息</p>
     *
     * @param propertyKeys
     * @return
     * @since
     */
    public static String formatSystemProperties(String[] propertyKeys) {
        StringBuilder formatResult = new StringBuilder();
        if (propertyKeys != null && propertyKeys.length > 0) {
            for (String key : propertyKeys)
                formatResult.append(key).append("=")
                        .append(properties.getProperty(key)).append("\n");
        }
        int length = 0;
        return (length = formatResult.length()) > 0 ?
                formatResult.substring(0, length - 1) : "";
    }

}