package fenoreste.inspei.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fenoreste.inspei.dao.AbonoSpeiDao;
import fenoreste.inspei.entity.AbonoSpei;

@Service
public class AbonoSpeiServiceImpl implements IAbonoSpeiService{
    
	@Autowired
	private AbonoSpeiDao abonoSpeiDao;
	
	@Override
	public AbonoSpei buscarPorId(String id) {
		return abonoSpeiDao.findById(id).orElse(null);
	}

	@Override
	public List<AbonoSpei> todasPorFecha(Integer fecha) {
		return abonoSpeiDao.findByfechaOperacion(fecha);
	}

}
