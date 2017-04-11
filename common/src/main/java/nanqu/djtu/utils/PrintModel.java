package nanqu.djtu.utils;

import nanqu.djtu.pojo.MaintenanceList;
import sun.applet.Main;

import java.awt.*;
import java.awt.List;
import java.util.*;

/**
 * Created by zwz on 2017/4/9.
 */
public class PrintModel {


    /**
     * 变量前面的文字描述
     */
    private String name;
    /**
     * 变量值
     */
    private String val;
    /**
     * 是否换行打印
     */
    private Boolean isWrap = false;
    /**
     * 图片信息
     */
    private Image image;


    public PrintModel(){

    }

    public PrintModel(String name, String val) {
        this.name = name;
        this.val = val;
    }
    public PrintModel(Image image) {
        this.image = image;
    }
    public PrintModel(String name, String val, Boolean isWrap) {
        this.name = name;
        this.val = val;
        this.isWrap = isWrap;
    }

    /**
     * 变量前面的文字描述
     */
    public String getName() {
        return name;
    }

    /**
     *
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 变量值
     */
    public String getVal() {
        return val;
    }

    /**
     *
     */
    public void setVal(String val) {
        this.val = val;
    }

    /**
     * 是否换行打印
     */
    public Boolean getIsWrap() {
        return isWrap;
    }

    /**
     *
     */
    public void setIsWrap(Boolean isWrap) {
        this.isWrap = isWrap;
    }

    /**
     * 图片信息
     */
    public Image getImage() {
        return image;
    }

    /**
     *
     */
    public void setImage(Image image) {
        this.image = image;
    }


}



