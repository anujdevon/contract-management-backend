package com.devon.contractmanagementsystem.model;

import javax.persistence.*;

@Entity
@Table(name = "user_files_mapping")
public class UserFileMapping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "userId")
    private int user_id;

    @Column(name = "contentId")
    private String content_id;

    public UserFileMapping(int user_id, String content_id) {
        this.user_id = user_id;
        this.content_id = content_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getContentId() {
        return content_id;
    }

    public void setContentId(String content_id) {
        this.content_id = content_id;
    }
}