package fenoreste.inspei.service;

import org.springframework.stereotype.Service;

import fenoreste.inspei.entity.Origen;



@Service
public interface IOrigenService {

	public Origen buscarPorId(Integer id);
	public Origen buscarMatriz();
	
	
}
