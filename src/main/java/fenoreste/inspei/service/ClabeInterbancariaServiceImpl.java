package fenoreste.inspei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fenoreste.inspei.dao.ClabeInterbancariaDao;
import fenoreste.inspei.entity.AuxiliarPK;
import fenoreste.inspei.entity.ClabeInterbancaria;

@Service
public class ClabeInterbancariaServiceImpl implements IClabeInterbancariaService{
    
	@Autowired
	private ClabeInterbancariaDao clabeInterbancariaDao;
	
	@Override
	public ClabeInterbancaria buscarPorId(AuxiliarPK pk) {
		return clabeInterbancariaDao.findById(pk).orElse(null);
	}

	@Override
	public ClabeInterbancaria buscarPorClabe(String clabe) {
		return clabeInterbancariaDao.findByClabe(clabe);
	}

}
