package com.pys.entity;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * @Description: 相册实体类
 */
public class Picture {

    private Long id;
    @NotBlank(message = "照片名字不能为空")
    private String picturename;
    @NotBlank(message = "时间地点不能为空")
    private String picturetime;
    @NotBlank(message = "照片路径不能为空")
    private String pictureaddress;
    @NotBlank(message = "照片描述不能为空")
    private String picturedescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPicturename() {
        return picturename;
    }

    public void setPicturename(String picturename) {
        this.picturename = picturename;
    }

    public String getPicturetime() {
        return picturetime;
    }

    public void setPicturetime(String picturetime) {
        this.picturetime = picturetime;
    }

    public String getPictureaddress() {
        return pictureaddress;
    }

    public void setPictureaddress(String pictureaddress) {
        this.pictureaddress = pictureaddress;
    }

    public String getPicturedescription() {
        return picturedescription;
    }

    public void setPicturedescription(String picturedescription) {
        this.picturedescription = picturedescription;
    }
}
