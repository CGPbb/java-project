package com.changgou.user.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_provinces")
public class Provinces {

    @Id
    private String provinceid;
    private String province;


    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }


    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

}
