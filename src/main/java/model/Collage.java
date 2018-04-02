package main.java.model;
public class Collage {

    private String id;
    private String title;
    private String src;
    private String user_id;

    public Collage(String id, String title, String src, String user_id) {
        this.id = id;
        this.title = title;
        this.src = src;
        this.user_id = user_id;
    }

    public Collage(String title, String src, String user_id) {
        this.id = null;
        this.title = title;
        this.src = src;
        this.user_id = user_id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getSrc() {
        return src;
    }

    public String getUserId() {
        return user_id;
    }

}
