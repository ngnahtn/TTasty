package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.Product;

public class ProductDAO {

    private static final EntityManagerFactory emf
            = Persistence.createEntityManagerFactory("TTastyShop");

    public List<Product> getAll() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Product> query = em.createQuery("SELECT p FROM Product p", Product.class);
        List<Product> list = query.getResultList();
        em.close();
        return list;
    }

    public boolean duplicateName(String name) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.name = :name", Long.class)
                .setParameter("name", name)
                .getSingleResult();
        em.close();
        return count > 0;
    }

    public boolean duplicateImg(String imgUrl) {
        EntityManager em = emf.createEntityManager();
        Long count = em.createQuery(
                "SELECT COUNT(p) FROM Product p WHERE p.image_url = :imgUrl", Long.class)
                .setParameter("imgUrl", imgUrl)
                .getSingleResult();
        em.close();
        return count > 0;
    }

    public void insertProduct(Product product) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(product);
        em.getTransaction().commit();
        em.close();
    }

    public Product getProductById(int id) {
        EntityManager em = emf.createEntityManager();
        Product product = em.find(Product.class, id);
        em.close();
        return product;
    }

    public void deleteProductById(int id) {
        EntityManager em = emf.createEntityManager();
        Product product = em.find(Product.class, id);

        if (product != null) {
            em.getTransaction().begin();
            em.remove(product);
            em.getTransaction().commit();
        }

        em.close();
    }
    
   
    public void updateProduct(int oldRollNo, Product updated) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        Product existing = em.find(Product.class, oldRollNo);
        if (existing != null) {
            existing.setName(updated.getName());
            existing.setPrice(updated.getPrice());
            existing.setUnit(updated.getUnit());
            existing.setQuantity(updated.getQuantity());
            existing.setImage_url(updated.getImage_url());

        }

        em.getTransaction().commit();
        em.close();
    }
    
    // update quantity of product
    public void updateProductQuantity(int productId, int quantityChange) {
    EntityManager em = emf.createEntityManager();
    em.getTransaction().begin();
    Product product = em.find(Product.class, productId);
    if (product != null) {
        product.setQuantity(product.getQuantity() + quantityChange); // quantityChange âm nếu trừ, dương nếu cộng
    }
    em.getTransaction().commit();
    em.close();
}

    
}

