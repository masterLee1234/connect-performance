package com.to.contr.vo;

public class MemberVO {

    private String userid;
    private String userpw;

    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getUserpw() {
        return userpw;
    }
    public void setUserpw(String userpw) {
        this.userpw = userpw;
    }

    @Override
    public String toString(){
        return "MemberVO [User Id = " + userid + ", User Pw = " + userpw + "]";
    }
}
