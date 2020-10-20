<%-- 
    Document   : index
    Created on : Feb 6, 2020, 7:06:55 PM
    Author     : Shymu
--%>
 <% 
   String sesusername=(String)session.getAttribute("HTSMedicUsername"),sesadmin=(String)session.getAttribute("HTSMedic"),sesfullname=(String)session.getAttribute("HTSMedicFullname"),sesuserrole=(String)session.getAttribute("HTSMedicUserrole"),loggedid=(String)session.getAttribute("HTSMedicId");
   if(sesusername==null || !(sesadmin.equals(sesusername))){
       String site = new String("../index.jsp");
       response.setStatus(response.SC_MOVED_TEMPORARILY);
       response.setHeader("Location", site);
   }
   %>
    
<jsp:include page="headers.jsp" flush="true"/>


 <div>
    <div class="" id="allplots" style="margin: 5px;padding: 0px;">
            <div class="col-sm-12" style="padding: 5px;min-width: 300px;" id="">
                    <div class="well">                  
                      <div class="row">
                            <div class="col-sm-12">
                                <h4 style="text-align: center;">Received Messages</h4>
                                <div class="" style="margin: 2px;padding: 8px;min-width: 210px;margin-left: 0;" id="viewreceivedmessages">
                                </div>
                            </div>  
                        </div>
                    </div>
            </div>
    </div>
 </div>
<!-- Modal -->
<div class="modal fade " id="myViewModal" tabindex="-1" role="dialog" aria-labelledby="myViewModalLabel" aria-hidden="false" data-backdrop="false"  style="margin-left:0; ">
<div class="modal-dialog modal-lg" style="width: 90%;min-width: 200px;">
<div class="modal-content"  id="mydivmodalbig" style="background-color: #eee;" >
<div class="modal-header" id="mydivmodalbigheader" style="background-color: #77B5CA;" >
<button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" ></span>
</button>
<h4 class="modal-title" id="myViewModalLabel"  style="text-align:center;">
Text Steganography Message Information
</h4>
</div>
<div class="modal-body" id="myViewmsg" style="overflow-y: auto;max-height: 700px;">
<h1 align="center" class="border"><br><br><br><br>
    <div style="margin:0 auto; text-align:center;">
    Loading...
    <br>
    <img src="../dist/img/loading.gif" >
    <br></div></h1>
</div>
<div class="modal-footer" style="background-color:white;color:black;position: absolute; width: 100%; height: 55px;">
<button type="button" class="btn btn-default" data-dismiss="modal" id="viewpressokay" style="color:red;padding:5px;">CLOSE
</button>

</div>
</div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false" data-backdrop="false">
<div class="modal-dialog modal-lg">
<div class="modal-content" id="mydivmodal">
<div class="modal-header" id="mydivmodalheader" style="background-color: #77B5ED;">
<button type="button" class="close" data-dismiss="modal"
aria-hidden="true"><span class="glyphicon glyphicon-remove" ></span>
</button>
<h4 class="modal-title" id="myModalLabel" style="text-align:center;">
Hospital Text Steganography
</h4>
</div>
<div class="modal-body" id="msg" style="margin-bottom:-25px;">
<h1 align="center" class="border"><br><br><br><br>
    <div style="margin:0 auto; text-align:center;">
    Loading...
    <br>
    <img src="../dist/img/loading.gif" >
    <br></div>
</h1>
</div>
<div class="modal-footer" style="background-color: white;" >
<button type="button" class="btn btn-default" data-dismiss="modal" id="pressokay" style="color:red;padding:5px;">CLOSE
</button>
</div>
</div><!-- /.modal-content -->
</div><!-- /.modal-dialog -->
</div><!-- /.modal -->
    </body>
    <script>
     // Make the DIV element draggable:
dragElement(document.getElementById("mydivmodal"));
dragElement(document.getElementById("mydivmodalbig"));
function dragElement(elmnt) {
  var pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0;
  if (document.getElementById(elmnt.id + "header")) {
    // if present, the header is where you move the DIV from:
    document.getElementById(elmnt.id + "header").onmousedown = dragMouseDown;
  } else {
    // otherwise, move the DIV from anywhere inside the DIV:
    elmnt.onmousedown = dragMouseDown;
  }

  function dragMouseDown(e) {
    e = e || window.event;
    e.preventDefault();
    // get the mouse cursor position at startup:
    pos3 = e.clientX;
    pos4 = e.clientY;
    document.onmouseup = closeDragElement;
    // call a function whenever the cursor moves:
    document.onmousemove = elementDrag;
  }

  function elementDrag(e) {
    e = e || window.event;
    e.preventDefault();
    // calculate the new cursor position:
    pos1 = pos3 - e.clientX;
    pos2 = pos4 - e.clientY;
    pos3 = e.clientX;
    pos4 = e.clientY;
    // set the element's new position:
    elmnt.style.top = (elmnt.offsetTop - pos2) + "px";
    elmnt.style.left = (elmnt.offsetLeft - pos1) + "px";
  }

  function closeDragElement() {
    // stop moving when mouse button is released:
    document.onmouseup = null;
    document.onmousemove = null;
  }
}
    $(document).ready(function(){
       loadreceivedmessages();
   });
     function getpatintrecords(){
       var submit="PatientInfo";
            $.ajax({
                url:'../medicUser',
                type:'POST',
                data:{submit:submit},
                dataType:'text',
                success:function(result){
                    
                    // CKEDITOR.replace('postsdescription');
                    $('#managepatientsinfo').html(result);
                        $("#searchpatientdetailsform").on('submit',(function(e){
                            e.preventDefault();
                            $('#searchedpatientrecord').html('<h5 align="center" class="border"><br><br> <div style="margin:0 auto; text-align:center;"> Searching ...<br>  <img src="../dist/img/spinner.gif" ><br></div></h5>');
                            var searchfield=$("#searchfield").val();
                            var searchitem=$("#searchitem").val();
                            var submit="SearchPatient";
                            $.ajax({
                                url:'../medicUser',
                                type:'POST',
                                data:{submit:submit,searchfield:searchfield,searchitem:searchitem},
                                dataType:'text',
                                success:function(result){
                                    $('#searchedpatientrecord').html(result);
                                }
                            });
                        }));
                        $(".ttpatientinfo").DataTable({
                            "lengthMenu":[ [10,25,50,-1],[10,25,50,"All"] ]
                        });
                }
          }); 
   }
       function getpatienttreatmentrecords(pno){
        
       var submit="PatientInfoTreatmentRecords";
            $.ajax({
                url:'../medicUser',
                type:'POST',
                data:{pno:pno,submit:submit},
                dataType:'text',
                success:function(result){
                    // CKEDITOR.replace('postsdescription');
                    $('#viewresultpatientrecords').html(result);
                     
                        $(".ttpatientrecords").DataTable({
                            "lengthMenu":[ [10,25,50,-1],[10,25,50,"All"] ]
                        });
                }
          }); 
   }
