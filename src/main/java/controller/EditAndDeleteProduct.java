/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;
import utils.ImgUrl;

/**
 *
 * @author tung
 */
public class EditAndDeleteProduct extends HttpServlet {

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
            out.println("<title>Servlet EditAndDeleteProduct</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EditAndDeleteProduct at " + request.getContextPath() + "</h1>");
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
        int id = Integer.parseInt(request.getParameter("id"));

        dao.deleteProductById(id);
        request.getRequestDispatcher("manageProducts").forward(request, response);
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

        ProductDAO dao = new ProductDAO();
        HttpSession session = request.getSession();
        String oldIDP = request.getParameter("oldID");
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String priceP = request.getParameter("price");
        String unit = request.getParameter("unit");
        String quantityP = request.getParameter("quantity");
        String image_url = request.getParameter("image_url");

        // parse
        double price = Double.parseDouble(priceP);
        int quantity = Integer.parseInt(quantityP);
        int oldID = Integer.parseInt(oldIDP);

        //check priceP and quantity < 0
        if (price < 0) {
            request.setAttribute("ErrorMess", "price can't not be negative");
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            return;
        }
        if (quantity < 0) {
            request.setAttribute("ErrorMess", "price can't not be negative");
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            return;
        }

        // Check local image file exist
        ImgUrl img = new ImgUrl();
        if (!img.isImageUrlExists(image_url)) {
            request.setAttribute("ErrorMess", "ERROR with that Img File Path");
            request.getRequestDispatcher("editProduct.jsp").forward(request, response);
            return;
        }

        Product p = new Product(name, price, unit, quantity, image_url);
        dao.updateProduct(oldID, p);
        request.getRequestDispatcher("manageProducts").forward(request, response);
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
