package me.will.sb.common;

import java.util.regex.Pattern;

public enum RegExp {
    /**
     *    * 手机号
     */
    PHONE("^1[3|4|5|7|8]\\d{9}$"),
    /**
     *   * 中文字符
     */

    CHINESE_CHARACTER("[\u4e00-\u9fa5]+"),
    /**
     *   * 双字节字符
     */

    DOUBLE_CHARACTER("[^x00-xff]"),
    /**
     *   * html标签
     */

    HTML("^<(S*?)[^>]*>.*?|<.*? />$"),
    /**
     *   * 电子邮件
     */

    EMAIL("\\w+([-+.]\\w+)*@\\w+([-.]w+)*.\\w+([-.]\\w+)*"),
    /**
     *   * url
     */

    URL("[a-zA-z]+://[^s]*"),
    /**
     *   * qq号
     */

    QQ("[1-9][0-9]{4,}"),
    /**
     *   * 邮编
     */

    ZIP("^[1-9]\\d{5}(?!d)$"),
    /**
     *   * 域名
     */

    DOMAIN("[a-zA-Z0-9][-a-zA-Z0-9]{0,62}(\\.[a-zA-Z0-9][-a-zA-Z0-9]{0,62})+\\.?"),
    /**
     *   * 账户允许大小写字母数字和下划线，4到15个
     */

    ACCOUNT("^[a-zA-Z0-9_]{4,15}$"),
    /**
     *   * ipv4
     */

    IPv4("^((25[0-5]|2[0-4]\\d|[0,1]?\\d\\d?)\\.){3}((25[0-5]|2[0-4]\\d|[0,1]?\\d\\d?))$"),
    /**
     *   * ipv6
     */

    IPv6("^([\\da-fA-F]{1,4}:){7}[\\da-fA-F]{1,4}|:((:[\\da−fA−F]1,4)1,6|:)|:((:[\\da−fA−F]1,4)1,6|:)|^[\\da-fA-F]{1,4}:((:[\\da-fA-F]{1,4}){1,5}|:)|([\\da−fA−F]1,4:)2((:[\\da−fA−F]1,4)1,4|:)|([\\da−fA−F]1,4:)2((:[\\da−fA−F]1,4)1,4|:)|^([\\da-fA-F]{1,4}:){3}((:[\\da-fA-F]{1,4}){1,3}|:)|([\\da−fA−F]1,4:)4((:[\\da−fA−F]1,4)1,2|:)|([\\da−fA−F]1,4:)4((:[\\da−fA−F]1,4)1,2|:)|^([\\da-fA-F]{1,4}:){5}:([\\da-fA-F]{1,4})?|([\\da−fA−F]1,4:)6:"),
    /**
     *   * 国内电话号码
     */

    TEL("^\\d{3}-\\d{8}|\\d{4}-{0,1}(\\d{8}|\\d{7})$"),
    /**
     *   * 数字
     */

    NUMBER("^[-]?\\d+(\\.\\d+)?$"),
    /**
     *   * 整数
     */

    INTEGER("^[-]?\\d+$"),
    /**
     *   * 常见日期
     */

    DATE("^\\d{4}-[0,1]{1}[0-9]{1}-[0-3]{1}[0-9]{1}$"),
    /**
     *   * 常见日期时间
     */

    DATETIME("^\\d{4}-[0,1]{1}[1-9]{1}-[0-3]{1}[0-9]{1} [0-2]{1}[0-9]{1}:[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}$");

    private final String regExp;

    public final boolean find(String str) {
        Pattern pattern = Pattern.compile(this.regExp, 0);
        return pattern.matcher((CharSequence) str).find();
    }

    public String toString() {
        return this.regExp;
    }

    RegExp(String regExp) {
        this.regExp = regExp;
    }
}
