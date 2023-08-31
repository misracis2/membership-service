package com.example.membership.repository;

import com.example.membership.status.BulkPassStatus;
import com.example.membership.model.BulkPassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BulkPassRepository extends JpaRepository<BulkPassEntity, Long> {
    List<BulkPassEntity> findByStatusAndRegDateGreaterThan(BulkPassStatus status, LocalDateTime regDate);
}
