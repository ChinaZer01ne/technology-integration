package com.github.common.core.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
public class EmojiCharacterConvertUtil {
    /**
     * 将表情字符转换成一般字符
     * @param str :
     * @return java.lang.String
     * @throws UnsupportedEncodingException
     */
    public static String emojiConvertString(String str) throws UnsupportedEncodingException {
        if (StringUtils.isBlank(str)) {
            return "";
        }
        String patternString = "([\\x{10000}-\\x{10ffff}\ud800-\udfff])";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);

        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb, "[[" + URLEncoder.encode(matcher.group(1), "UTF-8") + "]]");
            } catch (UnsupportedEncodingException e) {
                throw e;
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 将一般字符转换成表情字符
     * @param str :
     * @return java.lang.String
     * @throws UnsupportedEncodingException
     */
    public static String emojiRecovery(String str) throws UnsupportedEncodingException {

        if (StringUtils.isBlank(str)) {
            return "";
        }

        String patternString = "\\[\\[(.*?)\\]\\]";

        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(str);
        StringBuilder sb = new StringBuilder();
        while (matcher.find()) {
            try {
                matcher.appendReplacement(sb, URLDecoder.decode(matcher.group(1), "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                throw e;
            }
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    public static void main(String[] args) throws UnsupportedEncodingException {
        String afterEmoji = emojiConvertString("\uD83D\uDE04你好\uD83D\uDC4B");
        System.out.println(afterEmoji);

        String xxx = "wqwqw杀杀看灰色空间安徽省经考核好";
        String recoveryEmoji = emojiRecovery(xxx);
        System.out.println(recoveryEmoji);

    }
}
