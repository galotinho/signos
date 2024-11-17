package com.signo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.signo.models.Signo;

@Repository
public interface SignoRepository extends JpaRepository<Signo, Long> {
}