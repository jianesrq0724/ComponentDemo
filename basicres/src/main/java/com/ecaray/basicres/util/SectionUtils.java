package com.ecaray.basicres.util;

/**
 * 功能：
 * 创建者：ruiqin.shen
 * 创建日期：2018/2/5
 * 版权所有：深圳市亿车科技有限公司
 */

public class SectionUtils {

    private static final String SECTION_NAME_SPLIT = "_";

    /**
     * 简化后的路段名
     */
    public static String getSimpleSectionName(String sectionName) {
        //包含"_"，截取后半段
        if (sectionName.contains(SECTION_NAME_SPLIT)) {
            sectionName = sectionName.substring(sectionName.indexOf(SECTION_NAME_SPLIT) + 1, sectionName.length());
        }
        return sectionName;
    }
}
