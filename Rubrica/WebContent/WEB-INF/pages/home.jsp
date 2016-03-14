<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="rubrica" %>
<%@ taglib tagdir="/WEB-INF/tags/contatti" prefix="contatti" %>
<rubrica:main-container>
	<div class="row">
		<contatti:list />
		<div class="col-md-8">
			<form action="${pageContext.request.contextPath }/contatti/crea" method="POST">
				<fieldset>
					<legend>Nuovo Contatto</legend>
					<div class="form-group">
						<label for="nome">Nome:</label>
						<input type="text" name="nome" id="nome" class="form-control" />
					</div>
					<div class="form-group">
						<label for="cognome">Cognome:</label>
						<input type="text" name="cognome" id="cognome" class="form-control" />
					</div>
					<div class="form-group">
						<label for="telefono">Telefono:</label>
						<input type="text" name="telefono" id="telefono" class="form-control" />
					</div>
					<div class="form-group">
						<label for="email">Email:</label>
						<input type="email" name="email" id="email" class="form-control" />
					</div>
					<div class="form-group">
						<input type="submit" class="btn btn-primary" value="Salva" />
					</div>
				</fieldset>
			</form>
		</div>
	</div>
</rubrica:main-container>
