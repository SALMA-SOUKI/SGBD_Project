<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet"  type="text/css"  href="https://cdn.usebootstrap.com/bootstrap/4.4.1/css/bootstrap.min.css" >
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
  <script src="https://cdn.usebootstrap.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  <link href="http://fonts.googleapis.com/css?family=Open+Sans:400,700,600" rel="stylesheet" type="text/css">
<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.min.css'><link rel="stylesheet" href="./style2.css"><title>Insert title here</title>
    
    <link rel="stylesheet" href="./app.css">
        <link rel="stylesheet" href="./css.css">
    
</head>
<body>

<% int t =0; %>


<div id="contentWrapper">

    <div id="contentLeft">

        <ul id="leftNavigation">

  			<li class="clickable active" onclick="myFunction3()">
                <a href="#"><i class="fa fa-database leftNavIcon"></i> New DataBase </a>
            </li>
            
            
          <!--   <li class="active">
                <a href="#"><i class="fa fa-coffee leftNavIcon"></i> About us</a>
                <ul>
                    <li>
                        <a href="#"><i class="fa fa-angle-right leftNavIcon"></i> Our History</a>
                    </li>
                    <li>
                        <a href="#"><i class="fa fa-angle-right leftNavIcon"></i> Our Team</a>
                    </li>
                </ul>
            </li>--> 
            
            <% int k = 0; %>
           
           <c:forEach var="u" items="${user_data.entrySet()}">
           
           
            <li onclick="getDb('${u.getKey()}')">
                <a href="#"><i class="fa fa-database leftNavIcon"></i> ${u.getKey()}</a>
                
                <ul>
                <c:forEach var="tab" items="${u.getValue()}">
                                       <% k = k+1; %>
                    <li  onclick="myFunction('${tab}<%=k%>')">
                        <a href="#"><i class="fa fa-angle-right leftNavIcon"></i>${tab}</a>
                    </li>   
                      
                  </c:forEach>
                  
                </ul>
            </li>
            
            </c:forEach>
            
            <!-- here  -->             
            
             <li class="clickable active" id="btnsign2">
                <a><i class="fas fa-sign-out leftNavIcon"></i> SignIn to another SGBD </a>
       	 	</li>
            
             <li class="clickable active" id="logout">
               <a> <i class="fas fa-sign-out leftNavIcon"></i> Log out </a>
       	 	</li>
           
            
        </ul>
        
        
       

         
    </div>
    
    
    
    
    

    <div id="contentRight" >
         <c:forEach var="data_table" items="${tables_data}">
     
      <c:forEach var="data_table2" items="${data_table.entrySet()}">
                 <% t=t+1; %>
      
      

