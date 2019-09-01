package com.zhuzi.down.pic;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
@ToString
public class PicBean {
    private int index = 1;
    private int all;
    private String baseUrl;
    private String title;


}
