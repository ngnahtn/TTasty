/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.net.URLDecoder;
import model.User;

/**
 *
 * @author tung
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        String username = request.getParameter("user");
        String password = request.getParameter("pass");
        String r = request.getParameter("rem");
        HttpSession session = request.getSession();

        //1. tao cookie 
        Cookie ur = new Cookie("username", username);
        Cookie ps = new Cookie("password", password);
        Cookie remember = new Cookie("remember", r != null ? "on" : "off");

        //2. thiet lap thoi gian song
        if (r != null) {
            ur.setMaxAge(60 * 60); // 3600s
            ps.setMaxAge(60 * 60);
            remember.setMaxAge(60 * 60);

        } else {
            ur.setMaxAge(0); // 0s
            ps.setMaxAge(0);
            remember.setMaxAge(0);
        }

        //3. add vo cookie
        response.addCookie(ur);
        response.addCookie(ps);
        response.addCookie(remember);

        // Check if username or password is missing or empty
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {

            request.setAttribute("Login", "Please fill full username and password");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        // check login 
        UserDAO dao = new UserDAO();
        User account = dao.loginAndGetUser(username, password);
        if (account == null) {
            request.setAttribute("Login", "username or password isn't correct");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            // luu account nguoi dung
            session.removeAttribute("Account");
            session.setAttribute("Account", account);

            //neu la admin
            if (account.getRole() == 1) {
                session.setAttribute("user", username);
                response.sendRedirect("adminHome.jsp");
                return;
            }

            // luu ten user nguoi dung
            session.setAttribute("user", username);

            // Xử lý cart cookie để set lại session size
            Cookie[] cookies = request.getCookies();
            String txt = "";
            if (cookies != null) {
                for (Cookie c : cookies) {
                    if (c.getName().equals("cart")) {
                        txt += URLDecoder.decode(c.getValue(), "UTF-8");
                        break;
                    }
                }
            }

            int size = 0;
            if (!txt.isEmpty()) {
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
            }
            
            //reset lại đúng size
            session.setAttribute("size", size); 

            response.sendRedirect("index.jsp");

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
