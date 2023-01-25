package fenoreste.inspei.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fenoreste.inspei.entity.AbonoSpei;

public interface AbonoSpeiDao extends JpaRepository<AbonoSpei,String>{
   
	List<AbonoSpei> findByfechaOperacion(Integer fecha);
}
