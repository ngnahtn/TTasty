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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author tung
 */
public class BuyServlet extends HttpServlet {

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
            out.println("<title>Servlet BuyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyServlet at " + request.getContextPath() + "</h1>");
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

        String id = request.getParameter("id");
        String num_raw = request.getParameter("num");

        try {
            int num = Integer.parseInt(num_raw);
            Product p = dao.getProductById(Integer.parseInt(id));
            int stock = p.getQuantity(); // số lượng còn lại trong kho

            // Nếu vượt quá stock thì chỉnh về stock
            if (num > stock) {
                num = stock;
            }

            // neu ma trong cookie chua co ( tuc la thang dau tien)
            if (txt.isEmpty()) {
                txt = id + ":" + num;
            } else {
                // check trung cart
                String[] a = txt.split(",");
                boolean found = false;
                for (int i = 0; i < a.length; i++) {
                    String[] pair = a[i].split(":");
                    if (pair[0].equals(id)) {
                        int quantity = Integer.parseInt(pair[1]) + num;

                        // Nếu vượt quá stock thì chỉnh về stock
                        if (quantity > stock) {
                            quantity = stock;
                        }

                        a[i] = id + ":" + quantity;
                        found = true;
                        break;
                    }
                }
                // neu khong bi trung cart
                if (!found) {
                    txt += "," + id + ":" + num;

                    // neu trung thi cap nhat txt bang join a ngan bang dau phay    
                } else {
                    txt = String.join(",", a);
                }
            }

            // tao cooke moi ten la cart set 2 ngay
            // mã hóa (encode) nội dung chứa dấu , và : trước khi lưu vào cookie
            Cookie c = new Cookie("cart", URLEncoder.encode(txt, "UTF-8"));  // mã hóa chuỗi chứa ký tự đặc biệt
            c.setPath("/"); // Áp dụng cho toàn web app
            c.setMaxAge(2 * 24 * 60 * 60);
            response.addCookie(c);

            // Cập nhật lại session size sau khi cập nhật cookie
            int size = 0;
            String[] items = txt.split(",");
            for (String item : items) {
                String[] pair = item.split(":");
                if (pair.length == 2) {
                    try {
                        size += Integer.parseInt(pair[1]);
                    } catch (NumberFormatException e) {
                        // ignore
                    }
                }
            }
            
            request.getSession().setAttribute("size", size);
            
            response.sendRedirect("shop");

        } catch (NumberFormatException e) {
            System.out.println(e);
            response.sendRedirect("shop");
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
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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
