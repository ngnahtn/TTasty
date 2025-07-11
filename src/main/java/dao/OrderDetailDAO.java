package dao;

import jakarta.persistence.*;
import java.util.List;
import model.OrderDetail;
import model.OrderItemDTO;

public class OrderDetailDAO {

    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("TTastyShop");

    // Lấy tất cả chi tiết đơn hàng
    public List<OrderDetail> getAll() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<OrderDetail> query = em.createQuery("SELECT d FROM OrderDetail d", OrderDetail.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Thêm chi tiết đơn hàng
    public void insertOrderDetail(OrderDetail detail) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(detail);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    // Lấy danh sách chi tiết theo mã đơn hàng
    public List<OrderDetail> getByOrderId(int orderId) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<OrderDetail> query = em.createQuery(
                    "SELECT d FROM OrderDetail d WHERE d.order_id = :orderId", OrderDetail.class);
            query.setParameter("orderId", orderId);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    // Xóa chi tiết theo ID
    public void deleteById(int detailId) {
        EntityManager em = emf.createEntityManager();
        try {
            OrderDetail detail = em.find(OrderDetail.class, detailId);
            if (detail != null) {
                em.getTransaction().begin();
                em.remove(detail);
                em.getTransaction().commit();
            }
        } finally {
            em.close();
        }
    }

    public void deleteByOrderId(int orderId) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("DELETE FROM OrderDetail od WHERE od.order_id = :oid")
                .setParameter("oid", orderId)
                .executeUpdate();

        em.getTransaction().commit();
        em.close();
    }

    public List<OrderItemDTO> getOrderItems(int orderId) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT new model.OrderItemDTO(p.product_id, od.quantity) " +
              "FROM OrderDetail od JOIN Product p ON od.product_id = p.product_id " +
              "WHERE od.order_id = :orderId";
            return em.createQuery(jpql, OrderItemDTO.class)
                    .setParameter("orderId", orderId)
                    .getResultList();
        } finally {
            em.close();
        }
    }

}
