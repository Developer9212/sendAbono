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
	
	@Query(value = "SELECT sai_spei_entrada_aplica(?1,?2)", nativeQuery = true)
	Integer movs_aplicados(Integer idusuario,String sesion);
	
	@Query(value = "SELECT sai_auxiliar(?,?,?,(SELECT date(fechatrabajo) FROM origenes LIMIT 1))" , nativeQuery = true)
	String sai_auxiliar(Integer idorigenp,Integer idproducto,Integer idauxiliar);
}
