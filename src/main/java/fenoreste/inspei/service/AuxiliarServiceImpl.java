package fenoreste.inspei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fenoreste.inspei.dao.AuxiliarDao;
import fenoreste.inspei.entity.Auxiliar;
import fenoreste.inspei.entity.AuxiliarPK;

@Service
public class AuxiliarServiceImpl implements IAuxiliarService{
    
	@Autowired
	private AuxiliarDao auxiliarDao;
	
	@Override
	public Auxiliar buscarPorId(AuxiliarPK pk) {
		return auxiliarDao.findById(pk).orElse(null);
	}

}
