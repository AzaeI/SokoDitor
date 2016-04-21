package storage.bean;

import org.w3c.dom.Document;

import java.io.File;
import java.io.InputStream;

public class Level {
    private long id;
    private String name;
    private long user;
    private InputStream file;
    private int rank;
    private boolean is_genuine;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUser() {
        return user;
    }

    public void setUser(long user) {
        this.user = user;
    }

    public InputStream getFile() {
        return file;
    }

    public void setFile(InputStream file) {
        this.file = file;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean is_genuine() {
        return is_genuine;
    }

    public void setIs_genuine(boolean is_genuine) {
        this.is_genuine = is_genuine;
    }
}
