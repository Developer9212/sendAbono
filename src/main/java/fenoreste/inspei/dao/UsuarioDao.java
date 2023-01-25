package fenoreste.inspei.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fenoreste.inspei.entity.Usuario;

public interface UsuarioDao extends JpaRepository<Usuario,Integer> {

}
