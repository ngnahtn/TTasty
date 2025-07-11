package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.User;

//JPQL(jpa) lam viec truc tiep voi model khac voi jdbc

public class UserDAO {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("TTastyShop");

    public void insertUser(User user) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
        em.close();
    }

    public List<User> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
        List<User> list = query.getResultList();
        em.close();
        return list;
    }

    public boolean checkUsername(String username) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username", Long.class) // COUNT() trong JPA trả về kiểu Long (số nguyên lớn).
                       .setParameter("username", username)
                       .getSingleResult(); // Thực hiện truy vấn và lấy duy nhất một kết quả, là tổng số dòng có username trùng.
        em.close(); 
        return count > 0;
    }

    public boolean checkEmailDuplicate(String email) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.email = :email", Long.class)
                       .setParameter("email", email)
                       .getSingleResult();
        em.close();
        return count > 0;
    }

    public boolean checkOldPasswordDuplicateByUsername(String username, String newPassword) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<String> query = em.createQuery("SELECT u.password FROM User u WHERE u.username = :username", String.class);
        query.setParameter("username", username);
        List<String> result = query.getResultList();
        em.close();
        if (!result.isEmpty()) {
            return result.get(0).equals(newPassword);
        }
        return false;
    }

    public boolean checkEmail(String email) {
        return checkEmailDuplicate(email); // same as duplicate check
    }

    public User getUserByEmail(String email) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email = :email", User.class);
        query.setParameter("email", email);
        List<User> result = query.getResultList();
        em.close();
        return result.isEmpty() ? null : result.get(0);
    }

    public User getUserByUsername(String username) {
        EntityManager em = emf.createEntityManager();
        TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> result = query.getResultList();
        em.close();
        return result.isEmpty() ? null : result.get(0);
    }

    public boolean login(String username, String password) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery("SELECT COUNT(u) FROM User u WHERE u.username = :username AND u.password = :password", Long.class)
                       .setParameter("username", username)
                       .setParameter("password", password)
                       .getSingleResult();
        em.close();
        return count > 0;
    }

    public void changePassword(String username, String newPassword) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        User user = getUserByUsername(username); // Re-use method
        if (user != null) {
            user.setPassword(newPassword);
            em.merge(user); // update
        }
        em.getTransaction().commit();
        em.close();
    }
    
    public User getUserById(int id) {
    EntityManager em = emf.createEntityManager();
    try {
        return em.find(User.class, id); // JPA sẽ tự tìm theo khóa chính
    } finally {
        em.close();
    }
}

}
