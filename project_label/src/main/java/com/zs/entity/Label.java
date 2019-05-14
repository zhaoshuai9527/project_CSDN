package com.zs.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "tb_label")
public class Label implements Serializable {
    //标识主键
    @Id
    private String id ;
    private String labelName ;//标签名
    private String state ; //状态
    private Long count ;//标签个数
    private Long fans ;//关注数
    private String recommend ;//推荐

    public Label() {
    }
    public Label( String labelName, String state, Long count, Long fans, String recommend) {

        this.labelName = labelName;
        this.state = state;
        this.count = count;
        this.fans = fans;
        this.recommend = recommend;
    }
    public Label(String id, String labelName, String state, Long count, Long fans) {
        this.id = id;
        this.labelName = labelName;
        this.state = state;
        this.count = count;
        this.fans = fans;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelName() {
        return labelName;
    }

    public void setLabelName(String labelName) {
        this.labelName = labelName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Long getFans() {
        return fans;
    }

    public void setFans(Long fans) {
        this.fans = fans;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }
}
