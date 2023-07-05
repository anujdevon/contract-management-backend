package com.devon.contractmanagementsystem.service;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

import com.devon.contractmanagementsystem.model.FileDB;
import com.devon.contractmanagementsystem.repository.FileDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileStorageService {

    @Autowired
    private FileDBRepository fileDBRepository;

    public FileDB store(MultipartFile file, int userId) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//        System.out.println("userId: "+userId);
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        fileDB = fileDBRepository.save(fileDB);

        return fileDB;
    }

    public FileDB getFile(String id) {
        return (FileDB) fileDBRepository.findById(id).get();
    }

    public FileDB getFileByName(String name){
        Optional<FileDB> fileDBOptional = fileDBRepository.findByName(name);
        return fileDBOptional.orElse(null);
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }

    public void saveFile(FileDB fileDB){
        fileDBRepository.save(fileDB);
    }



}