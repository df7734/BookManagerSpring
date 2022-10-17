package com.example.bookmanager.repo;

import com.example.bookmanager.models.Passport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassportRepository extends JpaRepository<Passport, Long> {
}
