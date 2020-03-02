package models;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Advertisement implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String content;
    private String text;
    private LocalDateTime createDate;
    private Boolean approved;
    private User user;
    private TYPE type;



    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Boolean isApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public models.TYPE getType() {
        return type;
    }

    public void setType(models.TYPE type) {
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", text='" + text + '\'' +
                ", createDate=" + createDate +
                ", approved=" + approved +
                ", user=" + user +
                ", type=" + type +
                '}';
    }
}
