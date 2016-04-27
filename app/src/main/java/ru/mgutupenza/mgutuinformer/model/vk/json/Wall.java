package ru.mgutupenza.mgutuinformer.model.vk.json;

public class Wall {

    private long id;
    private long fromId;
    private long date;
    private String text;
    private String image;

    public Wall(long id, long fromId, long date, String text, String image) {
        this.id = id;
        this.fromId = fromId;
        this.date = date;
        this.text = text;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getFromId() {
        return fromId;
    }

    public void setFromId(long fromId) {
        this.fromId = fromId;
    }

    public long getDate() {
        return date;
    }

    public void setDate(long date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
