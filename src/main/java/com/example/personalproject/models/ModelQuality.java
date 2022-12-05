package com.example.personalproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

import java.sql.Date;

@Entity
public class ModelQuality {
    public ModelQuality(){}
    @Id
    @GeneratedValue
    private Long ID_Quality;
    private Date dateOfCompletion;
    @NotBlank
    private String comments;
    @ManyToOne(optional = true)
    private ModelUser user;
    @ManyToOne(optional = true)
    private ModelGood good;

    public ModelQuality(Date dateOfCompletion, String comments, ModelUser user, ModelGood good) {
        this.dateOfCompletion = dateOfCompletion;
        this.comments = comments;
        this.user = user;
        this.good = good;
    }

    public Long getID_Quality() {
        return ID_Quality;
    }

    public void setID_Quality(Long ID_Quality) {
        this.ID_Quality = ID_Quality;
    }

    public Date getDateOfCompletion() {
        return dateOfCompletion;
    }

    public void setDateOfCompletion(Date dateOfCompletion) {
        this.dateOfCompletion = dateOfCompletion;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public ModelUser getUser() {
        return user;
    }

    public void setUser(ModelUser user) {
        this.user = user;
    }

    public ModelGood getGood() {
        return good;
    }

    public void setGood(ModelGood good) {
        this.good = good;
    }
}
