package com.example.lgggggggx.shiyanzhou;

/**
 * Created by Administrator on 2016/10/31.
 */
public class Comment {
    private String username;
    private String content;
    private String date;
    public Comment(String username,String content,String date){
        this.username=username;
        this.content=content;
        this.date=date;
    }
    public String getUsername(){
        return username;
    }
    public String getContent(){
        return content;
    }
    public String getDate(){
        return date;
    }
}
