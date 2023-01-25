package fenoreste.inspei.service;

import fenoreste.inspei.entity.AuxiliarPK;
import fenoreste.inspei.entity.ClabeInterbancaria;

public interface IClabeInterbancariaService {
   
	public ClabeInterbancaria buscarPorId(AuxiliarPK pk);
	public ClabeInterbancaria buscarPorClabe(String clabe);
}
