package com.devon.contractmanagementsystem.service;

import java.io.IOException;
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
        FileDB fileDB = new FileDB(fileName, file.getContentType(), file.getBytes());
        fileDB = fileDBRepository.save(fileDB);

        return fileDB;
    }

    public FileDB getFile(String id) {
        return (FileDB) fileDBRepository.findById(id).get();
    }

    public Stream<FileDB> getAllFiles() {
        return fileDBRepository.findAll().stream();
    }


}