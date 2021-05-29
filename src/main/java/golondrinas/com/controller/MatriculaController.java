package golondrinas.com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import golondrinas.com.model.DetalleMatricula;
import golondrinas.com.model.Matricula;
import golondrinas.com.model.response.ResultadoResponse;
import golondrinas.com.service.AlumnoService;
import golondrinas.com.service.DetalleMatriculaService;
import golondrinas.com.service.DetallerUsuarioService;
import golondrinas.com.service.GradoService;
import golondrinas.com.service.MatriculaService;
import golondrinas.com.service.NivelService;
import golondrinas.com.service.SeccionService;

@Controller
@RequestMapping("/Matriculas")
public class MatriculaController {

	@Autowired
	private MatriculaService service;

	@Autowired
	private AlumnoService aservice;

	@Autowired
	private NivelService nService;

	@Autowired
	private GradoService gService;

	@Autowired
	private SeccionService sService;
	
	@Autowired
	private DetalleMatriculaService dmservice;

	@GetMapping("/frmMatricula")
	public String frmMatricula(Model model) {
		model.addAttribute("lstMat", service.listarMatriculas());
		model.addAttribute("lstAlumno", aservice.listarALumno());
		model.addAttribute("lstNivel", nService.listarNivel());
		model.addAttribute("lstGrado", gService.listarGrado());
		model.addAttribute("lstSeccion", sService.listarSeccion());
		return "Matriculas/frmMatricula";
	}

	@PostMapping("/registrarMatricula")
	@ResponseBody
	public ResultadoResponse registrarMatricula(@RequestBody Matricula objMatricula) {
		String mensaje = "Matricula registrada correctamente";
		Boolean respuesta = true;
		try {
			service.RegistrarMatricula(objMatricula);
		} catch (Exception ex) {
			mensaje = "Matricula no registrada";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}

	@GetMapping("/listarMatriculas")
	@ResponseBody
	public List<Matricula> listarMatriculas() {
		return service.listarMatriculas();
	}

	@DeleteMapping("/eliminarMatricula")
	@ResponseBody
	public ResultadoResponse eliminarMatricula(@RequestBody Matricula objMatricula) {
		String mensaje = "Matricula eliminada de forma logica";
		Boolean respuesta = true;
		try {
			service.EliminarMatricula(objMatricula);
		} catch (Exception ex) {
			mensaje = "Matricula no eliminada";
			respuesta = false;
		}
		return new ResultadoResponse(respuesta, mensaje);
	}
	
	@GetMapping("/listarDetalleMatricula")
	@ResponseBody
	public List<DetalleMatricula> listarDetalleMatricula(@RequestParam("idmatricula") String idmatricula){
		return dmservice.listarDetalleMatriculas(idmatricula);
	}

}