function loaduserroles(){
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:"LoadUserRoles"},
            dataType:'text',
            success:function(result){
               $("#userrole").html(result);
          }
      }); 
    }
   function loadsentmessages(){
      var submit="LoadMessageSentInformations";
           $.ajax({
                url:'../medicUser',
                type:'POST',
                data:{submit:submit},
                dataType:'text',
               success:function(result){
                   $('#viewsentmessages').html(result);
               }
         }); 
   }
   function loadreceivedmessages(){
      var submit="LoadMessageRecievedInformation";
           $.ajax({
                url:'../medicUser',
                type:'POST',
                data:{submit:submit},
                dataType:'text',
               success:function(result){
                   $('#viewreceivedmessages').html(result);
               }
         }); 
   }
   function loadyourinformation(){
      var submit="LoadYourPersonalInformation";
           $.ajax({
                url:'../medicUser',
                type:'POST',
                data:{submit:submit},
                dataType:'text',
               success:function(result){
                   $('#updateyourinformation').html(result);
                   $("#submitupdateuserinfoform").on('submit',(function(e){
                        e.preventDefault();
                        var submit=$("#submit").val();
                        var userid=$("#userid").val();
                        var updateusername=$("#updateusername").val();
                        var updateuserfullname=$("#updateuserfullname").val();
                        var updateuserphone=$("#updateuserphone").val();
                        var updateuseremail=$("#updateuseremail").val();
                        var doResend= confirm("Do you want to Update your Personal Information?");
                        if(doResend==true){
                           $('#updatepersonalresult').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Updating '+updateuserfullname+' Personal Details ...   <img src="../dist/img/spinner.gif" ><br></div></h5>');
                            $.ajax({
                                url:'../medicUser',
                                type:'POST',
                                data:{submit:"UpdatePersonalInfo",userid:userid,updateusername:updateusername,updateuserfullname:updateuserfullname,updateuserphone:updateuserphone,updateuseremail:updateuseremail},
                                dataType:'text',
                                success:function(result){
                                   $('#updatepersonalresult').html(result);
                              }
                          });
                        }
                }));
                   $(document).on('keyup','#newpassword', function(){ 
                        var newpassword=$('#newpassword').val();
                        var confirmnewpassword=$('#confirmnewpassword').val();
                        if (newpassword!=confirmnewpassword) {
                            $('#passdiv').hide();
                            $('#changepasswordresult').html('<h4 style="color:red;">New Password does not match with Confirm Password</h4>');
                        }
                        else{
                            $('#passdiv').show();
                            $('#changepasswordresult').html('');
                        }
                     });
                    $(document).on('keyup','#confirmnewpassword', function(){ 
                        var newpassword=$('#newpassword').val();
                        var confirmnewpassword=$('#confirmnewpassword').val();
                        if (newpassword!=confirmnewpassword) {
                            $('#passdiv').hide();
                            $('#changepasswordresult').html('<h4 style="color:red;">New Password does not match with Confirm Password</h4>');
                        }
                        else{
                            $('#passdiv').show();
                            $('#changepasswordresult').html('');
                        }
                     });
                    $("#submitchangepasswordform").on('submit',(function(e){
                        e.preventDefault();
                        var newpassword=$('#newpassword').val();
                        var confirmnewpassword=$('#confirmnewpassword').val();
                        var previous=$('#previous').val();
                        var submit=$("#submit").val();
                        if (newpassword==confirmnewpassword) {
                            var doResend= confirm("Do you want to Change your Password?");
                            if(doResend==true){
                               $('#changepasswordresult').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Update Personal Details ...   <img src="../dist/img/spinner.gif" ><br></div></h5>');
                                $.ajax({
                                    url:'../medicUser',
                                    type:'POST',
                                    data:{submit:"ChangePassword",previous:previous,newpassword:newpassword},
                                    dataType:'text',
                                    success:function(result){
                                       $('#changepasswordresult').html(result);
                                  }
                              });
                            }
                        }
                        else{
                            $('#changepasswordresult').html('<h4 style="color:red;">New Password does not match with Confirm Password</h4>');
                        }
                    }));
               }
         }); 
   }
    function loadsentinformation(){
      var submit="LoadYourSentInformation";
           $.ajax({
                url:'../medicUser',
                type:'POST',
                data:{submit:submit},
                dataType:'text',
               success:function(result){
                   $('#viewresultpatientrecordssent').html(result);
               }
         }); 
   }
function getreports(submit,reporttype){
    $.ajax({
        url:'../medicUser',
        type:'POST',
        data:{submit:submit,reporttype:reporttype},
        dataType:'text',
        success:function(result){
           $("#selectedreport").html(result);
      }
    }); 
}
       //view reports on change report type
$(document).on('change','#reporttype',(function(e){
        var reporttype=$('#reporttype').val();
        getreports("ReportType",reporttype);
      
  }));
$(document).on('click','#messagereceivedtabid',(function(e){
        loadsentmessages();
        loadreceivedmessages();
  }));
$(document).on('click','#profiletabid',(function(e){
        loadyourinformation();
  }));
$(document).on('click','#patientdetailstabid',(function(e){
        getpatintrecords();
  }));
$(document).on('click','#newusertabid',(function(e){
        loaduserroles();
  }));
