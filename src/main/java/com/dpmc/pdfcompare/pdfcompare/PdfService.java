package com.dpmc.pdfcompare.pdfcompare;

import ch.qos.logback.core.net.SyslogOutputStream;
import de.redsix.pdfcompare.PdfComparator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class PdfService {

    public String comparePdfs(
            MultipartFile file1,
            MultipartFile file2
    ) throws IOException {
        String path1 = saveFile(file1);
        String path2 = saveFile(file2);

        String resultFile = "D:\\pdfcompareapp\\files\\Compared";

        try {
            PdfComparator pdfComparator = new PdfComparator(path1, path2);
            boolean isEquals = pdfComparator.compare().writeTo(resultFile);

            System.out.println("Is equal: " + isEquals);
            System.out.println("Process complete");

            return resultFile;
        } finally {
            deleteFile(path1);
            deleteFile(path2);
        }
    }

    private String saveFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        String filePath = "D:\\pdfcompareapp\\files\\" + fileName;

        Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

        return filePath;
    }

    private void deleteFile(String filePath) throws IOException {
        // Delete the file at the specified path
        Files.deleteIfExists(Paths.get(filePath));
    }
}
