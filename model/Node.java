package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Node implements Serializable {
    private final String data;
    private int id;
    private final boolean gender;
    private final Date dateOfBirth;
    private final String homeTown;
    private final List<Content> contentList;
    private final DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public Node(String data, boolean gender, Date dateOfBirth, String homeTown) {
        this.data = data;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.homeTown = homeTown;
        this.id = 0;
        this.contentList = new ArrayList<>();
    }

    public String getData() {
        return data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Content> getContentList() {
        return contentList;
    }

    public String getGender() {
        if (this.gender) {
            return "Male";
        } else {
            return "Female";
        }
    }

    public String getDateOfBirth() {
        return this.dateFormat.format(this.dateOfBirth);
    }

    public String getHomeTown() {
        return homeTown;
    }

    @Override
    public String toString() {
        return this.data + " - " + this.id;
    }
}