$(document).on('click','#variousreportstabid',(function(e){
        $("#reporttype").val("");
  }));
   function getroles(submit){
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:submit},
            dataType:'text',
            success:function(result){
               $("#viewallrolesdisplay").html(result);
          }
      }); 
    }
    function getuserroles(submit){
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:submit},
            dataType:'text',
            success:function(result){
               $("#viewalluserrolesdisplay").html(result);
          }
      }); 
    }
    function getsharedgroups(pno,delfullname){
        $('#viewsharepatients').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Viewing Patient '+delfullname+'  To View Shared Groups... <br>  <img src="../dist/img/loading.gif" ><br></div></h4>');
        var submit="AddSharePatientInformation";
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:submit,pno:pno,delfullname:delfullname},
            dataType:'text',
            success:function(result){
                $("#viewsharepatients").html(result);
                
           }
       });
    }
    function getsharedgroupsrefresh(pno){
        $('#viewsharepatients').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Viewing Patient  Shared Groups<br>  <img src="../dist/img/loading.gif" ><br></div></h4>');
        var submit="AddSharePatientInformation";
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:submit,pno:pno},
            dataType:'text',
            success:function(result){
                $("#viewsharepatients").html(result);
                
           }
       });
    }
    function getusers(submit){
    $.ajax({
        url:'../medicUser',
        type:'POST',
        data:{submit:submit},
        dataType:'text',
        success:function(result){
           $("#viewallusersdisplay").html(result);
      }
  }); 
}
   $('#generatepass').click(function(){
        $.ajax({
            url:'../genUserPassword',
            method:'POST',
            dataType:'text',
            success:function(result){
                $('#genpass').val(result);
            }
        });
   });
   $(document).on('click','.removesel',(function(e){
    var equipmentid=$(this).data("id1");
    var thisselequipment=document.getElementById(equipmentid);
    if (thisselequipment.checked===true) {
        this.style.backgroundColor='grey';
    }
    else{
        this.style.backgroundColor='#05DBFF';
        
    }
}));
   $(document).on('click','.saverolesel',(function(e){
    var equipmentid=$(this).data("id1");
    var thisselequipment=document.getElementById(equipmentid);
    if (thisselequipment.checked===true) {
        this.style.backgroundColor='grey';
    }
    else{
        this.style.backgroundColor='#05DBFF';
        
    }
}));
$(document).on('click','.deleterolesel',(function(e){
    var equipmentid=$(this).data("id1");
    var thisselequipment=document.getElementById(equipmentid);
    if (thisselequipment.checked===true) {
        this.style.backgroundColor='grey';
    }
    else{
        this.style.backgroundColor='#05DBFF';
        
    }
}));
$(document).on('click','.viewrolesel',(function(e){
    var equipmentid=$(this).data("id1");
    var thisselequipment=document.getElementById(equipmentid);
    if (thisselequipment.checked===true) {
        this.style.backgroundColor='grey';
    }
    else{
        this.style.backgroundColor='#05DBFF';
        
    }
}));
$(document).on('click','.updaterolesel',(function(e){
    var equipmentid=$(this).data("id1");
    var thisselequipment=document.getElementById(equipmentid);
    if (thisselequipment.checked===true) {
        this.style.backgroundColor='grey';
    }
    else{
        this.style.backgroundColor='#05DBFF';
        
    }
}));
$(document).on('click','.removesel',(function(e){
    var equipmentid=$(this).data("id1");
    var thisselequipment=document.getElementById(equipmentid);
    if (thisselequipment.checked===true) {
        this.style.backgroundColor='grey';
    }
    else{
        this.style.backgroundColor='#05DBFF';
        
    }
}));
$(document).on('click','.delete',(function(e){
      e.preventDefault();
        var pno=$(this).data('id1');
        var delfullname=$(this).data('id2');
        var doResend= confirm("Do you want to Delete Patient "+delfullname+" Detailss ?");
          if(doResend==true){
                   $('#msg').html('<h5 align="center" class="border"><br><br><br><br> <div style="margin:0 auto; text-align:center;">  Deleting Patient '+delfullname+' ...  <br>  <img src="../dist/img/loading.gif" ><br></div></h4>');
                   $("#myModalLabel").html("Deleting Patient Details");
                   $("#myModal").modal('show');
                   var submit="DeletePatient";
                    $.ajax({
                        url:'../medicUser',
                        type:'POST',
                        data:{submit:submit,pno:pno,delfullname:delfullname},
                        dataType:'text',
                        success:function(result){
                            $("#msg").html(result);
                            $("#myModalLabel").html("DELETING MESSAGE");
                            $("#myModal").modal('show');
                            getpatintrecords();
                        }
                    }); 
           }
    
    }));
   $(document).on('click','.deleterecord',(function(e){
      e.preventDefault();
        var pino=$(this).data('id1');
        var cover=$(this).data('id2');
        var pno=$(this).data('id3');
        var doResend= confirm("Do you want to Delete "+cover+" Patient Record?");
          if(doResend==true){
                   $('#msg').html('<h5 align="center" class="border"><br><br> <div style="margin:0 auto; text-align:center;">  Deleting '+cover+' Patient Record ...  <br>  <img src="../dist/img/loading.gif" ><br></div></h4>');
                   $("#myModalLabel").html("Deleting Patient Record");
                   $("#myModal").modal('show');
                   var submit="DeletePatientRecord";
                    $.ajax({
                        url:'../medicUser',
                        type:'POST',
                        data:{submit:submit,pno:pno,cover:cover,pino:pino},
                        dataType:'text',
                        success:function(result){
                            $("#msg").html(result);
                            $("#myModalLabel").html("DELETING MESSAGE");
                            $("#myModal").modal('show');
                            $.ajax({
                                    url:'../medicUser',
                                    type:'POST',
                                    data:{submit:"ViewPatientRecords",pno:pno},
                                    dataType:'text',
                                    success:function(data){
                                        $('#viewresultpatientrecords').html(data);
                                        $(".ttpatientrecords").DataTable({
                                            "lengthMenu":[ [10,25,50,-1],[10,25,50,"All"] ]
                                        });
                                    }
                                });
                            getpatintrecords();
                        }
                    }); 
           }
    
    }));
   $(document).on('click','.sendnowrecord',(function(e){
        e.preventDefault();
        var pino=$(this).data('id1');
        var cover=$(this).data('id2');
        var pno=$(this).data('id3');
        var recordnotes=$(this).data('id4');
        // var doResend= confirm("Do you want to Delete "+cover+" Patient Record?");
        //   if(doResend==true){
            $('#msg').html('<h5 align="center" class="border"><br><br> <div style="margin:0 auto; text-align:center;">  Loading '+cover+' Patient Record to Send Information ...  <br>  <img src="../dist/img/loading.gif" ><br></div></h4>');
            $("#myModalLabel").html("Sending Patient Record");
            $("#myModal").modal('show');
            var submit="SendFormPatientRecord";
            $.ajax({
                url:'../medicUser',
                type:'POST',
                data:{submit:submit,pno:pno,cover:cover,pino:pino,recordnotes:recordnotes},
                dataType:'text',
                success:function(result){
                    $("#msg").html(result);
                    $("#myModalLabel").html("Sending Patient Record:"+ cover);
                    $("#myModal").modal('show');
                    CKEDITOR.replace('recordnotes', {
                        uiColor: '#44B8A4'
                    });
                   $(document).on('keyup','#searchsendreceipient',function(e){
                    e.preventDefault();
                    var searchsendreceipient=$("#searchsendreceipient").val();
                       $('#viewsendreceipient').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Saving Patient Treatment Record ...   <img src="../dist/img/spinner.gif" ><br></div></h5>');
                        var submit="SendReceipient";
                        $.ajax({
                            url:'../medicUser',
                            data:{submit:submit,searchsendreceipient:searchsendreceipient},
                            success:function(result){
                                $("#viewsendreceipient").html(result);
                                //send here
                               
                           }
                        }); 
                    });
                }
            }); 
    }));
 $(document).on('click','.sendingreceipient',function(e){
   e.preventDefault();
    var id=$(this).data('id1');
    var recepientname=$(this).data('id2');
    var recepientgroup=$(this).data('id3');
    var level=$(this).data('id4');
    var recordnotess=CKEDITOR.instances['recordnotes'].getData();
    var doResend= confirm("Do you want to Send Patient Information to "+recepientname+" ?");
    if(doResend==true){
        $('#saverecordresultpatientsent').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Sending Patient Information to '+recepientname+' ...  <br>  <img src="../dist/img/loading.gif" ><br></div></h5>');
            var submit="AddSendPatientInformation";
            $.ajax({
                url:'../medicUser',
                type:'POST',
                data:{submit:submit,recepientname:recepientname,recepientgroup:recepientgroup,recordnotess:recordnotess,level:level},
                dataType:'text',
                success:function(result){
                    $('#saverecordresultpatientsent').html(result);
                    loadsentinformation();
               }
           });
    }
});
//add record
// $("#addpatientrecordform").on('submit',(function(e){
$(document).on('click','.savenewpatientrecordbtn',function(){
// e.preventDefault();
var pno=$("#recordpno").val();
var patientrecord=CKEDITOR.instances['patientrecord'].getData();
var recordcovertext=$("#recordcovertext").val();
// var recorddisease=$("#recorddisease").val();
var recorddatedone=$("#recorddatedone").val();
 // alert(recorddatedone);
var doResend= confirm("Do you want to Save Patient Treatment and Disease Information ?");
if(doResend==true){
   $('#saverecordresultpatient').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Saving Patient Treatment Record ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
        var submit="SavePatientRecord";
     $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:submit,pno:pno,patientrecord:patientrecord,recordcovertext:recordcovertext,recorddatedone:recorddatedone},
            dataType:'text',
            success:function(result){
                 $("#saverecordresultpatient").html(result);
                 $.ajax({
                    url:'../medicUser',
                    type:'POST',
                    data:{submit:"ViewPatientRecords",pno:pno},
                    dataType:'text',
                    success:function(data){
                        $('#viewresultpatientrecords').html(data);
                    }
                });
                getpatintrecords();
                 
           }
       }); 
   }
});
   $(document).on('click','.newrecords',(function(e){
      e.preventDefault();
        var pno=$(this).data('id1');
        var delfullname=$(this).data('id2');
       $('#myViewmsg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Viewing Patient '+delfullname+' ...  To Add New Treatment Record<br>  <img src="../dist/img/loading.gif" ><br></div></h4>');
       $("#myViewModalLabel").html("Record of Patient Information");
       $("#myViewModal").modal('show');
       var submit="NewPatientInformation";
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:submit,pno:pno,delfullname:delfullname},
            dataType:'text',
            success:function(result){
               $('#myViewmsg').html(result);
               $("#myViewModalLabel").html("Adding Patient Record of Treament");
               $("#myViewModal").modal('show');
               CKEDITOR.replace('patientrecord', {
                    uiColor: '#94B8E4'
                });
               $(".ttpatientrecords").DataTable({
                    "lengthMenu":[ [10,25,50,-1],[10,25,50,"All"] ]
                });
               $("#addpatientrecordform").on('submit',(function(e){
                    e.preventDefault();
                    var pno=$("#recordpno").val();
                    var patientrecord=CKEDITOR.instances['patientrecord'].getData();
                    var recordcovertext=$("#recordcovertext").val();
                       // var recorddisease=$("#recorddisease").val();
                    var recorddatedone=$("#recorddatedone").val();
                    var doResend= confirm("Do you want to Save Patient Treatment and Disease Information ?");
                    if(doResend==true){
                       $('#saverecordresultpatient').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Saving Patient Treatment Record ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
                            var submit="SavePatientRecord";
                         $.ajax({
                                url:'../medicUser',
                                type:'POST',
                                data:{submit:submit,pno:pno,patientrecord:patientrecord,recordcovertext:recordcovertext,recorddatedone:recorddatedone},
                                dataType:'text',
                                success:function(result){
                                     $("#saverecordresultpatient").html(result);
                                     $.ajax({
                                        url:'../medicUser',
                                        type:'POST',
                                        data:{submit:"ViewPatientRecords",pno:pno},
                                        dataType:'text',
                                        success:function(data){
                                            $('#viewresultpatientrecords').html(data);
                                        }
                                    });
                                    getpatienttreatmentrecords(pno);
                               }
                           }); 
                       }
               }));
            }
        }); 
    }));
   $(document).on('click','.sendrecords',(function(e){
      e.preventDefault();
        var pno=$(this).data('id1');
        var delfullname=$(this).data('id2');
       $('#myViewmsg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Viewing Patient '+delfullname+'   To Send Record Information ...<br>  <img src="../dist/img/loading.gif" ><br></div></h4>');
       $("#myViewModalLabel").html("Records of Patient Information");
       $("#myViewModal").modal('show');
       var submit="NewSendPatientInformation";
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:submit,pno:pno,delfullname:delfullname},
            dataType:'text',
            success:function(result){
               $('#myViewmsg').html(result);
               $("#myViewModalLabel").html("Sending Patient Information");
               $("#myViewModal").modal('show');
               CKEDITOR.replace('patientrecord', {
                    uiColor: '#94B8E4'
                });
               $("#addpatientrecordform").on('submit',(function(e){
                    e.preventDefault();
                    var pno=$("#recordpno").val();
                    var patientrecord=CKEDITOR.instances['patientrecord'].getData();
                    var recordcovertext=$("#recordcovertext").val();
                       // var recorddisease=$("#recorddisease").val();
                    var recorddatedone=$("#recorddatedone").val();
                    var doResend= confirm("Do you want to Save Patient Treatment and Disease Information ?");
                    if(doResend==true){
                       $('#saverecordresultpatient').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Saving Patient Treatment Record ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
                            var submit="SavePatientRecord";
                         $.ajax({
                                url:'../medicUser',
                                type:'POST',
                                data:{submit:submit,pno:pno,patientrecord:patientrecord,recordcovertext:recordcovertext,recorddatedone:recorddatedone},
                                dataType:'text',
                                success:function(result){
                                     $("#saverecordresultpatient").html(result);
                                     $.ajax({
                                        url:'../medicUser',
                                        type:'POST',
                                        data:{submit:"ViewPatientRecords",pno:pno},
                                        dataType:'text',
                                        success:function(data){
                                            $('#viewresultpatientrecords').html(data);
                                        }
                                    });
                                    getpatintrecords();
                                     
                               }
                           }); 
                       }
               }));
            }
        }); 
    }));
    $(document).on('click','.newpatientallbtn',(function(e){
      e.preventDefault();
        // var pno=$(this).data('id1');
        // var delfullname=$(this).data('id2');
       $('#msg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Viewing form  To Add New Patient...<br>  <img src="../dist/img/loading.gif" ><br></div></h5>');
       $("#myModalLabel").html("New Patient Details");
       $("#myModal").modal('show');
       var submit="NewPatientForm";
        $.ajax({
            url:'../medicUser',
            type:'POST',
            dataType:'text',
            data:{submit:submit},
            success:function(result){
               $('#msg').html(result);
               $("#myModalLabel").html("New Patient Details");
               $("#myModal").modal('show');
               $("#savepatientdetailsform").on('submit',(function(e){
                    e.preventDefault();
                       var patientage=$("#patientage").val();
                       var patientfullname=$("#patientfullname").val();
                       var patientregion=$("#patientregion").val();
                       var patientcountry=$("#patientcountry").val();
                       var contactinfo=$("#contactinfo").val();
                       var placeofbirth=$("#placeofbirth").val();
                       var submit="NewPatient";
                       var doResend= confirm("Do you want to Save Patient Details?");
                        if(doResend==true){
                            $("#savepatientresult").html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Adding New Patient Details...<br>  <img src="../dist/img/spinner.gif" ><br></div></h5>');
                            $.ajax({
                               url:'../medicUser',
                               type:'POST',
                               data:{submit:submit,patientage:patientage,patientfullname:patientfullname,patientregion:patientregion,patientcountry:patientcountry,contactinfo:contactinfo,placeofbirth:placeofbirth},
                               dataType:'text',
                               success:function(result){
                                   $("#savepatientresult").html(result);
                                    getpatintrecords();
                              }
                            });
                        } 
                }));
            }
        });

    }));
   $(document).on('click','.newrecordallbtn',(function(e){
      e.preventDefault();
        // var pno=$(this).data('id1');
        // var delfullname=$(this).data('id2');
       $('#myViewmsg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Viewing form  To Add New Treatment Record...<br>  <img src="../dist/img/loading.gif" ><br></div></h4>');
       $("#myViewModalLabel").html("Record of Patient Information");
       $("#myViewModal").modal('show');
       var submit="PatientInformation";
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:submit},
            dataType:'text',
            success:function(result){
               $('#myViewmsg').html(result);
               $("#myViewModalLabel").html("Adding Patient Record of Treatment");
               $("#myViewModal").modal('show');
               $(document).on('keyup','#searchpatientitemtorecord',function(){
                    e.preventDefault();
                    var searchpatientitemtorecord=$("#searchpatientitemtorecord").val();
                       $('#viewpatienttorecord').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Saving Patient Treatment Record ...   <img src="../dist/img/spinner.gif" ><br></div></h5>');
                        var submit="NewPatientSearch";
                        $.ajax({
                            url:'../medicUser',
                            data:{submit:submit,searchpatientitemtorecord:searchpatientitemtorecord},
                            success:function(result){
                                $("#viewpatienttorecord").html(result);
                                $("#viewrecordpatients").html("<h3>Please Click patient to view add record</h3>");
                                $(document).on('click','.selectedpatient',function(){
                                    e.preventDefault();
                                    var pno=$(this).data('id1');
                                    var delfullname=$(this).data('id11');
                                    $('#viewrecordpatients').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Viewing Patient '+delfullname+' ...  To Add New Treatment Record<br>  <img src="../dist/img/loading.gif" ><br></div></h5>');
                                            var submit="AddNewPatientInformation";
                                            $.ajax({
                                                url:'../medicUser',
                                                type:'POST',
                                                data:{submit:submit,pno:pno,delfullname:delfullname},
                                                dataType:'text',
                                                success:function(result){
                                                    $("#viewrecordpatients").html(result);
                                                    CKEDITOR.replace('patientrecord', {
                                                        uiColor: '#94B8E4'
                                                    });
                                               }
                                           });
                                       //non 
                                });
                           }
                        }); 
               });

            }
        });

    }));
   $(document).on('click','.sharerecordall',(function(e){
      e.preventDefault();
        // var pno=$(this).data('id1');
        // var delfullname=$(this).data('id2');
       $('#myViewmsg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Viewing form  To Share Patient Treatment Record...<br>  <img src="../dist/img/loading.gif" ><br></div></h4>');
       $("#myViewModalLabel").html("Share Record of Patient Information to User Groups");
       $("#myViewModal").modal('show');
       var submit="PatientInformationShared";
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:submit},
            dataType:'text',
            success:function(result){
               $('#myViewmsg').html(result);
               $("#myViewModalLabel").html("Sharing Patient Record of Treatment to UserGroup");
               $("#myViewModal").modal('show');
               $(document).on('keyup','#searchpatientitemtoshare',function(){
                    e.preventDefault();
                    var searchpatientitemtoshare=$("#searchpatientitemtoshare").val();
                       $('#viewpatienttoshare').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Saving Patient Treatment Shareble Information ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
                        var submit="SharePatientSearch";
                        $.ajax({
                            url:'../medicUser',
                            data:{submit:submit,searchpatientitemtoshare:searchpatientitemtoshare},
                            success:function(result){
                                $("#viewpatienttoshare").html(result);
                                $("#viewsharepatients").html("<h3>Please Click patient to view add share</h3>");
                                $(document).on('click','.shareselectedpatient',function(){ 
                                    e.preventDefault();
                                    var pno=$(this).data('id1');
                                    var delfullname=$(this).data('id11');
                                    getsharedgroups(pno,delfullname);
                                });
                                $(document).on('click','.addgroup',function(){ 
                                    e.preventDefault();
                                    var pno=$(this).data('id4');
                                    var userrole=$(this).data('id2');
                                    var sharedgroups=$(this).data('id3');
                                    var submit="AddSharedGroup";
                                    var doResend= confirm("Do you want to Share This Patient To : "+userrole+"?");
                                    if(doResend==true){
                                        $.ajax({
                                            url:'../medicUser',
                                            type:'POST',
                                            data:{submit:submit,pno:pno,userrole:userrole,sharedgroups:sharedgroups},
                                            dataType:'text',
                                            success:function(result){
                                                alert(result);
                                                getsharedgroupsrefresh(pno);
                                           }
                                       });
                                    }
                                    
                                });
                                $(document).on('click','.removegroup',function(){ 
                                    e.preventDefault();
                                    var pno=$(this).data('id1');
                                    var userrole=$(this).data('id2');
                                    var sharedgroups=$(this).data('id3');
                                    var submit="RemoveSharedGroup";
                                    var doResend= confirm("Do you want to Unshare This Patient From : "+userrole+"?");
                                    if(doResend==true){
                                        $.ajax({
                                            url:'../medicUser',
                                            type:'POST',
                                            data:{submit:submit,pno:pno,userrole:userrole,sharedgroups:sharedgroups},
                                            dataType:'text',
                                            success:function(result){
                                                alert(result);
                                                getsharedgroupsrefresh(pno);
                                           }
                                       });
                                    }
                                    
                                });
                           }
                        }); 
               });

            }
        });

    }));

