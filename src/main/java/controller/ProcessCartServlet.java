/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import model.Cart;
import model.Item;
import model.Product;

/**
 *
 * @author tung
 */
public class ProcessCartServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcessCartServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessCartServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // xu li tang va giam san pham
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        ProductDAO dao = new ProductDAO();
        List<Product> listProduct = new ArrayList<>();
        listProduct = dao.getAll();

        Cookie[] arr = request.getCookies();
        String txt = "";

        // ton tai cookie roi
        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cart")) {
                    txt += URLDecoder.decode(c.getValue(), "UTF-8"); // decode đúng

                    // xoa cookie cu
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
        // getP cua num va id thong qua button tang va giam 
        Cart cart = new Cart(txt, listProduct);
        String num_raw = request.getParameter("num");
        String id_raw = request.getParameter("id");

        int id, num = 0;

        try {
            id = Integer.parseInt(id_raw);
            Product p = dao.getProductById(id);

            // lay quantity cua san pham do
            int quanStore = p.getQuantity();

            num = Integer.parseInt(num_raw);
            if (num == -1 && cart.getQuantityById(id) <= 1) {
                cart.removeItemById(id);
            } else {

                // kiem tra vuot qua stock
                if (num == 1 && cart.getQuantityById(id) >= quanStore) {
                    num = 0;
                    request.setAttribute("ErrorMess", "Only " + quanStore + " " + p.getName() + "(s) left in stock.");

                }
                double price = p.getPrice();
                Item t = new Item(p, num, price);
                cart.addItem(t);
            }

            List<Item> items = cart.getItems();
            txt = "";
            if (items.size() > 0) {
                txt = items.get(0).getProduct().getProduct_id() + ":"
                        + items.get(0).getQuantity();
                for (int i = 1; i < items.size(); i++) {
                    txt += "," + items.get(i).getProduct().getProduct_id() + ":"
                            + items.get(i).getQuantity();

                }
            }

            Cookie c = new Cookie("cart", java.net.URLEncoder.encode(txt, "UTF-8"));
            c.setPath("/"); // Áp dụng cho toàn web app
            c.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(c);
            session.setAttribute("cart", cart);
            
            // Cập nhật lại session size cho giỏ hàng 
            int size = 0;
            for (Item item : items) {
                size ++; 
            }
            request.getSession().setAttribute("size", size); 
  
            request.getRequestDispatcher("cart.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            System.out.println(e);
        }

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // xoa return item
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        // Lấy danh sách tất cả sản phẩm trong kho từ DAO
        ProductDAO dao = new ProductDAO();
        List<Product> listProduct = dao.getAll();

        // Đọc cookie giỏ hàng từ trình duyệt
        Cookie[] arr = request.getCookies();
        String txt = ""; // Dữ liệu thô từ cookie

        if (arr != null) {
            for (Cookie c : arr) {
                if (c.getName().equals("cart")) {
                    // Decode cookie cart
                    txt += URLDecoder.decode(c.getValue(), "UTF-8");

                    // Xoá cookie cũ để thay thế bằng cookie mới
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }

        // Debug thông tin giỏ hàng hiện tại
        System.out.println("Decoded cart txt = " + txt);

        // Lấy ID sản phẩm từ request (gửi từ form xóa)
        String id_raw = request.getParameter("id");
        try {
            int id = Integer.parseInt(id_raw); // Parse id

            // Tạo giỏ hàng từ cookie hiện tại
            Cart cart = new Cart(txt, listProduct);

            // Xoá sản phẩm khỏi giỏ hàng
            cart.removeItemById(id);

            // Build lại chuỗi txt để lưu lại cookie mới sau khi đã xoá
            List<Item> items = cart.getItems();
            txt = "";

            if (!items.isEmpty()) {
                StringBuilder sb = new StringBuilder();
                sb.append(items.get(0).getProduct().getProduct_id())
                        .append(":")
                        .append(items.get(0).getQuantity());

                for (int i = 1; i < items.size(); i++) {
                    sb.append(",")
                            .append(items.get(i).getProduct().getProduct_id())
                            .append(":")
                            .append(items.get(i).getQuantity());
                }

                txt = sb.toString();
            }

            // Tạo lại cookie mới với giá trị đã cập nhật
            Cookie c = new Cookie("cart", java.net.URLEncoder.encode(txt, "UTF-8"));
            c.setPath("/"); // Áp dụng cho toàn bộ app
            c.setMaxAge(2 * 24 * 60 * 60); 
            response.addCookie(c);

            // Cập nhật lại session size cho giỏ hàng 
            int size = 0;
            for (Item item : items) {
                size ++; 
            }
            request.getSession().setAttribute("size", size); 

            // Gửi lại cart mới cho trang JSP hiển thị
            session.setAttribute("cart", cart);
            
            request.getRequestDispatcher("cart.jsp").forward(request, response);

        } catch (NumberFormatException e) {
            
            System.out.println("Lỗi parse id: " + e);
            response.sendRedirect("cart.jsp");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
