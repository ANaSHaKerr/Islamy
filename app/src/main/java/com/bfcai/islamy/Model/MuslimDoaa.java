package com.bfcai.islamy.Model;

public class MuslimDoaa {
    String title;
    String subtitle;
    String num;

    public  MuslimDoaa(){}
    public MuslimDoaa(String title, String subtitle, String num) {
        this.title = title;
        this.subtitle = subtitle;
        this.num = num;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
