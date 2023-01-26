package fenoreste.inspei.service;

import fenoreste.inspei.entity.AuxiliarPK;

public interface IFuncionesSaiService {
 
	public boolean horario_actividad();
    public String session();	
    public Integer aplica_movs(Integer idusuario,String sesion);
    public String sai_auxiliar(AuxiliarPK pk);
}
