package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity //ánh xạ (map) với một bảng trong database.
@Table(name = "users") 
public class User {
    @Id // primary key 
    @GeneratedValue(strategy = GenerationType.IDENTITY) // tăng tự động (auto-increment).
    private int id;

    private String email;
    private String username;
    private String password;
    private int role;

    public User() {}
    
    public User( String email, String username, String password, int role) {
        
        this.email = email;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
