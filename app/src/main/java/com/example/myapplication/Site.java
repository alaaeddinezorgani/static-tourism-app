package com.example.myapplication;

public class Site {
    private int nameRes;
    private int summaryRes;
    private int descriptionRes;
    private int imageRes;
    private String website;
    private String phone;

    public Site(int nameRes, int summaryRes, int descriptionRes, int imageRes, String website, String phone){
        this.nameRes = nameRes;
        this.summaryRes = summaryRes;
        this.descriptionRes = descriptionRes;
        this.imageRes = imageRes;
        this.website = website;
        this.phone = phone;
    }


    public int getNameRes() {
        return nameRes;
    }

    public void setNameRes(int nameRes) {
        this.nameRes = nameRes;
    }

    public int getSummaryRes(){
        return this.summaryRes;
    }
    public void setSummaryRes(int summaryRes){
        this.summaryRes = summaryRes;
    }
    public int getDescriptionRes() {
        return descriptionRes;
    }

    public void setDescriptionRes(int descriptionRes) {
        this.descriptionRes = descriptionRes;
    }

    public int getImageRes() {
        return imageRes;
    }

    public void setImageRes(int imageRes) {
        this.imageRes = imageRes;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
