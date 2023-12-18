<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org"
	xmlns:layout="http://www.ultraq.net.nz/thymleaf/layout"
	layout:decorate="template6">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>DB</title>
</head>
<body>
	<div layout:fragment="pageContent">
		<div class="container mt-2">
		  <div class="col-md-12">
			<div class="card">
			  
				<div class="card-header">DB</div>
				<div class="card-body">
					<form th:action="@{executeRequest}" method="Post">
						<div class="form-group">
							<label class="control-label">Request:</label> 
							<textarea name="rqst" class="form-control" cols="30" rows="7"  th:text="${rqst}"></textarea>
							<span></span>
							
						</div>
						m-group">m-group">
							<label class="control-label">Request:</label> 
							<textarea name="rqst" class="form-control" cols="30" rows="7"  th:text="${rqst}"></textarea>
							<span></span>
							
						</div>
						<button class="btn btn-success" type="submit">Executer</button>
							<div class="form-group">
							<label class="
							<label class="control-label">Request:</label> 
							<textarea name="rqst" class="form-control" cols="30" rows="7"  th:text="${rqst}"></textarea>
							<span></span>
							
						</div>
						<button class="btn btn-success" type="submit">Executer</button>
							<div class="form-group">
							<label class="<button class="btn btn-success" type="submit">Executer</button>
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
</body>
</html>