package com.devon.contractmanagementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.UUID;
import javax.sql.DataSource;

@Service
public class FileService {

    private final DataSource dataSource;

    @Autowired
   public FileService(DataSource dataSource)
    {
        this.dataSource=dataSource;
    }

    public void uploadFile(HttpServletRequest request, String userEmail) throws IOException, SQLException, ServletException {
        Part filePart = request.getPart("file");
        String fileName = UUID.randomUUID().toString(); // Generate a unique file name
        String uploadDirectory = "/path/to/upload/directory/";

// Save the file to the upload directory
        String filePath = uploadDirectory + fileName;
        File file = new File(filePath);
        Files.copy(filePart.getInputStream(), file.toPath(), StandardCopyOption.REPLACE_EXISTING);

// Save the file details in the database
        saveFileDetails(userEmail, fileName, filePath);
    }

    private void saveFileDetails(String userEmail, String fileName, String filePath) throws SQLException {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO files (user_email, file_name, file_path) VALUES (?, ?, ?)")) {
            statement.setString(1, userEmail);
            statement.setString(2, fileName);
            statement.setString(3, filePath);
            statement.executeUpdate();
// Rest of the code...

        } catch (SQLException e) {
// Handle the exception appropriately (e.g., logging, throwing custom exception)
// Example:
            System.err.println("Error saving file details: " + e.getMessage());
            throw e;
        }
    }
}
