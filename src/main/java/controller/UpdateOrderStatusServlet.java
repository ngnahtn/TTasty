/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import dao.OrderDetailDAO;
import dao.ProductDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.Order;
import model.OrderItemDTO;
import model.User;
import utils.Email;

/**
 *
 * @author tung
 */
public class UpdateOrderStatusServlet extends HttpServlet {

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
            out.println("<title>Servlet UpdateOrderStatusServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateOrderStatusServlet at " + request.getContextPath() + "</h1>");
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
    // delete (reject) order
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        int orderId = Integer.parseInt(request.getParameter("orderId"));

        UserDAO uDAO = new UserDAO();
        OrderDAO oDAO = new OrderDAO();
        OrderDetailDAO odDAO = new OrderDetailDAO();
        ProductDAO pDAO = new ProductDAO();

        // Lấy thông tin đơn hàng TRƯỚC KHI XÓA
        Order o = oDAO.getOrderById(orderId);
        if (o == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Đơn hàng không tồn tại");
            return;
        }

        User u = uDAO.getUserById(o.getUser_id());
        if (u == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Người dùng không tồn tại");
            return;
        }

        // Nếu đơn đã được Accept → cộng lại số lượng
        if ("Accepted".equalsIgnoreCase(o.getStatus())) {
            List<OrderItemDTO> items = odDAO.getOrderItems(orderId);
            for (OrderItemDTO item : items) {
                pDAO.updateProductQuantity(item.getProductId(), item.getQuantity());
            }
        }

        String gmail = u.getEmail();

        // Xóa chi tiết rồi xóa đơn
        odDAO.deleteByOrderId(orderId);
        oDAO.deleteOrderById(orderId);

        User account = (User) session.getAttribute("Account");
        if (account.getRole() == 1) {
            // Gửi mail
            Email e = new Email();
            e.sendOrderRejectedEmail(gmail, o.getBuyerName());
            response.sendRedirect("order"); // trở lại trang danh sách đơn
        } else {
            response.sendRedirect("historyOrder");
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
    // updated accpet
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int orderId = Integer.parseInt(request.getParameter("orderId"));
        String newStatus = request.getParameter("newStatus");
        
        
        UserDAO uDAO = new UserDAO();
        OrderDAO dao = new OrderDAO();
        OrderDetailDAO odDAO = new OrderDetailDAO();
        ProductDAO pDAO = new ProductDAO();

        dao.updateOrderStatus(orderId, newStatus);

        Order o = dao.getOrderById(orderId);
        User u = (User) uDAO.getUserById(o.getUser_id());
        String gmail = (String) u.getEmail();

        // Giảm số lượng sản phẩm
        List<OrderItemDTO> items = odDAO.getOrderItems(orderId);
        for (OrderItemDTO item : items) {
            pDAO.updateProductQuantity(item.getProductId(), -item.getQuantity());
        }

        // gui mail thong bao reject ve
        Email e = new Email();
        e.sendOrderAcceptedEmail(gmail, o.getBuyerName());

        // Quay lại trang danh sách
        response.sendRedirect("order");
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
