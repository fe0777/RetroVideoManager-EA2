package com.notificacion.notificaciones.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.notificacion.notificaciones.Model.Notificacion;

public interface NotificacionRepository extends JpaRepository<Notificacion, Long> {

}
