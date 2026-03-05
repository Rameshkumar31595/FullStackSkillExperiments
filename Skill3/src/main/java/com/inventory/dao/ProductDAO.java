package com.inventory.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.inventory.entity.Product;
import com.inventory.util.HibernateUtil;

public class ProductDAO {

    // CREATE
    public void saveProduct(Product product) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();
            session.persist(product);
            transaction.commit();

            System.out.println("Product saved successfully");

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // READ
    public Product getProduct(int id) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Product.class, id);
        }
    }

    // UPDATE
    public void updateProduct(int id, double price, int quantity) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Product product = session.get(Product.class, id);

            if (product != null) {
                product.setPrice(price);
                product.setQuantity(quantity);
                session.merge(product);
                System.out.println("Product updated successfully");
            } else {
                System.out.println("Product not found");
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    // DELETE
    public void deleteProduct(int id) {

        Transaction transaction = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            transaction = session.beginTransaction();

            Product product = session.get(Product.class, id);

            if (product != null) {
                session.remove(product);
                System.out.println("Product deleted successfully");
            } else {
                System.out.println("Product not found");
            }

            transaction.commit();

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}
