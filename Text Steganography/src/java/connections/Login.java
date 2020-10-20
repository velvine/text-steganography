/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package connections;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class Login extends HttpServlet {

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
            out.println("<title>Servlet Logins</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Logins at " + request.getContextPath() + "</h1>");
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
//   processRequest(request, response);
        PrintWriter out=null;
        response.setContentType("text/html");
        out=response.getWriter();
//        databaseConnection dbConnection=new databaseConnection();
        Connection connection;
        Statement statement;
        ResultSet resultset;
        ResultSet rs;
//        connection= dbConnection.getConnection();
      try{  
            Class.forName("com.mysql.jdbc.Driver");
            connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital?user=root&password=");
            String username=request.getParameter("username");
            String password=request.getParameter("password");
            if(username.equals("")){
                out.println("Username Should not be empty");
            }
            else if(password.equals("")){
                out.println("Password Should not be empty");
           }
           else{
            statement= connection.createStatement();
            rs=statement.executeQuery("SELECT Username,Password,Fullname,Userrole,id,Status FROM users where Username='"+username+"' && Password='"+password+"' && Status!='Disabled'");
            if(rs.next()){
                String loggedusername=rs.getString("Username"),loggeduserrole=rs.getString("Userrole"),loggedfullname=rs.getString("Fullname"),loggedid=rs.getString("id");
                HttpSession session=request.getSession();
                 switch (loggeduserrole) {
                    case "Admin":
                         session.setAttribute("HTSAdminUsername", loggedusername);
                         session.setAttribute("HTSAdminUserrole", loggeduserrole);
                         session.setAttribute("HTSAdminFullname", loggedfullname);
                         session.setAttribute("HTSAdmin", loggedusername);
                         session.setAttribute("HTSAdminId", loggedid);
                         session.setAttribute("HTSLog","Logged");
                         session.setMaxInactiveInterval(3600);
                         out.print("Login Success Admin");
                    break;
                     default:
                         session.setAttribute("HTSMedicUsername", loggedusername);
                         session.setAttribute("HTSMedicUserrole", loggeduserrole);
                         session.setAttribute("HTSMedicFullname", loggedfullname);
                         session.setAttribute("HTSMedic", loggedusername);
                         session.setAttribute("HTSMedicId", loggedid);
                         session.setAttribute("HTSLog","Logged");
                         session.setMaxInactiveInterval(3600);
                         out.print("Login Success Medic");
                    break;
                 }
            }
               else 
            {
                out.print("Login Failed"); 
            }
              
            rs.close();
            statement.close();
            connection.close();
           }
      } catch (ClassNotFoundException ex) {
            out.println("No Database Drivers: " +ex.getMessage());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
      }
      catch (SQLException ex) {
            out.println("Database Error: " +ex.getMessage());
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
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
