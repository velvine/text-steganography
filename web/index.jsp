<%-- 
    Document   : index
    Created on : Feb 5, 2020, 9:59:59 PM
    Author     : Shymu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Restricted Page</title>
         <link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">      
        <link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">

        <link href="themes/css/bootstrappage.css" rel="stylesheet"/>

        <!-- global styles -->
        <link href="themes/css/flexslider.css" rel="stylesheet"/>
        <link href="themes/css/main.css" rel="stylesheet"/>
        <link rel="icon" type="image/png" sizes="32x32" href="dist/img/favicon.ico">

        <link rel="icon" type="image/png" sizes="32x32" href="dist/img/favicon.ico">
        <link href="jquery/jquery-ui-1.12.1.custom/jquery-ui.min.css" rel="stylesheet" type="text/css" >
        <link href="bootstrap/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
        <link href="themes/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link href="themes/vendor/bootstrap/css/bootstrap-theme.min.css" rel="stylesheet" type="text/css">
        <link href="assets/vendor/metisMenu/metisMenu.min.css" rel="stylesheet" type="text/css">
        <link href="assets/asdist/css/sb-admin-2.min.css" rel="stylesheet" type="text/css">
        <link href="dttables/datatables.min.css" rel="stylesheet" type="text/css">

        <script src="themes/js/jquery.scrolltotop.js" type="text/javascript"></script>
        <script src="jquery/jquery-3.2.1.min.js" type="text/javascript"></script>
        <script src="themes/vendor/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
        <script src="dttables/datatables.min.js" type="text/javascript"></script>
        <script src="assets/vendor/bootstrap/js/bootstrap.min.js" type="text/javascript"></script> 
        <script src="assets/vendor/metisMenu/metisMenu.min.js" type="text/javascript" ></script>
        <script src="assets/asdist/js/sb-admin-2.min.js" type="text/javascript"></script>

        <script type="text/javascript" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/dataTables.buttons.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.flash.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jszip/3.1.3/jszip.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/pdfmake.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/pdfmake/0.1.53/vfs_fonts.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.html5.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/buttons/1.6.2/js/buttons.print.min.js"></script>

        <script src="themes/vendor/raphael/raphael.min.js"></script>
        <script src="themes/vendor/morrisjs/morris.min.js"></script>
        <script src="themes/data/morris-data.js"></script>
        <script src="sb-admin/vendor/ckeditor/ckeditor.js"></script>
    </head>
    <body>
        <div class="container">
             <% 
//            String ses=(String)session.getAttribute("Username");
//            if(ses==null){
//                String site = new String("index.jsp");
//                response.setStatus(response.SC_MOVED_TEMPORARILY);
//                response.setHeader("Location", site);
//            }
            %>
            <div class="" id="loginformbody" style="background-color:white;">
                <div class=""  style="margin-top:1%;">
                    <form action="build/login.php" method="post"  name="loginform" id="loginform" role="form" class="form-horizontal" >
                       <div class="panel-body"  style="padding: 0;">
                            <div class="col-sm-4"></div>

                            <div class="col-sm-4" style="text-align: center;background-color:white;">
                              <div class="login-logo" style="margin-bottom: 20px;">
                               <img src = "dist/img/header.png" style="padding:20px;" />
                               <div class="" style="margin-bottom:10px;">
                                <h4 style="font-size:30px;text-align:left;font-family: 'Mulish', sans-serif;"><b>Welcome</b></h4> 
                               <div class="" style="text-align:left;font-size:23px;">Sign in to continue</div>
                               </div>
                              </div>
                            <div class="alert alert-dismissable" id="loaddiv"  style="border: 1px #fff solid;">
                            </div>
                            <div class="alert alert-danger alert-dismissable" id="errordiv"  style="">
                                  <center><b style="font-size:20px;"><p id="errorp"></p></b></center>
                            </div>
                            <div class="alert alert-success" id="successdiv"  style="">
                                  <center><b style="font-size:20px;"><p id="successp"></p></b></center>
                            </div>
                            <div class="form-group has-feedback">

                              <div class="col-sm-12">
                                <input type="text" name="username" id="username" class="form-control" placeholder="Username" style="font-size:18px;" required>
                              </div>
                            </div>
                            <div class="form-group has-feedback">

                              <div class="col-sm-12">
                                <input type="password" name="password" id="password" class="form-control" placeholder="Password" style="font-size:18px;" required>
                              </div>
                            </div>
                            <div class="" style="text-align: center;" >

                               <button type="submit" name="submit" id="add" class="btn btn-info btn-block" style="font-size:18px;padding:10px;" ><b> Sign In</b></button>
                            </div>
                            <button type="button" id="resetpassword"  class="btn btn-default btn-large pull-right" style="font-size:18px;padding:10px;" ><b>Forgot Password</b></button>
                            </div>
                            <div class="col-sm-4"></div>
                        </div>
                  </form>
                </div> 
                </div>
            
            
        </div>
        <!-- Modal -->
