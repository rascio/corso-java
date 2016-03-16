<%@tag import="it.r.rubrica.webapp.servlet.utils.login.LoginUtils"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="it">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">
    
    <script type="text/javascript" src="https://code.jquery.com/jquery-1.12.0.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
    <script type="text/javascript">
    	var Rubrica = {
    		url: function(url) {
    			return "${pageContext.request.contextPath}" + url; 
    		}
    	}
    </script>
</head>
<body>
	<div class="container">	
		<div class="header clearfix">
        	<h3 class="text-muted">Rubrica</h3>
        	<% if (LoginUtils.isUserLogged(request)) { %>
        		<div class="pull-right">
        			Benvenuto, ${currentUser.nome } ${currentUser.cognome }
        		</div>
        	<% }
        	else {
        	%>
        	<form action="${pageContext.request.contextPath }/login" method="POST" class="form-inline pull-right">
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
        	<% } %>
      	</div>
      	<hr/>
		<jsp:doBody></jsp:doBody>
	</div>
</body>