package com.imooc.monitor.tool;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * BASE64编解码
 *
 * @Author: Mr.Chen
 * @Version 1.0
 * @Date: 2019/12/14 15:17
 */
public class Base64Util {

    /**
     * BASE64编码
     *
     * @param param
     * @return
     */
    public static String encode(String param) throws UnsupportedEncodingException {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(param.getBytes("UTF-8"));
    }

    /**
     * BASE64解码
     *
     * @param param
     * @return
     */
    public static String decode(String param) throws IOException, UnsupportedEncodingException {
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] bytes = decoder.decodeBuffer(param);
        return new String(bytes, "UTF-8");
    }

}
