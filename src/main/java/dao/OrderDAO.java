package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import model.Order;
import model.OrderFullDTO;
import model.OrderItemDTO;
import model.User;

public class OrderDAO {

    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("TTastyShop");

    // Lấy tất cả đơn hàng
    public List<Order> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Order> query = em.createQuery("SELECT o FROM Order o", Order.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Thêm đơn hàng mới
    public void insertOrder(Order order) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(order);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Tìm đơn hàng theo ID
    public Order getOrderById(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Order.class, id);
        } finally {
            em.close();
        }
    }

    // Xóa đơn hàng theo ID
    public void deleteOrderById(int orderId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Order order = em.find(Order.class, orderId);
        if (order != null) {
            em.remove(order);
        }

        em.getTransaction().commit();
        em.close();
    }

    // get all full order
    public List<OrderFullDTO> getAllOrdersWithDetails() {
        EntityManager em = emf.createEntityManager();
        try {
            // 1. Lấy danh sách tất cả đơn hàng
            String jpql = "SELECT o, u FROM Order o JOIN User u ON o.user_id = u.id ORDER BY o.order_id DESC";
            List<Object[]> orders = em.createQuery(jpql, Object[].class).getResultList();

            List<OrderFullDTO> result = new ArrayList<>();

            for (Object[] row : orders) {
                Order order = (Order) row[0];
                User user = (User) row[1];

                // 2. Lấy chi tiết sản phẩm trong đơn hàng
                String itemSql = "SELECT new model.OrderItemDTO(p.name, p.image_url, od.quantity, od.price, (od.quantity * od.price)) "
                        + "FROM OrderDetail od JOIN Product p ON od.product_id = p.product_id "
                        + "WHERE od.order_id = :oid";
                List<OrderItemDTO> items = em.createQuery(itemSql, OrderItemDTO.class)
                        .setParameter("oid", order.getOrder_id())
                        .getResultList();

                OrderFullDTO full = new OrderFullDTO(
                        order.getOrder_id(),
                        order.getUser_id(),
                        order.getBuyerName(),
                        order.getPhone(),
                        order.getAddress(),
                        order.getStatus(),
                        order.getOrder_date(),
                        user.getUsername(),
                        items
                );
                result.add(full);
            }

            return result;
        } finally {
            em.close();
        }
    }

    public void updateOrderStatus(int orderId, String newStatus) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Order order = em.find(Order.class, orderId);
            if (order != null) {
                order.setStatus(newStatus);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

}
