package fenoreste.inspei.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fenoreste.inspei.entity.AuxiliarPK;
import fenoreste.inspei.entity.ClabeInterbancaria;

public interface ClabeInterbancariaDao  extends JpaRepository<ClabeInterbancaria,AuxiliarPK>{
	
	ClabeInterbancaria findByClabe(String clable);

}
