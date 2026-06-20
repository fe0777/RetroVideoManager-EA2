package com.membresias.membresias.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.membresias.membresias.Model.Membresia;

public interface MembresiaRepository extends JpaRepository<Membresia, Long> {
}
