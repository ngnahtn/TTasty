/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import model.Cart;
import model.Item;
import model.Order;
import model.OrderDetail;
import model.User;
import static utils.Email.sendOrderConfirmation;

/**
 *
 * @author tung
 */
public class CheckOutServlet extends HttpServlet {

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
            out.println("<title>Servlet CheckOutServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CheckOutServlet at " + request.getContextPath() + "</h1>");
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");

        OrderDAO dao = new OrderDAO();
        OrderDetailDAO daoDetail = new OrderDetailDAO();

        // Lấy ngày giờ hiện tại
        Date order_date = new Date();

        // kiem tra empty info
        if (name.length() == 0 || address.length() == 0 || phone.length() == 0) {
            request.setAttribute("ErrorMess", "Please fill full infomation");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
            return;
        }

        // kiem tra invalid phone
        if (phone.length() < 10) {

            request.setAttribute("ErrorMess", "Invalid phone number");
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
            return;
        }

        User u = (User) session.getAttribute("Account");
        Order o = new Order(u.getId(), order_date, address, phone, name, "Pending");

        // insert order vo database
        dao.insertOrder(o);

        // insert cac items hang vao database
        Cart c = (Cart) session.getAttribute("cart");
        List<Item> list = c.getItems();

        for (Item i : list) {
            BigDecimal price = BigDecimal.valueOf(i.getPrice()).
                    multiply(BigDecimal.valueOf(i.getQuantity()));
            OrderDetail od = new OrderDetail(o.getOrder_id(),
                    i.getProduct().getProduct_id(),
                    i.getQuantity(),
                    price);
            daoDetail.insertOrderDetail(od);
        }

        sendOrderConfirmation(u.getEmail(), o.getBuyerName());
        
        // xoa cart
        session.removeAttribute("cart");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("cart")) {
                    cookie.setValue("");
                    cookie.setPath("/");
                    cookie.setMaxAge(0); // xoá ngay lập tức
                    response.addCookie(cookie);
                }
            }
        }
        // set lai size
        session.setAttribute("size", 0);
        
        session.setAttribute("SuccessMess", "Order placed successfully! Please check your email and wait for our confirmation.");
        response.sendRedirect("checkout.jsp");

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
