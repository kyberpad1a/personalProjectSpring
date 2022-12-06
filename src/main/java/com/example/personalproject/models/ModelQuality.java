package com.example.personalproject.models;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import java.sql.Date;

@Entity
public class ModelQuality {
    public ModelQuality(){}
    @Id
    @GeneratedValue
    private Long ID_Quality;
    @NotNull(message = "Поле не может быть пустым")
    private Date dateOfCompletion;
    @NotBlank
    @Size(max = 100, message = "Поле не может содержать больше 100 символов")
    private String comments;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
    private ModelUser user;
    @ManyToOne(optional = true, cascade = CascadeType.ALL)
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
