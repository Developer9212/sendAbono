package fenoreste.inspei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fenoreste.inspei.dao.FolioTarjetaDao;
import fenoreste.inspei.entity.AuxiliarPK;
import fenoreste.inspei.entity.FolioTarjeta;

@Service
public class FolioTarjetaServiceImpl implements IFolioTarjetaService{

	@Autowired
	private FolioTarjetaDao folioTarjetaDao;
	
	@Override
	public FolioTarjeta buscarPorId(AuxiliarPK pk) {
	   return folioTarjetaDao.findById(pk).orElse(null);
	}

}
