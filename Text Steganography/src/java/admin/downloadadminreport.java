/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package admin;

import ciphers.HideandCipher;
import connections.databaseConnection;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author user
 */
public class downloadadminreport extends HttpServlet {

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
            out.println("<title>Servlet downloadadminreport</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet downloadadminreport at " + request.getContextPath() + "</h1>");
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
        HttpSession session=request.getSession();
                    session=request.getSession();
                    String sesusername=(String)session.getAttribute("HTSMedicUsername"),sesadmin=(String)session.getAttribute("HTSMedic"),sesfullname=(String)session.getAttribute("HTSMedicFullname"),sesuserrole=(String)session.getAttribute("HTSMedicUserrole"),loggedid=(String)session.getAttribute("HTSMedicId");
                    String selectedreport = request.getParameter("selectedreport");
                    String reporttype = request.getParameter("reporttype");
                    String months[] = {
                    "Jan", "Feb", "Mar", "Apr",
                    "May", "Jun", "Jul", "Aug",
                    "Sep", "Oct", "Nov", "Dec"};
                    // Create a calendar initialized with the
                    // current date and time in the default
                    // locale and timezone.
                    Calendar calendar = Calendar.getInstance();
                    // Display current time and date information.
                    String gendate=months[calendar.get(Calendar.MONTH)]+" "+calendar.get(Calendar.DATE)+"-"+calendar.get(Calendar.YEAR);
                    String gentime=calendar.get(Calendar.HOUR_OF_DAY)+":"+calendar.get(Calendar.MINUTE)+":"+calendar.get(Calendar.SECOND);
        try { 
//            para.setFont(new Font(Font.FontFamily.TIMES_ROMAN, 12,Font.NORMAL, BaseColor.RED));
            databaseConnection dbConnection=new databaseConnection();
            Connection connection;
            Statement statement=null,stmt=null;
            ResultSet resultset=null,rs=null;
            String selectedreportss="";
            if (reporttype.equals("PatientReports")) {
                selectedreportss="PatientReports";
            }
            else if (reporttype.equals("GroupsReport")) {
                selectedreportss="GroupsReport";
            }
            else{
                selectedreportss=selectedreport;
            }
            switch(selectedreportss){
                case "PatientReports":
                    //generate a patient report here
//                    PrintWriter out = response.getWriter();
//                String pno=request.getParameter("selectedreport");
                    connection= dbConnection.getConnection();
                    HideandCipher hideandcipher=new HideandCipher();
               
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM patients where Pno='"+selectedreport+"' and Groups LIKE '%"+sesuserrole+"%'");
                        while(resultset.next()){
                            File file = new File("D:"+reporttype+" For "+resultset.getString("Fullname")+" On "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(reporttype);
                            document.open();
                            Paragraph parat1 =new Paragraph(reporttype+" For "+resultset.getString("Fullname"));
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                                parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(1);

                            PdfPCell c1 = new PdfPCell(new Phrase("Patient Notes and Records"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);

                            stmt= connection.createStatement();
                            rs=stmt.executeQuery("SELECT * FROM patientsinfo where Pno='"+selectedreport+"'");
                            int ids=1; 
                            while(rs.next()){
                                String recordnotes=rs.getString("Record");
                                String cover=rs.getString("Cover");
                                String recordnotesek=rs.getString("DK");

                                String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                                String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                                recordnotesdec=recordnotesdec.replaceAll("<p>","");
                                recordnotesdec=recordnotesdec.replaceAll("</p>","\n");
                                recordnotesdec=recordnotesdec.replaceAll("&nbsp;"," ");
                                table.addCell(""+ids+".\t "+"\t\t\t\tDone on: "+rs.getTimestamp("DateAdded")+"\n\n"+recordnotesdec);
                                ids++;
                            }
                            document.add(new Paragraph(" "));
                            document.add(table);

                            document.close();

                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();   
                        }
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }
                break;
                case "AllPatientsReport":
                    //generate patient records report here for various users/groups 1. All Patientsrecords, 2. All Patientsrecords(you), 3. All patientsrecords(group)
                    connection= dbConnection.getConnection();
                    hideandcipher=new HideandCipher();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM patients where Groups LIKE '%"+sesuserrole+"%'");
                            int sno=1;
                            File file = new File("D:"+reporttype+" For AllPatientsReport On "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(reporttype);
                            document.open();
                            Paragraph parat1 =new Paragraph(selectedreport);
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            // Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                            //     parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            // document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(1);

                            PdfPCell c1 = new PdfPCell(new Phrase("Patient Notes and Records"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);
                        while(resultset.next()){
                            String pnos=resultset.getString("Pno");
                            c1 = new PdfPCell(new Phrase(""+sno+".\t "+"\t\t\t\t"+resultset.getString("Fullname")));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            stmt= connection.createStatement();
                            rs=stmt.executeQuery("SELECT * FROM patientsinfo where Pno='"+pnos+"'");
                            int ids=1; 
                            while(rs.next()){
                                String recordnotes=rs.getString("Record");
                                String cover=rs.getString("Cover");
                                String recordnotesek=rs.getString("DK");
                                String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                                String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                                recordnotesdec=recordnotesdec.replaceAll("<p>","");
                                recordnotesdec=recordnotesdec.replaceAll("</p>","\n");
                                recordnotesdec=recordnotesdec.replaceAll("&nbsp;"," ");
                                table.addCell(""+ids+".\t "+"\t\t\t\tDone on: "+rs.getTimestamp("DateAdded")+"\n\n"+recordnotesdec);
                                ids++;
                            }
                            
                            sno++; 
                        }
                        document.add(new Paragraph(" "));
                            document.add(table);

                            document.close();

                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();  
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }
                break;
                case "AllPatientsByYour":
                    //generate patient records report here for various users/groups 1. All Patientsrecords, 2. All Patientsrecords(you), 3. All patientsrecords(group)
                    connection= dbConnection.getConnection();
                    hideandcipher=new HideandCipher();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM patients where Groups LIKE '%"+sesuserrole+"%'");
                            int sno=1;
                            File file = new File("D:"+reporttype+" For AllPatientsByYour On "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(reporttype);
                            document.open();
                            Paragraph parat1 =new Paragraph(selectedreport);
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            // Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                            //     parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            // document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(1);

                            PdfPCell c1 = new PdfPCell(new Phrase("Patient Notes and Records"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);
                        while(resultset.next()){
                            String pnos=resultset.getString("Pno");
                            c1 = new PdfPCell(new Phrase(""+sno+".\t "+"\t\t\t\t"+resultset.getString("Fullname")));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            stmt= connection.createStatement();
                            rs=stmt.executeQuery("SELECT * FROM patientsinfo where Pno='"+pnos+"' and Medic='"+loggedid+"'");
                            int ids=1; 
                            while(rs.next()){
                                String recordnotes=rs.getString("Record");
                                String cover=rs.getString("Cover");
                                String recordnotesek=rs.getString("DK");
                                String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                                String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                                recordnotesdec=recordnotesdec.replaceAll("<p>","");
                                recordnotesdec=recordnotesdec.replaceAll("</p>","\n");
                                recordnotesdec=recordnotesdec.replaceAll("&nbsp;"," ");
                                table.addCell(""+ids+".\t "+"\t\t\t\tDone on: "+rs.getTimestamp("DateAdded")+"\n\n"+recordnotesdec);
                                ids++;
                            }
                            
                            sno++; 
                        }
                            document.add(new Paragraph(" "));
                            document.add(table);
                            document.close();
                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();  
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }
                break;
                case "GroupsReport":
                    //generate patient records report here for various users/groups 1. All Patientsrecords, 2. All Patientsrecords(you), 3. All patientsrecords(group)
                    connection= dbConnection.getConnection();
                    hideandcipher=new HideandCipher();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM patients where Groups LIKE '%"+selectedreport+"%'");
                            int sno=1;
                            File file = new File("D:"+reporttype+" For GroupsReport On "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(reporttype);
                            document.open();
                            Paragraph parat1 =new Paragraph("UserRole :"+selectedreport);
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            // Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                            //     parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            // document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(1);

                            PdfPCell c1 = new PdfPCell(new Phrase("Patient Notes and Records"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);
                        while(resultset.next()){
                            String pnos=resultset.getString("Pno");
                            c1 = new PdfPCell(new Phrase(""+sno+".\t "+"\t\t\t\t"+resultset.getString("Fullname")));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            stmt= connection.createStatement();
                            rs=stmt.executeQuery("SELECT * FROM patientsinfo where Pno='"+pnos+"' and Medic='"+loggedid+"'");
                            int ids=1; 
                            while(rs.next()){
                                String recordnotes=rs.getString("Record");
                                String cover=rs.getString("Cover");
                                String recordnotesek=rs.getString("DK");
                                String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                                String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                                recordnotesdec=recordnotesdec.replaceAll("<p>","");
                                recordnotesdec=recordnotesdec.replaceAll("</p>","\n");
                                recordnotesdec=recordnotesdec.replaceAll("&nbsp;"," ");
                                table.addCell(""+ids+".\t "+"\t\t\t\tDone on: "+rs.getTimestamp("DateAdded")+"\n\n"+recordnotesdec);
                                ids++;
                            }
                            
                            sno++; 
                        }
                            document.add(new Paragraph(" "));
                            document.add(table);
                            document.close();
                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();  
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }
                break;
                case "AllMessages":
                    //generates any reports about messages send and recived. 1.By you, 2.To You, 3.To Your Group, 4.By Your Group 
                    connection= dbConnection.getConnection();
                    hideandcipher=new HideandCipher();
               
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM messages");
                        File file = new File("D:"+selectedreport+" On "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(selectedreport);
                            document.open();
                            Paragraph parat1 =new Paragraph(selectedreport);
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            // Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                            //     parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            // document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(4);

                            PdfPCell c1 = new PdfPCell(new Phrase("Sender"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Receiver"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Type"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Message"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);
                            int ids=1; 
                        while(resultset.next()){
                            
                            String recordnotes=resultset.getString("Message");
                            String cover=resultset.getString("Sender")+" "+resultset.getString("Receiver");
                            String recordnotesek=resultset.getString("DK");
                            String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                            String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                                table.addCell(""+ids+".\t\t"+resultset.getString("Sender"));
                                table.addCell(resultset.getString("Receiver"));
                                table.addCell(resultset.getString("Level"));
                            recordnotesdec=recordnotesdec.replaceAll("<p>","");
                            recordnotesdec=recordnotesdec.replaceAll("</p>","\n");
                            recordnotesdec=recordnotesdec.replaceAll("&nbsp;"," ");
                                table.addCell("Left on: "+resultset.getTimestamp("DateAdded")+"\n\n"+recordnotesdec);
                            ids++;
                        }
                         document.add(new Paragraph(" "));
                            document.add(table);

                            document.close();

                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();   
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }

                break;
                case "ALlSendByYou":
                    //generates any reports about messages send and recived. 1.By you, 2.To You, 3.To Your Group, 4.By Your Group 
                    connection= dbConnection.getConnection();
                    hideandcipher=new HideandCipher();
               
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM messages Where Sender='"+sesfullname+"'");
                        File file = new File("D:"+selectedreport+" On "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(selectedreport);
                            document.open();
                            Paragraph parat1 =new Paragraph(selectedreport+" :"+sesfullname);
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            // Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                            //     parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            // document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(3);

                            PdfPCell c1 = new PdfPCell(new Phrase("Receiver"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Type"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Message"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);
                            int ids=1; 
                        while(resultset.next()){
                            
                            String recordnotes=resultset.getString("Message");
                            String cover=resultset.getString("Sender")+" "+resultset.getString("Receiver");
                            String recordnotesek=resultset.getString("DK");
                            String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                            String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                                table.addCell(resultset.getString("Receiver"));
                                table.addCell(resultset.getString("Level"));
                            recordnotesdec=recordnotesdec.replaceAll("<p>","");
                            recordnotesdec=recordnotesdec.replaceAll("</p>","\n");
                            recordnotesdec=recordnotesdec.replaceAll("&nbsp;"," ");
                                table.addCell("Left on: "+resultset.getTimestamp("DateAdded")+"\n\n"+recordnotesdec);
                            ids++;
                        }
                         document.add(new Paragraph(" "));
                            document.add(table);

                            document.close();

                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();   
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }

                break;
                case "AllReceived":
                    //generates any reports about messages send and recived. 1.By you, 2.To You, 3.To Your Group, 4.By Your Group 
                    connection= dbConnection.getConnection();
                    hideandcipher=new HideandCipher();
               
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM messages Where Receiver='"+sesfullname+"'");
                        File file = new File("D:"+selectedreport+" On "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(selectedreport);
                            document.open();
                            Paragraph parat1 =new Paragraph(selectedreport+" :"+sesfullname);
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            // Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                            //     parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            // document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(3);

                            PdfPCell c1 = new PdfPCell(new Phrase("Sender"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Type"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Message"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);
                            int ids=1; 
                        while(resultset.next()){
                            
                            String recordnotes=resultset.getString("Message");
                            String cover=resultset.getString("Sender")+" "+resultset.getString("Receiver");
                            String recordnotesek=resultset.getString("DK");
                            String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                            String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                                table.addCell(resultset.getString("Sender"));
                                table.addCell(resultset.getString("Level"));
                            recordnotesdec=recordnotesdec.replaceAll("<p>","");
                            recordnotesdec=recordnotesdec.replaceAll("</p>","\n");
                            recordnotesdec=recordnotesdec.replaceAll("&nbsp;"," ");
                                table.addCell("Left on: "+resultset.getTimestamp("DateAdded")+"\n\n"+recordnotesdec);
                            ids++;
                        }
                         document.add(new Paragraph(" "));
                            document.add(table);

                            document.close();

                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();   
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }

                break;
                case "AllReceivedByGroup":
                    //generates any reports about messages send and recived. 1.By you, 2.To You, 3.To Your Group, 4.By Your Group 
                    connection= dbConnection.getConnection();
                    hideandcipher=new HideandCipher();
               
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM messages Where Receiver='"+sesuserrole+"'");
                        File file = new File("D:"+selectedreport+" On "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(selectedreport);
                            document.open();
                            Paragraph parat1 =new Paragraph(selectedreport+" :"+sesfullname);
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            // Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                            //     parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            // document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(2);

                            PdfPCell c1 = new PdfPCell(new Phrase("Sender"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Message"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);
                            int ids=1; 
                        while(resultset.next()){
                            
                            String recordnotes=resultset.getString("Message");
                            String cover=resultset.getString("Sender")+" "+resultset.getString("Receiver");
                            String recordnotesek=resultset.getString("DK");
                            String recordnotesmessagehiden=hideandcipher.seekMessage(hideandcipher.StegSeek(recordnotes,cover));
                            String recordnotesdec=hideandcipher.OTPDecryption(recordnotesmessagehiden,recordnotesek);
                                table.addCell(resultset.getString("Sender"));
                            recordnotesdec=recordnotesdec.replaceAll("<p>","");
                            recordnotesdec=recordnotesdec.replaceAll("</p>","\n");
                            recordnotesdec=recordnotesdec.replaceAll("&nbsp;"," ");
                                table.addCell("Left on: "+resultset.getTimestamp("DateAdded")+"\n\n"+recordnotesdec);
                            ids++;
                        }
                         document.add(new Paragraph(" "));
                            document.add(table);

                            document.close();

                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();   
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }

                break;
                case "AllUsersReport":
                    //Reports about users,roles and userroles with activities carried
                    connection= dbConnection.getConnection();
                    hideandcipher=new HideandCipher();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM users");
                        File file = new File("D:"+selectedreport+" On "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(selectedreport);
                            document.open();
                            Paragraph parat1 =new Paragraph(selectedreport);
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            // Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                            // parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            // document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(7);
                            PdfPCell c1 = new PdfPCell(new Phrase("Fullname"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("UserRole"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Phone"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Email"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Status"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Date Created"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);
                            int ids=1; 
                        while(resultset.next()){
                            table.addCell(resultset.getString("Fullname"));
                            table.addCell(resultset.getString("UserRole"));
                            table.addCell(resultset.getString("Phone"));
                            table.addCell(resultset.getString("Email"));
                            table.addCell(resultset.getString("Status"));
                            table.addCell(resultset.getString("dateadded"));
                            ids++;
                        }
                         document.add(new Paragraph(" "));
                            document.add(table);

                            document.close();

                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();   
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }

                break;
                case "AllActiveUsersReport":
                    //Reports about users,roles and userroles with activities carried
                    connection= dbConnection.getConnection();
                    hideandcipher=new HideandCipher();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM users where Status='Active' || Status='Generated' || Status='Changed'");
                        File file = new File("D:"+selectedreport+" On "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(selectedreport);
                            document.open();
                            Paragraph parat1 =new Paragraph(selectedreport);
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            // Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                            // parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            // document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(6);
                            PdfPCell c1 = new PdfPCell(new Phrase("Fullname"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("UserRole"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Phone"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Email"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Date Created"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);
                            int ids=1; 
                        while(resultset.next()){
                            table.addCell(resultset.getString("Fullname"));
                            table.addCell(resultset.getString("UserRole"));
                            table.addCell(resultset.getString("Phone"));
                            table.addCell(resultset.getString("Email"));
                            table.addCell(resultset.getString("dateadded"));
                            ids++;
                        }
                         document.add(new Paragraph(" "));
                            document.add(table);

                            document.close();

                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();   
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }

                break;
                case "AllDeactivatedUsersReport":
                    //Reports about users,roles and userroles with activities carried
                    connection= dbConnection.getConnection();
                    hideandcipher=new HideandCipher();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM users where Status='Reset' || Status='Disabled'");
                        File file = new File("D:"+selectedreport+" On "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(selectedreport);
                            document.open();
                            Paragraph parat1 =new Paragraph(selectedreport);
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            // Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                            // parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            // document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(5);
                            PdfPCell c1 = new PdfPCell(new Phrase("Fullname"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("UserRole"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Phone"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Email"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Date Created"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            table.setHeaderRows(1);
                            int ids=1; 
                        while(resultset.next()){
                            table.addCell(resultset.getString("Fullname"));
                            table.addCell(resultset.getString("UserRole"));
                            table.addCell(resultset.getString("Phone"));
                            table.addCell(resultset.getString("Email"));
                            table.addCell(resultset.getString("dateadded"));
                            ids++;
                        }
                         document.add(new Paragraph(" "));
                            document.add(table);

                            document.close();

                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();   
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }

                break;
                case "AllUserRolesReport":
                    //generate patient records report here for various users/groups 1. All Patientsrecords, 2. All Patientsrecords(you), 3. All patientsrecords(group)
                    connection= dbConnection.getConnection();
                    hideandcipher=new HideandCipher();
                    try{
                        statement= connection.createStatement();
                        resultset=statement.executeQuery("SELECT * FROM userroles group by UserRole");
                            int sno=1;
                            File file = new File("D:"+selectedreport+" At "+gendate+".pdf");
                            FileOutputStream fileout = new FileOutputStream(file);
                            Document document = new Document();
                            PdfWriter.getInstance(document, fileout);
                            document.addAuthor(sesfullname);
                            document.addTitle(selectedreport);
                            document.open();
                            Paragraph parat1 =new Paragraph(selectedreport);
                                parat1.setAlignment(Element.ALIGN_CENTER);
                            // Paragraph parat2 =new Paragraph("Patient: "+resultset.getString("Fullname")+", Age: "+resultset.getString("Age")+" ,Place of Birth: "+resultset.getString("BirthPlace"));
                            //     parat2.setAlignment(Element.ALIGN_CENTER);
                            Paragraph parat3 =new Paragraph("Generated by:"+sesfullname+" On "+gendate+" At "+gentime);
                                parat3.setAlignment(Element.ALIGN_CENTER);
                            document.add(parat1);
                            // document.add(parat2);
                            document.add(parat3);
                            PdfPTable table = new PdfPTable(3);

                            PdfPCell c1 = new PdfPCell(new Phrase("Roles and Userroles assigned"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            c1.setColspan(3);
                            table.addCell(c1);
                            table.setHeaderRows(1);
                        while(resultset.next()){
                            String userrol=resultset.getString("UserRole");
                            c1 = new PdfPCell(new Phrase(""+sno+".\t "+resultset.getString("UserRole")));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            c1.setColspan(3);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Role"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Type"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            c1 = new PdfPCell(new Phrase("Description"));
                            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
                            table.addCell(c1);
                            stmt= connection.createStatement();
                            rs=stmt.executeQuery("SELECT userroles.URid,userroles.UserRole,userroles.Role as URole,roles.Rid,roles.Role,roles.Description,roles.Type from userroles,roles Where UserRole='"+userrol+"' and userroles.Role=roles.Rid and roles.Type='Saves'");
                            int ids=1; 
                            while(rs.next()){
                                table.addCell(""+ids+".\t"+rs.getString("Role"));
                                table.addCell(rs.getString("Type"));
                                table.addCell(rs.getString("Description"));
                                ids++;
                            }
                            sno++; 
                        }
                        document.add(new Paragraph(" "));
                            document.add(table);

                            document.close();
                            File downloadFile = file;
                            FileInputStream inStream = new FileInputStream(downloadFile);
                            // modifies response
                            response.setContentType("application/octet-stream");
                            response.setContentLength((int) downloadFile.length());
                            // forces download
                            String headerKey = "Content-Disposition";
                            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                            response.setHeader(headerKey, headerValue);

                            // obtains response's output stream
                            OutputStream outStream = response.getOutputStream();

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;
                            while ((bytesRead = inStream.read(buffer)) != -1) {
                                outStream.write(buffer, 0, bytesRead);
                            }
                            inStream.close();
                            outStream.close();  
                    resultset.close();
                    statement.close();
                    connection.close();
                    }  catch (SQLException ex) {
                        System.out.println(ex.getMessage());
                        try (PrintWriter out = response.getWriter()) {
                            /* TODO output your page here. You may use following sample code. */
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Database Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+ex.getMessage()+"</h4>");
                            out.close();
                        }//error code 1146-table doesn't exists
                    }
                    catch (Exception e) {
                        try (PrintWriter out = response.getWriter()) {
                            out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Document Generation Error</h3>");
                            out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">"+e.getMessage()+"</h4>");
                            out.close();
                        }
                    }
                break;
                default:
                    System.out.println("Unknown Document Request");
                    try (PrintWriter out = response.getWriter()) {
                        /* TODO output your page here. You may use following sample code. */
                        out.println("<h3 style=\"text-align:center;color:red;font-size:20px;margin-top:10%;\">Unknown Document Request</h3>");
                        out.println("<h4 style=\"text-align:center;color:orange;font-size:20px;\">Request could not be completed now.</h4>");
                        out.close();
                    }
                break;
            }	

        } catch (Exception e) {
                e.printStackTrace();
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
        processRequest(request, response);
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