$(document).on('click','.update',(function(e){
      e.preventDefault();
        var pno=$(this).data('id1');
        var delfullname=$(this).data('id2');
       $('#msg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Viewing Patient '+delfullname+' ...  <br>  <img src="../dist/img/loading.gif" ><br></div></h4>');
       $("#myModalLabel").html("Viewing Patient Details");
       $("#myModal").modal('show');
       var submit="ViewEditPatient";
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:submit,pno:pno,delfullname:delfullname},
            dataType:'text',
            success:function(result){
               $('#msg').html(result);
               $("#myModalLabel").html("VIEWING PATIENT");
               $("#myModal").modal('show');
               $("#updatepatientdetailsform").on('submit',(function(e){
                    e.preventDefault();
                    var pno=$("#updatepno").val();
                    var patientage=$("#updatepatientage").val();
                    var fullname=$("#updatefullname").val();
                    var patientregion=$("#updatepatientregion").val();
                    var patientcountry=$("#updatepatientcountry").val();
                    var contactinfo=$("#updatecontactinfo").val();
                    var placeofbirth=$("#updateplaceofbirth").val();
                    var doResend= confirm("Do you want to Update Patient "+fullname+" Details ?");
                    if(doResend==true){
                       $('#updateresultpatient').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;">  Updating Patient '+fullname+' ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
                       var submit="UpdatePatient";
                        $.ajax({
                           url:'../medicUser',
                           type:'POST',
                           data:{submit:submit,pno:pno,patientage:patientage,fullname:fullname,patientregion:patientregion,patientcountry:patientcountry,contactinfo:contactinfo,placeofbirth:placeofbirth},
                           dataType:'text',
                           success:function(result){
                                $("#updateresultpatient").html(result);
                                getpatintrecords();
                          }
                      }); 
                  }
               }));
            }
        }); 
    }));
   //add new role form
