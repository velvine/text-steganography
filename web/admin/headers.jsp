<%-- 
    Document   : headers
    Created on : Sep 21, 2020, 1:06:08 AM
    Author     : Saamu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
 <% 
    String sesusername=(String)session.getAttribute("HTSAdminUsername"),sesadmin=(String)session.getAttribute("HTSAdmin"),sesfullname=(String)session.getAttribute("HTSAdminFullname"),sesuserrole=(String)session.getAttribute("HTSAdminUserrole");
    if(sesusername==null || !(sesuserrole.equals("Admin")) || !(sesadmin.equals(sesusername))){
        String site = new String("../index.jsp");
        response.setStatus(response.SC_MOVED_TEMPORARILY);
        response.setHeader("Location", site);
    }
    %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Medic Account</title>
        <link rel="icon" type="image/png" sizes="32x32" href="../dist/img/favicon.ico">
        <link href="../jquery/jquery-ui-1.12.1.custom/jquery-ui.min.css" rel="stylesheet" type="text/css" >
        <link href="../bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="../themes/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="../themes/vendor/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
        <link href="../assets/vendor/metisMenu/metisMenu.min.css" rel="stylesheet" type="text/css">
        <link href="../assets/asdist/css/sb-admin-2.min.css" rel="stylesheet" type="text/css">
        <link href="../dttables/datatables.min.css" rel="stylesheet" type="text/css">

        <script src="../themes/js/jquery.scrolltotop.js" type="text/javascript"></script>
        <script src="../jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="../themes/vendor/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="../dttables/datatables.min.js" type="text/javascript"></script>
        <script src="../assets/vendor/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
        <script src="../assets/vendor/metisMenu/metisMenu.min.js" type="text/javascript" ></script>
        <script src="../assets/asdist/js/sb-admin-2.min.js" type="text/javascript"></script>

        <script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.flash.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.html5.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.print.min.js"></script>

        <script src="../themes/vendor/raphael/raphael.min.js"></script>
        <script src="../themes/vendor/morrisjs/morris.min.js"></script>
        <script src="../themes/data/morris-data.js"></script>
        <script src="../sb-admin/vendor/ckeditor/ckeditor.js"></script>

        <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0;">
            <div class="">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
            </div>
            <!-- /.navbar-header -->
            <div style="background-color: white;">
            <a href="index.jsp" class="pull-left"><img src="../dist/img/header.png" width="250px" height="50px" style="padding: 5px;"  alt="Hospitalised Text Steganography"/>
             <span style="color:green;font-size:18px;"><%=sesuserrole %> Logged In</span>
            </a>
            <ul class="nav navbar-top-links navbar-right" >
              <li><span style="color: green;" ><i class="fa fa-phone fa-fw"></i> +254789200000</span> </li>   
            
              <li><span class="" style="padding: 12px;font-size: 13px;" > <i class="fa fa-envelope fa-fw"></i>info@hos.com</span> 
                </li>
              <li><a href="myaccount.jsp" style="color:orange;"><i class="fa fa-user fa-fw"></i> <%=sesfullname %></a>
                </li>
              <li><a href="../logouts" style="color:red;"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                </li>
                <!-- /.dropdown -->
            </ul>
          </div>
            <!-- /.navbar-top-links -->
              <div class="navbar-default sidebar" role="navigation" style="padding-bottom: 50px;">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="index.jsp" class="active"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>

                         <li>
                            <a style="cursor: pointer;"><i class="fa fa-building fa-fw"></i> Patients<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li> <a href="patientsinfo.jsp" class="" >Patients </a> </li>
                                 <li> <a href="patientrecords.jsp" class="" >Messages </a> </li>
                            </ul>
                        </li>

                        <li>
                            <a style="cursor: pointer;"><i class="fa fa-user fa-fw"></i> Accounts<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li style="cursor: pointer;">
                                    <a href="manageusers.jsp" > Manage Users</a>
                                </li>
                                <li style="cursor: pointer;">
                                    <a href="myaccount.jsp" > Your Profile</a>
                                </li>
                            </ul>
                        </li>
                        
                        <li>
                            <a href="variousreports.jsp"><i class="fa fa-table fa-fw"></i> Reports</a>
                        </li>
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>
    </head>
   <body>
 <div id="page-wrapper">
