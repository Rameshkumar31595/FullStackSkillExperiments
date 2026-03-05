package com.inventory.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Best for MySQL
    private int id;

    @Column(nullable = false)
    private String name;

    private String description;

    private double price;

    private int quantity;

    // Default Constructor (Required by Hibernate)
    public Product() {}

    // Parameterized Constructor
    public Product(String name, String description, double price, int quantity) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getQuantity() { return quantity; }

    // Setters
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    @Override
    public String toString() {
        return "Product [id=" + id +
                ", name=" + name +
                ", description=" + description +
                ", price=" + price +
                ", quantity=" + quantity + "]";
    }
}
