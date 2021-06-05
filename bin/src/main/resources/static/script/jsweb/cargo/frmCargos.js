
$(document).on("click", "#btnagregarcargo", function() {
	$("#txtnomcargo").val("");
	$("#hddidcargo").val("0");
	$("#modalcargo").modal("show");
});

$(document).on("click", ".btnactualizarcargo", function() {
	$("#txtnomcargo").val($(this).attr("data-nomcargo"));
	$("#hddidcargo").val($(this).attr("data-codcargo"));
	$("#modalcargo").modal("show");
});

$(document).on("click", "#btnregistrarcargo", function() {
	if ($("#txtnomcargo").val() === "") {
		$("#errornomcargo").text("Es obligatorio el nombre del cargo.");
	} else {
		$("#errornomcargo").text("");
	}
	if ($("#txtnomcargo").val() !== "") {
		if ($("#hddidcargo").val() === "0") {
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/Cargo/registrarCargo",
				data: JSON.stringify({
					nombre: $("#txtnomcargo").val(),
				}),
				success: function(resultado) {
					var estilo = "danger";
					if (resultado.respuesta) {
						estilo = "success";
						ListarCargos();
					}
					mostrarMensaje(resultado.mensaje, estilo);
				}
			});
		} else {
			$.ajax({
				type: "POST",
				contentType: "application/json",
				url: "/Cargo/registrarCargo",
				data: JSON.stringify({
					idcargo: $("#hddidcargo").val(),
					nombre: $("#txtnomcargo").val(),
				}),
				success: function(resultado) {
					var estilo = "danger";
					if (resultado.respuesta) {
						estilo = "success";
						ListarCargos();
					}
					mostrarMensaje(resultado.mensaje, estilo);
				}
			});
		}
		$("#modalcargo").modal("hide");
	}


});

$(document).on("click", ".btneliminarcargo", function() {
	//alert($(this).attr("data-codcurso"));
	$("#mensajeeliminar").text("¿Está seguro de eliminar el cargo: " +
		$(this).attr("data-nomcargo") + "?");
	$("#hddidcargoeliminar").val($(this).attr("data-codcargo"));
	$("#modaleliminarcargo").modal("show");
});

$(document).on("click", "#btneliminarcargo", function() {
	$.ajax({
		type: "DELETE",
		contentType: "application/json",
		url: "/Cargo/eliminarCargo",
		data: JSON.stringify({
			idcargo: $("#hddidcargoeliminar").val()
		}),
		success: function(resultado) {
			var estilo = "danger";
			if (resultado.respuesta) {
				estilo = "success";
				ListarCargos();
			}
			mostrarMensaje(resultado.mensaje, estilo);
			$("#modaleliminarcargo").modal("hide");
		}
	});
});
/*
$(document).on("input", "#txtcredcurso", function() {
	this.value = this.value.replace(/[^0-9]/g, '');
});*/

function mostrarMensaje(mensaje, estilo) {
	$("#mensaje").html("");
	$("#mensaje").append("<div class='alert alert-" + estilo
		+ " alert-dismissible fade show' role='alert'>"
		+ "<strong>" + mensaje + "</strong>"
		+ "<button type='button' class='close' data-dismiss='alert'"
		+ " aria-label='Close'>"
		+ "<span aria-hidden='true'>&times;</span></button></div>"
	);
}



function ListarCargos() {
	$.ajax({
		type: "GET",
		url: "/Cargo/listarCargos",
		dataType: "json",
		success: function(resultado) {
			$("#tblcargo > tbody").html("");
			$.each(resultado, function(index, value) {
				$("#tblcargo > tbody").append("<tr>" +
					"<td>" + value.idcargo + "</td>" +
					"<td>" + value.nombre + "</td>" +
					"<td>" + value.estado + "</td>" +
					"<td><button type='button' class='btn btn-info btnactualizarcargo' " +
					" data-codcargo='" + value.idcargo + "'" +
					" data-nomcargo='" + value.nombre + "'" +
					" data-estado='" + value.estado + "'>Actualizar</button>" +
					"</td>" +
					"<td><button type='button' class='btn btn-danger btneliminarcargo' " +
					" data-codcargo='" + value.idcargo + "'" +
					" data-nomcargo='" + value.nombre + "'>Eliminar</button>" +
					"</td></tr>")
			})

		}
	})
}
