/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package medic;

import medic.medicUser;
import ciphers.HideandCipher;
import connections.databaseConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
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
public class medicUser extends HttpServlet {
String searchitem,searchfield,sno,username,fullname,useridno,userphone,useremail,gender,userrole,genpass;
 String submit,patientage,patientregion,patientcountry,contactinfo,placeofbirth,delfullname,pno;
 String recordtreatment,recorddisease,recorddatedone,recordcovertext,searchfieldreport,searchitemreport,searchenddatereport,searchstartdatereport;
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
            HttpSession session=request.getSession();
            if (!session.getAttribute("HTSLog").equals("Logged")) {
                String site = new String("index.jsp");
//                response.sendRedirect(site);
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site); 
            }
            session=request.getSession();
            String sesusername=(String)session.getAttribute("HTSMedicUsername"),sesadmin=(String)session.getAttribute("HTSMedic"),sesfullname=(String)session.getAttribute("HTSMedicFullname"),sesuserrole=(String)session.getAttribute("HTSMedicUserrole"),loggedid=(String)session.getAttribute("HTSMedicId");
            response.setContentType("text/html;charset=UTF-8");
            try (PrintWriter out = response.getWriter()) {
            databaseConnection dbConnection=new databaseConnection();
            Connection connection;
            Statement statement=null,stmt=null,rolestmt=null;
            ResultSet resultset=null,rs=null,rolers=null;
            submit=request.getParameter("submit");
            connection= dbConnection.getConnection();
            try{
            rolestmt= connection.createStatement();
            rolers=rolestmt.executeQuery("SELECT userroles.URid,userroles.UserRole,userroles.Role as URole,roles.Rid,roles.Role,roles.Description,roles.Type from userroles,roles Where UserRole='"+sesuserrole+"' and roles.Role='"+submit+"' and userroles.Role=roles.Rid");
            if(rolers.next()){
            switch (submit) {
                case "PatientInfo"://1
                    out.print("<form role=\"form\" class=\"form-horizontal\" name=\"searchpatientdetailsform\" id=\"searchpatientdetailsform\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">");
                    out.println(""
                    + "<div class=\"row\">\n" +
                    " <div class=\"col-sm-12\" style=\"background-color:#77B5ED;\" >\n" 
                    + "<div class=\"form-group has-feedback\" style=\"padding-top:10px;\">\n" +
                    "   <div class=\"col-sm-3\">\n" +
                    "   <select name=\"searchfield\" id=\"searchfield\" class=\"select form-control\" required=\"required\">\n" +
                    "   <option value=\"\">Choose What to Search...</option>\n" +
                    "   <option value=\"Fullname\" Selected>Patient Fullname</option>\n" +
                    "   <option value=\"Age\">Patient Age</option>\n" +
                    "   <option value=\"VisitDate\">Patient Visit Date</option>\n" +
                    "   <option value=\"BirthPlace\">Patient Birth Place</option>\n" +
                    "   </select>\n" +
                    "   </div>\n" +
                    
                    "   <div class=\"col-sm-4\">\n" +
                    "   <input type=\"\" name=\"searchitem\" id=\"searchitem\" class=\" form-control\" placeholder=\"Enter Patient(s) to search\" style=\"color:#73C4FF;\">\n" +
                    "   <span class=\"glyphicon glyphicon-search form-control-feedback\"></span>\n" +
                    "   </div>\n" +
                    "   <div class=\"col-sm-1\">\n" +
                    "   <button  class=\"btn btn-small btn-primary\" name=\"searchitemstmtbtn\" id=\"searchitemstmtbtn\" >Search <span class=\"glyphicon glyphicon-search \"></span></button>\n" +
                    "   </div>\n" +
                    "   <div class=\"col-sm-1\">\n" +
                    "   <button  class=\"btn btn-small btn-default newpatientallbtn\" name=\"newpatientallbtn\" id=\"newpatientallbtn\" >Patients <span class=\"fa fa-plus-square\"></span></button>\n" +
                    "   </div>\n" +
                    "   <div class=\"col-sm-1\">\n" +
                    "   <button  class=\"btn btn-small btn-success newrecordallbtn\" name=\"newrecordallbtn\" id=\"newrecordallbtn\" >Records <span class=\"fa fa-hospital-o\"></span></button>\n" +
                    "   </div>\n" +
                    "   <div class=\"col-sm-1\">\n" +
                    "   <button  class=\"btn btn-small btn-warning sharerecordall\" name=\"sharerecordall\" id=\"sharerecordall\" >Shares <span class=\"glyphicon glyphicon-share \"></span></button>\n" +
                    "   </div>\n" +
                    "   </div>"
                    + "</div>\n" +
                    "  </div>\n" +
                    "<div class=\"row\">"
                    + "<div class=\"col-sm-12 \" style=\"padding:10px;background-color:#05DBFF;\">\n"
                    + "<div class=\"col-sm-1\">Sno </div>"
                    + " <div class=\"col-sm-2\">FullName</div>"
                    + " <div class=\"col-sm-1\">Age</div>"
                    + " <div class=\"col-sm-1\">Country</div>"
                    + " <div class=\"col-sm-1\">Birth Place</div>"
                    + " <div class=\"col-sm-3\">Contact</div>"
                    + " <div class=\"col-sm-2\">Actions</div>\n" +
                    "   </div>\n" +
                    "   </div>");
                    connection= dbConnection.getConnection();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM patients Where Groups LIKE '%"+sesuserrole+"%' order by Pno desc");
                        int ids=1; 
                        out.println("<div id=\"searchedpatientrecord\">");
                        while(resultset.next()){
                            out.println("<div class=\"row\">"
                            + "<div class=\"col-sm-12 \" style=\"padding:10px;margin-top:5px;background-color:white;\">\n"
                            + "<div class=\"col-sm-1\">"+ids+"</div>"
                            + " <div class=\"col-sm-2\">"+resultset.getString("Fullname")+"</div>"
                            + " <div class=\"col-sm-1\">"+resultset.getString("Age")+"</div>"
                            + " <div class=\"col-sm-1\">"+resultset.getString("Country")+"</div>"
                            + " <div class=\"col-sm-1\">"+resultset.getString("BirthPlace")+"</div>"
                            + " <div class=\"col-sm-3\">"+resultset.getString("Contact")+"</div>"
                            + " <div class=\"col-sm-2\" style=\"padding:0;\">"+
                                "<button type=\"button\" name=\"newrecords\" class=\"newrecords btn btn-small btn-success\" title=\"Create New Patient Information Record\" data-id1=\""+resultset.getInt("Pno")+"\" data-id11=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-plus-sign\"></span></button>" +
                                "<button type=\"button\" name=\"update\" class=\"update btn btn-small btn-warning\" title=\"View and Update Patient Records\" data-id1=\""+resultset.getInt("Pno")+"\" data-id2=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-edit\"></span></button>" +
                                "<button type=\"button\" name=\"sendrecords\" class=\"sendrecords btn btn-small btn-primary\" title=\"View and Send Patient Records\" data-id1=\""+resultset.getInt("Pno")+"\" data-id11=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-share\"></span></button>" +
                                "<button type=\"button\" name=\"delete\" class=\"delete btn btn-small btn-danger\" data-id1=\""+resultset.getInt("Pno")+"\" data-id2=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-remove\"></span></button>" +
                        "  </div></div></div>");
                        ids++;
                        }
                        out.println("</div>");
                    resultset.close();
                    statement.close();
                    connection.close();

                    }  catch (SQLException ex) {
                        out.print("SqlExceptions: " +ex.getMessage());
                         //error code 1146-table doesn't exists
                    } 
                    out.println("</form>");
                break;
                case "NewPatientSearch"://2
                    out.println(""
                            + "<div class=\"col-sm-12 \" style=\"padding:10px;background-color:#77B5ED;\">\n"
                            + " <div class=\"col-sm-5\">Patient Name</div>"
                            + " <div class=\"col-sm-2\">Age</div>"
                            + " <div class=\"col-sm-4\">BirthPlace</div>"+
                            "  </div>");
                    String searchpatientitemtorecord=request.getParameter("searchpatientitemtorecord");
                    connection= dbConnection.getConnection();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM patients where Fullname Like'%"+searchpatientitemtorecord+"%' and Groups LIKE '%"+sesuserrole+"%' order by Pno desc limit 10");
                        int ids=1; 
                        while(resultset.next()){
                            out.println(""
                            + "<div class=\"col-sm-12 selectedpatient\" style=\"padding:10px;margin:1px;background-color:#fef;\" data-id1=\""+resultset.getInt("Pno")+"\" data-id11=\""+resultset.getString("Fullname")+"\">\n"
                            + " <div class=\"col-sm-5\">"+resultset.getString("Fullname")+"</div>"
                            + " <div class=\"col-sm-2\">"+resultset.getString("Age")+"</div>"
                            + " <div class=\"col-sm-4\">"+resultset.getString("BirthPlace")+"</div><br>"+
                            "  </div>");
                        ids++;
                        }
                        out.println("</div>");
                    resultset.close();
                    statement.close();
                    connection.close();

                    }  catch (SQLException ex) {
                        out.print("SqlExceptions: " +ex.getMessage());
                         //error code 1146-table doesn't exists
                    } 
                    out.println("</form>");
                break;
                case "SendReceipient"://3
                    out.println(""
                            + "<div class=\"col-sm-12 \" style=\"padding:10px;background-color:#77B5ED;\">\n"
                            + " <div class=\"col-sm-5\">Receipient</div>"
                            + " <div class=\"col-sm-2\">Level</div>"
                            + " <div class=\"col-sm-4\">Group</div>"
                            + " </div>");
                    String searchsendreceipient=request.getParameter("searchsendreceipient");
                    connection= dbConnection.getConnection();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM users where Fullname Like'%"+searchsendreceipient+"%' order by id desc limit 8");
                        
                        while(resultset.next()){
                            out.println(""
                            + "<div class=\"col-sm-12 sendingreceipient\" style=\"padding:10px;margin:1px;background-color:#fef;\" data-id1=\""+resultset.getInt("id")+"\" data-id2=\""+resultset.getString("Fullname")+"\" data-id3=\""+resultset.getString("Userrole")+"\" data-id4=\"User\">\n"
                            + " <div class=\"col-sm-5\">"+resultset.getString("Fullname")+"</div>"
                            + " <div class=\"col-sm-2\">User</div>"
                            + " <div class=\"col-sm-4\">"+resultset.getString("Userrole")+"</div><br>"
                            + " </div>");
                        }
                        stmt= connection.createStatement();
                        rs=statement.executeQuery("SELECT * FROM userroles where UserRole Like'%"+searchsendreceipient+"%' group by UserRole order by URid desc limit 8");
                        while(rs.next()){
                            out.println(""
                            + "<div class=\"col-sm-12 sendingreceipient\" style=\"padding:10px;margin:1px;background-color:#fef;\" data-id1=\""+rs.getInt("URid")+"\" data-id2=\""+rs.getString("UserRole")+"\" data-id3=\""+rs.getString("Userrole")+"\" data-id4=\"UserRole\">\n"
                            + " <div class=\"col-sm-5\">"+rs.getString("UserRole")+"</div>"
                            + " <div class=\"col-sm-2\">UserRole</div>"
                            + " <div class=\"col-sm-4\">"+rs.getString("Userrole")+"</div><br>"
                            + " </div>");
                        }
                        resultset.close();
                        statement.close();
                        rs.close();
                        stmt.close();
                        connection.close();
                    }  catch (SQLException ex) {
                        out.print("SqlExceptions: " +ex.getMessage());
                         //error code 1146-table doesn't exists
                    } 
                    connection= dbConnection.getConnection();
                    try{
                        stmt= connection.createStatement();
                        rs=statement.executeQuery("SELECT * FROM userroles where UserRole Like'%"+searchsendreceipient+"%' group by UserRole order by URid desc limit 8");
                    // statement.close();
                    rs.close();
                    stmt.close();
                    connection.close();

                    }  catch (SQLException ex) {
                        out.print("No More Receipient Found");
                         //error code 1146-table doesn't exists
                    } 
                    out.println("</form>");
                break;
                case "SharePatientSearch"://4
                    out.println(""
                        + "<div class=\"col-sm-12 \" style=\"padding:10px;background-color:#77B5ED;\">\n"
                        + " <div class=\"col-sm-5\">Patient Name</div>"
                        + " <div class=\"col-sm-2\">Age</div>"
                        + " <div class=\"col-sm-4\">BirthPlace</div>"+
                        "  </div>");
                    String searchpatientitemtoshare=request.getParameter("searchpatientitemtoshare");
                    connection= dbConnection.getConnection();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM patients where Fullname Like'%"+searchpatientitemtoshare+"%' and Groups LIKE '%"+sesuserrole+"%' order by Pno desc limit 10");
                        int ids=1; 
                        while(resultset.next()){
                            out.println(""
                            + "<div class=\"col-sm-12 shareselectedpatient\" style=\"padding:10px;margin:1px;background-color:#fef;\" data-id1=\""+resultset.getInt("Pno")+"\" data-id11=\""+resultset.getString("Fullname")+"\">\n"
                            + " <div class=\"col-sm-5\">"+resultset.getString("Fullname")+"</div>"
                            + " <div class=\"col-sm-2\">"+resultset.getString("Age")+"</div>"
                            + " <div class=\"col-sm-4\">"+resultset.getString("BirthPlace")+"</div><br>"+
                            "  </div>");
                        ids++;
                        }
                        out.println("</div>");
                    resultset.close();
                    statement.close();
                    connection.close();

                    }  catch (SQLException ex) {
                        out.print("SqlExceptions: " +ex.getMessage());
                         //error code 1146-table doesn't exists
                    } 
                    out.println("</form>");
                break;
                default:
                    out.print("<h4 style=\"text-align:center;color:red;\">Invalid Command "+submit+"</h4>");
                break;
            }
        }
        else{
            out.print("<h4 style=\"text-align:center;color:red;\">You Are Not Authorised to "+submit+"<br>Please Contact Administration for More Information</h4>");
        }
        rolers.close();
        rolestmt.close();
        connection.close();
        }  catch (SQLException ex) {
        out.print("SqlExceptions: " +ex.getMessage());
         //error code 1146-table doesn't exists
        }
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
        HttpSession session=request.getSession();
            if (!session.getAttribute("HTSLog").equals("Logged")) {
                String site = new String("index.jsp");
//                response.sendRedirect(site);
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", site); 
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
//        processRequest(request, response);
        HttpSession session=request.getSession();
        if (!session.getAttribute("HTSLog").equals("Logged")) {
            String site = new String("index.jsp");
            response.setStatus(response.SC_MOVED_TEMPORARILY);
            response.setHeader("Location", site); 
        }
        PrintWriter out=null;
        response.setContentType("text/html");
        out=response.getWriter();
        session=request.getSession();
        String sesusername=(String)session.getAttribute("HTSMedicUsername"),sesadmin=(String)session.getAttribute("HTSMedic"),sesfullname=(String)session.getAttribute("HTSMedicFullname"),sesuserrole=(String)session.getAttribute("HTSMedicUserrole"),loggedid=(String)session.getAttribute("HTSMedicId");
         //save user personal details
//        checkLoggedIn(request,response);
        databaseConnection dbConnection=new databaseConnection();
        Connection connection;
        Statement statement=null,stmt=null,rolestmt=null;
        ResultSet resultset=null,rs=null,rolers=null;
        submit=request.getParameter("submit");
        connection= dbConnection.getConnection();
        try{
        rolestmt= connection.createStatement();
        rolers=rolestmt.executeQuery("SELECT userroles.URid,userroles.UserRole,userroles.Role as URole,roles.Rid,roles.Role,roles.Description,roles.Type from userroles,roles Where UserRole='"+sesuserrole+"' and roles.Role='"+submit+"' and userroles.Role=roles.Rid");
        if(rolers.next()){
        switch (submit) {
            case "NewUser"://5
                username=request.getParameter("username");
                fullname=request.getParameter("fullname");
                useridno=request.getParameter("useridno");
                useremail=request.getParameter("useremail");
                userphone=request.getParameter("userphone");
                genpass=request.getParameter("genpass");
                userrole=request.getParameter("userrole");
                if(username.equals("")){
                    out.print("Username should not be blank");
                }
                else if (fullname.equals("")){
                    out.print("Enter at least One Name");
                }
                 else if (useridno.equals("")){
                    out.print("National ID Should Not be Blank");
                }
                 else if (userphone.equals(" ")){
                    out.print("Phone Number Should Not be Blank");
                }
                 else if (useremail.equals("")){
                    out.print("Email Should Not be Blank");
                }
                 else if (genpass.equals("")){
                    out.print("Password Should Not be Empty");
                }
                else{
                    connection= dbConnection.getConnection();
                    try{
                        statement= connection.createStatement();
                        String sql="INSERT INTO users(`Fullname`, `Username`,IDno,Phone,Email, `Userrole`, `Password`) VALUES('"+fullname+"','"+username+"','"+useridno+"','"+userphone+"','"+useremail+"','"+userrole+"','"+genpass+"')";
                        int result=statement.executeUpdate(sql);
                        if(result==1){
                            out.print("<h4 style=\"text-align:center;\">Inserted User Personal Details successfully</h4>");
                        }
                         else
                        {
                            out.print("<h4 style=\"text-align:center;color:red;\">Failed to insert User Personal Details</h4>");
                        }
                        statement.close();
                        connection.close();
                         
                    }  catch (SQLException ex) {
                        //System.out.println("SqlExceptions: " +ex.getMessage());
                        out.print("Error:"+ex.getMessage());
                        //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                        Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }
            break;
            case "NewPatient"://6
                patientage=request.getParameter("patientage");
                String fullnames=request.getParameter("patientfullname");
                patientregion=request.getParameter("patientregion");
                patientcountry=request.getParameter("patientcountry");
                contactinfo=request.getParameter("contactinfo");
                placeofbirth=request.getParameter("placeofbirth");
                if(patientage.equals("")){
                    out.print("Patient age should not be Empty");
                }
                else if (fullnames.equals("")){
                    out.print("Enter at least One Name");
                }
                else if (patientregion.equals("")){
                    out.print("Patient Region Should Not be Empty");
                }
                 else if (patientcountry.equals(" ")){
                    out.print("Patient Country Should Not be Empty");
                }
                else if (placeofbirth.equals("")){
                    out.print("Place ofBirth Should Not be Blank");
                }
                else{
                    connection= dbConnection.getConnection();
                    try{
                        statement= connection.createStatement();
                        String sql="INSERT INTO patients(`Fullname`,Age,Region,Country, `BirthPlace`, `Contact`,Creator,Groups) VALUES('"+fullnames+"','"+patientage+"','"+patientregion+"','"+patientcountry+"','"+placeofbirth+"','"+contactinfo+"','"+loggedid+"','"+sesuserrole+"')";
                        int result=statement.executeUpdate(sql);
                        if(result==1){
                            out.print("Inserted New Patient Details successfully");
                        }
                        else
                        {
                            out.print("Failed to insert New Patient Details");
                        }
                        statement.close();
                        connection.close();
                    }  catch (SQLException ex) {
                        //System.out.println("SqlExceptions: " +ex.getMessage());
                        out.print("Error:"+ex.getMessage());
                        //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                        Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }
            break;
            case "SavePatientRecord"://7
                pno=request.getParameter("pno");
                String patientrecordss=request.getParameter("patientrecord");
                // recorddisease=request.getParameter("recorddisease");
                recorddatedone=request.getParameter("recorddatedone");
                recordcovertext=request.getParameter("recordcovertext")+" "+recorddatedone;
                if(patientrecordss.equals("")){
                    out.print("Patient Record/notes Information should not be Empty");
                }
                else if (recorddatedone.equals("")){
                    out.print("Please Select Date Done");
                }
                else{
                    HideandCipher hideandcipher=new HideandCipher();
                    
                    //save recorddiseasekey,recorddiseaseStegKey,recordcovertext
                    /*to decrpty and see message, 
                    use StegSeek(recorddiseaseStegKey,recordcovertext) then seekMessage(StegSeek(recorddiseaseStegKey,recordcovertext))
                    to get encrypted message(recorddiseaseStegMessage), then decrypt
                    OTPDecryption(recorddiseaseStegMessage,recorddiseasekey)
                    */
                    //Treatment encipher and hide using otp and hide data in paragraph steganography 
                    String patientrecordkey = hideandcipher.RandomAlpha(patientrecordss.length());
                    String patientrecordenc = hideandcipher.OTPEncryption(patientrecordss,patientrecordkey);
                    String patientrecordhide=hideandcipher.hidingMessageBin(patientrecordenc);
                    String patientrecordStegKey=hideandcipher.StegKey(patientrecordhide,recordcovertext);
                    connection= dbConnection.getConnection();
                    try{
                        statement= connection.createStatement();
                        String sql="INSERT INTO patientsinfo(`Pno`,Cover,Record,`DateDone`,DK,Medic)"
                                 + " VALUES('"+pno+"','"+recordcovertext+"',"
                                 + "'"+patientrecordStegKey+"','"+recorddatedone+"','"+patientrecordkey+"','"+loggedid+"')";
                        int result=statement.executeUpdate(sql);
                        if(result==1){
                            out.print("Saved Patient Record Information successfully.");
                        }
                        else
                        {
                            out.print("Failed to Save Patient Information");
                        }
                        statement.close();
                        connection.close();
                    }  catch (SQLException ex) {
                        //System.out.println("SqlExceptions: " +ex.getMessage());
                        out.print("Error:"+ex.getMessage());
                        //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                        Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            break;
            case "SearchPatient"://8
                connection= dbConnection.getConnection();
                searchfield=request.getParameter("searchfield");
                searchitem=request.getParameter("searchitem");
                try{
                    statement= connection.createStatement();
                    switch (searchfield) {
                        case "VisitDate":
                            resultset=statement.executeQuery("SELECT * FROM patients where Groups LIKE '%"+sesuserrole+"%' order by Pno desc");
                            out.println("<h4 style=\"text-align:center;color:red;\">This Search Has Not Been Activated</h4>");
                            break;
                        case "Fullname":
                        case "Age":
                        case "BirthPlace":
                            resultset=statement.executeQuery("SELECT * FROM patients where "+searchfield+" Like'%"+searchitem+"%' and Groups LIKE '%"+sesuserrole+"%' order by Pno desc");
                            int ids=0;
                            while(resultset.next()){
                                ids++;
                                out.println("<tr>"
                                + "<td>"+ids+"</td>"
                                + " <td>"+resultset.getString("Fullname")+"</td>"
                                + " <td>"+resultset.getString("Age")+"</td>"
                                + " <td>"+resultset.getString("Country")+"</td>"
                                + " <td>"+resultset.getString("BirthPlace")+"</td>"
                                + " <td>"+resultset.getString("Contact")+"</td>"
                                + " <td style=\"padding:0;\">"+
                                    "<button type=\"button\" name=\"newrecords\" class=\"newrecords btn btn-small btn-success\" title=\"Create New Patient Information Record\" data-id1=\""+resultset.getInt("Pno")+"\" data-id11=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-plus-sign\"></span></button>" +
                                    "<button type=\"button\" name=\"update\" class=\"update btn btn-small btn-warning\" title=\"View and Update Patient Records\" data-id1=\""+resultset.getInt("Pno")+"\" data-id2=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-edit\"></span></button>" +
                                    "<button type=\"button\" name=\"sendrecords\" class=\"sendrecords btn btn-small btn-primary\" title=\"View and Send Patient Records\" data-id1=\""+resultset.getInt("Pno")+"\" data-id11=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-share\"></span></button>" +
                                    "<button type=\"button\" name=\"delete\" class=\"delete btn btn-small btn-danger\" data-id1=\""+resultset.getInt("Pno")+"\" data-id2=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-remove\"></span></button>" +
                            "  </td></tr>");
                            }                       
                            if(ids==0){
                                out.println("<h4 style=\"text-align:center;color:red;\">No Patient Found</h4>");
                            }
                        break;
                        default:
                            out.println("<h4 style=\"text-align:center;color:red;\">Invalid Search Option Selected</h4>");
                        break;
                    }
                    resultset.close();
                    statement.close();
                    connection.close();
                }  catch (SQLException ex) {
                    out.print("SqlExceptions: " +ex.getMessage());
                    //error code 1146-table doesn't exists
                } 
            break;
            // case "SearchReport"://9
            //     connection= dbConnection.getConnection();
            //     searchfieldreport=request.getParameter("searchfieldreport");
            //     searchitemreport=request.getParameter("searchitemreport");
            //     searchenddatereport=request.getParameter("searchenddatereport");
            //     searchstartdatereport=request.getParameter("searchstartdatereport");
            //     HideandCipher hideandcipher=new HideandCipher();
            //     try{
            //     statement= connection.createStatement();
            //     switch (searchfieldreport) {
            //         case "AllPatients":
            //             resultset=statement.executeQuery("SELECT * FROM patients where Groups LIKE '%"+sesuserrole+"%' order by Pno desc");
            //             out.println("<h4 style=\"text-align:center;color:red;\">This Search Has Not Been Activated</h4>");
            //             break;
            //         case "PatientRecords":
            //             resultset=statement.executeQuery("SELECT * FROM patientsinfo where Cover Like'%"+searchitemreport+"%' and DateDone BETWEEN '"+searchstartdatereport+"' AND '"+searchenddatereport+"'order by PIno desc");
            //             int ids=0;
            //             out.println("<div class=\"row\">"
            //             + "<div class=\"col-sm-12 \" style=\"padding-top:10px;background-color:grey;\">\n"
            //             + "<div class=\"col-sm-1\">Sno </div>"
            //             + " <div class=\"col-sm-2\">Cover</div>"
            //             + " <div class=\"col-sm-4\">Disease</div>"
            //             + " <div class=\"col-sm-3\">Treatment</div>"
            //             + " <div class=\"col-sm-2\">Date Done</div>\n"+
            //             "</div>\n" +
            //             "</div>");
            //             while(resultset.next()){
            //                 ids++;
            //                 String disease=resultset.getString("Disease");
            //                 String treatment=resultset.getString("Treatment");
            //                 String cover=resultset.getString("Cover");
            //                 String diseaseek=resultset.getString("DiseaseDK");
            //                 String treatmentek=resultset.getString("TreatmentDK");
            //                 String diseasemessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(disease,cover));
            //                 String treatmentmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(treatment,cover));
            //                 String diseasedec=hideandcipher.OTPDecryption(diseasemessagehiden,diseaseek);
            //                 String treatmentdec=hideandcipher.OTPDecryption(treatmentmessagehiden,treatmentek);
            //                  out.println("<div class=\"row\">"
            //                 + "<div class=\"col-sm-12 \" style=\"padding:10px;margin-top:5px;background-color:white;\">\n"
            //                 + "<div class=\"col-sm-1\">"+ids+"</div>"
            //                 + " <div class=\"col-sm-2\">"+cover+"</div>"
            //                 + " <div class=\"col-sm-4\">"+diseasedec+"</div>"
            //                 + " <div class=\"col-sm-3\">"+treatmentdec+"</div>"
            //                 + " <div class=\"col-sm-2\">"+resultset.getString("DateDone")+"</div>"+
            //                 "  </div></div>");
            //             }
            //             //out.println("</div>");                        
            //             if(ids==0){
            //                 out.println("<h4 style=\"text-align:center;color:red;\">No Patient Found</h4>");
            //             }
            //         break;
            //         default:
            //             out.println("<h4 style=\"text-align:center;color:red;\">Invalid Search Option Selected</h4>");
            //         break;
            //     }
            //     resultset.close();
            //     statement.close();
            //     connection.close();

            //     }  catch (SQLException ex) {
            //         out.print("SqlExceptions: " +ex.getMessage());
            //         //error code 1146-table doesn't exists
            //     }
            // break;
            case "NewPatientForm"://10
                    out.print("<div class=\"col-sm-12\" style=\"padding: 5px;min-width: 300px;\" id=\"\">\n"+
                            "<div class=\"well\" style=\"padding: 5px;\">\n"+                  
                                "<div style=\"margin: 2px;padding: 8px;min-width: 210px;margin-left: 0;\">\n"+
                                        "<form role=\"form\" name=\"savepatientdetailsform\" id=\"savepatientdetailsform\" class=\"form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n"+
                                            "<div class=\"form-group has-feedback\">\n"+
                                                "<div class=\"col-sm-4\">\n"+
                                                    "<input type=\"text\" name=\"patientfullname\" id=\"patientfullname\" placeholder=\"Patient Fullname\" class=\"form-control\" required=\"required\">\n"+
                                                    "<span class=\"glyphicon glyphicon-user form-control-feedback\"></span>\n"+
                                                "</div>\n"+
                                                "<div class=\"col-sm-3\">\n"+ 
                                                  "<input type=\"number\" name=\"patientage\" id=\"patientage\" class=\"form-control\" placeholder=\"Age\" max=\"200\" min=\"1\" required=\"required\">\n"+
                                                    "<span class=\"glyphicon glyphicon-edit form-control-feedback\"></span>\n"+
                                                "</div>\n"+
                                                "<div class=\"col-sm-5\">\n"+ 
                                                    "<textarea name=\"contactinfo\" id=\"contactinfo\"  class=\"form-control\" placeholder=\"Patient Contact information Phone number or address\" ></textarea>\n"+
                                                    "<span class=\"glyphicon glyphicon-phone form-control-feedback\"></span>\n"+
                                                "</div>\n"+
                                            "</div>\n"+
                                            
                                          "<div class=\"form-group has-feedback\">\n"+
                                            "<div class=\"col-sm-4\">\n"+ 
                                             "<input type=\"text\" name=\"patientregion\" id=\"patientregion\" class=\"form-control\" placeholder=\"Specify Patient Region\"  required=\"required\">\n"+
                                            "<span class=\"glyphicon glyphicon-edit form-control-feedback\"></span>\n"+
                                            "</div>\n"+
                                            "<div class=\"col-sm-4\">\n"+
                                                "<input type=\"text\" name=\"placeofbirth\" id=\"placeofbirth\" class=\"form-control\" placeholder=\"Enter Patient Place of Birth\" required=\"required\">\n"+
                                            "<span class=\"glyphicon glyphicon-edit form-control-feedback\"></span>\n"+
                                            "</div>\n"+
                                            "<div class=\"col-sm-4\">\n"+
                                                "<select name=\"patientcountry\" id=\"patientcountry\" class=\"select form-control\" required=\"\"><option value=\"\">Select Patient Country</option>\n"+
                                                "<option value=\"Kenya\">Kenya</option>\n"+
                                                "<option value=\"Uganda\">Uganda</option>\n"+
                                                "</select>\n"+
                                            "</div>\n"+
                                        "</div>\n"+
                                        
                                        "<div class=\"form-group has-feedback\">\n"+
                                        "<button class=\"pull-right btn btn-success btn-large\">Save New Patient Details</button>\n"+
                                        "<div id=\"savepatientresult\"></div></div>\n" +
                                        "</div>\n"+
                                        "</form>\n"+
                                "</div>\n"+
                            "</div>\n"+  
                        "</div>");
                break;
            case "AddNewPatientInformation"://11
                connection= dbConnection.getConnection();
                pno=request.getParameter("pno");
                delfullname=request.getParameter("delfullname");
                try{
                    statement= connection.createStatement();
                    resultset=statement.executeQuery("SELECT * FROM patients where Pno='"+pno+"' and Groups LIKE '%"+sesuserrole+"%' order by Pno desc");
                    int ids=0;
                    out.println("<div id=\"searchedpatientrecord\">");
                    while(resultset.next()){
                        ids++;
                        String covertext=resultset.getString("Fullname");
                        out.println(" <div class=\"\">\n" +
                        " <div class=\"\" style=\"margin-top:0;padding:top-0;margin: 2px;padding: 2px;min-width: 210px;margin-left: 0;\">\n" +
                        "<form role=\"form\" name=\"addpatientrecordform\" id=\"addpatientrecordform\" class=\"form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                        "<input type=\"hidden\" value=\""+resultset.getInt("Pno")+"\" name=\"recordpno\" id=\"recordpno\">\n" +
                        "<input type=\"hidden\" value=\""+covertext+"\" name=\"recordcovertext\" id=\"recordcovertext\" placeholder=\"0.00\" class=\"form-control\" disabled required=\"required\" >\n"+
                        "<div class=\"form-group has-feedback\" style=\"margin-top:0;padding-top:0;padding: 2px;min-width: 210px;margin-left: 0;background-color:white;\">\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Fullname</label>\n" +
                        "<label class=\"col-sm-2 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("Fullname")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Age</label>\n" +
                        " <label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("Age")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">BirthPlace</label>\n" +
                        " <label class=\"col-sm-2 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("BirthPlace")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Cover</label>\n" +
                        "<label class=\"col-sm-3 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+covertext+"</label>\n" +
                        "</div>"+

                        "<div class=\" col-sm-5 \" style=\"background-color:white;\">"+
                        "<div class=\"form-group has-feedback\">\n" +
                        "<div class=\"col-sm-10\">\n" +
                        "<input type=\"date\" title=\"Date Done\" name=\"recorddatedone\" id=\"recorddatedone\" placeholder=\"Enter Patient Fullname\" class=\"form-control\" required=\"required\">\n" +
                        "</div>\n" +
                        " </div>\n" +
                        "<div class=\"form-group has-feedback\">\n" +
                        "<div class=\"col-sm-12\"><textarea name=\"patientrecord\" id=\"patientrecord\" placeholder=\"Enter Post Message\"></textarea></div>\n"+
                        "</div>\n" +
                        "<div class=\"form-group has-feedback\" id=\"saverecordresultpatient\"></div>\n"+
                        "<div class=\"form-group has-feedback\">\n" +
                        "<button type=\"button\" class=\"pull-right btn btn-success btn-large savenewpatientrecordbtn\">Save Patient Record</button>\n" +
                        "</div>\n" +
                        "</div>\n"+
                        "<div class=\"col-sm-7 \" style=\"background-color:;\">"
                        + "<div id=\"viewresultpatientrecords\" style=\"\">");
                        out.println(getPatienttreatmentRecords(pno));
                        out.println("</div></form>\n" +
                        "</div>\n" +
                        "</div>");
                    }
                out.println("</div>");
                statement.close();
                connection.close();

                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "AddSharePatientInformation"://12
                connection= dbConnection.getConnection();
                pno=request.getParameter("pno");
                // delfullname=request.getParameter("delfullname");
                try{
                    statement= connection.createStatement();
                    resultset=statement.executeQuery("SELECT * FROM patients where Pno='"+pno+"' and Groups LIKE '%"+sesuserrole+"%' order by Pno desc");
                    int ids=0;
                    out.println("<div id=\"searchedpatientrecord\">");
                    while(resultset.next()){
                        ids++;
                        String covertext=resultset.getString("Fullname");
                        out.println(" <div class=\"\">\n" +
                        " <div class=\"\" style=\"margin-top:0;padding:top-0;margin: 2px;padding: 5px;min-width: 210px;margin-left: 0;\">\n" +
                        "<form role=\"form\" name=\"addpatientrecordform\" id=\"addpatientrecordform\" class=\"form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                        "<input type=\"hidden\" value=\""+resultset.getInt("Pno")+"\" name=\"recordpno\" id=\"recordpno\">\n" +
                        "<input type=\"hidden\" value=\""+covertext+"\" name=\"recordcovertext\" id=\"recordcovertext\" placeholder=\"0.00\" class=\"form-control\" disabled required=\"required\" >\n"+
                        "<div class=\"form-group has-feedback\" style=\"margin-top:0;padding-top:0;padding: 8px;min-width: 210px;margin-left: 0;background-color:white;\">\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Fullname</label>\n" +
                        "<label class=\"col-sm-2 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("Fullname")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Age</label>\n" +
                        " <label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("Age")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">BirthPlace</label>\n" +
                        " <label class=\"col-sm-2 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("BirthPlace")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Cover</label>\n" +
                        "<label class=\"col-sm-3 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+covertext+"</label>\n" +
                        "</div>"+
                        "<div class=\" col-sm-5 \" style=\"background-color:white;\">"+
                        "<h4 style=\"text-align:center;\">User Groups to Share</h4>\n"+
                        "<div id=\"viewresultpatientrecords\" style=\"margin-left:1%;\">");
                       out.println(getGroupstoShare(pno,sesuserrole,resultset.getString("Groups")));
                        out.println("</div>\n" +
                        "</div>\n"+
                        "<div class=\"col-sm-6 \" style=\"background-color:grey;\">"
                        + "<div id=\"viewresultpatientrecords\" style=\"margin-left:1%;\">");
                        out.println(getSharedGroups(pno,sesuserrole));
                        out.println("</div></form>\n" +
                        "</div>\n" +
                        "</div>");
                    }
                out.println("</div>");
                statement.close();
                connection.close();

                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "NewPatientInformation"://13
                connection= dbConnection.getConnection();
                pno=request.getParameter("pno");
                delfullname=request.getParameter("delfullname");
                try{
                    statement= connection.createStatement();
                    resultset=statement.executeQuery("SELECT * FROM patients where Pno='"+pno+"' and Groups LIKE '%"+sesuserrole+"%' order by Pno desc");
                    int ids=0;
                    out.println("<div id=\"searchedpatientrecord\" style=\"padding:0px;\">");
                    while(resultset.next()){
                        ids++;
                        String covertext=resultset.getString("Fullname");
                        out.println(" <div class=\"\" style=\"margin:0px;\">\n" +
                        " <div class=\"\" style=\"margin-top:0;padding:top-0;margin: 2px;padding: 8px;min-width: 210px;margin-left: 0;\">\n" +
                        "<form role=\"form\" name=\"addpatientrecordform\" id=\"addpatientrecordform\" class=\"form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                        "<input type=\"hidden\" value=\""+resultset.getInt("Pno")+"\" name=\"recordpno\" id=\"recordpno\">\n" +
                        "<input type=\"hidden\" value=\""+covertext+"\" name=\"recordcovertext\" id=\"recordcovertext\" placeholder=\"0.00\" class=\"form-control\" disabled required=\"required\" >\n"+
                        "<div class=\"form-group has-feedback\" style=\"margin-top:0;padding-top:0;padding: 8px;min-width: 210px;margin-left: 0;background-color:white;\">\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Fullname</label>\n" +
                        "<label class=\"col-sm-2 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("Fullname")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Age</label>\n" +
                        " <label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("Age")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">BirthPlace</label>\n" +
                        " <label class=\"col-sm-2 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("BirthPlace")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Cover</label>\n" +
                        "<label class=\"col-sm-3 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+covertext+"</label>\n" +
                        "</div>"+
                        "<div class=\" col-sm-5 \" style=\"background-color:white;\">"+
                        "<div class=\"form-group has-feedback\">\n" +
                        "<div class=\"col-sm-10\">\n" +
                        "<input type=\"date\" title=\"Date Done\" name=\"recorddatedone\" id=\"recorddatedone\" placeholder=\"Enter Patient Fullname\" class=\"form-control\" required=\"required\">\n" +
                        "</div>\n" +
                        " </div>\n" +
                        "<div class=\"form-group has-feedback\">\n" +
                        "<div class=\"col-sm-12\"><textarea name=\"patientrecord\" id=\"patientrecord\" placeholder=\"Enter New Record\"></textarea></div>\n"+
                        "</div>\n" +
                        "<div class=\"form-group has-feedback\" id=\"saverecordresultpatient\"></div>\n"+
                        "<div class=\"form-group has-feedback\">\n" +
                        "<button class=\"pull-right btn btn-success btn-large\">Save Patient Treatment Record</button>\n" +
                        "</div>\n" +
                        "</div>\n"+
                        "<div class=\"col-sm-7 \" style=\"background-color:;\">"
                        + "<div id=\"viewresultpatientrecords\" style=\"\">");
                        out.println(getPatienttreatmentRecords(pno));
                        out.println("</div></form>\n" +
                        "</div>\n" +
                        "</div>");
                    }
                out.println("</div>");
                statement.close();
                connection.close();

                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "NewSendPatientInformation"://14
                connection= dbConnection.getConnection();
                pno=request.getParameter("pno");
                delfullname=request.getParameter("delfullname");
                try{
                    statement= connection.createStatement();
                    resultset=statement.executeQuery("SELECT * FROM patients where Pno='"+pno+"' and Groups LIKE '%"+sesuserrole+"%' order by Pno desc");
                    int ids=0;
                    while(resultset.next()){
                        ids++;
                        String covertext=resultset.getString("Fullname");
                        out.println(" <div class=\"\">\n" +
                        " <div class=\"\" style=\"margin-top:0;padding:top-0;margin: 2px;padding: 8px;min-width: 210px;margin-left: 0;\">\n" +
                        "<form role=\"form\" name=\"addpatientrecordform\" id=\"addpatientrecordform\" class=\"form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                        "<input type=\"hidden\" value=\""+resultset.getInt("Pno")+"\" name=\"recordpno\" id=\"recordpno\">\n" +
                        "<input type=\"hidden\" value=\""+covertext+"\" name=\"recordcovertext\" id=\"recordcovertext\" placeholder=\"0.00\" class=\"form-control\" disabled required=\"required\" >\n"+
                        "<div class=\"form-group has-feedback\" style=\"margin-top:0;padding-top:0;padding: 8px;min-width: 210px;margin-left: 0;background-color:white;\">\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Fullname</label>\n" +
                        "<label class=\"col-sm-2 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("Fullname")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Age</label>\n" +
                        " <label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("Age")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">BirthPlace</label>\n" +
                        " <label class=\"col-sm-2 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("BirthPlace")+"</label>\n" +
                        "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Cover</label>\n" +
                        "<label class=\"col-sm-3 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+covertext+"</label>\n" +
                        "</div>"+
                        "<div class=\"col-sm-12 \" style=\"background-color:grey;\">"
                        + "<div id=\"viewresultpatientrecords\" style=\"\">");
                        out.println(getPatienttreatmentRecords(pno));
                        out.println("</div></div></form>\n" +
                        "</div>\n" +
                        "</div>");
                    }
                statement.close();
                connection.close();

                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "PatientInformation"://15
                out.print("<form role=\"form\" class=\"form-horizontal\" name=\"searchpatientdetailsform\" id=\"searchpatientdetailsform\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">");
                    out.println("<div class=\"row\">\n" +
                    "   <div class=\"col-sm-12\" >\n" +
                    "   <div class=\"form-group has-feedback\" >\n" +
                    "   <div class=\"col-sm-3\" style=\"padding:0;background-color:grey;\">\n" +
                    "   <input type=\"\" name=\"searchpatientitemtorecord\" id=\"searchpatientitemtorecord\" class=\" form-control\" placeholder=\"Enter Patient Name \" style=\"color:#73C4FF;\">\n" +
                    "   <span class=\"glyphicon glyphicon-search form-control-feedback\"></span>\n" +
                    "   <div class=\"row\">\n" +
                    "   <div class=\"col-sm-12\" style=\"\" >\n" +
                    "   <div id=\"viewpatienttorecord\" style=\"padding:0;\"><h4>No Patient Searched</h4>\n" +
                    "   </div>\n"+
                    "   </div>\n"+
                    "   </div>\n"+
                    "   </div>\n" +
                    "   <div class=\"col-sm-9\" style=\"padding:0;background-color:;\">\n" +
                    "   <div class=\"row\">\n" +
                    "   <div class=\"col-sm-12\" style=\"\" >\n" +
                    "   <div id=\"viewrecordpatients\" style=\"padding:0;\"><h4>Please Search Patient name then Click to display Information</h4>\n" +
                    "   </div>\n"+
                    "   </div>\n"+
                    "   </div>\n"+
                    "   </div>\n" +
                    "   </div>" + 
                    "   </div>\n" +
                    "   </div>\n");
            break;
            case "PatientInformationShared"://16
                out.print("<form role=\"form\" class=\"form-horizontal\" name=\"searchpatientdetailsform\" id=\"searchpatientdetailsform\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">");
                    out.println("<div class=\"row\">\n" +
                    "   <div class=\"col-sm-12\" >\n" +
                    "   <div class=\"form-group has-feedback\" >\n" +
                    "   <div class=\"col-sm-5\" style=\"padding:0;background-color:grey;\">\n" +
                    "   <input type=\"\" name=\"searchpatientitemtoshare\" id=\"searchpatientitemtoshare\" class=\" form-control\" placeholder=\"Enter Patient Name \" style=\"color:#73C4FF;\">\n" +
                    "   <span class=\"glyphicon glyphicon-search form-control-feedback\"></span>\n" +
                    "   <div class=\"row\">\n" +
                    "   <div class=\"col-sm-12\" style=\"\" >\n" +
                    "   <div id=\"viewpatienttoshare\" style=\"padding:0;\"><h4>No Patient Searched</h4>\n" +
                    "   </div>\n"+
                    "   </div>\n"+
                    "   </div>\n"+
                    "   </div>\n" +
                    "   <div class=\"col-sm-7\" style=\"padding:0;background-color:;\">\n" +
                    "   <div class=\"row\">\n" +
                    "   <div class=\"col-sm-12\" style=\"\" >\n" +
                    "   <div id=\"viewsharepatients\" style=\"padding:0;\"><h4>Please Search Patient name then Click to display Information</h4>\n" +

                    "   </div>\n"+
                    "   </div>\n"+
                    "   </div>\n"+
                    "   </div>\n" +

                    "   </div>" + 
                    "   </div>\n" +
                    "   </div>\n");
            break;
            case "ViewPatientRecords"://17
                pno=request.getParameter("pno");
                out.println(getPatienttreatmentRecords(pno));
            break;
            case "PatientInfoTreatmentRecords":
                pno=request.getParameter("pno");
                out.println(getPatienttreatmentRecords(pno));
            break;
            case "ViewEditPatient"://18
                connection= dbConnection.getConnection();
                pno=request.getParameter("pno");
                delfullname=request.getParameter("delfullname");
                try{
                    statement= connection.createStatement();
                    resultset=statement.executeQuery("SELECT * FROM patients where Pno='"+pno+"' and Groups LIKE '%"+sesuserrole+"%' order by Pno desc");
                    int ids=0;
                    out.println("<div id=\"searchedpatientrecord\">");
                    while(resultset.next()){
                        ids++;
                        out.println(" <div class=\"well\">\n" +
                        "<div class=\"\" style=\"margin: 2px;padding: 8px;min-width: 210px;margin-left: 0\">\n" +
                        "<form role=\"form\" name=\"updatepatientdetailsform\" id=\"updatepatientdetailsform\" class=\"form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                        "<input type=\"hidden\" value=\""+resultset.getInt("Pno")+"\" name=\"updatepno\" id=\"updatepno\">\n" +
                        "<div class=\"form-group has-feedback\">\n" +
                        "<label class=\"col-sm-3 control-label\">Update Fullname</label>\n" +
                        "<div class=\"col-sm-9\">\n" +
                        "<input type=\"text\" value=\""+resultset.getString("Fullname")+"\" name=\"updatefullname\" id=\"updatefullname\" placeholder=\"Enter Patient Fullname\" class=\"form-control\" required=\"required\">\n" +
                        "<span class=\"glyphicon glyphicon-user form-control-feedback\"></span>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "<div class=\"form-group has-feedback\">\n" +
                        "<label class=\"col-sm-3 control-label\">Update Age</label>\n" +
                        "<div class=\"col-sm-9\"> \n" +
                        "<input type=\"number\" value=\""+resultset.getString("Age")+"\" name=\"updatepatientage\" id=\"updatepatientage\" class=\"form-control\" placeholder=\"xxx\" max=\"300\" min=\"1\" required=\"required\">\n" +
                        "<span class=\"glyphicon glyphicon-edit form-control-feedback\"></span>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "<div class=\"form-group has-feedback\">\n" +
                        "<label class=\"col-sm-3 control-label\">Update Contact Info</label>\n" +
                        "<div class=\"col-sm-9\"> \n" +
                        "<textarea name=\"updatecontactinfo\" id=\"updatecontactinfo\"  class=\"form-control\" placeholder=\"Enter Patient Contact information Phone number or address\" >"+resultset.getString("Contact")+"</textarea>\n" +
                        "<span class=\"glyphicon glyphicon-phone form-control-feedback\"></span>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "<div class=\"form-group has-feedback\">\n" +
                        "<label class=\"col-sm-3 control-label\">Update Region</label>\n" +
                        "<div class=\"col-sm-9\"> \n" +
                        "<input type=\"text\" value=\""+resultset.getString("Region")+"\" name=\"updatepatientregion\" id=\"updatepatientregion\" class=\"form-control\" placeholder=\"Specify Patient Region\"   required=\"required\">\n" +
                        "<span class=\"glyphicon glyphicon-edit form-control-feedback\"></span>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "<div class=\"form-group has-feedback\">\n" +
                        "<label class=\"col-sm-3 control-label\">Update Place of Birth</label>\n" +
                        "<div class=\"col-sm-9\">\n" +
                        "<input type=\"text\" value=\""+resultset.getString("BirthPlace")+"\" name=\"updateplaceofbirth\" id=\"updateplaceofbirth\" class=\"form-control\" placeholder=\"Enter Patient Place of Birth\"   required=\"required\">\n" +
                        "<span class=\"glyphicon glyphicon-edit form-control-feedback\"></span>\n" +
                        "</div>\n" +
                        "</div>\n" +
                        "<div class=\"form-group has-feedback\">\n" +
                        "<label class=\"col-sm-3 control-label\">Update Country</label>\n" +
                        "<div class=\"col-sm-9\">\n" +
                        "<input type=\"text\" value=\""+resultset.getString("Country")+"\" name=\"updatepatientcountry\" id=\"updatepatientcountry\" class=\"form-control\" placeholder=\"Enter Patient Country\"   required=\"required\">\n" +                                
                        "</div>\n" +
                        "</div>\n" +
                        "<div class=\"form-group has-feedback\">\n" +
                        "<button class=\"pull-right btn btn-success btn-large\">Update Patient Details</button>\n" +
                        "<div id=\"updateresultpatient\"></div></div>\n" +
                        "</form>\n" +
                        "</div>\n" +
                        "</div>");
                    }
                out.println("</div>");
                statement.close();
                connection.close();

                }  catch (SQLException ex) {
                //System.out.println("SqlExceptions: " +ex.getMessage());
                out.print("Error:"+ex.getMessage());
                //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "DeletePatient"://19
                connection= dbConnection.getConnection();
                pno=request.getParameter("pno");
                delfullname=request.getParameter("delfullname");
                try{
                    statement= connection.createStatement();
                    String sql="DELETE FROM patients where Pno='"+pno+"' and Groups LIKE '%"+sesuserrole+"%'";
                    int result=statement.executeUpdate(sql);
                    if(result==1){
                        out.print("<h4 style=\"text-align:center;\">Deleting Patient "+delfullname+" Details Done</h4>");
                    }
                    else
                    {
                        out.print("<h4 style=\"text-align:center;color:red;\">Failed to Delete Patient "+delfullname+" Details</h4>");
                    }
                    statement.close();
                    connection.close();
                     
                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "DeletePatientRecord"://20
                connection= dbConnection.getConnection();
                pno=request.getParameter("pno");
                String pino=request.getParameter("pino");
                String cover=request.getParameter("cover");
                try{
                    statement= connection.createStatement();
                    String sql="DELETE FROM patientsinfo where PIno='"+pino+"' and Medic='"+loggedid+"'";
                    int result=statement.executeUpdate(sql);
                    if(result==1){
                        out.print("<h4 style=\"text-align:center;\">Deleting Patient "+cover+" Record Done</h4>");
                    }
                    else
                    {
                        out.print("<h4 style=\"text-align:center;color:red;\">Failed to Delete Patient "+cover+" Record</h4>");
                    }
                    statement.close();
                    connection.close();
                     
                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "UpdatePatient"://21
                connection= dbConnection.getConnection();
                pno=request.getParameter("pno");
                patientage=request.getParameter("patientage");
                    fullname=request.getParameter("fullname");
                    patientregion=request.getParameter("patientregion");
                    patientcountry=request.getParameter("patientcountry");
                    contactinfo=request.getParameter("contactinfo");
                    placeofbirth=request.getParameter("placeofbirth");
                    if(patientage.equals("")){
                        out.print("Patient age should not be Empty");
                    }
                    else if (fullname.equals("")){
                        out.print("Enter at least One Name");
                    }
                    else if (patientregion.equals("")){
                        out.print("Patient Region Should Not be Empty");
                    }
                    else if (patientcountry.equals("")){
                        out.print("Patient Country Should Not be Empty");
                    }
                    else if (placeofbirth.equals("")){
                        out.print("Place ofBirth Should Not be Blank");
                    }
                    else{
                        try{
                            statement= connection.createStatement();
                            String sql="UPDATE patients SET Fullname='"+fullname+"',Age='"+patientage+"',Region='"+patientregion+"',Contact='"+contactinfo+"',BirthPlace='"+placeofbirth+"',Country='"+patientcountry+"' where Pno='"+pno+"'";
                            int result=statement.executeUpdate(sql);
                            if(result==1){
                                out.print("<h4 style=\"text-align:center;\">Updating Patient "+delfullname+" Details Done</h4>");
                            }
                            else
                            {
                                out.print("<h4 style=\"text-align:center;color:red;\">Failed to Update Patient "+delfullname+" Details</h4>");
                            }
                            statement.close();
                            connection.close();

                        }  catch (SQLException ex) {
                            //System.out.println("SqlExceptions: " +ex.getMessage());
                            out.print("Error:"+ex.getMessage());
                            //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                            Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                        }
                }
            break;
            case "AddNewRoleForm"://Role 22
                out.println("<div class=\"row\" style=\"min-width:200px;\" >\n"+
                "<div class=\"col-sm-5\">\n"+
                    "<form role=\"form\" name=\"addnewroleform\" id=\"addnewroleform\" class=\"form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n"+
                        "<div class=\"form-group has-feedback\">\n"+
                            "<label class=\"col-sm-3 control-label\">Role Name</label>\n"+
                            "<div class=\"col-sm-9\">\n"+
                                "<input class=\"form-control\" type=\"text\" name=\"rolename\" id=\"rolename\" placeholder=\"RoleName (WithoutSpace)\" required=\"required\">\n"+
                                "<span class=\"glyphicon glyphicon-edit form-control-feedback\"></span>\n"+ 
                            "</div>\n"+
                        "</div>\n"+
                        "<div class=\"form-group has-feedback\">\n"+
                            "<label class=\"col-sm-3 control-label\">Role Type</label>\n"+
                            "<div class=\"col-sm-9\"> \n"+
                                "<select name=\"roletype\" id=\"roletype\" class=\"select form-control\" required=\"required\">\n"+
                                    "<option value=\"\">Choose Role Type...</option>\n"+
                                    "<option value=\"Views\">Viewing</option>\n"+
                                    "<option value=\"Saves\">Saving</option>\n"+
                                    "<option value=\"Deletes\">Deleting</option>\n"+
                                    "<option value=\"Updates\">Updating</option>\n"+
                                "</select>\n"+
                             "</div>\n"+
                         "</div>\n"+
                         "<div class=\"form-group has-feedback\">\n"+
                            "<label class=\"col-sm-3 control-label\">Description</label>\n"+
                            "<div class=\"col-sm-9\">\n"+
                                "<textarea class=\"form-control\" name=\"description\" id=\"description\" placeholder=\"Description\" ></textarea>\n"+
                                    "<span class=\"glyphicon glyphicon-edit form-control-feedback\"></span>\n"+
                            "</div>\n"+
                        "</div>\n"+
                        "<div class=\"form-group has-feedback\">\n"+
                            "<button  class=\"btn btn-success btn-large pull-right\" style=\"margin:5px;\" name=\"submitnewlabbtn\" id=\"submitnewlabbtn\">Save Role Information</button>\n"+
                        "</div>\n"+
                        "<div class=\"form-group has-feedback\" id=\"addnewroleresult\">\n"+
                            
                        "</div>\n"+
                    "</form>\n"+
                "</div>\n"+
                "<div class=\"col-sm-7\">\n"+
                    "<div class=\"col-sm-12\"  style=\"padding:10px;background-color:#05DBFF;\">\n"+
                            "<div class=\"col-sm-1\" style=\"text-align:left;padding:0;\">Sno </div>\n"+
                            "<div class=\"col-sm-4\" style=\"text-align:left;padding:0;\">Role</div>\n"+
                            "<div class=\"col-sm-1\" style=\"text-align:left;padding:0;\">Type</div>\n"+
                            "<div class=\"col-sm-5\" style=\"text-align:left;padding:0;\">Description</div>\n"+
                    "</div>\n"+
                    "<div class=\"col-sm-12 form-group has-feedback\" id=\"viewallrolesdisplay\" style=\"overflow-y:auto;max-height:500px;\"></div>\n"+
                "</div>\n"+
                "</div>");
            break;
            case "ViewRoles"://Role 22
                connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    resultset=statement.executeQuery("SELECT * FROM roles order by Rid desc");
                    int sno=1;
                    while(resultset.next()){
                        out.println("<div class=\"row\"><div class=\"col-sm-12\"  style=\"padding:10px;background-color:white;\">\n"+
                                "<div class=\"col-sm-1\" style=\"text-align:left;padding:0;\">"+sno+"</div>\n"+
                                "<div class=\"col-sm-4\" style=\"text-align:left;padding:0;\">"+resultset.getString("Role")+"</div>\n"+
                                "<div class=\"col-sm-1\" style=\"text-align:left;padding:0;\">"+resultset.getString("Type")+"</div>\n"+
                                "<div class=\"col-sm-5\" style=\"text-align:left;padding:0;\">"+resultset.getString("Description")+"</div>\n"+
                            "</div></div>");
                        sno++;
                    }
                    resultset.close();
                    statement.close();
                    connection.close();
                }  catch (SQLException ex) {
                    out.println("SqlExceptions: " +ex.getMessage());
                    //error code 1146-table doesn't exists
                } 
            break;
            case "AddNewRole"://Role 23
                String rolename=request.getParameter("rolename");
                String roletype=request.getParameter("roletype");
                String description=request.getParameter("description");
                if(rolename.equals("")){
                    out.print("Role Name should not be Empty");
                }
                else if (roletype.equals("")){
                    out.print("Role Type Should Not be Empty");
                }
                 else if (description.equals(" ")){
                    out.print("Role Description Should Not be Empty");
                }
                else{
                    connection= dbConnection.getConnection();
                    try{
                        statement= connection.createStatement();
                        String sql="INSERT INTO roles(`Role`, `Type`,Description) VALUES('"+rolename+"','"+roletype+"','"+description+"')";
                        int result=statement.executeUpdate(sql);
                        if(result==1){
                            out.print("Inserted New Role Details successfully");
                        }
                        else
                        {
                            out.print("Failed to insert New Role Details");
                        }
                        statement.close();
                        connection.close();
                    }  catch (SQLException ex) {
                        //System.out.println("SqlExceptions: " +ex.getMessage());
                        out.print("Error:"+ex.getMessage());
                        //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                        Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                 }
            break;
            case "AddNewUserRoleForm"://Role 24
                out.println("<div class=\"row\" style=\"min-width:200px;\" >\n"+
                    "<div class=\"col-sm-5\">\n"+
                    "<form role=\"form\" name=\"addnewuserroleform\" id=\"addnewuserroleform\" class=\"form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n"+
                    "<input type=\"hidden\" name=\"submit\" id=\"submit\" class=\"form-control\" value=\"AddNewUserRole\"  required=\"required\">\n"+
                        "<div class=\"form-group has-feedback\" style=\"margin:5px;\">\n"+
                            "<label class=\"col-sm-3 control-label\">User Role</label>\n"+
                            "<div class=\"col-sm-9\">\n"+
                                "<input class=\"form-control\" type=\"text\" name=\"userrolename\" id=\"userrolename\" placeholder=\"Userrole Name\" required=\"required\">\n"+
                                "<span class=\"glyphicon glyphicon-edit form-control-feedback\"></span>\n"+ 
                            "</div>\n"+
                        "</div>\n"+
                         "<div class=\"form-group has-feedback\" style=\"margin:5px;\">\n"+
                            "<label class=\"col-sm-3 control-label\">Description</label>\n"+
                            "<div class=\"col-sm-9\">\n"+
                                "<textarea class=\"form-control\" name=\"description\" id=\"description\" placeholder=\"Description\" ></textarea>\n"+
                                    "<span class=\"glyphicon glyphicon-edit form-control-feedback\"></span>\n"+
                            "</div>\n"+
                        "</div>\n"+
                        
                        "<div style=\"overflow-y:auto;max-height:400px;padding:2px;\">\n"+
                        "<h5 style=\"text-align:center;color:green;font-weight:bold;\">1.Select Save Roles and Functions</h5>\n");
                        connection= dbConnection.getConnection();
                        try{
                            statement= connection.createStatement();
                            resultset=statement.executeQuery("SELECT * FROM roles WHERE Type='Saves'");
                            int sno=1; 
                            while(resultset.next()){
                                String roles=resultset.getString("Rid")+"%"+resultset.getString("Role");
                                out.println("<div class=\"row\" >\n"+
                                        "<div class=\"col-sm-12 saverolesel\" style=\"padding:1px;margin:1px;background-color:#05DBFF;\" data-id1=\"savesrole"+sno+"\">\n"+
                                        "<label class=\"col-sm-12\" style=\"font-size:11px;margin:1px;\">\n"+
                                        "<div class=\"col-sm-2\" style=\"padding:2px;\"><div class=\"col-sm-6\" style=\"padding:0;\"><input type=\"checkbox\" class=\"savesrole\"  name=\"savesrole[]\" id=\"savesrole"+sno+"\" value=\""+roles+"\"></div><div class=\"col-sm-6\" style=\"padding:2px;\"> "+sno+"</div></div>\n"+ 
                                        "<div class=\"col-sm-4\">"+resultset.getString("Role")+"</div>\n"+ 
                                        "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                        "</label>\n"+
                                        "</div>\n"+
                                    "</div>\n");
                                sno++;
                            }
                            resultset.close();
                            statement.close();
                            connection.close();
                        }  catch (SQLException ex) {
                            out.println("SqlExceptions: " +ex.getMessage());
                            //error code 1146-table doesn't exists
                        } 
                        out.println("<h5 style=\"text-align:center;color:blue;font-weight:bold;\">2.Select View Roles and Functions</h5>");
                        connection= dbConnection.getConnection();
                        try{
                            statement= connection.createStatement();
                            resultset=statement.executeQuery("SELECT * FROM roles WHERE Type='Views'");
                            int sno=1; 
                            while(resultset.next()){
                                String roles=resultset.getString("Rid")+"%"+resultset.getString("Role");
                                out.println("<div class=\"row\" >\n"+
                                        "<div class=\"col-sm-12 viewrolesel\" style=\"padding:1px;margin:1px;background-color:#05DBFF;\" data-id1=\"viewsrole"+sno+"\">\n"+
                                        "<label class=\"col-sm-12\" style=\"font-size:11px;margin:1px;\">\n"+
                                        "<div class=\"col-sm-2\" style=\"padding:2px;\"><div class=\"col-sm-6\" style=\"padding:0;\"><input type=\"checkbox\" class=\"viewsrole\" name=\"viewsrole[]\" id=\"viewsrole"+sno+"\" value=\""+roles+"\"></div><div class=\"col-sm-6\" style=\"padding:2px;\"> "+sno+"</div></div>\n"+ 
                                        "<div class=\"col-sm-4\">"+resultset.getString("Role")+"</div>\n"+ 
                                        "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                        "</label>\n"+
                                        "</div>\n"+
                                    "</div>\n");
                                sno++;
                            }
                            resultset.close();
                            statement.close();
                            connection.close();
                        }  catch (SQLException ex) {
                            out.println("SqlExceptions: " +ex.getMessage());
                            //error code 1146-table doesn't exists
                        } 

                        out.println("<h5 style=\"text-align:center;color:orange;font-weight:bold;\">3.Select Update Roles and Functions</h5>");
                        connection= dbConnection.getConnection();
                        try{
                            statement= connection.createStatement();
                            resultset=statement.executeQuery("SELECT * FROM roles WHERE Type='Updates'");
                            int sno=1; 
                            while(resultset.next()){
                                String roles=resultset.getString("Rid")+"%"+resultset.getString("Role");
                                out.println("<div class=\"row\" >\n"+
                                        "<div class=\"col-sm-12 updaterolesel\" style=\"padding:1px;margin:1px;background-color:#05DBFF;\" data-id1=\"updatesrole"+sno+"\">\n"+
                                        "<label class=\"col-sm-12\" style=\"font-size:11px;margin:1px;\">\n"+
                                        "<div class=\"col-sm-2\" style=\"padding:2px;\"><div class=\"col-sm-6\" style=\"padding:0;\"><input type=\"checkbox\" class=\"updatesrole\" name=\"updatesrole[]\" id=\"updatesrole"+sno+"\" value=\""+roles+"\"></div><div class=\"col-sm-6\" style=\"padding:2px;\"> "+sno+"</div></div>\n"+ 
                                        "<div class=\"col-sm-4\">"+resultset.getString("Role")+"</div>\n"+ 
                                        "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                        "</label>\n"+
                                        "</div>\n"+
                                    "</div>\n");
                                sno++;
                            }
                            resultset.close();
                            statement.close();
                            connection.close();
                        }  catch (SQLException ex) {
                            out.println("SqlExceptions: " +ex.getMessage());
                            //error code 1146-table doesn't exists
                        } 

                        out.println("<h5 style=\"text-align:center;color:red;font-weight:bold;\">4.Select Delete Roles and Functions</h5>");
                        connection= dbConnection.getConnection();
                        try{
                            statement= connection.createStatement();
                            resultset=statement.executeQuery("SELECT * FROM roles WHERE Type='Deletes'");
                            int sno=1; 
                            while(resultset.next()){
                                String roles=resultset.getString("Rid")+"%"+resultset.getString("Role");
                                out.println("<div class=\"row\" >\n"+
                                        "<div class=\"col-sm-12 deleterolesel\" style=\"padding:1px;margin:1px;background-color:#05DBFF;\" data-id1=\"deletesrole"+sno+"\">\n"+
                                        "<label class=\"col-sm-12\" style=\"font-size:11px;margin:1px;\">\n"+
                                        "<div class=\"col-sm-2\" style=\"padding:2px;\"><div class=\"col-sm-6\" style=\"padding:0;\"><input type=\"checkbox\" class=\"deletesrole\" name=\"deletesrole[]\" id=\"deletesrole"+sno+"\" value=\""+roles+"\"></div><div class=\"col-sm-6\" style=\"padding:2px;\"> "+sno+"</div></div>\n"+ 
                                        "<div class=\"col-sm-4\">"+resultset.getString("Role")+"</div>\n"+ 
                                        "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                        "</label>\n"+
                                        "</div>\n"+
                                    "</div>\n");
                                sno++;
                            }
                            resultset.close();
                            statement.close();
                            connection.close();
                        }  catch (SQLException ex) {
                            out.println("SqlExceptions: " +ex.getMessage());
                            //error code 1146-table doesn't exists
                        }

                        out.println("</div>");
                        out.println("<div class=\"form-group has-feedback\" style=\"padding:0;margin:1px;\">\n"+
                            "<button  class=\"btn btn-success btn-large pull-right\" style=\"margin:5px;\" name=\"submitnewlabbtn\" id=\"submitnewlabbtn\"  >Save UserRole Information</button>\n"+
                        "</div>\n"+
                        "<div class=\"form-group has-feedback\" style=\"padding:0;margin:1px;\" id=\"addnewuserroleresult\"></div>\n"+
                    "</form>\n"+
                "</div>\n"+
                "<div class=\"col-sm-7\">\n"+
                    "<div class=\"col-sm-12\"  style=\"padding:10px;background-color:#05DBFF;\">\n"+
                            "<div class=\"col-sm-1\">Sno </div>\n"+
                            "<div class=\"col-sm-2\">UserRole</div>\n"+
                            "<div class=\"col-sm-1\">Roles</div>\n"+
                            "<div class=\"col-sm-4\">Description</div>\n"+
                            "<div class=\"col-sm-4\">Actions</div>\n"+
                    "</div>\n"+
                    "<div class=\"form-group has-feedback\" id=\"viewalluserrolesdisplay\"></div>\n"+
                "</div>\n"+
                "</div>\n");
            break;
            case "LoadUserRoles"://Role 25
                connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    resultset=statement.executeQuery("SELECT * FROM `userroles` group by UserRole ");
                    out.println("<option value=\"\">Choose Userrole</option>"); 
                    while(resultset.next()){
                        out.println("<option value=\""+resultset.getString("UserRole")+"\">"+resultset.getString("UserRole")+"</option>"); 
                    }
                    resultset.close();
                    statement.close();
                    connection.close();
                }  catch (SQLException ex) {
                    out.println("SqlExceptions: " +ex.getMessage());
                    //error code 1146-table doesn't exists
                }
            break;
            case "ViewUserRoles"://Role 26
                connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    resultset=statement.executeQuery("SELECT * FROM `userroles` group by UserRole ");
                    int sno=1;
                    while(resultset.next()){
                        String userrol=resultset.getString("UserRole");
                        stmt= connection.createStatement();
                        rs=stmt.executeQuery("SELECT * from userroles WHERE UserRole='"+userrol+"' group by URid");
                        int role=0;
                        while(rs.next()){
                            role++;
                        }
                        out.println("<div class=\"col-sm-12\"  style=\"padding:10px;\">\n"+
                                "<div class=\"col-sm-1\">"+sno+"</div>\n"+
                                "<div class=\"col-sm-2\">"+resultset.getString("UserRole")+"</div>\n"+
                                "<div class=\"col-sm-1\">"+role+"</div>\n"+
                                "<div class=\"col-sm-4\">"+resultset.getString("Description")+"</div>\n"+
                                "<div class=\"col-sm-4\" style=\"padding:0;\">\n"+
                                "<button class=\"btn btn-small btn-warning btn-large userroleinfo\" id=\"userroleinfo\" style=\"margin-top:0;margin: 1px;padding:5px;\" data-id1=\""+resultset.getString("URid")+"\" data-id2=\""+resultset.getString("UserRole")+"\" data-id3=\""+resultset.getString("Description")+"\" title=\"View Userroles Info\"><span class=\"glyphicon glyphicon-info-sign\" ></span></button>\n"+

                                "<button class=\"btn btn-small btn-warning btn-large removeuserrole\" id=\"removeuserrole\" style=\"margin-top:0;margin: 1px;padding:5px;\" data-id1=\""+resultset.getString("URid")+"\" data-id2=\""+resultset.getString("UserRole")+"\" data-id3=\""+resultset.getString("Description")+"\" title=\"Issue Userrole\"><span class=\"glyphicon glyphicon-minus\" ></span></button>\n"+

                                "<button class=\"btn btn-small btn-warning btn-large addusersrole\" id=\"addusersrole\" style=\"margin-top:0;margin: 1px;padding:5px;\" data-id1=\""+resultset.getString("URid")+"\" data-id2=\""+resultset.getString("UserRole")+"\" data-id3=\""+resultset.getString("Description")+"\" title=\"Add Role to Userrole\"><span class=\"glyphicon glyphicon-plus\" ></span></button>\n"+

                                    "<button class=\"btn btn-small btn-danger btn-large deleteuserrole\" id=\"deleteuserrole\" style=\"margin-top:0;margin: 1px;padding:5px;\" data-id1=\""+resultset.getString("URid")+"\" data-id2=\""+resultset.getString("UserRole")+"\" data-id3=\""+resultset.getString("Description")+"\" title=\"Delete Userrole\"><span class=\"glyphicon glyphicon-remove\" ></span></button>\n"+
                                    "</div>\n"+
                            "</div>");
                        sno++;
                        // out.println("<option value=\""+resultset.getString("UserRole")+"\">"+resultset.getString("UserRole")+"</option>"); 
                    }
                    resultset.close();
                    statement.close();
                    connection.close();
                }  catch (SQLException ex) {
                    out.println("SqlExceptions: " +ex.getMessage());
                    //error code 1146-table doesn't exists
                }
            break;
            case "AddNewUserRole"://Role 27
                String userrolename=request.getParameter("userrolename");
                String descriptions=request.getParameter("description");
                String savesrole=request.getParameter("saveroles");
                String viewsrole=request.getParameter("viewroles");
                String updatesrole=request.getParameter("updateroles");
                String deletesrole=request.getParameter("deleteroles");
                // out.println("String Saves"+savesrole.split(",").length);
                int savessel=savesrole.split(",").length;
                int viewssel=viewsrole.split(",").length;
                int updatessel=updatesrole.split(",").length;
                int deletessel=deletesrole.split(",").length;
                String saves[]=savesrole.split(",");
                String views[]=viewsrole.split(",");
                String updates[]=updatesrole.split(",");
                String deletes[]=deletesrole.split(",");
                //save saves
                if (savesrole.equals("Non")) {
                    out.println("No Save Role Selected or Added<br>");
                }
                else{
                    for(int i=0;i<savessel;i++){
                        String yoursaves[]=saves[i].split("%");
                        String role=yoursaves[0];
                        rolename=yoursaves[1];
                        String uniq=role+" "+userrolename;
                        connection= dbConnection.getConnection();
                        try{
                           statement= connection.createStatement();
                           String sql="INSERT INTO userroles(UserRole,Role,Description,UniqueId) VALUES('"+userrolename+"','"+role+"','"+descriptions+"','"+uniq+"')";
                           int result=statement.executeUpdate(sql);
                           if(result==1){
                               out.println("Created New UserRole Details successfully::"+rolename+"<br>");
                           }
                           else
                           {
                               out.println("Failed to Create New UserRole Details::"+rolename+"<br>");
                           }
                           statement.close();
                           connection.close();
                       }  catch (SQLException ex) {
                           //System.out.println("SqlExceptions: " +ex.getMessage());
                           out.print("Error:"+ex.getMessage()+"<br>");
                           //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                           Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    }
                }
                //save views
                if (viewsrole.equals("Non")) {
                    out.println("No View Role Selected or Added<br>");
                }
                else{
                    for(int i=0;i<viewssel;i++){
                        String yourviews[]=views[i].split("%");
                        String role=yourviews[0];
                        rolename=yourviews[1];
                        String uniq=role+" "+userrolename;
                        connection= dbConnection.getConnection();
                        try{
                           statement= connection.createStatement();
                           String sql="INSERT INTO userroles(UserRole,Role,Description,UniqueId) VALUES('"+userrolename+"','"+role+"','"+descriptions+"','"+uniq+"')";
                           int result=statement.executeUpdate(sql);
                           if(result==1){
                               out.println("Created New UserRole Details successfully::"+rolename+"<br>");
                           }
                           else
                           {
                               out.println("Failed to Create New UserRole Details::"+rolename+"<br>");
                           }
                           statement.close();
                           connection.close();
                       }  catch (SQLException ex) {
                           //System.out.println("SqlExceptions: " +ex.getMessage()+"<br>");
                           out.print("Error:"+ex.getMessage()+"<br>");
                           //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                           Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    }
                }
                //save updates
                if (updatesrole.equals("Non")) {
                    out.println("No Update Role Selected or Added<br>");
                }
                else{
                    for(int i=0;i<updatessel;i++){
                        String yourupdates[]=updates[i].split("%");
                        String role=yourupdates[0];
                        rolename=yourupdates[1];
                        String uniq=role+" "+userrolename;
                        connection= dbConnection.getConnection();
                        try{
                           statement= connection.createStatement();
                           String sql="INSERT INTO userroles(UserRole,Role,Description,UniqueId) VALUES('"+userrolename+"','"+role+"','"+descriptions+"','"+uniq+"')";
                           int result=statement.executeUpdate(sql);
                           if(result==1){
                               out.println("Created New UserRole Details successfully::"+rolename+"<br>");
                           }
                           else
                           {
                               out.println("Failed to Create New UserRole Details::"+rolename+"<br>");
                           }
                           statement.close();
                           connection.close();
                       }  catch (SQLException ex) {
                           //System.out.println("SqlExceptions: " +ex.getMessage()+"<br>");
                           out.print("Error:"+ex.getMessage()+"<br>");
                           //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                           Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    }
                }
                //save delete
                if (deletesrole.equals("Non")) {
                    out.println("No Delete Role Selected or Added<br>");
                }
                else{
                    for(int i=0;i<deletessel;i++){
                        String yourdeletes[]=deletes[i].split("%");
                        String role=yourdeletes[0];
                        rolename=yourdeletes[1];
                        String uniq=role+" "+userrolename;
                        connection= dbConnection.getConnection();
                        try{
                           statement= connection.createStatement();
                           String sql="INSERT INTO userroles(UserRole,Role,Description,UniqueId) VALUES('"+userrolename+"','"+role+"','"+descriptions+"','"+uniq+"')";
                           int result=statement.executeUpdate(sql);
                           if(result==1){
                               out.println("Created New UserRole Details successfully::"+rolename+"<br>");
                           }
                           else
                           {
                               out.println("Failed to Create New UserRole Details::"+rolename+"<br>");
                           }
                           statement.close();
                           connection.close();
                       }  catch (SQLException ex) {
                           //System.out.println("SqlExceptions: " +ex.getMessage()+"<br>");
                           out.print("Error:"+ex.getMessage()+"<br>");
                           //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                           Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                       }
                    }
                }
               
            break;
            case "PatientInfo"://28
                    out.print("<form role=\"form\" class=\"form-horizontal\" name=\"searchpatientdetailsform\" id=\"searchpatientdetailsform\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">");
                    out.println(""
                    + "<div class=\"row\">\n" +
                    " <div class=\"col-sm-12\" style=\"background-color:#77B5ED;\" >\n" 
                    + "<div class=\"form-group has-feedback\" style=\"padding-top:10px;\">\n" +
                    "   <div class=\"col-sm-3\">\n" +
                    "   <select name=\"searchfield\" id=\"searchfield\" class=\"select form-control\" required=\"required\">\n" +
                    "   <option value=\"\">Choose What to Search...</option>\n" +
                    "   <option value=\"Fullname\" Selected>Patient Fullname</option>\n" +
                    "   <option value=\"Age\">Patient Age</option>\n" +
                    "   <option value=\"VisitDate\">Patient Visit Date</option>\n" +
                    "   <option value=\"BirthPlace\">Patient Birth Place</option>\n" +
                    "   </select>\n" +
                    "   </div>\n" +
                    
                    "   <div class=\"col-sm-4\">\n" +
                    "   <input type=\"\" name=\"searchitem\" id=\"searchitem\" class=\" form-control\" placeholder=\"Enter Patient(s) to search\" style=\"color:#73C4FF;\">\n" +
                    "   <span class=\"glyphicon glyphicon-search form-control-feedback\"></span>\n" +
                    "   </div>\n" +
                    "   <div class=\"col-sm-1 col-xs-2\">\n" +
                    "   <button  class=\"btn btn-small btn-primary\" name=\"searchitemstmtbtn\" id=\"searchitemstmtbtn\" > <span class=\"glyphicon glyphicon-search \"></span></button>\n" +
                    "   </div>\n" +
                    "   <div class=\"col-sm-1 col-xs-2\">\n" +
                    "   <button  class=\"btn btn-small btn-default newpatientallbtn\" name=\"newpatientallbtn\" id=\"newpatientallbtn\" > <span class=\"fa fa-plus-square\"></span></button>\n" +
                    "   </div>\n" +
                    "   <div class=\"col-sm-1 col-xs-2\">\n" +
                    "   <button  class=\"btn btn-small btn-success newrecordallbtn\" name=\"newrecordallbtn\" id=\"newrecordallbtn\" > <span class=\"fa fa-hospital-o\"></span></button>\n" +
                    "   </div>\n" +
                    "   <div class=\"col-sm-1 col-xs-2\">\n" +
                    "   <button  class=\"btn btn-small btn-warning sharerecordall\" name=\"sharerecordall\" id=\"sharerecordall\" > <span class=\"glyphicon glyphicon-share \"></span></button>\n" +
                    "   </div>\n" +
                    "   </div>"
                    + "</div>\n" +
                    "  </div>\n" +
                    "<table class=\"table ttpatientinfo table-hover\" ><thead>"
                    + "<tr style=\"\">\n"
                    + "<th>Sno </th>"
                    + " <th>FullName</th>"
                    + " <th>Age</th>"
                    + " <th>Country</th>"
                    + " <th>Birth Place</th>"
                    + " <th>Contact</th>"
                    + " <th>Actions</th>\n" +
                    "   </tr></thead><tbody id=\"searchedpatientrecord\">");
                    connection= dbConnection.getConnection();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM patients Where Groups LIKE '%"+sesuserrole+"%' order by Pno desc");
                        int ids=1; 
                        while(resultset.next()){
                            out.println("<tr>"
                            + "<td>"+ids+"</td>"
                            + " <td>"+resultset.getString("Fullname")+"</td>"
                            + " <td>"+resultset.getString("Age")+"</td>"
                            + " <td>"+resultset.getString("Country")+"</td>"
                            + " <td>"+resultset.getString("BirthPlace")+"</td>"
                            + " <td>"+resultset.getString("Contact")+"</td>"
                            + " <td style=\"padding:0;\">"+
                                "<button type=\"button\" name=\"newrecords\" class=\"newrecords btn btn-small btn-success\" title=\"Create New Patient Information Record\" data-id1=\""+resultset.getInt("Pno")+"\" data-id11=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-plus-sign\"></span></button>" +
                                "<button type=\"button\" name=\"update\" class=\"update btn btn-small btn-warning\" title=\"View and Update Patient Records\" data-id1=\""+resultset.getInt("Pno")+"\" data-id2=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-edit\"></span></button>" +
                                "<button type=\"button\" name=\"sendrecords\" class=\"sendrecords btn btn-small btn-primary\" title=\"View and Send Patient Records\" data-id1=\""+resultset.getInt("Pno")+"\" data-id11=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-share\"></span></button>" +
                                "<button type=\"button\" name=\"delete\" class=\"delete btn btn-small btn-danger\" data-id1=\""+resultset.getInt("Pno")+"\" data-id2=\""+resultset.getString("Fullname")+"\" style=\"font-size:11px;padding:5px;margin:1px;\"><span class=\"glyphicon glyphicon-remove\"></span></button>" +
                        "  </td></tr>");
                        ids++;
                        }
                        out.println("</tbody></table>");
                    resultset.close();
                    statement.close();
                    connection.close();

                    }  catch (SQLException ex) {
                        out.print("SqlExceptions: " +ex.getMessage());
                         //error code 1146-table doesn't exists
                    } 
                    out.println("</form>");
                break;
            case "ViewRoleForm"://Role 29
                String userroleid=request.getParameter("userroleid");
                String userrole=request.getParameter("userrole");
                
                out.println("<div class=\"row\" style=\"min-width:200px;\" >\n"+
                "<div class=\"col-sm-12\">\n"+
                    "<form role=\"form\" name=\"removeroleform\" id=\"removeroleform\" class=\"form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n"+
                        "<div style=\"overflow-y:auto;max-height:500px;\">");

                            connection= dbConnection.getConnection();
                            try{
                                statement= connection.createStatement();
                                resultset=statement.executeQuery("SELECT userroles.URid,userroles.UserRole,userroles.Role as URole,roles.Rid,roles.Role,roles.Description,roles.Type from userroles,roles Where UserRole='"+userrole+"' and userroles.Role=roles.Rid and roles.Type='Saves'");
                                out.println("<h5 style=\"text-align:left;color:blue;font-weight:bold;\">1.Save Roles and Functions</h5>");
                                int sno=1; 
                                while(resultset.next()){
                                    String roles=resultset.getString("Rid")+"%"+resultset.getString("Role");
                                    out.println("<div class=\"row\" >\n"+
                                            "<div class=\"col-sm-12\" style=\"padding:1px;margin:1px;\">\n"+
                                            "<div class=\"col-sm-1\" style=\"padding:2px;\"> "+sno+"</div>\n"+ 
                                            "<div class=\"col-sm-5\">"+resultset.getString("Role")+"</div>\n"+ 
                                            "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                            "</div>\n"+
                                        "</div>\n");
                                    sno++;
                                }
                                resultset.close();
                                statement.close();
                                connection.close();
                            }  catch (SQLException ex) {
                                out.println("SqlExceptions: " +ex.getMessage());
                                //error code 1146-table doesn't exists
                            }
                            connection= dbConnection.getConnection();
                            try{
                                statement= connection.createStatement();
                                resultset=statement.executeQuery("SELECT userroles.URid,userroles.UserRole,userroles.Role as URole,roles.Rid,roles.Role,roles.Description,roles.Type from userroles,roles Where UserRole='"+userrole+"' and userroles.Role=roles.Rid and roles.Type='Views'");
                                out.println("<h5 style=\"text-align:left;color:blue;font-weight:bold;\">2.View Roles and Functions</h5>");
                                int sno=1; 
                                while(resultset.next()){
                                    String roles=resultset.getString("Rid")+"%"+resultset.getString("Role");
                                    out.println("<div class=\"row\" >\n"+
                                            "<div class=\"col-sm-12\" style=\"padding:1px;margin:1px;\">\n"+
                                            "<div class=\"col-sm-1\" style=\"padding:2px;\"> "+sno+"</div>\n"+ 
                                            "<div class=\"col-sm-5\">"+resultset.getString("Role")+"</div>\n"+ 
                                            "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                            "</div>\n"+
                                        "</div>\n");
                                    sno++;
                                }
                                resultset.close();
                                statement.close();
                                connection.close();
                            }  catch (SQLException ex) {
                                out.println("SqlExceptions: " +ex.getMessage());
                                //error code 1146-table doesn't exists
                            } 

                            connection= dbConnection.getConnection();
                            try{
                                statement= connection.createStatement();
                                resultset=statement.executeQuery("SELECT userroles.URid,userroles.UserRole,userroles.Role as URole,roles.Rid,roles.Role,roles.Description,roles.Type from userroles,roles Where UserRole='"+userrole+"' and userroles.Role=roles.Rid and roles.Type='Updates'");
                                out.println("<h5 style=\"text-align:left;color:blue;font-weight:bold;\">3.Update Roles and Functions</h5>");
                                int sno=1; 
                                while(resultset.next()){
                                    String roles=resultset.getString("Rid")+"%"+resultset.getString("Role");
                                    out.println("<div class=\"row\" >\n"+
                                            "<div class=\"col-sm-12\" style=\"padding:1px;margin:1px;\">\n"+
                                            "<div class=\"col-sm-1\" style=\"padding:2px;\"> "+sno+"</div>\n"+ 
                                            "<div class=\"col-sm-5\">"+resultset.getString("Role")+"</div>\n"+ 
                                            "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                            "</div>\n"+
                                        "</div>\n");
                                    sno++;
                                }
                                resultset.close();
                                statement.close();
                                connection.close();
                            }  catch (SQLException ex) {
                                out.println("SqlExceptions: " +ex.getMessage());
                                //error code 1146-table doesn't exists
                            }
                            connection= dbConnection.getConnection();
                            try{
                                statement= connection.createStatement();
                                resultset=statement.executeQuery("SELECT userroles.URid,userroles.UserRole,userroles.Role as URole,roles.Rid,roles.Role,roles.Description,roles.Type from userroles,roles Where UserRole='"+userrole+"' and userroles.Role=roles.Rid and roles.Type='Deletes'");
                                out.println("<h5 style=\"text-align:left;color:blue;font-weight:bold;\">4.Delete Roles and Functions</h5>");
                                int sno=1; 
                                while(resultset.next()){
                                    String roles=resultset.getString("Rid")+"%"+resultset.getString("Role");
                                    out.println("<div class=\"row\" >\n"+
                                            "<div class=\"col-sm-12\" style=\"padding:1px;margin:1px;\">\n"+
                                            "<div class=\"col-sm-1\" style=\"padding:2px;\"> "+sno+"</div>\n"+ 
                                            "<div class=\"col-sm-5\">"+resultset.getString("Role")+"</div>\n"+ 
                                            "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                            "</div>\n"+
                                        "</div>\n");
                                    sno++;
                                }
                                resultset.close();
                                statement.close();
                                connection.close();
                            }  catch (SQLException ex) {
                                out.println("SqlExceptions: " +ex.getMessage());
                                //error code 1146-table doesn't exists
                            }
                            
                        out.println("</div>\n"+
                    "</form>\n"+
                "</div>\n"+
                "</div>");
            break;
            case "DeleteUserRole"://Role 30
                connection= dbConnection.getConnection();
                userroleid=request.getParameter("userroleid");
                userrole=request.getParameter("userrole");
                try{
                    statement= connection.createStatement();
                    String sql="DELETE FROM userroles where UserRole='"+userrole+"'";
                    int result=statement.executeUpdate(sql);
                    if(result>=1){
                        out.print("<h4 style=\"text-align:center;\">Deleting UserRole "+userrole+" Details Done</h4>");
                    }
                    else
                    {
                        out.print("<h4 style=\"text-align:center;color:red;\">Failed to Delete UserRole "+userrole+" Details</h4>");
                    }
                    statement.close();
                    connection.close();
                     
                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "RemoveRoleForm"://Role 31
                userroleid=request.getParameter("userroleid");
                userrole=request.getParameter("userrole");
                out.println("<div class=\"row\" style=\"min-width:200px;\" >\n"+
                "<div class=\"col-sm-12\">\n"+
                    "<form role=\"form\" name=\"removeroleform\" id=\"removeroleform\" class=\"form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n"+
                    "<input type=\"hidden\" name=\"submit\" id=\"submit\" class=\"form-control\" value=\"RemoveRole\"  required=\"required\">\n"+
                    "<input type=\"hidden\" name=\"userroleid\" id=\"userroleid\" class=\"form-control\" value=\""+userroleid+"\"  required=\"required\">\n"+
                        "<h3>Select Roles to Remove from "+userrole+"</h3>\n"+
                        "<div style=\"overflow-y:auto;max-height:500px;\">\n");

                        int sno=1; 
                        connection= dbConnection.getConnection();
                        try{
                            statement= connection.createStatement();
                            resultset=statement.executeQuery("SELECT userroles.URid,userroles.UserRole,userroles.Role as URole,roles.Rid,roles.Role,roles.Description,roles.Type from userroles,roles Where UserRole='"+userrole+"' and userroles.Role=roles.Rid and roles.Type='Saves'");
                            out.println("<h5 style=\"text-align:center;color:green;font-weight:bold;\">1.Select Save Roles and Functions</h5>");
                            while(resultset.next()){
                                String roles=resultset.getString("Rid")+"%"+resultset.getString("Role")+"%"+userrole;
                                out.println("<div class=\"row\" >\n"+
                                        "<div class=\"col-sm-12 removesel\" style=\"padding:1px;margin:1px;background-color:#05DBFF;\" data-id1=\"removesel"+sno+"\">\n"+
                                        "<label class=\"col-sm-12\" style=\"font-size:11px;margin:1px;\">\n"+
                                        "<div class=\"col-sm-2\" style=\"padding:2px;\"><div class=\"col-sm-6\" style=\"padding:0;\"><input type=\"checkbox\" class=\"removesels\"  name=\"removesel[]\" id=\"removesel"+sno+"\" value=\""+roles+"\"></div><div class=\"col-sm-6\" style=\"padding:2px;\"> "+sno+"</div></div>\n"+ 
                                        "<div class=\"col-sm-4\">"+resultset.getString("Role")+"</div>\n"+ 
                                        "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                        "</label>\n"+
                                        "</div>\n"+
                                    "</div>\n");
                                sno++;
                            }
                            resultset.close();
                            statement.close();
                            connection.close();
                        }  catch (SQLException ex) {
                            out.println("SqlExceptions: " +ex.getMessage());
                            //error code 1146-table doesn't exists
                        } 
                        connection= dbConnection.getConnection();
                        try{
                            statement= connection.createStatement();
                            resultset=statement.executeQuery("SELECT userroles.URid,userroles.UserRole,userroles.Role as URole,roles.Rid,roles.Role,roles.Description,roles.Type from userroles,roles Where UserRole='"+userrole+"' and userroles.Role=roles.Rid and roles.Type='Views'");
                            out.println("<h5 style=\"text-align:center;color:blue;font-weight:bold;\">2.Select View Roles and Functions</h5>");
                            while(resultset.next()){
                                String roles=resultset.getString("Rid")+"%"+resultset.getString("Role")+"%"+userrole;
                                out.println("<div class=\"row\" >\n"+
                                        "<div class=\"col-sm-12 removesel\" style=\"padding:1px;margin:1px;background-color:#05DBFF;\" data-id1=\"removesel"+sno+"\">\n"+
                                        "<label class=\"col-sm-12\" style=\"font-size:11px;margin:1px;\">\n"+
                                        "<div class=\"col-sm-2\" style=\"padding:2px;\"><div class=\"col-sm-6\" style=\"padding:0;\"><input type=\"checkbox\" class=\"removesels\"  name=\"removesel[]\" id=\"removesel"+sno+"\" value=\""+roles+"\"></div><div class=\"col-sm-6\" style=\"padding:2px;\"> "+sno+"</div></div>\n"+ 
                                        "<div class=\"col-sm-4\">"+resultset.getString("Role")+"</div>\n"+ 
                                        "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                        "</label>\n"+
                                        "</div>\n"+
                                    "</div>\n");
                                sno++;
                            }
                            resultset.close();
                            statement.close();
                            connection.close();
                        }  catch (SQLException ex) {
                            out.println("SqlExceptions: " +ex.getMessage());
                            //error code 1146-table doesn't exists
                        } 
                        connection= dbConnection.getConnection();
                        try{
                            statement= connection.createStatement();
                            resultset=statement.executeQuery("SELECT userroles.URid,userroles.UserRole,userroles.Role as URole,roles.Rid,roles.Role,roles.Description,roles.Type from userroles,roles Where UserRole='"+userrole+"' and userroles.Role=roles.Rid and roles.Type='Updates'");
                            out.println("<h5 style=\"text-align:center;color:orange;font-weight:bold;\">3.Select Update Roles and Functions</h5>");
                            while(resultset.next()){
                                String roles=resultset.getString("Rid")+"%"+resultset.getString("Role")+"%"+userrole;
                                out.println("<div class=\"row\" >\n"+
                                        "<div class=\"col-sm-12 removesel\" style=\"padding:1px;margin:1px;background-color:#05DBFF;\" data-id1=\"removesel"+sno+"\">\n"+
                                        "<label class=\"col-sm-12\" style=\"font-size:11px;margin:1px;\">\n"+
                                        "<div class=\"col-sm-2\" style=\"padding:2px;\"><div class=\"col-sm-6\" style=\"padding:0;\"><input type=\"checkbox\" class=\"removesels\"  name=\"removesel[]\" id=\"removesel"+sno+"\" value=\""+roles+"\"></div><div class=\"col-sm-6\" style=\"padding:2px;\"> "+sno+"</div></div>\n"+ 
                                        "<div class=\"col-sm-4\">"+resultset.getString("Role")+"</div>\n"+ 
                                        "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                        "</label>\n"+
                                        "</div>\n"+
                                    "</div>\n");
                                sno++;
                            }
                            resultset.close();
                            statement.close();
                            connection.close();
                        }  catch (SQLException ex) {
                            out.println("SqlExceptions: " +ex.getMessage());
                            //error code 1146-table doesn't exists
                        }
                        connection= dbConnection.getConnection();
                        try{
                            statement= connection.createStatement();
                            resultset=statement.executeQuery("SELECT userroles.URid,userroles.UserRole,userroles.Role as URole,roles.Rid,roles.Role,roles.Description,roles.Type from userroles,roles Where UserRole='"+userrole+"' and userroles.Role=roles.Rid and roles.Type='Deletes'");
                            out.println("<h5 style=\"text-align:center;color:red;font-weight:bold;\">4.Select Delete Roles and Functions</h5>");
                            while(resultset.next()){
                                String roles=resultset.getString("Rid")+"%"+resultset.getString("Role")+"%"+userrole;
                                out.println("<div class=\"row\" >\n"+
                                        "<div class=\"col-sm-12 removesel\" style=\"padding:1px;margin:1px;background-color:#05DBFF;\" data-id1=\"removesel"+sno+"\">\n"+
                                        "<label class=\"col-sm-12\" style=\"font-size:11px;margin:1px;\">\n"+
                                        "<div class=\"col-sm-2\" style=\"padding:2px;\"><div class=\"col-sm-6\" style=\"padding:0;\"><input type=\"checkbox\" class=\"removesels\"  name=\"removesel[]\" id=\"removesel"+sno+"\" value=\""+roles+"\"></div><div class=\"col-sm-6\" style=\"padding:2px;\"> "+sno+"</div></div>\n"+ 
                                        "<div class=\"col-sm-4\">"+resultset.getString("Role")+"</div>\n"+ 
                                        "<div class=\"col-sm-6\">"+resultset.getString("Description")+"</div>\n"+ 
                                        "</label>\n"+
                                        "</div>\n"+
                                    "</div>\n");
                                sno++;
                            }
                            resultset.close();
                            statement.close();
                            connection.close();
                        }  catch (SQLException ex) {
                            out.println("SqlExceptions: " +ex.getMessage());
                            //error code 1146-table doesn't exists
                        }
                        out.println("</div>\n"+
                        "<div class=\"form-group has-feedback\">\n"+
                            "<button  class=\"btn btn-warning btn-large pull-right\" style=\"margin:5px;\" name=\"submitnewlabbtn\" id=\"submitnewlabbtn\"  >Remove Selected Role</button>\n"+
                        "</div>\n"+
                        "<div class=\"form-group has-feedback\" id=\"removeuserroleresult\">\n"+
                            
                        "</div>\n"+
                    "</form>\n"+
                "</div>\n"+
                "</div>\n");
            break;
            case "RemoveRole"://Role 32
                String removesel=request.getParameter("removesel");
                int removessel=removesel.split(",").length;
                String removes[]=removesel.split(",");
                for(int i=0;i<removessel;i++){
                    String yourremoves[]=removes[i].split("%");
                    String role=yourremoves[0];
                    rolename=yourremoves[1];
                    String userroless=yourremoves[2];

                    connection= dbConnection.getConnection();
                    try{
                        statement= connection.createStatement();
                        String sql="DELETE FROM userroles where UserRole='"+userroless+"' and Role='"+role+"'";
                        int result=statement.executeUpdate(sql);
                        if(result>=1){
                            out.print("<h4 style=\"text-align:center;\">You have successifuly Removed Role "+rolename+" :From Userrole "+userroless+"</h4>");
                        }
                        else
                        {
                            out.print("<h4 style=\"text-align:center;color:red;\">Failed to Remove Role "+rolename+" :From UserRole "+userroless+" Details</h4>");
                        }
                        statement.close();
                        connection.close();
                         
                    }  catch (SQLException ex) {
                        //System.out.println("SqlExceptions: " +ex.getMessage());
                        out.print("Error:"+ex.getMessage());
                        //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                        Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            break;
            case "ViewUsers"://Role 33
                out.println("<div class=\"row\" style=\"min-width:200px;\">\n"+
                "<div class=\"col-sm-12\">\n"+
                    "<div class=\"col-sm-12\"  style=\"padding:10px;background-color:#05DBFF;\">\n"+
                            "<div class=\"col-sm-1\">Sno </div>\n"+
                            "<div class=\"col-sm-2\">Fullname</div>\n"+
                            "<div class=\"col-sm-1\">Userrole</div>\n"+
                            "<div class=\"col-sm-1\">Phone</div>\n"+
                            "<div class=\"col-sm-2\">Email</div>\n"+
                            "<div class=\"col-sm-4\">Actions</div>\n"+
                    "</div>\n"+
                    "<div class=\"form-group has-feedback\" id=\"viewallusersdisplay\"></div>\n"+
                "</div>\n"+
                "</div>\n");
            break;
            case "ViewAllUsers"://Role 34
                connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    resultset=statement.executeQuery("SELECT * FROM users");
                    sno=1; 
                    while(resultset.next()){
                        out.println("<div class=\"row\" >\n"+
                                "<div class=\"col-sm-12\" style=\"padding:10px;margin-top:5px;background-color:white;\">\n"+
                                "<div class=\"col-sm-1\"> "+sno+"</div>\n"+ 
                                "<div class=\"col-sm-2\">"+resultset.getString("Fullname")+"</div>\n"+ 
                                "<div class=\"col-sm-1\">"+resultset.getString("Userrole")+"</div>\n"+ 
                                "<div class=\"col-sm-1\">"+resultset.getString("Phone")+"</div>\n"+ 
                                "<div class=\"col-sm-2\">"+resultset.getString("Email")+"</div>\n"+
                                "<div class=\"col-sm-4\">\n"+
                                    "<button class=\"btn btn-small btn-success btn-large userinfo\" id=\"userinfo\" style=\"margin-top:0;margin: 1px;\" data-id1=\""+resultset.getString("id")+"\" data-id2=\""+resultset.getString("Fullname")+"\" data-id3=\""+resultset.getString("Userrole")+"\" data-id4=\""+resultset.getString("Phone")+"\" data-id5=\""+resultset.getString("Email")+"\" title=\"User Infos\">Info <span class=\"glyphicon glyphicon-info-sign\" ></span></button>\n");
                                    if (resultset.getString("Status").equals("Disabled")) {
                                        out.println("<button class=\"btn btn-small btn-success btn-large activateuser\" id=\"activateuser\" style=\"margin-top:0;margin: 1px;\" data-id1=\""+resultset.getString("id")+"\" data-id2=\""+resultset.getString("Fullname")+"\" data-id3=\""+resultset.getString("Userrole")+"\" data-id4=\""+resultset.getString("Phone")+"\" data-id5=\""+resultset.getString("Email")+"\" title=\"Activate User\">Activate <span class=\"glyphicon glyphicon-eye-open\" ></span></button>\n"); 
                                    }
                                    else{
                                        out.println("<button class=\"btn btn-small btn-warning btn-large muteuser\" id=\"muteuser\" style=\"margin-top:0;margin: 1px;\" data-id1=\""+resultset.getString("id")+"\" data-id2=\""+resultset.getString("Fullname")+"\" data-id3=\""+resultset.getString("Userrole")+"\" data-id4=\""+resultset.getString("Phone")+"\" data-id5=\""+resultset.getString("Email")+"\" title=\"Deactivate/Mute User\">Mute <span class=\"glyphicon glyphicon-eye-close\" ></span></button>\n"); 
                                    }
                                      out.println("<button class=\"btn btn-small btn-danger btn-large deleteuser\" id=\"deleteuser\" style=\"margin-top:0;margin: 1px;\" data-id1=\""+resultset.getString("id")+"\" data-id2=\""+resultset.getString("Fullname")+"\" data-id3=\""+resultset.getString("Userrole")+"\" data-id4=\""+resultset.getString("Phone")+"\" data-id5=\""+resultset.getString("Email")+"\" title=\"Delete User\">Delete <span class=\"glyphicon glyphicon-remove\" ></span></button>\n");  
                                out.println("</div>\n"+
                            "</div></div>\n");
                        sno++;
                    }
                    resultset.close();
                    statement.close();
                    connection.close();
                }  catch (SQLException ex) {
                    out.println("SqlExceptions: " +ex.getMessage());
                    //error code 1146-table doesn't exists
                }
            break;
            case "ViewUserInfo"://Role 35
                String id=request.getParameter("id");
                String fullname=request.getParameter("fullname");
                String userroles=request.getParameter("userroles");
                String phone=request.getParameter("phone");
                String email=request.getParameter("email");
                out.println("<div class=\"row\" style=\"min-width:200px;\" >\n"+
                    "<div class=\"form-group has-feedback\">\n"+
                        "<label class=\"col-sm-3 control-label\">Name: "+fullname+"</label>\n"+
                        "<label class=\"col-sm-2 control-label\">UserRole: "+userroles+"</label>\n"+
                        "<label class=\"col-sm-3 control-label\">Phone: "+phone+"</label>\n"+
                        "<label class=\"col-sm-3 control-label\">Email: "+email+"</label>\n"+
                    "</div>\n"+
                "</div>\n");
            break;
            case "DeleteUser"://Role 36
                id=request.getParameter("id");
                fullname=request.getParameter("fullname");
                userroles=request.getParameter("userroles");
                email=request.getParameter("email");
                connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    String sql="DELETE FROM users where id='"+id+"'";
                    int result=statement.executeUpdate(sql);
                    if(result>=1){
                        out.print("<h4 style=\"text-align:center;\">You have successifuly Deleted Role "+fullname+" :From Userrole "+userroles+"</h4>");
                    }
                    else
                    {
                        out.print("<h4 style=\"text-align:center;color:red;\">Failed to Delete Role "+fullname+" :From UserRole "+userroles+" Details</h4>");
                    }
                    statement.close();
                    connection.close();
                     
                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "MuteUser"://Role 37
                id=request.getParameter("id");
                fullname=request.getParameter("fullname");
                userroles=request.getParameter("userroles");
                email=request.getParameter("email");
                connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    String sql="UPDATE users SET Status='Disabled' where id='"+id+"'";
                    int result=statement.executeUpdate(sql);
                    if(result==1){
                        out.print("<h4 style=\"text-align:center;\">You have successifuly Deactivated:<br>1.User "+fullname+" </h4>");
                    }
                    else
                    {
                        out.print("<h4 style=\"text-align:center;color:red;\">Error Deactivating User "+fullname+" </h4>");
                    }
                    statement.close();
                    connection.close();

                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "ActivateUser"://Role 38
                id=request.getParameter("id");
                fullname=request.getParameter("fullname");
                userroles=request.getParameter("userroles");
                email=request.getParameter("email");
                connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    String sql="UPDATE users SET Status='Active' where id='"+id+"'";
                    int result=statement.executeUpdate(sql);
                    if(result==1){
                        out.print("<h4 style=\"text-align:center;\">You have successifuly Activated:<br>1.User "+fullname+" </h4>");
                    }
                    else
                    {
                        out.print("<h4 style=\"text-align:center;color:red;\">Error Activating User "+fullname+" </h4>");
                    }
                    statement.close();
                    connection.close();

                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "AddSharedGroup"://Role 39
                pno=request.getParameter("pno");
                userrole=request.getParameter("userrole");
                String sharedgroups=request.getParameter("sharedgroups");
                String newgrpsshare=sharedgroups+","+userrole;
                connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    String sql="UPDATE patients SET Groups='"+newgrpsshare+"' where Pno='"+pno+"'";
                    int result=statement.executeUpdate(sql);
                    if(result==1){
                        out.print("You have successifuly Shared To: 1.Userrole "+userrole);
                    }
                    else
                    {
                        out.print("Error Sharing Patient to Userrole "+userrole);
                    }
                    statement.close();
                    connection.close();

                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "UpdatePersonalInfo"://Role 40
                String updateuserfullname=request.getParameter("updateuserfullname");
                String updateusername=request.getParameter("updateusername");
                String userid=request.getParameter("userid");
                String userphone=request.getParameter("updateuserphone");
                String useremail=request.getParameter("updateuseremail");

                connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    String sql="UPDATE `users` SET `Fullname`='"+updateuserfullname+"', `Username`='"+updateusername+"', `Phone`='"+userphone+"', `Email`='"+useremail+"' where id='"+userid+"'";
                    int result=statement.executeUpdate(sql);
                    if(result==1){
                        out.print("You have successifuly Updated Your Information ");
                    }
                    else
                    {
                        out.print("Error Updating Your Information");
                    }
                    statement.close();
                    connection.close();

                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "ChangePassword"://Role 41
                String previous=request.getParameter("previous");
                String newpassword=request.getParameter("newpassword");

                connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    String sql="UPDATE `users` SET `Password`='"+newpassword+"' where id='"+loggedid+"' and Password='"+previous+"'";
                    int result=statement.executeUpdate(sql);
                    if(result==1){
                        out.print("You have successifuly Changed Your Password ");
                    }
                    else
                    {
                        out.print("Error Changing Your Password");
                    }
                    statement.close();
                    connection.close();

                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "RemoveSharedGroup"://Role 42
                pno=request.getParameter("pno");
                String userroleremove=request.getParameter("userrole");
                String sharedgroupsremove=request.getParameter("sharedgroups");
                String newgrpsshareremove="";
                int sharedgrps=sharedgroupsremove.split(",").length;
                String grps[]=sharedgroupsremove.split(",");
                for(int i=0;i<sharedgrps;i++){
                    if (sesuserrole.equals(grps[i]) ) {
                        if (newgrpsshareremove.equals("")) {
                           newgrpsshareremove=sesuserrole;
                        }
                        else{
                            newgrpsshareremove+=newgrpsshareremove+","+grps[i];
                        }
                        
                    }
                    else{
                        if (!grps[i].equals(userroleremove)) {
                            newgrpsshareremove+=newgrpsshareremove+","+grps[i];
                        }
                    }
                }
                connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    String sql="UPDATE patients SET Groups='"+newgrpsshareremove+"' where Pno='"+pno+"'";
                    int result=statement.executeUpdate(sql);
                    if(result==1){
                        out.print("You have successifuly Unshared : 1.Userrole "+userroleremove);
                    }
                    else
                    {
                        out.print("Error Unsharing Userrole "+userroleremove);
                    }
                    statement.close();
                    connection.close();

                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "SendFormPatientRecord"://43
                connection= dbConnection.getConnection();
                pino=request.getParameter("pino");
                pno=request.getParameter("pno");
                cover=request.getParameter("cover");
                String recordnotes=request.getParameter("recordnotes");
                try{
                    statement= connection.createStatement();
                    resultset=statement.executeQuery("SELECT * FROM patients where Pno='"+pno+"' and Groups LIKE '%"+sesuserrole+"%' order by Pno desc");
                    int ids=0;
                    while(resultset.next()){
                        ids++;
                        String covertext=resultset.getString("Fullname");
                        out.println(" <div class=\"\">\n" +
                        " <div class=\"\" style=\"margin-top:0;padding:top-0;margin: 1px;padding: 2px;min-width: 210px;margin-left: 0;\">\n" +
                        "<form role=\"form\" name=\"addpatientrecordform\" id=\"addpatientrecordform\" class=\"form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n" +
                        "<input type=\"hidden\" value=\""+resultset.getInt("Pno")+"\" name=\"recordpno\" id=\"recordpno\">\n" +
                        "<input type=\"hidden\" value=\""+covertext+"\" name=\"recordcovertext\" id=\"recordcovertext\" placeholder=\"0.00\" class=\"form-control\" disabled required=\"required\" >\n"+
                        "<div class=\"form-group has-feedback\" style=\"margin-top:0;padding-top:0;padding: 3px;min-width: 210px;margin-left: 0;background-color:white;\">\n" +
                            "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Fullname</label>\n" +
                            "<label class=\"col-sm-2 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("Fullname")+"</label>\n" +
                            "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Age</label>\n" +
                            " <label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("Age")+"</label>\n" +
                            "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">BirthPlace</label>\n" +
                            " <label class=\"col-sm-2 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+resultset.getString("BirthPlace")+"</label>\n" +
                            "<label class=\"col-sm-1 control-label\" style=\"text-align:left;font-weight:bold;\">Cover</label>\n" +
                            "<label class=\"col-sm-3 control-label\" style=\"text-align:left;font-weight:plain;color:green;\">"+covertext+"</label>\n" +
                        "</div>"+
                        "<div class=\"row\">"+
                        "<div class=\" col-sm-7 \" style=\"background-color:white;\">"+
                            "<div class=\"form-group has-feedback\">\n" +
                            "<h4 style=\"text-align:center;color:black;\">Edit Information Before Sending</h4>\n"+
                            "<div class=\"col-sm-12\" style=\"padding:0;margin:0;\"><textarea name=\"recordnotes\" id=\"recordnotes\">"+recordnotes+"</textarea>\n"+
                            "</div></div>\n" +
                            "   <div class=\"form-group has-feedback\" style=\"padding:0;background-color:grey;\">\n" +
                            "   <input type=\"\" name=\"searchsendreceipient\" id=\"searchsendreceipient\" class=\" form-control\" placeholder=\"Enter User or UserRole \" style=\"color:#73C4FF;\">\n" +
                            "   <span class=\"glyphicon glyphicon-search form-control-feedback\"></span>\n" +
                            "   <div id=\"viewsendreceipient\" style=\"padding:0;\"><h4>No Receipient Searched</h4>\n" +
                            
                            "   </div>\n"+
                            "<div class=\"form-group has-feedback\" id=\"saverecordresultpatientsent\"></div>\n"+
                            
                        "</div></div>\n"+
                        "<div class=\"col-sm-5 \" style=\"background-color:grey;\">"
                        + "<div id=\"viewresultpatientrecordssent\" style=\"margin-left:1%;\">");
                        out.println(getPatientSendRecords(sesfullname));
                        out.println("</div></div></div></form>\n" +
                        "</div></div>");
                    }
                statement.close();
                connection.close();
                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "LoadYourPersonalInformation"://44
                    connection= dbConnection.getConnection();
                try{
                    statement= connection.createStatement();
                    resultset=statement.executeQuery("SELECT * FROM users where id='"+loggedid+"'");
                    while(resultset.next()){
                        out.println("<form role=\"form\" name=\"submitupdateuserinfoform\" id=\"submitupdateuserinfoform\" class=\"panel form-horizontal\" action=\"\" method=\"post\" enctype=\"multipart/form-data\">\n"+
                                "<input type=\"hidden\" name=\"submit\" id=\"submit\" class=\"form-control\" value=\"UpdatePersonalInfo\"  required=\"required\">\n"+
                                "<input type=\"hidden\" name=\"userid\" id=\"userid\" class=\"form-control\"  value=\""+resultset.getString("id")+"\"  required=\"required\">\n"+
                                "<h4 style=\"text-align: center;\">Edit Your Infomation</h4>\n"+
                                "<div class=\"form-group has-feedback\">\n"+
                                  "<label class=\"col-sm-2 control-label\">Username:</label>\n"+
                                  "<div class=\"col-sm-10\"> \n"+
                                "<input type=\"text\" name=\"updateusername\" id=\"updateusername\" class=\"form-control\" placeholder=\"Edit Your Username\" value=\""+resultset.getString("Username")+"\"  required=\"required\">\n"+
                                "<span class=\"glyphicon glyphicon-user form-control-feedback\"></span>\n"+
                                "</div>\n"+
                              "</div>\n"+
                                 "<div class=\"form-group has-feedback\">\n"+
                                  "<label class=\"col-sm-2 control-label\">Fullname:</label>\n"+
                                  "<div class=\"col-sm-10\"> \n"+
                                "<input type=\"text\" name=\"updateuserfullname\" id=\"updateuserfullname\" class=\"form-control\" placeholder=\"Edit Your Fullname\" value=\""+resultset.getString("Fullname")+"\"  required=\"required\">\n"+
                                "<span class=\"glyphicon glyphicon-user form-control-feedback\"></span>\n"+
                                "</div>\n"+
                              "</div>\n"+
                              "<div class=\"form-group has-feedback\">\n"+
                                "<label class=\"col-sm-2 control-label\">Phone</label>\n"+
                                "<div class=\"col-sm-10\"> \n"+
                                  "<input type=\"text\" name=\"updateuserphone\" id=\"updateuserphone\" class=\"form-control\" placeholder=\"+2547xxxxxxxx\" value=\""+resultset.getString("Phone")+"\"  required=\"required\">\n"+
                                "<span class=\"glyphicon glyphicon-phone form-control-feedback\"></span>\n"+
                                "</div>\n"+
                              "</div>\n"+
                              "<div class=\"form-group has-feedback\">\n"+
                                "<label class=\"col-sm-2 control-label\">Email</label>\n"+
                                "<div class=\"col-sm-10\"> \n"+
                                 "<input type=\"email\" name=\"updateuseremail\" id=\"updateuseremail\" class=\"form-control\" placeholder=\"yourname@domain.com\" value=\""+resultset.getString("Email")+"\"  required=\"required\">\n"+
                                "<span class=\"glyphicon glyphicon-edit form-control-feedback\"></span>\n"+
                                "</div>\n"+
                              "</div>\n"+
                            "<div class=\"form-group has-feedback\" id=\"updatepersonalresult\" style=\"text-align:center;\">\n"+
                             "</div>\n"+
                          "<div class=\"form-group has-feedback\">\n"+
                            "<button class=\"btn btn-large btn-success pull-right\" name=\"updateuserinfobtn\" id=\"updateuserinfobtn\" type=\"submit\">Update Personal Information</button>\n"+
                          "</div>\n"+
                                "</form>");
                    }
                    statement.close();
                    connection.close();
                }  catch (SQLException ex) {
                    //System.out.println("SqlExceptions: " +ex.getMessage());
                    out.print("Error:"+ex.getMessage());
                    //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                    Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                }
            break;
            case "LoadYourSentInformation"://45
              out.println(getPatientSendRecords(sesfullname));
            break;
            case "LoadMessageSentInformations"://46
              out.println(getPatientMessageRecordss(sesfullname));
            break;
            case "LoadMessageRecievedInformation"://47
              out.println(getPatientReceivedRecords(sesfullname,sesuserrole));
            break;
            case "AddSendPatientInformation"://48
                pno=request.getParameter("pno");
                String recordnotess=request.getParameter("recordnotess");
                String recepientname=request.getParameter("recepientname");
                String recepientgroup=request.getParameter("recepientgroup");
                String level=request.getParameter("level");
                recordcovertext=sesfullname+" "+recepientname;
                if(recordnotess.equals("")){
                    out.print("Patient Record/notes Information should not be Empty");
                }
                else if (recepientname.equals("")){
                    out.print("Please Click on Available Users/Groups");
                }
                else{
                  HideandCipher  hideandcipher=new HideandCipher();
                    
                    //save recorddiseasekey,recorddiseaseStegKey,recordcovertext
                    /*to decrpty and see message, 
                    use StegSeek(recorddiseaseStegKey,recordcovertext) then seekMessage(StegSeek(recorddiseaseStegKey,recordcovertext))
                    to get encrypted message(recorddiseaseStegMessage), then decrypt
                    OTPDecryption(recorddiseaseStegMessage,recorddiseasekey)
                    */
                    //Treatment encipher and hide using otp and hide data in paragraph steganography 
                    String recordnotesskey = hideandcipher.RandomAlpha(recordnotess.length());
                    String recordnotessenc = hideandcipher.OTPEncryption(recordnotess,recordnotesskey);
                    String recordnotesshide=hideandcipher.hidingMessageBin(recordnotessenc);
                    String recordnotessStegKey=hideandcipher.StegKey(recordnotesshide,recordcovertext);
                    connection= dbConnection.getConnection();
                    try{
                        statement= connection.createStatement();
                        String sql="INSERT INTO messages(`Sender`,Receiver,Message,`DK`,Level)"
                                 + " VALUES('"+sesfullname+"','"+recepientname+"',"
                                 + "'"+recordnotessStegKey+"','"+recordnotesskey+"','"+level+"')";
                        int result=statement.executeUpdate(sql);
                        if(result==1){
                            out.print("Sent Patient Record Information successfully.");
                        }
                        else
                        {
                            out.print("Failed to Send Patient Information");
                        }
                        statement.close();
                        connection.close();
                    }  catch (SQLException ex) {
                        //System.out.println("SqlExceptions: " +ex.getMessage());
                        out.print("Error:"+ex.getMessage());
                        //request.getRequestDispatcher("newdepartment.jsp").include(request, response);
                        Logger.getLogger(medicUser.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            break;
            case "ReportType"://Role 49
                String reporttype=request.getParameter("reporttype");
                switch(reporttype){
                    case "PatientReports":
                        out.println("<option value=\"\">Select Patients</option>\n");
                        connection= dbConnection.getConnection();
                        try{
                            statement= connection.createStatement();
                            resultset=statement.executeQuery("SELECT * FROM patients");
                            sno=1; 
                            while(resultset.next()){
                                out.println("<option value=\""+resultset.getString("pno")+"\">"+resultset.getString("Fullname")+" Age"+resultset.getString("Age")+"</option>\n");
                            }
                            resultset.close();
                            statement.close();
                            connection.close();
                        }  catch (SQLException ex) {
                            out.println("<option value=\"\">SqlExceptions: " +ex.getMessage()+"</option>");
                            //error code 1146-table doesn't exists
                        }
                    break;
                    case "RecordsReports"://50
                        out.println("<option value=\"\">Select Patients Report</option>\n"+
                            "<option value=\"AllPatientsReport\">All Patients Reports</option>\n"+
                            "<option value=\"AllPatientsByYour\">All Patients Records By You</option>");
                    break;
                    case "GroupsReport"://51
                        out.println("<option value=\"\">Select Groups</option>\n");
                        connection= dbConnection.getConnection();
                        try{
                            statement= connection.createStatement();
                            resultset=statement.executeQuery("SELECT * FROM userroles group by UserRole");
                            sno=1; 
                            while(resultset.next()){
                                out.println("<option value=\""+resultset.getString("UserRole")+"\">"+resultset.getString("UserRole")+"</option>\n");
                            }
                            resultset.close();
                            statement.close();
                            connection.close();
                        }  catch (SQLException ex) {
                            out.println("<option value=\"\">SqlExceptions: " +ex.getMessage()+"</option>");
                            //error code 1146-table doesn't exists
                        }
                    break;
                    case "MessagesReports"://51
                        out.println("<option value=\"\">Select Messages</option>\n"+
                            "<option value=\"AllMessages\">All Messages</option>\n"+
                            "<option value=\"ALlSendByYou\">All Messages You Sent</option>\n"+
                            "<option value=\"AllReceived\">All Messages You Received</option>\n"+
                            "<option value=\"AllReceivedByGroup\">All Messages Received by This Group</option>");
                    break;
                    case "UserReports"://52
                        out.println("<option value=\"\">Select From User Reports</option>\n"+
                            "<option value=\"AllUsersReport\">All Users Report</option>\n"+
                            "<option value=\"AllActiveUsersReport\">All Active Users Report</option>\n"+
                            "<option value=\"AllDeactivatedUsersReport\">All Deactivated Users Report</option>\n"+
                            "<option value=\"AllUserRolesReport\">All UserRoles Report</option>");
                    break;
                    default:
                        out.println("<option value=\"\">Report Type Not Defined</option>");
                    break;
                }
            break;
            default:
                out.print("<h4 style=\"text-align:center;color:red;\">Invalid Command "+submit+"</h4>");
            break;
        }
        }
        else{
            out.print("<h4 style=\"text-align:center;color:red;\">You Are Not Authorised to "+submit+"<br>Please Contact Administration for More Information</h4>");
        }
        rolers.close();
        rolestmt.close();
        connection.close();
        }  catch (SQLException ex) {
        out.print("SqlExceptions: " +ex.getMessage());
         //error code 1146-table doesn't exists
        }   
    }
    public String getPatientSendRecords(String sesfullname) {
        String patientrecord="";
        databaseConnection dbConnection=new databaseConnection();
        Connection connection;
        Statement statement=null,stmt=null;
        ResultSet resultset=null,rs=null;
        patientrecord+="<div class=\"row\">"
        + "<div class=\"col-sm-12 \" style=\"padding-top:10px;background-color:#77B5AA;\">\n"
        + " <div class=\"col-sm-8\" style=\"padding:0;text-align:left;\">Notes/Record</div>"
        + " <div class=\"col-sm-4\" style=\"padding:0;text-align:left;\">Receiver</div>\n"+
        "</div>\n" +
        "</div>";
        connection= dbConnection.getConnection();
        try{
            statement= connection.createStatement();
            resultset=statement.executeQuery("SELECT * FROM messages WHERE Sender='"+sesfullname+"' order by Mid desc");
            int ids=1; 
            HideandCipher hideandcipher=new HideandCipher();
            //String recordtreatmentdec=hideandcipher.OTPDecryption(recordtreatmentStegMessage,recordtreatmentkey);
            patientrecord+="<div style=\"overflow-y:auto;max-height:500px;\">"; 
            while(resultset.next()){
                String recordnotes=resultset.getString("Message");
                String cover=resultset.getString("Sender")+" "+resultset.getString("Receiver");
                String recordnotesek=resultset.getString("DK");
                String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                // String treatmentmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(treatment,cover));
                String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                // String treatmentdec=hideandcipher.OTPDecryption(treatmentmessagehiden,treatmentek);
                patientrecord+="<div class=\"\">"
                + "<div class=\"col-sm-12 \" style=\"padding:10px;margin-top:5px;background-color:white;\">\n"
                + " <div class=\"col-sm-8\"  style=\"padding:0;font-size:11px;text-align:left;\">"+ids+"."+recordnotesdec+"</div>"
                + " <div class=\"col-sm-4\"  style=\"padding:0;text-align:left;font-size:10px;\">"+resultset.getString("Receiver")+"</div>"
                + "</div>\n" +
                " </div>";
                ids++;
            }
            patientrecord+="</div>"; 
            resultset.close();
            statement.close();
            connection.close();
        }  catch (SQLException ex) {
            patientrecord+="SqlExceptions: " +ex.getMessage();
            //error code 1146-table doesn't exists
        } 
            
            return patientrecord;
    }
    public String getPatientMessageRecordss(String sesfullname) {
        String patientrecord="";
        databaseConnection dbConnection=new databaseConnection();
        Connection connection;
        Statement statement=null,stmt=null;
        ResultSet resultset=null,rs=null;
        patientrecord+="<div class=\"\">"
        + "<div class=\"col-sm-12 \" style=\"padding:10px;background-color:#77B5EA;\">\n"
        + " <div class=\"col-sm-8\" style=\"padding:0;text-align:left;\">Message Sent</div>"
        + " <div class=\"col-sm-4\" style=\"padding:0;text-align:left;\">Receiver</div>\n"+
        "</div>\n" +
        "</div>";
        connection= dbConnection.getConnection();
        try{
            statement= connection.createStatement();
            resultset=statement.executeQuery("SELECT * FROM messages WHERE Sender='"+sesfullname+"' order by Mid desc");
            int ids=1; 
            HideandCipher hideandcipher=new HideandCipher();
            //String recordtreatmentdec=hideandcipher.OTPDecryption(recordtreatmentStegMessage,recordtreatmentkey);
            patientrecord+="<div style=\"\">"; 
            while(resultset.next()){
                String recordnotes=resultset.getString("Message");
                String cover=resultset.getString("Sender")+" "+resultset.getString("Receiver");
                String recordnotesek=resultset.getString("DK");
                String recorddate=resultset.getString("DateAdded");
                String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                // String treatmentmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(treatment,cover));
                String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                // String treatmentdec=hideandcipher.OTPDecryption(treatmentmessagehiden,treatmentek);
                patientrecord+="<div class=\"\">"
                + "<div class=\"col-sm-12 \" style=\"padding:10px;margin-top:5px;background-color:white;\">\n"
                + " <div class=\"col-sm-8\"  style=\"padding:0;text-align:left;\">"+ids+". <span class=\"text-primary\">"+recorddate+"</span> "+recordnotesdec+"</div>"
                + " <div class=\"col-sm-4\"  style=\"padding:0;text-align:left;\">"+resultset.getString("Receiver")+"</div>"
                + "</div>\n" +
                " </div>";
                ids++;
            }
            patientrecord+="</div>"; 
            resultset.close();
            statement.close();
            connection.close();
        }  catch (SQLException ex) {
            patientrecord+="SqlExceptions: " +ex.getMessage();
            //error code 1146-table doesn't exists
        } 
            
            return patientrecord;
    }
    public String getPatientReceivedRecords(String sesfullname,String sesuserrole) {
        String patientrecord="";
        databaseConnection dbConnection=new databaseConnection();
        Connection connection;
        Statement statement=null,stmt=null;
        ResultSet resultset=null,rs=null;
        patientrecord+="<div class=\"\">"
        + "<div class=\"col-sm-12 \" style=\"padding:10px;background-color:#77B5EA;\">\n"
        + " <div class=\"col-sm-8\" style=\"padding:0;text-align:left;\">Message Sent</div>"
        + " <div class=\"col-sm-4\" style=\"padding:0;text-align:left;\">Sender</div>\n"+
        "</div>\n" +
        "</div>";
        connection= dbConnection.getConnection();
        try{
            statement= connection.createStatement();
            resultset=statement.executeQuery("SELECT * FROM messages WHERE Receiver='"+sesfullname+"' or Receiver='"+sesuserrole+"' order by Mid desc");
            int ids=1; 
            HideandCipher hideandcipher=new HideandCipher();
            //String recordtreatmentdec=hideandcipher.OTPDecryption(recordtreatmentStegMessage,recordtreatmentkey);
            patientrecord+="<div style=\"\">"; 
            while(resultset.next()){
                String recordnotes=resultset.getString("Message");
                String cover=resultset.getString("Sender")+" "+resultset.getString("Receiver");
                String recordnotesek=resultset.getString("DK");
                String recorddate=resultset.getString("DateAdded");
                String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                // String treatmentmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(treatment,cover));
                String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                // String treatmentdec=hideandcipher.OTPDecryption(treatmentmessagehiden,treatmentek);
                patientrecord+="<div class=\"\">"
                + "<div class=\"col-sm-12 \" style=\"padding:10px;margin-top:5px;background-color:white;\">\n"
                + " <div class=\"col-sm-8\"  style=\"padding:0;text-align:left;\">"+ids+". <span class=\"text-primary\">"+recorddate+"</span> "+recordnotesdec+"</div>"
                + " <div class=\"col-sm-4\"  style=\"padding:0;text-align:left;\">"+resultset.getString("Sender")+"</div>"
                + "</div>\n" +
                " </div>";
                ids++;
            }
            patientrecord+="</div>"; 
            resultset.close();
            statement.close();
            connection.close();
        }  catch (SQLException ex) {
            patientrecord+="SqlExceptions: " +ex.getMessage();
            //error code 1146-table doesn't exists
        } 
            
            return patientrecord;
    }
    public String getPatienttreatmentRecords(String pno) {
        String patientrecord="";
        databaseConnection dbConnection=new databaseConnection();
        Connection connection;
        Statement statement=null,stmt=null;
        ResultSet resultset=null,rs=null;
        patientrecord+="<table class=\"table ttpatientrecords table-hover\" ><thead>"
        + "<tr>"
        + " <th>Notes/Record</th>"
        + " <th>User</th>"
        + " <th>Date</th>"
        + " <th>Actions</th>"+
        "</tr>" +
        "</thead><tbody>";
        connection= dbConnection.getConnection();
        try{
            statement= connection.createStatement();
            resultset=statement.executeQuery("SELECT * FROM patientsinfo WHERE Pno='"+pno+"' order by PIno desc");
            int ids=1; 
            HideandCipher hideandcipher=new HideandCipher();
            //String recordtreatmentdec=hideandcipher.OTPDecryption(recordtreatmentStegMessage,recordtreatmentkey);
            while(resultset.next()){
                String userid=resultset.getString("Medic"),username="";
                    stmt= connection.createStatement();
                    rs=stmt.executeQuery("SELECT * FROM users WHERE id='"+userid+"'");
                    while(rs.next()){
                        username=rs.getString("Fullname");
                    }
                String recordnotes=resultset.getString("Record");
                String cover=resultset.getString("Cover");
                String recordnotesek=resultset.getString("DK");
                String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                // String treatmentmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(treatment,cover));
                String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                // String treatmentdec=hideandcipher.OTPDecryption(treatmentmessagehiden,treatmentek);
                patientrecord+="<tr>"
                + " <td>"+recordnotesdec+"</td>"
                + " <td>"+username+"</td>"
                + " <td>"+resultset.getString("DateDone")+"</td>"
                +"<td class=\"col-sm-1\"  style=\"padding:2px;text-align:right;\">"+
                "<button type=\"button\" name=\"deleterecord\" class=\"deleterecord btn btn-large btn-danger\" title=\"Delete Record\" data-id1=\""+resultset.getInt("PIno")+"\" data-id2=\""+resultset.getString("Cover")+"\" data-id3=\""+pno+"\" style=\"font-size:14px;padding:1px;margin:1px;text-align:left;\"><span class=\"glyphicon glyphicon-remove\"></span></button>" +
                "<button type=\"button\" name=\"sendnowrecord\" class=\"sendnowrecord btn btn-large btn-primary\" title=\"View to Send Record\" data-id1=\""+resultset.getInt("PIno")+"\" data-id2=\""+resultset.getString("Cover")+"\" data-id3=\""+pno+"\" data-id4=\""+recordnotesdec+"\" style=\"font-size:14px;padding:1px;margin:1px;text-align:left;\"><span class=\"glyphicon glyphicon-send\"></span></button>" +
                "</td>"
                + "</tr>";
                ids++;
            }
           

            resultset.close();
            statement.close();
            connection.close();
        }  catch (SQLException ex) {
            patientrecord+="SqlExceptions: " +ex.getMessage();
            //error code 1146-table doesn't exists
        } 
           patientrecord+="</tbody></table>";
            return patientrecord;
    }
     public String getSharedGroups(String pno,String sesuserrole) {
        String patientrecord="";
        databaseConnection dbConnection=new databaseConnection();
        Connection connection;
        Statement statement=null,stmt=null;
        ResultSet resultset=null,rs=null;
        patientrecord+="<div class=\"row\">"
        + "<div class=\"col-sm-12 \" style=\"padding-top:10px;background-color:#77B5AA;\">\n"
        + " <div class=\"col-sm-1\" style=\"padding:0;text-align:left\">Sno</div>"
        + " <div class=\"col-sm-8\" style=\"padding:0;text-align:left\">UserGroup</div>"
        + " <div class=\"col-sm-3\" style=\"padding:0;text-align:left\">Actions</div>\n"+
        "</div>\n" +
        "</div>";
        connection= dbConnection.getConnection();
        try{
            statement= connection.createStatement();
            resultset= statement.executeQuery("SELECT * FROM patients WHERE Pno='"+pno+"' order by Pno desc");
            HideandCipher hideandcipher=new HideandCipher();
            //String recordtreatmentdec=hideandcipher.OTPDecryption(recordtreatmentStegMessage,recordtreatmentkey);
            while(resultset.next()){
                int ids=1; 
                String groups=resultset.getString("Groups");
                int sharedgrps=groups.split(",").length;
                String grps[]=groups.split(",");
                for(int i=0;i<sharedgrps;i++){
                    patientrecord+="<div class=\"row\">"
                    + "<div class=\"col-sm-12 \" style=\"padding:10px;margin-top:5px;background-color:white;\">\n"
                    + "<div class=\"col-sm-1\"  style=\"padding:0;font-size:11px;text-align:left\">"+ids+"</div>"
                    + "<div class=\"col-sm-8\"  style=\"padding:0;font-size:10px;text-align:left\">"+grps[i]+"</div>"
                    + "<div class=\"col-sm-1\"  style=\"padding:0;\">";
                    if (i==0){
                        patientrecord+="N/A";
                    }
                    else{
                        patientrecord+="<button type=\"button\" name=\"removegroup\" class=\"removegroup btn btn-large btn-warning\" data-id1=\""+pno+"\" data-id2=\""+grps[i]+"\" data-id3=\""+groups+"\" style=\"font-size:11px;padding:1px;margin:1px;text-align:left;\"><span class=\"glyphicon glyphicon-minus\"></span></button>";
                    }
                    patientrecord+="</div>"
                    + "</div>\n" +
                    " </div>";
                    ids++;
                }
            }
            resultset.close();
            statement.close();
            connection.close();
        }  catch (SQLException ex) {
            patientrecord+="SqlExceptions: " +ex.getMessage();
            //error code 1146-table doesn't exists
        } 
            patientrecord+="</div>"; 
            return patientrecord;
    }
    public String getGroupstoShare(String pno,String sesuserrole,String sharedgroups) {
        String patientrecord="";
        databaseConnection dbConnection=new databaseConnection();
        Connection connection;
        Statement statement=null,stmt=null;
        ResultSet resultset=null,rs=null;
        connection= dbConnection.getConnection();
        try{ 
            statement= connection.createStatement();
            resultset=statement.executeQuery("SELECT * FROM userroles WHERE UserRole NOT IN('"+sharedgroups+"') group by UserRole order by URid desc");
            
            int ids=1; 
            while(resultset.next()){
                if (!sharedgroups.contains(resultset.getString("UserRole"))) {
                    patientrecord+="<div class=\"row\">"
                    + "<div class=\"col-sm-12 \" style=\"padding:10px;margin-top:5px;background-color:white;\">\n"
                    + "<div class=\"col-sm-1\"  style=\"padding:0;font-size:11px;text-align:left\">"+ids+"</div>"
                    + "<div class=\"col-sm-8\"  style=\"padding:0;font-size:10px;text-align:left\">"+resultset.getString("UserRole")+"</div>"
                    + "<div class=\"col-sm-1\"  style=\"padding:0;\">";
                    patientrecord+="<button type=\"button\" name=\"addgroup\" class=\"addgroup btn btn-large btn-success\" data-id1=\""+resultset.getInt("URid")+"\" data-id2=\""+resultset.getString("UserRole")+"\" data-id3=\""+sharedgroups+"\" data-id4=\""+pno+"\" style=\"font-size:11px;padding:1px;margin:1px;text-align:left;\"><span class=\"glyphicon glyphicon-plus\"></span></button>";
                    patientrecord+="</div>"
                    + "</div>\n" +
                    " </div>";
                    ids++;
                }
            }
            resultset.close();
            statement.close();
            connection.close();
        }  catch (SQLException ex) {
            patientrecord+="SqlExceptions: " +ex.getMessage();
            //error code 1146-table doesn't exists
        } 
            patientrecord+="</div>"; 
            return patientrecord;
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
