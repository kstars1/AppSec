<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <script>
      function check(field){
         if(field = name)
            var x = document.getElementbyId("username");
         else
            var x = document.getElementbyId("password");
         var input = x.value;
         var reg= /\')/;
         var reg1= /1=1/;
         var reg2= / or /;
         var reg3= /\")/;
         var reg4= / and /;
         var reg5= / union /;
         if(reg.exec(input)!=null || reg1.exec(input)!=null || reg2.exec(input)!=null || reg3.exec(input)!=null || reg4.exec(input)!=null || reg5.exec(input)!=null){
            alert("Hey there, are you using SQLInjection? Please do not");
            x.value = '';
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
               <td><input type="text" name="username" id="username" value= "${user.username}" onchange="check("name");" onkeypress="this.onchange(); onpaste="this.onchange()" oninput="this.onchange()"/> </td>
            </tr>
            <tr>
               <td>Password</td>
               <td><input type="password" name="password" id="password" value= "${user.password}" onchange="check("pass");" onkeypress="this.onchange(); onpaste="this.onchange()" oninput="this.onchange()"/> </td>
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
