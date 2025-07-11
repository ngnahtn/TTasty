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
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import model.User;
import utils.Email;

/**
 *
 * @author tung
 */
public class RegisterServlet extends HttpServlet {

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
            out.println("<title>Servlet RegisterServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RegisterServlet at " + request.getContextPath() + "</h1>");
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
        String email = request.getParameter("email");
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String repassword = request.getParameter("repassword");

        HttpSession session = request.getSession();
        UserDAO dao = new UserDAO();

        if (email == null || username == null || password == null || repassword == null
                || email.trim().isEmpty() || username.trim().isEmpty()
                || password.trim().isEmpty() || repassword.trim().isEmpty()) {

            request.setAttribute("errorMessage", "Please fill full information to register!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // check gmail 
        String gmailRegex = "^[a-zA-Z0-9._%+-]+@gmail\\.com$";
        // Compile with CASE_INSENSITIVE flag
        Pattern pattern = Pattern.compile(gmailRegex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            request.setAttribute("errorMessage", "Please enter a valid Gmail ");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // Check password complexity
        // At least 8 characters, at least one uppercase, at least one special character
        String passwordRegex = "^(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        Pattern passwordPattern = Pattern.compile(passwordRegex);
        Matcher passwordMatcher = passwordPattern.matcher(password);
        if (!passwordMatcher.matches()) {
            request.setAttribute("errorMessage", "Password must be at least 8 characters long, contain at least one uppercase letter, and one special character.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // check duplicate email
        if (dao.checkEmailDuplicate(email)) {
            request.setAttribute("errorMessage", "email already exists ");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // check repassword
        if (!password.equals(repassword)) {
            request.setAttribute("errorMessage", "password and repassword not match");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // check duplicate username
        if (dao.checkUsername(username)) {
            request.setAttribute("errorMessage", "username already exists ");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // check duplicate username
        if (dao.checkEmail(email)) {
            request.setAttribute("errorMessage", "email already exists ");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        Email e = new Email();
        int rdOTP = e.sendEmailOTP(email);
        User account = new User(email, username, password, 2);

        // luu session otp , account cua nguoi dung
        session.setAttribute("OTP", rdOTP);
        session.removeAttribute("Account");
        session.setAttribute("Account", account);

        response.sendRedirect("verifyEmail.jsp");
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
