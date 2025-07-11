package model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "feedback")
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feedback_id")
    private int feedbackId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = true)
    private User user;  // đổi tên cho rõ ràng

    @Column(name = "content", nullable = false, columnDefinition = "NVARCHAR(1000)")
    private String content;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "status", columnDefinition = "NVARCHAR(50)")
    private String status;

    @Column(name = "type", columnDefinition = "NVARCHAR(50)")
    private String type;

    // Auto set created_at, status, type khi persist
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        if (status == null) {
            status = "New";
        }
        if (type == null) {
            type = "Feedback";
        }
    }

    @Transient
    private String createdAtStr;

    public String getCreatedAtStr() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return createdAt != null ? createdAt.format(formatter) : "";
    }

    // Constructors
    public Feedback() {
    }

    public Feedback(User user, String content, LocalDateTime created_at, String status, String type) {
        this.user = user;
        this.content = content;
        this.createdAt = createdAt;
        this.status = status;
        this.type = type;
    }

    // Getters and Setters
    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime created_at) {
        this.createdAt = created_at;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
