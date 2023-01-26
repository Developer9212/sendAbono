package fenoreste.inspei.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fenoreste.inspei.dao.FuncionDao;
import fenoreste.inspei.entity.AuxiliarPK;

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

	@Override
	public Integer aplica_movs(Integer idusuario, String sesion) {
		return funcionesDao.movs_aplicados(idusuario, sesion);
	}

	@Override
	public String sai_auxiliar(AuxiliarPK pk) {
		return funcionesDao.sai_auxiliar(pk.getIdorigenp(),pk.getIdproducto(),pk.getIdauxiliar());
	}

}