$(document).on('click','#addrole',(function(e){
      e.preventDefault();
        $('#myViewmsg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Loading New Role Form  ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
        $("#myViewModalLabel").html("Add New Role");
        $("#myViewModal").modal('show');
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:"AddNewRoleForm"},
            dataType:'text',
            success:function(result){
                  $('#myViewmsg').html(result);
                  $("#myViewModalLabel").html("Adding New Role Form");
                  $("#myViewModal").modal('show');
                  getroles("ViewRoles","","","");
                  //add new role here
                  $("#addnewroleform").on('submit',(function(e){
                    e.preventDefault();
                        var rolename=$("#rolename").val();
                        var roletype=$("#roletype").val();
                        var description=$("#description").val();
                        var labusage=$("#labusage").val();
                       $('#addnewroleresult').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Adding Role Details  ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
                        $.ajax({
                            url:'../medicUser',
                            type:'POST',
                            data:{submit:"AddNewRole",rolename:rolename,roletype:roletype,description:description},
                            dataType:'text',
                            success:function(result){
                               $("#addnewroleresult").html(result);
                               getroles("ViewRoles","","","");
                          }
                      }); 
                }));
                  
            }
        });
  }));
$(document).on('click','#adduserrole',(function(e){
      e.preventDefault();
        $('#myViewmsg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Loading New UserRole Form  ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
        $("#myViewModalLabel").html("Add New UserRole");
        $("#myViewModal").modal('show');
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:"AddNewUserRoleForm"},
            dataType:'text',
            success:function(result){
                  $('#myViewmsg').html(result);
                  $("#myViewModalLabel").html("Adding New UserRole Form");
                  $("#myViewModal").modal('show');
                  getuserroles("ViewUserRoles","","","");
                  //add new role here
                  $("#addnewuserroleform").on('submit',(function(e){
                    e.preventDefault();
                    var saveroles='';
                    var viewroles='';
                    var updateroles='';
                    var deleteroles='';
                    var userrolename=$('#userrolename').val();
                    var description=$('#description').val();
                    var saves=$('.savesrole');
                    var views=$('.viewsrole');
                    var updates=$('.updatesrole');
                    var deletes=$('.deletesrole');
                    for (var i=0;i<saves.length;i++){
                        if (saves[i].checked===true) {
                            if (saveroles.length>0) {
                                saveroles+=',';
                            }
                            saveroles+=saves[i].value;
                        }
                    }
                    for (var i=0;i<views.length;i++){
                        if (views[i].checked===true) {
                            if (viewroles.length>0) {
                                viewroles+=',';
                            }
                            viewroles+=views[i].value;
                        }
                    }
                    for (var i=0;i<updates.length;i++){
                        if (updates[i].checked===true) {
                            if (updateroles.length>0) {
                                updateroles+=',';
                            }
                            updateroles+=updates[i].value;
                        }
                    }
                    for (var i=0;i<deletes.length;i++){
                        if (deletes[i].checked===true) {
                            if (deleteroles.length>0) {
                                deleteroles+=',';
                            }
                            deleteroles+=deletes[i].value;
                        }
                    }
                    if (deleteroles.length==0) {
                        deleteroles='Non';
                    }
                    if (saveroles.length==0) {
                        saveroles='Non';
                    }
                    if (updateroles.length==0) {
                        updateroles='Non';
                    }
                    if (viewroles.length==0) {
                        viewroles='Non';
                    }
                       $('#addnewuserroleresult').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Adding Userroles Details  ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
                        $.ajax({
                            url:'../medicUser',
                            type:'POST',
                            data:{submit:"AddNewUserRole",saveroles:saveroles,updateroles:updateroles,viewroles:viewroles,deleteroles:deleteroles,userrolename:userrolename,description:description},
                            dataType:'text',
                            success:function(result){
                               $("#addnewuserroleresult").html(result);
                               getuserroles("ViewUserRoles","","","");
                               loaduserroles();
                          },
                          error:function(msg){
                            // alert("Could not:"+msg);
                          }
                      }); 
                }));
            }
        });
  }));
