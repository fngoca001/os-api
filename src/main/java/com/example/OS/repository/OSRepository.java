package com.example.OS.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.OS.domain.OS;

public interface OSRepository extends JpaRepository<OS, Long> {

}
