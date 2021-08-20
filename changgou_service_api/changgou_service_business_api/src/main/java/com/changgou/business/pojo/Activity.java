package com.changgou.business.pojo;


import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Activity实体类
 */
@Table(name = "tb_activity")
public class Activity {

    @Id
    private Integer id;
    private String title;
    private Date startTime;
    private Date endTime;
    private String status;
    private String content;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }


    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