$(document).on('click','#viewusers',(function(e){
      e.preventDefault();
        $('#myViewmsg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Loading View Users Form  ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
        $("#myViewModalLabel").html("View Users");
        $("#myViewModal").modal('show');
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:"ViewUsers"},
            dataType:'text',
            success:function(result){
                  $('#myViewmsg').html(result);
                  $("#myViewModalLabel").html("View Users Form");
                  $("#myViewModal").modal('show');
                  getusers("ViewAllUsers");
            }
        });
  }));
  //remove userrole  form
$(document).on('click','#removeuserrole',(function(e){
      e.preventDefault();
        var userroleid=$(this).data('id1');
        var userrole=$(this).data('id2');
        var description=$(this).data('id3');
        $('#msg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Loading Remove UserRole Form for '+userrole+' ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
        $("#myModalLabel").html("Remove Role");
        $("#myModal").modal('show');
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:"RemoveRoleForm",userroleid:userroleid,userrole:userrole},
            dataType:'text',
            success:function(result){
                  $('#msg').html(result);
                  $("#myModalLabel").html("Remove Role Form for "+userrole);
                  $("#myModal").modal('show');
                  getuserroles("ViewUserRoles","","","");
                  // getrolesuser("ViewRemoveRoles",userroleid,"","");
                  //add new role here
                  $("#removeroleform").on('submit',(function(e){
                    e.preventDefault();
                    var removesel='';
                    var saves=$('.removesels');
                    for (var i=0;i<saves.length;i++){
                        if (saves[i].checked===true) {
                            if (removesel.length>0) {
                                removesel+=',';
                            }
                            removesel+=saves[i].value;
                        }
                    }
                       $('#removeuserroleresult').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Removing Role Details  ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
                        $.ajax({
                            url:'../medicUser',
                            type:'POST',
                            data:{submit:"RemoveRole",removesel:removesel},
                            dataType:'text',
                            success:function(result){
                               $("#removeuserroleresult").html(result);
                               getuserroles("ViewUserRoles","","","");
                          }
                      }); 
                }));
                  
            }
        });
  }));
  //ADD role to userrole  form
