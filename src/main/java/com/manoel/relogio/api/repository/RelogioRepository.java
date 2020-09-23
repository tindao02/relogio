package com.manoel.relogio.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.manoel.relogio.api.model.Relogio;

@Repository
public interface RelogioRepository extends JpaRepository<Relogio, Long>
{

}
