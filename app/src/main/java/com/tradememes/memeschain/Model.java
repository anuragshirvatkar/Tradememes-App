package com.tradememes.memeschain;

public class Model {
    public String imageUrl;
    public String dp;
    public String insid;
    public String insi;
    public Model(){

    }
    public String getImageUrl() {
        return imageUrl;
    }
    public String getDp() {
        return dp;
    }
    public String getInsid() {
        return insid;
    }
    public String getInsi() {
        return insi;
    }

    public Model(String imageUrl,  String dp, String insid,String insi) {
        this.imageUrl = imageUrl;
        this.dp = dp;
        this.insid = insid;
        this.insi=insi;
    }
}
