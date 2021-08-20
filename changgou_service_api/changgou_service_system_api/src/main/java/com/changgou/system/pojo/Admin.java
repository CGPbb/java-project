package com.changgou.system.pojo;


import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_admin")
public class Admin {

  @Id
  private Integer id;
  private String loginName;
  private String password;
  private String status;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getLoginName() {
    return loginName;
  }

  public void setLoginName(String loginName) {
    this.loginName = loginName;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

}
