package com.deepexi.domain.dto;

import javax.validation.constraints.*;

/**
 * Created by donh on 2019/2/18.
 */
public class ProductDto {

    @NotEmpty(message = "商品名称不能为空") //名字不能为空，而且长度必须在2和30之间
    @Size(min=2, max=30, message = "商品名长度必须在2和30之间")
    private String name;

    @NotNull(message = "商品类型不能为空")
    private Integer type;

    private String tag;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}