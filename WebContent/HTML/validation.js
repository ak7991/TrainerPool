
$(document).ready(function() {

  $('#box').submit(function(e) {
   
    var FirstName =document.getElementById('FirstName').value;
    var LastName = document.getElementById('LastName').value;

    var Age = parseInt(document.getElementById('Age').value);
    var Gender =document.getElementById('Gender').value;
    var Contact = parseInt(document.getElementById('Contact').value);
    var UserID = parseInt(document.getElementById('UserID').value);
    var EmailID = document.getElementById('EmailID').value;
    var password = document.getElementById('Password').value;
    var ConfirmPassWord =document.getElementById('ConfirmPassword').value;

   
    if (isNaN(Age)) {
      alert("Age must be number. Please Input Correctly");
    }
   
    
    if (isNaN(Contact)) {
        alert("Contact must be number. Please Input Correctly");
      }
    
    if (isNaN(UserID)) {
        alert("UserID must be number. Please Input Correctly");
      }
    
    if (Password.length < 8) {
        alert("Invalid  password");
       }
    if (EmailID.length < 1) {
      alert("Invalid Email");
    	} 
    else {
      var regEx = /^[A-Z0-9][A-Z0-9._%+-]{0,63}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/;
      var validEmail = regEx.test(EmailID);
      if (!validEmail) {
        alert("Invalid Email Please enter correctly");
      }
    }
   
  });
  });

  

  