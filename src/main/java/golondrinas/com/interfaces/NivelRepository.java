package golondrinas.com.interfaces;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import golondrinas.com.model.Nivel;

@Repository
public interface NivelRepository extends JpaRepository<Nivel, String>{
	
	@Query(value= "{call sp_MantListarNivel()}", nativeQuery = true)
	List<Nivel> listarNiveles();
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_MantRegistrarNivel(:nombre)}", nativeQuery = true)
	void RegistrarNivel(@Param("nombre") String nombre);
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_MantActualizarNivel(:idnivel, :nombre)}", nativeQuery = true)
	void ActualizarNivel(@Param("idnivel") String idnivel, @Param("nombre") String nombre);
	
	@Transactional
	@Modifying
	@Query(value = "{call sp_MantEliminarNivel(:idnivel)}", nativeQuery = true)
	void EliminarNivel(@Param("idnivel") String idnivel);
	
	
}
