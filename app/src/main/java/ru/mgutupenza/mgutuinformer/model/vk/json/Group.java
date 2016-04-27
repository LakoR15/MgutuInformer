package ru.mgutupenza.mgutuinformer.model.vk.json;

public class Group {

    private long gid;
    private String name;
    private String photo;

    public Group(long gid, String name, String photo) {
        this.gid = -1 * gid;
        this.name = name;
        this.photo = photo;
    }

    public long getGid() {
        return gid;
    }

    public void setGid(long gid) {
        this.gid = gid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
