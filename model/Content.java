package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Content implements Serializable {
    private final Node user;
    private final String title;
    private final String data;
    private final int id;
    private final Set<Node> like;
    private final List<String> comment;
    private final String timeStamp;
    private final DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    public Content(Node user, String title, String data, int id, String timeStamp) {
        this.user = user;
        this.title = title;
        this.data = data;
        this.id = id;
        this.timeStamp = timeStamp;
        this.like = new HashSet<>();
        this.comment = new LinkedList<>();
    }

    public Node getUser() {
        return user;
    }

    public Set<Node> getLike() {
        return like;
    }

    public List<String> getComment() {
        return comment;
    }

    public String getTitle() {
        return title;
    }

    public String getData() {
        return data;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public int getId() {
        return id;
    }

    public void addLike(Node node) {
        this.like.add(node);
    }

    public void addComment(Node node, String comment) {
        this.comment.add(node.toString() + ": " + comment);
    }

    @Override
    public String toString() {
        return "Content{" +
                "title=" + title +
                ", data=" + data +
                ", like=" + like +
                ", comment=" + comment +
                '}';
    }

    public String getStrLike() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        sb.append("Has ").append(this.like.size()).append(" likes by:").append("<br/>");
        for (Node node : this.like) {
            sb.append("+ ").append(node).append("<br/>");
        }
        sb.append("<html>");
        return sb.toString();
    }

    public String getStrComment() {
        StringBuilder sb = new StringBuilder();
        sb.append("<html>");
        for (String s : this.comment) {
            sb.append(s).append("<br/>");
        }
        sb.append("<html>");
        return sb.toString();
    }

    public long getValueTimeStamp() {
        Date date;
        try {
            date = this.dateFormat.parse(this.timeStamp);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return date.getTime();
    }
}
