package com.example.membership.repository;

import com.example.membership.model.PassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassRepository extends JpaRepository<PassEntity, Long> {
}