<div class="table-wrapper" id="${data_table2.getKey()}<%=t %>"  style="display:none" > <!-- style="display:none" --><!--  id="${data_table2.getKey()}" -->

	 <form action="addArticle.html" method="post">
	  <table class="fl-table">
	  <thead>
	<c:forEach var="tables_metadat" items="${tables_metadata}">
		<c:forEach var="tables_metadata2" items="${tables_metadat.entrySet()}">
		
		
		



	
	<c:if test="${tables_metadata.indexOf(tables_metadat) == tables_data.indexOf(data_table) && tables_metadata2.getKey() == data_table2.getKey()}">
	  <tr>
	  	<c:forEach var="metadata_value" items="${tables_metadata2.getValue()}">
	  
	    <th>${metadata_value.split(";")[0]}</th>    
	     
	   </c:forEach>
	    
	  </tr>
	  
	  </c:if>
	 </c:forEach>
	  	 </c:forEach>
	  
	  </thead>	
	  <tbody>

	  <c:forEach var="content_table" items="${data_table2.getValue()}">
	    <tr>
	   <c:forEach var="column_data" items="${content_table.split(';')}">
	    
	     <td>${column_data}</td>
	     
	   </c:forEach>
	    
	    </tr>
	  </c:forEach>
	  
	  <!-- &name=${d.name}&numberEmployee=${d.numberEmployee} -->
	 </tbody>
	 </table>
	            
	   
	 	  
	 	  
	 
	   </form>
	
				
				<button class="btnn" onclick="myFunction2('${data_table2.getKey()}')"> <span>Console</span></button> 
	
	   
	  </div>
	  
	  </c:forEach>
	  
	  	   
	  	   	  	  	  </c:forEach>
	  	   
	  	   
	  	   
	  	   
	  	   
	  	   
	  	   
	  	   <div class="login-body" id="newDatabase" style="display:none">
	  	   
	  	    <form class="form-style-7"  action="creation.html" method="POST">
                <ul>
                <li>
                    <label  for="fname">Create database yourDataBase ;</label>
                    <input type="text" name="name"  minlength="10" maxlength="40">
                    <span>Enter your full name here</span>
                </li>
                <li>
                    <label for="email">use yourDataBase ;</label>
                    <input type="text" name="email"  required minlength="6" maxlength="26">
                    <span>Enter a valid email address</span>
                </li>
                <li>
                    <label for="url">Create yourTable (  att1[type;pk]  ,  att2[type]  ,... ) ;</label>
                    <input type="text" name="url" maxlength="100" onc>
                    <span>azertyu</span>
                
                <li>
                    <input type="submit" value="Create your data base " >
                </li>
                </ul>
                </form>
	  	   
	  	   
	  	   </div>
	  	   
	  	   
	  	  <!-- other  --> 
	  	   
	  	   
      <div class="user-modal" id="contsign2" style="display:none">
		<div class="user-modal-container">
			
         <form class="form" action="VerifyUser3.html" method="post">

					<p class="fieldset">
						<label class="image-replace username" for="signin-email">Admin Login</label>
						<input class="full-width has-padding has-border" id="signin-emailAdmin" type="text" placeholder="Admin Login" name="adminlogin">
						<input class="full-width has-padding has-border" id="signin-emailAdmin" type="text" placeholder="Admin Login" name="login" style="display:none" value="${login}">
						<input class="full-width has-padding has-border" id="signin-emailAdmin" type="text" placeholder="Admin Login" name="password" style="display:none" value="${auth.get(0).get(1)}">
												
						<span class="error-message">An account with this email address does not exist!</span>
					</p>
					
					

					<p class="fieldset">
						<input class="full-width" type="submit" value="Login">
					</p>
					
					<p class="form-bottom-message" id="addUser"><a>Add a user</a></p>
					
				</form>
		
		</div>
		</div>
	  	   
	  	   
	  	   
	  	   
	  	   
	  	 <div class="user-modal" id="ContaddUser">
		<div class="user-modal-container">
			
         <form class="form" action="addUser2.html" method="post">
	<c:if test="${result != null}">
		<p class="error"> ${result} </p>
	</c:if>

					
					<p class="fieldset">
						<label class="image-replace username" for="signin-email">User name</label>
						
						<input class="full-width has-padding has-border" id="signin-emailAdmin" type="text" placeholder="User name" name="Username">
						<input class="full-width has-padding has-border" id="signin-emailAdmin" type="text" placeholder="Admin Login" name="login" style="display:none" value="${login}">
						<input class="full-width has-padding has-border" id="signin-emailAdmin" type="text" placeholder="Admin Login" name="password" style="display:none" value="${auth.get(0).get(1)}">
												
					</p>
					
					<p class="fieldset">
						<label class="image-replace username" for="signin-email">Data Name</label>
						<input class="full-width has-padding has-border" id="signin-email" type="text" placeholder="Data Name" name="DataName">
						<span class="error-message">An account with this email address does not exist!</span>
					</p>
					
					<p class="fieldset">
						<label class="image-replace username" for="signin-email">privileges</label>
						<input class="full-width has-padding has-border" id="signin-email" type="text" placeholder="privileges" name="privileges">
						<span class="error-message">An account with this email address does not exist!</span>
					</p>
					

					<p class="fieldset">
						<input class="full-width" type="submit" value="Add">
						
					</p>
					
					<p class="form-bottom-message" id="connect"><a>Connect to database</a></p>
					
					
				</form>
		
		</div>
		</div>
	  	   
	  	   
	  	   
	  	   
	  	   
	  	   
    <!-- lets make a simple HTML code editor -->

    <div class="container" id="console" style="display:none">
    
   
       
		<div class="container mt-2">
		  <div class="col-md-12">
			<div class="card">
			  
				<div class="card-header">DB</div>
				<div class="card-body">
					<form action="executeRequest.html" method="Post">
						<div class="form-group">
							<textarea name="login" cols="30" rows="7"  th:text="${login}" style="display:none" >${login}</textarea>
							<label class="control-label">Request:</label> 
							<textarea name="rqst" class="form-control" cols="30" rows="7"  th:text="${rqst}"></textarea>
							
							<span></span>
							
						</div>
						<button class="btn btn-success" type="submit">Executer</button>
							<div class="form-group">
							<label class="control-label">Response:</label> 
							<textarea name="fdbb" class="form-control" cols="30" rows="5" th:text="${fdbb}"></textarea>
							<span></span>
							</div>
					</form>
				
				</div>
			  </div>	
			</div>
		

	</div>
    </div>
	  	   
	