<div class="modal fade" id="resetModal" tabindex="-1" role="dialog" aria-labelledby="resetModalLabel" style="" aria-hidden="false">
<div class="modal-dialog">
<div class="modal-content" >
<div class="modal-header" style="background-color: #77B5ED">
<button type="button" class="close" data-dismiss="modal" aria-hidden="true">x
</button>
<h4 class="modal-title" id="resetModalLabel">
<!--<?php echo $yourname; ?>-->
</h4>
</div>
<div class="modal-body" id="resetmsg">

    <div class="well" style="width: 100%;min-width: 250px;margin-top: 10%;margin-left: 0;">
      <center>
        <form  method="post" action="" name="resetpasswordform" id="resetpasswordform" role="form" class="form-horizontal" >
        <h4>To reset your Password , Enter the following Information</h4>
        <h3>A Reset Password will be provided to Your Phone Number</h3>
          <div class="form-group has-feedback">
            <label for="user_id" class="col-sm-3 control-label">Phone Number</label>
              <div class="col-sm-9"> 
                <input type="tel" class="form-control" name="resetphone" id="resetphone" placeholder="Registered +25711111111" required="">
                <span class="glyphicon glyphicon-phone form-control-feedback"></span>
             </div> 
          </div>
          <div class="form-group has-feedback">
            <label for="user_pass" class="col-sm-3 control-label">ID NO</label>
              <div class="col-sm-9"> 
                <input type="number" class="form-control" name="resetidno" id="resetidno" placeholder="Enter ID NO" maxlength="8" minlength="8" required="">
                <span class="glyphicon glyphicon-pencil form-control-feedback"></span>
             </div> 
          </div>
          <div class="form-group"> 
            <div class=" "> 
              <button type="submit" class="pull-right btn btn-large btn-warning" name="resetbtn" id="resetbtn">Reset Password</button><br>
            </div> 
          </div>
        </form>
      </center>
 </div>
</div>
<div class="modal-footer"  >
<button type="button" class="btn btn-default" data-dismiss="modal">Close
</button>
</div>
</div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" data-backdrop="false">
<div class="modal-dialog">
<div class="modal-content" >
<div class="modal-header" style="background-color: #77B5ED;">
<button type="button" class="close" data-dismiss="modal"
aria-hidden="true">×
</button>
<h4 class="modal-title" id="myModalLabel" >
 Message Information
</h4>
</div>
<div class="modal-body" id="msg">
<h1 align="center" class="border"><br><br><br><br>
    <div style="margin:0 auto; text-align:center;">
    Loading...
    <??>
    <br>
    <img src="dist/img/loading.gif" >
    <br></div></h1>
</div>
<div class="modal-footer" >
<button type="button" class="btn btn-default" data-dismiss="modal" id="pressokay">Ok
</button>
</div>
</div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
    </body>
    <script>
  $(function () {
    $('#errordiv').hide();
    $('#loaddiv').hide();
    $('#successdiv').hide();
    $('#selectedproperty').hide();
  });
  //reset password here
$('#resetpassword').click(function(){
    $('#resetModal').modal({"backdrop":"static"});
    $("#resetModal").modal('show');
});
// $("#resetpasswordform").on('submit',(function(e){
//      e.preventDefault();
//      $('#myModal').modal({"backdrop":"static"});
//      $.ajax({
//         url:"build/reset.php",
//         type:"POST",
//         data:new FormData(this),
//         processData:false,
//         contentType:false,
//         cache:false,
//         success:function(data){
//               $("#msg").html(data);
//               $("#myModalLabel").html("Forgot Pasword-:Password Reset");
//               $("#myModal").modal('show');
//           }
//       });  
//     }));
$("#loginform").on('submit',(function(e){
     e.preventDefault();
     $('#errordiv').hide();
    $('#loaddiv').hide();
    $('#successdiv').hide();
     $('#loaddiv').show();
     var username=$("#username").val();
     var password=$("#password").val();
     if(username===""){
         $('#loaddiv').html("Please Enter Username");
     }
     else if(password===""){
         $('#loaddiv').html("Please Enter Password");
     }
     else{
         $('#loaddiv').html('<h4 align="center" class="border"><div style="margin:0 auto; text-align:center;"> Authenticating User ...  <img src="dist/img/spinner.gif" ></div></h4>');
         $.ajax({
            url:'Login',
            method:'POST',
            data:{username:username,password:password},
            dataType:"text",
            success:function(result){
                if (result=="Login Success Admin") {
                    $('#loaddiv').hide();
                    $('#errordiv').hide();
                    $('#successdiv').show();
                    $('#successp').html('<h4 align="center" class="border"><div style="margin:0 auto; text-align:center;"><i class="glyphicon glyphicon-ok"></i>'+result+' Redirecting ...  <img src="dist/img/spinner.gif" ></div></h4>');
                    window.location.href='admin/';
                }
                else if (result=="Login Success Medic") {
                    $('#loaddiv').hide();
                    $('#errordiv').hide();
                    $('#successdiv').show();
                    $('#successp').html('<h4 align="center" class="border"><div style="margin:0 auto; text-align:center;"><i class="glyphicon glyphicon-ok"></i>'+result+' Redirecting ...  <img src="dist/img/spinner.gif" ></div></h4>');
                    window.location.href='medic/';
                }
                else{
                    $('#loaddiv').hide();
                    $('#successdiv').hide();
                    $('#errordiv').show();
                    $('#errorp').html('<h4 align="center" class="border"><div style="margin:0 auto; text-align:center;"><i class="glyphicon glyphicon-remove"></i> '+result+'</div></h4>');
                }
           }
       });
     }  
}));
              //reset password here
            // $('#resetpassword').click(function(){
            //     $('#resetModal').modal({"backdrop":"static"});
            //     $("#resetModal").modal('show');
            // });
            // $("#resetpasswordform").on('submit',(function(e){
            //      e.preventDefault();
            //      $('#myModal').modal({"backdrop":"static"});
            //      $.ajax({
            //         url:"build/reset.php",
            //         type:"POST",
            //         data:new FormData(this),
            //         processData:false,
            //         contentType:false,
            //         cache:false,
            //         success:function(data){
            //               $("#msg").html(data);
            //               $("#myModalLabel").html("Forgot Pasword-:Password Reset");
            //               $("#myModal").modal('show');
            //           }
            //       });  
            //     }));
</script>
</html>
