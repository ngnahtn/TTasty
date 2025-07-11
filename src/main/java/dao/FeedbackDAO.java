package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Feedback;
import utils.JPAUtil;

import java.util.List;

public class FeedbackDAO {

    public void insert(Feedback feedback) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(feedback);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<Feedback> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<Feedback> query = em.createQuery("SELECT f FROM Feedback f ORDER BY f.createdAt DESC", Feedback.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public Feedback getById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(Feedback.class, id);
        } finally {
            em.close();
        }
    }

    public void updateStatus(int feedbackId, String newStatus) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Feedback fb = em.find(Feedback.class, feedbackId);
            if (fb != null) {
                fb.setStatus(newStatus);
                em.merge(fb);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void delete(int feedbackId) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            Feedback fb = em.find(Feedback.class, feedbackId);
            if (fb != null) {
                em.remove(fb);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public void markFeedbackReviewed(int id) {
        EntityManager em = JPAUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            Feedback feedback = em.find(Feedback.class, id);
            if (feedback != null) {
                feedback.setStatus("Reviewed");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            em.close();
          
        }
    }

}
