package com.devon.contractmanagementsystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.devon.contractmanagementsystem.service.FileStorageService;
import com.devon.contractmanagementsystem.message.ResponseFile;
import com.devon.contractmanagementsystem.message.ResponseMessage;
import com.devon.contractmanagementsystem.model.FileDB;
import com.devon.contractmanagementsystem.model.User;
import com.devon.contractmanagementsystem.model.UserFileMapping;
import com.devon.contractmanagementsystem.repository.UserFileMappingRepository;
import com.devon.contractmanagementsystem.repository.UserRepository;

@RestController
@CrossOrigin
public class FileController {

    @Autowired
    private FileStorageService storageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFileMappingRepository userFileMappingRepository;

    @PostMapping("/upload")
    public ResponseEntity<ResponseMessage> uploadFile(
            @RequestParam("file") MultipartFile file,
            @RequestParam("userId") int userId
    ) {
        String message = "";
        try {
            User user = userRepository.findById(userId);
            if(user != null)
            {
                FileDB fileDB = storageService.store(file, user.getId());
                FileDB saveFile = storageService.getFile(fileDB.getId());
                UserFileMapping mapping = new UserFileMapping(user.getId(), fileDB.getId());
                userFileMappingRepository.save(mapping);
                message = "Uploaded the file successfully: ";
                return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
            }
            else{
                throw new IllegalArgumentException("Invalid user ID: "+userId);
            }

        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
        }
    }

    @GetMapping("/files")
    public ResponseEntity<List<ResponseFile>> getListFiles() {
        List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId())
                    .toUriString();

            return new ResponseFile(
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getData().length);
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) {
        FileDB fileDB = storageService.getFile(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}