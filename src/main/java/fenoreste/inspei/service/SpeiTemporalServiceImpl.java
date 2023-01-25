package fenoreste.inspei.service;

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
	
	

}
