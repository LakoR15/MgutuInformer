package ru.mgutupenza.mgutuinformer.model.vk.json;

public class Profile {

    private long uid;
    private String firstName;
    private String lastName;
    private String photo;

    public Profile(long uid, String firstName, String lastName, String photo) {
        this.uid = uid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.photo = photo;
    }

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
