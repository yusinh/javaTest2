package org.example;

public class Article {
    private int id;
    private String title;
    private String content;
    private String dateTime;
    private int hit;

    public Article(int id, String title, String content, String dateTime, int hit) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.dateTime = dateTime;
        this.hit = hit;
    }

    public int getHit() {
        return hit;
    }

    public void setHit(int hit) {
        this.hit = hit;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
