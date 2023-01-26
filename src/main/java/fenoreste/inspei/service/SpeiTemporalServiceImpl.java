package fenoreste.inspei.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fenoreste.inspei.dao.SpeiTemporalDao;
import fenoreste.inspei.entity.SpeiTemporal;

@Service
public class SpeiTemporalServiceImpl implements ISpeiTemporalService{
    
	@Autowired
	private SpeiTemporalDao speiTemporalDao;
    
	@Override
	public void guardar(SpeiTemporal mov) {
		speiTemporalDao.save(mov);
	}

	@Override
	@Transactional
	public void eliminar(String sesion) {
		List<SpeiTemporal>todasAplicado = speiTemporalDao.todasAplicado(sesion);
		for(int i = 0;i<todasAplicado.size();i++) {
			SpeiTemporal spei = todasAplicado.get(i);
			System.out.println(spei.isAplicado());
			speiTemporalDao.delete(todasAplicado.get(i));
		}
	}
	
	

}
