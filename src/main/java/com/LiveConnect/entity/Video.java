package com.LiveConnect.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "videos")
public class Video {

    @Id
    private String id;
    private String title;
    private String description;
    private String uploaderId; // ID of the user who uploaded the video
    private Date uploadDate;
    private String filePath; // Path or URL to the video file
    private long views;
    private long likes;

    // Constructors
    public Video(String originalFilename, String filePath, String mp4, long size){

    }
    public Video() {
    }
    public Video(String title, String description, String uploaderId, Date uploadDate, String filePath) {
        this.title = title;
        this.description = description;
        this.uploaderId = uploaderId;
        this.uploadDate = uploadDate;
        this.filePath = filePath;
        this.views = 0;
        this.likes = 0;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUploaderId() {
        return uploaderId;
    }

    public void setUploaderId(String uploaderId) {
        this.uploaderId = uploaderId;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    public long getLikes() {
        return likes;
    }

    public void setLikes(long likes) {
        this.likes = likes;
    }
}
