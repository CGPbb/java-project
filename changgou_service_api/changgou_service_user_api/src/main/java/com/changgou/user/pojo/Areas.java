package com.changgou.user.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_areas")
public class Areas {

    @Id
    private String areaid;
    private String area;
    private String cityid;


    public String getAreaid() {
        return areaid;
    }

    public void setAreaid(String areaid) {
        this.areaid = areaid;
    }


    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }


    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

}
