package fenoreste.inspei.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fenoreste.inspei.entity.AuxiliarPK;
import fenoreste.inspei.entity.FolioTarjeta;

public interface FolioTarjetaDao extends JpaRepository<FolioTarjeta, AuxiliarPK>{

}
