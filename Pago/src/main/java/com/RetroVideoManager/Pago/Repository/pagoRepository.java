package com.RetroVideoManager.Pago.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.RetroVideoManager.Pago.model.PagoModel;

@Repository
public interface pagoRepository extends JpaRepository<PagoModel, Long> {

}
