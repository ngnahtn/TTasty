/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.User;
import utils.Email;

/**
 *
 * @author tung
 */
public class ForgotServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
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
            out.println("<title>Servlet forgotServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet forgotServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String email = request.getParameter("email");
        UserDAO dao = new UserDAO();
        HttpSession session = request.getSession();
        Email emailRe = new Email();
        
        // check syntax gmail 
        String gmailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        // Compile with CASE_INSENSITIVE flag
        Pattern pattern = Pattern.compile(gmailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            request.setAttribute("errorMessage", "Please enter a valid Gmail ");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
            return;
        }
        
        // check exits email 
        if (dao.checkEmail(email)) {
            User account = new User();
            account = dao.getUserByEmail(email);
            
            session.removeAttribute("Account");
            session.setAttribute("Account", account);
            
            // gui otp den email do de verify
            int otpNew = emailRe.sendEmailOTP(email);   
            session.removeAttribute("OTP");
            session.setAttribute("OTP", otpNew);
            response.sendRedirect("verifyEmail.jsp");
            
        } else {
            request.setAttribute("errorMessage", "Do not exits that email");
            request.getRequestDispatcher("forgotPassword.jsp").forward(request, response);
        }
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
