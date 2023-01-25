package fenoreste.inspei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fenoreste.inspei.dao.TablaDao;
import fenoreste.inspei.entity.Tabla;
import fenoreste.inspei.entity.TablaPK;

@Service
public class TablaServiceImpl implements ITablaService{
	
	@Autowired
	private TablaDao tablaDao;

	@Override
	public Tabla buscarPorId(TablaPK pk) {
		return tablaDao.findById(pk).orElse(null);
	}

}
