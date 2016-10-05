package com.archa.archamessenger.Models;

/**
 * Created by doosoun on 16. 10. 5..
 */
public class Contacts {
    private int profile;
    private String personName;
    private String personPhone;

    public Contacts(int profile, String personName, String personPhone){
        this.profile = profile;
        this.personName = personName;
        this.personPhone = personPhone;
    }

    public int getProfile(){
        return this.profile;
    }
    public String getPersonName(){
        return this.personName;
    }
    public String getPersonPhone(){
        return this.personPhone;
    }
    public void setProfile(int profile){
        this.profile = profile;
    }
    public void setPersonName(String name){
        this.personName = name;
    }
    public void setPersonPhone(String phone){
        this.personPhone = phone;
    }
}
