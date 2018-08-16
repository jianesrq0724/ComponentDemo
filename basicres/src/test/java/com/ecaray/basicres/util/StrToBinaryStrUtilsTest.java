package com.ecaray.basicres.util;


import org.junit.Test;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/3/22
 */
public class StrToBinaryStrUtilsTest  {

    @Test
    public void testStrToBinarystr() throws Exception {
        String app_id = StrToBinaryStrUtils.strToBinarystr("123456789");
        System.out.println(app_id);

        String requestKey = StrToBinaryStrUtils.strToBinarystr("D3029C73406221B02026B684BB00579C");
        System.out.println(requestKey);
    }

}