/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import model.Product;
import utils.ImgUrl;

/**
 *
 * @author tung
 */
public class ManageProductsServlet extends HttpServlet {

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
            out.println("<title>Servlet AddProductsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddProductsServlet at " + request.getContextPath() + "</h1>");
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
        List<Product> list = new ArrayList<>();
        ProductDAO dao = new ProductDAO();
        list = dao.getAll();

        request.setAttribute("dataProducts", list);
        request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
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

        // add products 
        String nameProduct = request.getParameter("nameProduct");
        String priceStr = request.getParameter("price");
        String unit = request.getParameter("unit");
        String quantityStr = request.getParameter("quantity");
        String img_url = request.getParameter("image_url");
        
        
        
        ProductDAO dao = new ProductDAO();

        // check fill full information
        if (nameProduct == null || nameProduct.trim().isEmpty()
                || priceStr == null || priceStr.trim().isEmpty()
                || unit == null || unit.trim().isEmpty()
                || quantityStr == null || quantityStr.trim().isEmpty()
                || img_url == null || img_url.trim().isEmpty()) {

            request.setAttribute("ErrorMessage", "Please fill all required fields");
            request.setAttribute("dataProducts", dao.getAll());
            request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
            return;
        }

        // Parse
        float price = Float.parseFloat(priceStr);
        int quantity = Integer.parseInt(quantityStr);

        // check price >= 0 
        if (price < 0) {
            request.setAttribute("ErrorMessage", "Please enter price >= 0");
            request.setAttribute("dataProducts", dao.getAll());
            request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
            return;
        }

        // check quantity >= 0 
        if (quantity < 0) {
            request.setAttribute("ErrorMessage", "Please enter quantity >= 0");
            request.setAttribute("dataProducts", dao.getAll());
            request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
            return;
        }

        // check img_url exist
        ImgUrl img = new ImgUrl();
        if (!img.isImageUrlExists(img_url)) {
            request.setAttribute("ErrorMessage", "ERROR with that Img URL");
            request.setAttribute("dataProducts", dao.getAll());
            request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
            return;
        }

        // check duplicate nameProduct 
        if (dao.duplicateName(nameProduct)) {
            request.setAttribute("ErrorMessage", "Already exits that product name");
            request.setAttribute("dataProducts", dao.getAll());
            request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
            return;
        }

        // check duplicate url pic
        if (dao.duplicateImg(img_url)) {
            request.setAttribute("ErrorMessage", "Already exits that img");
            request.setAttribute("dataProducts", dao.getAll());
            request.getRequestDispatcher("manageProduct.jsp").forward(request, response);
            return;

        }

        Product pr = new Product(nameProduct, price, unit, quantity, img_url);
        dao.insertProduct(pr);

        // SET lại danh sách sản phẩm
        request.setAttribute("dataProducts", dao.getAll());

        request.setAttribute("SuccessMessage", "Success Add Product");
        request.getRequestDispatcher("manageProduct.jsp").forward(request, response);

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
