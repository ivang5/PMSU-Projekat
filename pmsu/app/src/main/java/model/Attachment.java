package model;

import android.util.Base64;

public class Attachment {
    private Integer id;
    private Base64 data;
    private String type;
    private String name;

    public Attachment() {
    }

    public Attachment(int id, Base64 data, String type, String name) {
        this.id = id;
        this.data = data;
        this.type = type;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Base64 getData() {
        return data;
    }

    public void setData(Base64 data) {
        this.data = data;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Attachment" +
                "id=" + id +
                ", data=" + data +
                ", type='" + type +
                ", name='" + name;
    }
}
