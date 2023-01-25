package fenoreste.inspei.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import fenoreste.inspei.entity.Auxiliar;
import fenoreste.inspei.entity.AuxiliarPK;


public interface FuncionDao extends JpaRepository<Auxiliar,AuxiliarPK> {

	@Query(value="SELECT spei_entrada_servicio_activo_inactivo()",nativeQuery = true)
	public boolean activo();
	
	@Query(value = "SELECT text(pg_backend_pid())||'-'||trim(to_char(now(),'ddmmyy'))" , nativeQuery = true)
	String sesion();
}
