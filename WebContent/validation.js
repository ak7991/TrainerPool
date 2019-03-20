function validation(){
    
    var firstname = document.getElementById("FirstName").value;
    var lastname = document.getElementById("LastName").value;
    var age = document.getElementById("Age").value;
      
    var contact = document.getElementById("Contact").value;
    var userid = document.getElementById("UserId").value;
    var emailid = document.getElementById("EmailID").value;
    var password = document.getElementById("Password").value;
    var confirmpassword = document.getElementById("ConfirmPassword").value;
      
      
   
    if (isNaN(age)) {
      alert("Age must be number. Please Input Correctly");
    }
   
    
    
    if (isNaN(contact) || contact.length < 10) {
        alert("Contact must be number. Please Input Correctly");
      }
    
    if (isNaN(userid)) {
        alert("UserID must be number. Please Input Correctly");
      }
    
   
    if(password != confirmpassword){
        alert("Password doesn't match");
    }
    
     if (password.length < 8) {
        alert("Password must be of minimum 8 characters");
       }
    if (email.length < 1) {
      alert("Invalid Email");
    	} 
    else {
      var regEx = /^[A-Z0-9][A-Z0-9._%+-]{0,63}@(?:[A-Z0-9-]{1,63}\.){1,125}[A-Z]{2,63}$/;
      var validEmail = regEx.test(EmailID);
      if (!validEmail) {
        alert("Invalid Email Please enter correctly");
      }
    }
    
    
  
}