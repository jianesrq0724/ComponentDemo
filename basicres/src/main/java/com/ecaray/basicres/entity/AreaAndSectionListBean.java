package com.ecaray.basicres.entity;

import com.ecar.ecarnetwork.bean.ResBase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 类描述: 片区id所对应的路段id列表
 * 创建人: Eric_Huang
 * 创建时间: 2018/3/23 11:23
 */
public class AreaAndSectionListBean extends ResBase {


    /**
     * data : [{"areaid":"20170630103359565481703455606527","cancode":"0002","canname":"任丘东站区","degree":"","mounthcardnum":"323","sectionnum":"0","sections":[{"sectioncode":"000002","sectionid":"20170707114634282386935909229391","sectionname":"机场东路"},{"sectioncode":"300001","sectionid":"20170825165337680186919413650059","sectionname":"周转数路段1"},{"sectioncode":"300002","sectionid":"20170825165414388128983830210126","sectionname":"周转数路段2"},{"sectioncode":"300003","sectionid":"20170825165431502246843552177238","sectionname":"周转数路段3"},{"sectioncode":"300004","sectionid":"20170825165447773357704618499567","sectionname":"周转数路段4"},{"sectioncode":"300005","sectionid":"20170825165514050393712843460924","sectionname":"周转数路段5"},{"sectioncode":"300006","sectionid":"20170825165549190139699163982081","sectionname":"周转数路段6"},{"sectioncode":"300007","sectionid":"20170825165601352206010441001640","sectionname":"周转数路段7"},{"sectioncode":"300008","sectionid":"20170825165618573470642250842343","sectionname":"周转数路段8"},{"sectioncode":"300009","sectionid":"20170825165633291616591864652535","sectionname":"周转数路段9"},{"sectioncode":"300010","sectionid":"20170825165645537188820371249920","sectionname":"周转数路段10"},{"sectioncode":"300011","sectionid":"20170921102819924683851569112381","sectionname":"周转数路段11-1"},{"sectioncode":"qdn001","sectionid":"20180129092948108625815622143980","sectionname":"黔东南测试路段"}],"sortnum":"0002"}]
     */

    private List<DataBean> data;

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public List<DataBean> getData() {
        return data;
    }


    public static class DataBean {
        /**
         * areaid : 20170630103359565481703455606527
         * cancode : 0002
         * canname : 任丘东站区
         * degree :
         * mounthcardnum : 323
         * sectionnum : 0
         * sections : [{"sectioncode":"000002","sectionid":"20170707114634282386935909229391","sectionname":"机场东路"},{"sectioncode":"300001","sectionid":"20170825165337680186919413650059","sectionname":"周转数路段1"},{"sectioncode":"300002","sectionid":"20170825165414388128983830210126","sectionname":"周转数路段2"},{"sectioncode":"300003","sectionid":"20170825165431502246843552177238","sectionname":"周转数路段3"},{"sectioncode":"300004","sectionid":"20170825165447773357704618499567","sectionname":"周转数路段4"},{"sectioncode":"300005","sectionid":"20170825165514050393712843460924","sectionname":"周转数路段5"},{"sectioncode":"300006","sectionid":"20170825165549190139699163982081","sectionname":"周转数路段6"},{"sectioncode":"300007","sectionid":"20170825165601352206010441001640","sectionname":"周转数路段7"},{"sectioncode":"300008","sectionid":"20170825165618573470642250842343","sectionname":"周转数路段8"},{"sectioncode":"300009","sectionid":"20170825165633291616591864652535","sectionname":"周转数路段9"},{"sectioncode":"300010","sectionid":"20170825165645537188820371249920","sectionname":"周转数路段10"},{"sectioncode":"300011","sectionid":"20170921102819924683851569112381","sectionname":"周转数路段11-1"},{"sectioncode":"qdn001","sectionid":"20180129092948108625815622143980","sectionname":"黔东南测试路段"}]
         * sortnum : 0002
         */

