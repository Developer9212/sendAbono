package fenoreste.inspei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fenoreste.inspei.dao.TarjetaDao;
import fenoreste.inspei.entity.Tarjeta;

@Service
public class TarjetaServiceImpl implements ITarjetaService {
	
	@Autowired
	private TarjetaDao tarjetaDao;

	@Override
	public Tarjeta buscarPorId(String idtarjeta) {
		return tarjetaDao.findById(idtarjeta).orElse(null);
	}
	
	

}
