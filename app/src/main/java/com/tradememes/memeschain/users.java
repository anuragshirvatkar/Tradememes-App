package com.tradememes.memeschain;

public class users {
    public String name;

    public String fix;
    public String pas;
    public String instaid;
    public users(){

    }

    public String getName() {
        return name;
    }



    public String getFix() {
        return fix;
    }
public String getInstaid(){return instaid;}
    public String getPas() {
        return pas;
    }


    public users(String name,  String fix,String instaid ,String pas) {
        this.name = name;

        this.fix = fix;
        this.instaid = instaid;
        this.pas = pas;
    }
}

