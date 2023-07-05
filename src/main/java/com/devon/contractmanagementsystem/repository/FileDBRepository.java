package com.devon.contractmanagementsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devon.contractmanagementsystem.model.FileDB;

import java.util.Optional;


@Repository
public interface FileDBRepository extends JpaRepository <FileDB,String>{
    Optional<FileDB> findByName(String name);
}
