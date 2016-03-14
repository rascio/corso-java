<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags/layout" prefix="rubrica" %>
<%@ taglib tagdir="/WEB-INF/tags/contatti" prefix="contatti" %>
<rubrica:main-container>
	<div class="row">
		<contatti:list />
		<div class="col-md-8">
			<h3>${dettaglio.nome } ${dettaglio.cognome }</h3>
			<div>
				email: ${dettaglio.email }
			</div>
			<div>
				telefono: ${dettaglio.telefono }
			</div>
		</div>
	</div>
</rubrica:main-container>
