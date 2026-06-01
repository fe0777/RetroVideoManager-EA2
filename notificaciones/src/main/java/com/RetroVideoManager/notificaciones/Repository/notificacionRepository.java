package com.RetroVideoManager.notificaciones.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.RetroVideoManager.notificaciones.Model.notificacionModel;
public interface notificacionRepository extends JpaRepository<notificacionModel, Long> {

}
