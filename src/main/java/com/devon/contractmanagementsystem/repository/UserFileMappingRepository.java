package com.devon.contractmanagementsystem.repository;

import com.devon.contractmanagementsystem.model.UserFileMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserFileMappingRepository extends JpaRepository<UserFileMapping, Integer> {

    List<UserFileMapping> findByUserId(int userId);
}