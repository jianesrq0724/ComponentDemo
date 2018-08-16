package com.ecaray.basicres.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/3
 */
public class StringUtilsTest {

    @Test
    public void binstrToStr() {
        String s1 = StringUtils.BinstrToStr("111000 111000 110011 110101 110011 110011 110111 110000 110011");
        String s2 = StringUtils.BinstrToStr("1000100 110011 110000 110010 111001 1000011 110111 110011 110100 110000 110110 110010 110010 110001 1000010 110000 110010 110000 110010 110110 1000010 110110 111000 110100 1000010 1000010 110000 110000 110101 110111 111001 1000011");
        System.out.println(s1);
        System.out.println(s2);
    }
}