</div>


	   






</div>


<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<script src="jquery.ssd-vertical-navigation.min.js"></script>
<script src="./app.js"></script>
        <script src="./main.js"></script>
         <script src="./script.js"></script>
        
      

<script>
var div = document.getElementById("console");
var div2 = document.getElementById("newDatabase");
var contsign2 = document.getElementById("contsign2");
var btnsign2 = document.getElementById("btnsign2");
var addUser = document.getElementById("addUser");
var ContaddUser = document.getElementById("ContaddUser");
var connect = document.getElementById("connect");
var logout = document.getElementById("logout");
var dataB;
var dataT;
var login = '<%=request.getAttribute("login")%>'; 
var tables = new Array();
var i = 0;
 <c:forEach var="u" items="${user_data.entrySet()}">
     <c:forEach var="tab" items="${u.getValue()}">
        tables[i] = '${tab}'+(i+1);
        console.log(tables[i])
        i++;
      </c:forEach>
</c:forEach>

function myFunction(kar){
	
	
	
	div.style.display = "none";
	div2.style.display = "none";

	
	for(let i=0;i<tables.length;i++){
		var tab = document.getElementById(tables[i]);
		tab.style.display = "none";
	}
	contsign2.style.display = "none";
	ContaddUser.style.display = "none";

	var table = document.getElementById(kar);
	table.style.display = "block";
}


function myFunction2(table){
	for(let i=0;i<tables.length;i++){
		var tab = document.getElementById(tables[i]);
		tab.style.display = "none";
	}
	
	div2.style.display = "none";
	contsign2.style.display = "none";
	ContaddUser.style.display = "none";


	div.style.display = "block";
	dataT = table

	console.log(dataT);


}

function myFunction3(){
	for(let i=0;i<tables.length;i++){
		var tab = document.getElementById(tables[i]);
		tab.style.display = "none";
	}
	div.style.display = "none";
	div2.style.display = "block";
	ContaddUser.style.display = "none";

	contsign2.style.display = "none";

}

btnsign2.addEventListener("click",function(){
	for(let i=0;i<tables.length;i++){
		var tab = document.getElementById(tables[i]);
		tab.style.display = "none";
	}
	div.style.display = "none";
	div2.style.display = "none";
	ContaddUser.style.display = "none";
	contsign2.style.display = "block";
	
	
})


addUser.addEventListener("click",function(){
	for(let i=0;i<tables.length;i++){
		var tab = document.getElementById(tables[i]);
		tab.style.display = "none";
	}
	div.style.display = "none";
	div2.style.display = "none";
	ContaddUser.style.display = "block";
	
})
	
connect.addEventListener("click",function(){
	for(let i=0;i<tables.length;i++){
		var tab = document.getElementById(tables[i]);
		tab.style.display = "none";
	}
	div.style.display = "none";
	div2.style.display = "none";
	ContaddUser.style.display = "none";
	contsign2.style.display = "block";
	
})

logout.addEventListener("click",function(){
	window.location.href  = "http://localhost:8080/SGBD/SignIn.jsp"
		console.log("logout")

})


function getDb(database){
	
	dataB = database;
	console.log(dataB);
	console.log(login);

	
}

</script>
</body>
</html>