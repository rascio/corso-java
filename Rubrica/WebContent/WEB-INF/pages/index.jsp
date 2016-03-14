<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="rubrica" %>
<!doctype html>
<html lang="it">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="header clearfix">
        	<h3 class="text-muted">Rubrica</h3>
        	<form action="${pageContext.request.contextPath}/login" method="POST" class="form-inline pull-right">
        		<div class="form-group">
   					<input type="text" class="form-control" name="username" id="username" placeholder="username" />
        		</div>
        		<div class="form-group">
   					<input type="password" class="form-control" name="password" id="password" placeholder="password" />
        		</div>
        		<div class="form-group">
        			<input type="submit" class="btn btn-primary" value="Login" />
        		</div>
        	</form>
      	</div>
      	<hr/>
      	<div class="row">
      		<div class="col-md-3">
      			<form action="${pageContext.request.contextPath}/registrazione" method="POST">
      				<fieldset>
      					<legend>Registrazione:</legend>
	      				<div class="form-group">
	      					<label for="username">Username:</label>
	      					<input type="text" class="form-control" name="username" id="username" />
	      				</div>
	      				<div class="form-group">
	      					<label for="password">Password:</label>
	      					<input type="password" class="form-control" name="password" id="password" />
	      				</div>
	      				<div class="form-group">
	      					<label for="nome">Nome:</label>
	      					<input type="text" class="form-control" name="nome" id="nome" />
	      				</div>
	      				<div class="form-group">
	      					<label for="cognome">Cognome:</label>
	      					<input type="text" class="form-control" name="cognome" id="cognome" />
	      				</div>
	      				<div class="form-group">
	      					<label for="email">Email:</label>
	      					<input type="email" class="form-control" name="email" id="email" />
	      				</div>
	      				<div class="form-group">
							<input type="submit" class="btn btn-primary" value="Registrati" />
	      				</div>
      				</fieldset>
      			</form>
      		</div>
      	</div>
	</div>
</body>
</html>