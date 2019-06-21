package com.bangdao.common.utils;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

@Slf4j
public class Pin4jUtils {
    private Pin4jUtils(){}
    /**
     * 将文字转为汉语全拼拼音,并按小写返回
     */
    public static String toHanyuPinyin(String content) throws Exception{
        char[] cl_chars = content.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V) ;
        for (int i=0; i<cl_chars.length; i++){
            // 如果字符是中文,则将中文转为汉语拼音
            if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")){
                hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
            } else {// 如果字符不是中文,则不转换
                hanyupinyin += cl_chars[i];
            }
        }
        return hanyupinyin;
    }
    /**
     * 获取所有汉子的首字母,并按小写返回
     */
    public static String getFirstLetters(String content)
            throws Exception{
        char[] cl_chars = content.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        for (int i = 0; i < cl_chars.length; i++) {
            String str = String.valueOf(cl_chars[i]);
            if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
                hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0].substring(0, 1);
            } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
                hanyupinyin += cl_chars[i];
            } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
                hanyupinyin += cl_chars[i];
            } else {// 否则不转换
                hanyupinyin += cl_chars[i];//如果是标点符号的话，带着
            }
        }
        return hanyupinyin;
    }
    /**
     * 取所有汉子中第一个汉字的第一个字符(输出小写)
     */
    public static String getFirstLetter(String content) throws Exception{
        char[] cl_chars = content.trim().toCharArray();
        String hanyupinyin = "";
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部大写
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
        String str = String.valueOf(cl_chars[0]);
        if (str.matches("[\u4e00-\u9fa5]+")) {// 如果字符是中文,则将中文转为汉语拼音,并取第一个字母
            hanyupinyin = PinyinHelper.toHanyuPinyinStringArray(
                    cl_chars[0], defaultFormat)[0].substring(0, 1);;
        } else if (str.matches("[0-9]+")) {// 如果字符是数字,取数字
            hanyupinyin += cl_chars[0];
        } else if (str.matches("[a-zA-Z]+")) {// 如果字符是字母,取字母
            hanyupinyin += cl_chars[0];
        }
        return hanyupinyin;
    }
    public static void main(String[] args) throws Exception {
        Pin4jUtils hanyuPinyinHelper = new Pin4jUtils();
        //duofadefadushoukongfangadaofudafadisanfang，hfiwkD12
        System.out.println(hanyuPinyinHelper.toHanyuPinyin("多发的发独守空房阿道夫打" +
                "发第三方，hfiwkD12"));
        //dfdfdskfadfdfdsf，hfiwkD12
        System.out.println(hanyuPinyinHelper.getFirstLetters("多发的发独守空房阿道夫打" +
                "发第三方，hfiwkD12"));
        //D
        System.out.println(hanyuPinyinHelper.getFirstLetter("多发的发独守空房阿道夫打" +
                "发第三方，hfiwkD12"));
    }
}