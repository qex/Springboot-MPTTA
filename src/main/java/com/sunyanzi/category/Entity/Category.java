package com.sunyanzi.category.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Category implements Serializable {

    private Short cid;

    @JsonIgnore
    private Short left;

    private Short right;

    private Short width;

    private Short depth;

    @NotBlank(message = "请填写分类名称")
    private String name;

    /* nothing about lombok here ... */

    public Short getCid() {
        return cid;
    }

    public void setCid(Short cid) {
        this.cid = cid;
    }

    public Short getLeft() {
        return left;
    }

    public void setLeft(Short left) {
        this.left = left;
    }

    public Short getRight() {
        return right;
    }

    public void setRight(Short right) {
        this.right = right;
    }

    public Short getWidth() {
        return width;
    }

    public void setWidth(Short width) {
        this.width = width;
    }

    public Short getDepth() {
        return depth;
    }

    public void setDepth(Short depth) {
        this.depth = depth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
