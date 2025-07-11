package model;

import java.math.BigDecimal;

public class OrderItemDTO {
    private int productId;
    private String productName;
    private String imageUrl;
    private int quantity;
    private BigDecimal price;
    private BigDecimal total;

    // Constructor đầy đủ
    public OrderItemDTO(String productName, String imageUrl, int quantity, BigDecimal price, BigDecimal total) {
        this.productName = productName;
        this.imageUrl = imageUrl;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
    }

    // Constructor đơn giản dùng khi chỉ cần productId và quantity
    public OrderItemDTO(int productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    // Getter - Setter
    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
