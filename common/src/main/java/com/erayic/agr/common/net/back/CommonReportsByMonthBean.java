package com.erayic.agr.common.net.back;

import java.util.List;

/**
 * 作者：hejian
 * 邮箱：hkceey@outlook.com
 * 注解：旬报
 */

public class CommonReportsByMonthBean {
    private String Title;
    private String SummaryTxt;
    private String ConentTxt;
    private String Source;
    private String PubTime;
    private List<CommonReportImg> ReportImg;

    public CommonReportsByMonthBean(String title, String summaryTxt,
                                    String conentTxt,String source,
                                    String pubTime, List<CommonReportImg> reportImg) {
        Title = title;
        SummaryTxt = summaryTxt;
        ConentTxt = conentTxt;
        Source = source;
        PubTime = pubTime;
        ReportImg = reportImg;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSummaryTxt() {
        return SummaryTxt;
    }

    public void setSummaryTxt(String summaryTxt) {
        SummaryTxt = summaryTxt;
    }

    public String getConentTxt() {
        return ConentTxt;
    }

    public void setConentTxt(String conentTxt) {
        ConentTxt = conentTxt;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getPubTime() {
        return PubTime;
    }

    public void setPubTime(String pubTime) {
        PubTime = pubTime;
    }

    public List<CommonReportImg> getReportImg() {
        return ReportImg;
    }

    public void setReportImg(List<CommonReportImg> reportImg) {
        ReportImg = reportImg;
    }

    public CommonReportsByMonthBean() {
    }

    public class CommonReportImg {
        private String ImgTitle;
        private String ImgUrl;

        public String getImgTitle() {
            return ImgTitle;
        }

        public void setImgTitle(String imgTitle) {
            ImgTitle = imgTitle;
        }

        public String getImgUrl() {
            return ImgUrl;
        }

        public void setImgUrl(String imgUrl) {
            ImgUrl = imgUrl;
        }

        public CommonReportImg() {
        }

        public CommonReportImg(String imgTitle, String imgUrl) {
            ImgTitle = imgTitle;
            ImgUrl = imgUrl;
        }
    }
}
