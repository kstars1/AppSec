<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <script>
      window.addEventListener('input', function(e){
         var reg= /\')/;
         var reg1= /1=1/;
         var reg2= / or /;
         var reg3= /\")/;
         var reg4= / and /;
         var reg5= / union /;
         if(reg.exec(e)!=null || reg1.exec(e)!=null || reg2.exec(e)!=null || reg3.exec(e)!=null || reg4.exec(e)!=null || reg5.exec(e)!=null){
            alert("Hey there, are you using SQLInjection? Please do not");
            e.stopPropagation();
    }
});
</script>
   <head>
      <meta charset="UTF-8">
      <title>Login</title>
   </head>
   <body>
      
 
      <h3>Login Page</h3>
      <p style="color: red;">${errorString}</p>
 
 
      <form method="POST" action="${pageContext.request.contextPath}/index.jsp">
         <table border="0">
            <tr>
               <td>Username</td>
               <td><input type="text" name="username" value= "${user.username}" /> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password" value= "${user.password}" /> </td>
            </tr>
           
            <tr>
               <td colspan ="2">
                  <input type="submit" value= "Submit" />
                  <a href="${pageContext.request.contextPath}/">Cancel</a>
               </td>
            </tr>
         </table>
      </form>
      
  
      
   </body>
</html>
