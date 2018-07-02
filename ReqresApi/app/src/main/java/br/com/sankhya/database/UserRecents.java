package br.com.sankhya.database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

public class UserRecents {
    private Long id;

    @ColumnInfo(name = "firstName")
    @NonNull
    private String firstName;

    @ColumnInfo(name = "lastName")
    @NonNull
    private String lastName;

    @ColumnInfo(name = "saveTime")
    private String saveTime;

    @ColumnInfo(name = "avatar")
    private String avatar;

    public UserRecents(@NonNull String firstName, @NonNull String lastName, String saveTime, String avatar) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.saveTime = saveTime;
        this.avatar = avatar;
    }

    public UserRecents() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @NonNull
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NonNull String firstName) {
        this.firstName = firstName;
    }

    @NonNull
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@NonNull String lastName) {
        this.lastName = lastName;
    }

    public String getSaveTime() {
        return saveTime;
    }

    public void setSaveTime(String saveTime) {
        this.saveTime = saveTime;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
