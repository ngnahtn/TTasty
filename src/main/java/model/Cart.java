/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author tung
 */
public class Cart {

    // cart
    private List<Item> items = new ArrayList<>();

    public List<Item> getItems() {
        return items;
    }

    //get item from id
    private Item getItemByID(int id) {
        for (Item i : items) {
            if (i.getProduct().getProduct_id() == id) {
                return i;
            }
        }
        return null;
    }

    // get quantity from id
    public int getQuantityById(int id) {
        return getItemByID(id).getQuantity();
    }

    public void addItem(Item t) {

        // duplicate in the cart
        if (getItemByID(t.getProduct().getProduct_id()) != null) {
            Item m = getItemByID(t.getProduct().getProduct_id());
            m.setQuantity(m.getQuantity() + t.getQuantity());

            // doesn't have that item in the cart yet    
        } else {
            items.add(t);
        }
    }

    public void removeItemById(int id) {
        if (getItemByID(id) != null) {
            items.remove(getItemByID(id));
        }
    }

    public double getTotalMoney() {
        double total = 0;
        for (Item i : items) {
            total += (i.getQuantity() * i.getPrice());
        }
         
        return total;
    }

    private Product getProductById(int id, List<Product> list) {
        for (Product p : list) {
            if (p.getProduct_id() == id) {
                return p;
            }
        }
        return null;
    }

    // Bam txt cua cookie de xu li
    public Cart(String txt, List<Product> list) {
        try {
            String[] s = txt.split(",");
            for (String st : s) {
                String[] n = st.split(":");
                int id = Integer.parseInt(n[0]);
                int quantity = Integer.parseInt(n[1]);
                Product p = getProductById(id, list);

                if (p != null) {
                    Item t = new Item(p, quantity, p.getPrice() * 1);  
                    items.add(t);
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

    }
    
    


}
