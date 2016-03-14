<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="col-md-3">
	<div style="padding-bottom: 10px">
		<form class="form-inline" id="list-search-form" >
			<div class="form-group">
				<input type="text" id="list-search-term" class="form-control" />
			</div>
			<div class="form-group">
				<input type="submit" class="btn btn-primary" value="cerca" />
			</div>
		</form>
	</div>
	<ul class="list-group" id="contatti-list">
	</ul>
	<a href="${pageContext.request.contextPath }/home">Nuovo</a>
	<script type="text/javascript">
		$(function() {
			function ricerca(term) {
				$.get(Rubrica.url('/contatti/ricerca'), {testo: term}, function(response) {
					var list = $('#contatti-list');
					list.empty();
					for (var i = 0 ; i < response.list.length ; i++) {
						var contatto = response.list[i];
						var url = Rubrica.url('/contatti/dettaglio?contattoId=' + contatto.id);
						var element = $('<li class="list-group-item"><a href="' + url + '">' + contatto.nome + ' ' + contatto.cognome + '</a></li>)');
						list.append(element);
					}
				})
			}
			$('#list-search-form').submit(function(e) {
				e.preventDefault();
				var term = $('#list-search-term').val();
				ricerca(term);
			});
			ricerca('');
		});
	</script>
</div>