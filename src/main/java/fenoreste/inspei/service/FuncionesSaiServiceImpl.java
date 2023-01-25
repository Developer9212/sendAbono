package fenoreste.inspei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fenoreste.inspei.dao.FuncionDao;

@Service
public class FuncionesSaiServiceImpl implements IFuncionesSaiService {
    
	@Autowired
	private FuncionDao funcionesDao;
	
	@Override
	public boolean horario_actividad() {		
		return funcionesDao.activo();
	}

	@Override
	public String session() {
		return funcionesDao.sesion();
	}

}