        //片区
        private String areaid;
        private String cancode;
        private String canname;
        private String degree;
        private String mounthcardnum;
        private String sectionnum;
        private String sortnum;
        private List<SectionsBean> sections;

        public void setAreaid(String areaid) {
            this.areaid = areaid;
        }

        public void setCancode(String cancode) {
            this.cancode = cancode;
        }

        public void setCanname(String canname) {
            this.canname = canname;
        }

        public void setDegree(String degree) {
            this.degree = degree;
        }

        public void setMounthcardnum(String mounthcardnum) {
            this.mounthcardnum = mounthcardnum;
        }

        public void setSectionnum(String sectionnum) {
            this.sectionnum = sectionnum;
        }

        public void setSortnum(String sortnum) {
            this.sortnum = sortnum;
        }

        public void setSections(List<SectionsBean> sections) {
            this.sections = sections;
        }

        public String getAreaid() {
            return areaid;
        }

        public String getCancode() {
            return cancode;
        }

        public String getCanname() {
            return canname;
        }

        public String getDegree() {
            return degree;
        }

        public String getMounthcardnum() {
            return mounthcardnum;
        }

        public String getSectionnum() {
            return sectionnum;
        }

        public String getSortnum() {
            return sortnum;
        }

        public List<SectionsBean> getSections() {
            return sections;
        }

        public static class SectionsBean {
            /**
             * sectioncode : 000002
             * sectionid : 20170707114634282386935909229391
             * sectionname : 机场东路
             */

            private String sectioncode;
            private String sectionid;
            private String sectionname;

            //片区
            private String areaName;

            private boolean isShowArea;

            public void setSectioncode(String sectioncode) {
                this.sectioncode = sectioncode;
            }

            public void setSectionid(String sectionid) {
                this.sectionid = sectionid;
            }

            public void setSectionname(String sectionname) {
                this.sectionname = sectionname;
            }

            public String getSectioncode() {
                return sectioncode;
            }

            public String getSectionid() {
                return sectionid;
            }

            public String getSectionname() {
                return sectionname;
            }

            public String getAreaName() {
                return areaName;
            }

            public void setAreaName(String areaName) {
                this.areaName = areaName;
            }

            public boolean isShowArea() {
                return isShowArea;
            }

            public void setShowArea(boolean showArea) {
                isShowArea = showArea;
            }
        }
    }

    /**
     * 根据片区id获取路段id列表
     *
     * @param areaId 路段id
     * @return 路段id列表
     */
    public List<DataBean.SectionsBean> getSectionList4AreaId(String areaId) {
        List<DataBean.SectionsBean> lSectionList = new ArrayList<>();
        if (data == null || data.size() == 0) {
            return lSectionList;
        }

        for (DataBean lDatum : data) {
            if (lDatum.getAreaid().equals(areaId)) {
                lSectionList = lDatum.getSections();
                return lSectionList;
            }
        }
        return lSectionList;
    }

    //获取片区列表
    public List<Map<String, String>> getAreaIdList() {
        List<Map<String, String>> lAreaList = new ArrayList<>();
        Map<String, String> lAreaMap = new HashMap<>();

        if (data != null && data.size() > 0) {
            for (DataBean lDatum : data) {
                lAreaMap.put(lDatum.areaid, lDatum.canname);
                lAreaList.add(lAreaMap);
            }
        }
        return lAreaList;
    }

    /**
     * 过滤的数据，可能存在片区下的路段为空的情况
     *
     * @return
     */
    public List<DataBean> getFilterData() {
        List<AreaAndSectionListBean.DataBean> data = getData();
        Iterator<DataBean> iterator = data.iterator();
        while (iterator.hasNext()) {
            AreaAndSectionListBean.DataBean next = iterator.next();
            if (next.getSections().size() == 0) {
                iterator.remove();
            }
        }
        return data;
    }

}
