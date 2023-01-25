package fenoreste.inspei.service;

import java.util.List;

import fenoreste.inspei.entity.AbonoSpei;

public interface IAbonoSpeiService {
    
	public AbonoSpei buscarPorId(String id);
	public List<AbonoSpei>todasPorFecha(Integer fecha);
}