$(document).on('click','#addusersrole',(function(e){
      e.preventDefault();
        var userroleid=$(this).data('id1');
        var userrole=$(this).data('id2');
        var description=$(this).data('id3');
        $('#userrolename').val(userrole);
        $('#description').val(description);
        alert("Please select Role(s) to Add. UserRole Has Been Selected");
     
  }));
  //view userrole  form
$(document).on('click','#userroleinfo',(function(e){
      e.preventDefault();
        var userroleid=$(this).data('id1');
        var userrole=$(this).data('id2');
        var description=$(this).data('id3');
        $('#msg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Loading  UserRole Form for '+userrole+' ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
        $("#myModalLabel").html("UserRole Information");
        $("#myModal").modal('show');
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:"ViewRoleForm",userroleid:userroleid,userrole:userrole},
            dataType:'text',
            success:function(result){
                  $('#msg').html(result);
                  $("#myModalLabel").html("UserRole Information Form for "+userrole);
                  $("#myModal").modal('show');
                  getuserroles("ViewUserRoles","","","");
            }
        });
  }));
 //delete userrole  form
$(document).on('click','#deleteuserrole',(function(e){
      e.preventDefault();
        var userroleid=$(this).data('id1');
        var userrole=$(this).data('id2');
        var description=$(this).data('id3');
        var saveconfirm= confirm("Do you want to Delete UserRole "+userrole+" ?");
            if(saveconfirm==true){
                $('#msg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Deleting UserRole '+userrole+' ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
                $("#myModalLabel").html("Delete UserRole");
                $("#myModal").modal('show');
                $.ajax({
                    url:'../medicUser',
                    type:'POST',
                    data:{submit:"DeleteUserRole",userroleid:userroleid,userrole:userrole},
                    dataType:'text',
                    success:function(result){
                          $('#msg').html(result);
                          $("#myModalLabel").html("Delete UserRole "+userrole);
                          $("#myModal").modal('show');
                          getuserroles("ViewUserRoles","","","");
                          loaduserroles();
                    }
                });
            }
  }));
   //view user info here
  $(document).on('click','.userinfo',(function(e){
    e.preventDefault();
        var id=$(this).data('id1');
        var fullname=$(this).data('id2');
        var userroles=$(this).data('id3');
        var phone=$(this).data('id4');
        var email=$(this).data('id5');
    $('#msg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Loading View Users '+fullname+' Info Form  ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
    $("#myModalLabel").html("View Users Information for "+ fullname);
    $("#myModal").modal('show');
        $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:"ViewUserInfo",id:id,fullname:fullname,userroles:userroles,phone:phone,email:email},
            dataType:'text',
            success:function(result){
                $('#msg').html(result);
                $("#myModalLabel").html("View User Information for "+fullname);
                $("#myModal").modal('show');
          }
      }); 
}));
   //mute/disable/deactivate user here
  $(document).on('click','.muteuser',(function(e){
    e.preventDefault();
        var id=$(this).data('id1');
        var fullname=$(this).data('id2');
        var userroles=$(this).data('id3');
        var phone=$(this).data('id4');
        var email=$(this).data('id5');
        var doResend= confirm("Do you want to Deactivate User: "+fullname+"?");
        if(doResend==true){
            $('#msg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Muting/Deactivating/Disabling  User '+fullname+'  ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
            $("#myModalLabel").html("Deactivate Users : "+ fullname);
            $("#myModal").modal('show');
                $.ajax({
                    url:'../medicUser',
                    type:'POST',
                    data:{submit:"MuteUser",id:id,fullname:fullname,userroles:userroles,phone:phone,email:email},
                    dataType:'text',
                    success:function(result){
                        $('#msg').html(result);
                        $("#myModalLabel").html("Deactivating User: "+fullname);
                        $("#myModal").modal('show');
                        getusers("ViewAllUsers");
                  }
              }); 
        }
    }));
   //mute/disable/deactivate user here
  $(document).on('click','.activateuser',(function(e){
    e.preventDefault();
        var id=$(this).data('id1');
        var fullname=$(this).data('id2');
        var userroles=$(this).data('id3');
        var phone=$(this).data('id4');
        var email=$(this).data('id5');
        var doResend= confirm("Do you want to Activate User: "+fullname+"?");
        if(doResend==true){
            $('#msg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Activating User '+fullname+'  ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
            $("#myModalLabel").html("Activating User : "+ fullname);
            $("#myModal").modal('show');
                $.ajax({
                    url:'../medicUser',
                    type:'POST',
                    data:{submit:"ActivateUser",id:id,fullname:fullname,userroles:userroles,phone:phone,email:email},
                    dataType:'text',
                    success:function(result){
                        $('#msg').html(result);
                        $("#myModalLabel").html("Activating User: "+fullname);
                        $("#myModal").modal('show');
                        getusers("ViewAllUsers");
                  }
              }); 
        }
    }));
     //mute/disable/deactivate user here
  $(document).on('click','.deleteuser',(function(e){
    e.preventDefault();
        var id=$(this).data('id1');
        var fullname=$(this).data('id2');
        var userroles=$(this).data('id3');
        var phone=$(this).data('id4');
        var email=$(this).data('id5');
        var doResend= confirm("Do you want to Delete User: "+fullname+"?");
        if(doResend==true){
            $('#msg').html('<h5 align="center" class="border"><br> <div style="margin:0 auto; text-align:center;"> Deleting  User '+fullname+'  ...   <img src="../dist/img/spinner.gif" ><br></div></h4>');
            $("#myModalLabel").html("Deleting Users : "+ fullname);
            $("#myModal").modal('show');
                $.ajax({
                    url:'../medicUser',
                    type:'POST',
                    data:{submit:"DeleteUser",id:id,fullname:fullname,userroles:userroles,phone:phone,email:email},
                    dataType:'text',
                    success:function(result){
                        $('#msg').html(result);
                        $("#myModalLabel").html("Deleting User: "+fullname);
                        $("#myModal").modal('show');
                        getusers("ViewAllUsers");
                  }
              }); 
        }
    }));
     $("#saveuserdetailsform").on('submit',(function(e){
     e.preventDefault();
     $('#myModal').modal({"backdrop":"static"});
        var username=$("#username").val();
        var fullname=$("#fullname").val();
        var useridno=$("#useridno").val();
        var userphone=$("#userphone").val();
        var useremail=$("#useremail").val();
        var userrole=$("#userrole").val();
        var genpass=$("#genpass").val();
        var submit="NewUser";
     $.ajax({
            url:'../medicUser',
            type:'POST',
            data:{submit:submit,username:username,fullname:fullname,useridno:useridno,userphone:userphone,useremail:useremail,userrole:userrole,genpass:genpass},
            dataType:'text',
//            data:new FormData(this),
//            processData:false,
//            contentType:false,
//            cache:false,
            success:function(result){
                $("#msg").html(result);
                 $("#myModalLabel").html("New User Registration");
                 $("#myModal").modal('show');
                 $.ajax({
                    url:'../medicUser',
                    type:'POST',
                    data:{submit:submit},
                    dataType:'text',
                    success:function(result){
                        $('#manageusersinfo').html(result);
                    }
               }); 
           }
       }); 
}));
</script>
</html>

