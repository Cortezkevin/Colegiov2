package golondrinas.com.interfaces;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import golondrinas.com.model.Seccion;

@Repository
public interface SeccionRepository extends JpaRepository<Seccion, String>{
	
	@Transactional
	@Modifying
	@Query(value="{call sp_MantRegistrarSeccion(:pnombre)}",nativeQuery = true)
	void registrarSeccion(@Param("pnombre")String Nombre);
	
	@Transactional
	@Modifying
	@Query(value="{call sp_MantActualizarSeccion(:pidseccion,:pnombre,:pestado)}", 
	nativeQuery=true)
	void actualizarSeccion(@Param("pidseccion")String idseccion,
			@Param("pnombre")String nombre,
			@Param("pestado")String estado);
	
	@Transactional
	@Modifying
	@Query(value="{call sp_MantEliminarSeccion(:pidseccion)}",nativeQuery = true)
	void eliminarSeccion(@Param("pidseccion")String idseccion);
	
	
}
