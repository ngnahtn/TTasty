package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.User;
import utils.JPAUtil;

//JPQL(jpa) lam viec truc tiep voi model khac voi jdbc

public class UserDAO {

    public void insertUser(User user) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<User> getAll() {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    public boolean checkUsername(String username) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class)
                           .setParameter("username", username)
                           .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public boolean checkEmailDuplicate(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                           .setParameter("email", email)
                           .getSingleResult();
            return count > 0;
        } finally {
            em.close();
        }
    }

    public boolean checkOldPasswordDuplicateByUsername(String username, String newPassword) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<String> query = em.createQuery("SELECT u.password FROM User u WHERE u.username = :username", String.class);
            query.setParameter("username", username);
            List<String> result = query.getResultList();
            if (!result.isEmpty()) {
                return result.get(0).equals(newPassword);
            }
            return false;
        } finally {
            em.close();
        }
    }

    public boolean checkEmail(String email) {
        return checkEmailDuplicate(email);
    }

    public User getUserByEmail(String email) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
            query.setParameter("email", email);
            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    public User getUserByUsername(String username) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
            query.setParameter("username", username);
            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    public User loginAndGetUser(String username, String password) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            TypedQuery<User> query = em.createQuery(
                "SELECT u FROM User u WHERE u.username = :username AND u.password = :password",
                User.class
            );
            query.setParameter("username", username);
            query.setParameter("password", password);
            List<User> result = query.getResultList();
            return result.isEmpty() ? null : result.get(0);
        } finally {
            em.close();
        }
    }

    public boolean login(String username, String password) {
        return loginAndGetUser(username, password) != null;
    }

    public void changePassword(String username, String newPassword) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            em.getTransaction().begin();
            User user = getUserByUsername(username);
            if (user != null) {
                user.setPassword(newPassword);
                em.merge(user);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
    
    public User getUserById(int id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            return em.find(User.class, id);
        } finally {
            em.close();
        }
    }
}
