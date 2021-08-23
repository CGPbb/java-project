package com.changgou.user.pojo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_cities")
public class Cities {

  @Id
  private String cityid;
  private String city;
  private String provinceid;


  public String getCityid() {
    return cityid;
  }

  public void setCityid(String cityid) {
    this.cityid = cityid;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public String getProvinceid() {
    return provinceid;
  }

  public void setProvinceid(String provinceid) {
    this.provinceid = provinceid;
  }

}
