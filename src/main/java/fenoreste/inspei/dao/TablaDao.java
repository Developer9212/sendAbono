package fenoreste.inspei.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fenoreste.inspei.entity.Tabla;
import fenoreste.inspei.entity.TablaPK;

public interface TablaDao extends JpaRepository<Tabla,TablaPK> {

}
