package model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    private int user_id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date order_date;

    private String status;
    private String address;
    private String phone;

    // Gắn với cột mới đổi tên
    @Column(name = "buyer_name") 
    private String buyerName;

    public Order() {
    }

    public Order(int user_id, Date order_date, String address, String phone, String buyerName , String status) {
        this.user_id = user_id;
        this.order_date = order_date;
        this.address = address;
        this.phone = phone;
        this.buyerName = buyerName;
        this.status = status;
    }

    // Getter & Setter cho buyerName
    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    // Các Getter & Setter khác
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Date getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Date order_date) {
        this.order_date = order_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
