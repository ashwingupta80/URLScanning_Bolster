package com.example.urlscanning.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScanResultRepository extends JpaRepository<ScanResultEntity, Long> {
}
