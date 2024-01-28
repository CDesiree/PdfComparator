package com.dpmc.pdfcompare.pdfcompare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("api/pdf")
@CrossOrigin(origins = "http://localhost:3000")
public class PdfController {
    private final PdfService pdfService;
    public String RESULT_FILE_PATH = "D:\\pdfcompareapp\\files\\Compared.pdf";

    @Autowired
    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/compare")
    public String comparePdf(
            @RequestParam("file1") MultipartFile file1,
            @RequestParam("file2") MultipartFile file2
            ) throws IOException {
                String resultFile = pdfService.comparePdfs(file1, file2);
                return resultFile;
            }
    @GetMapping("/download")
    public ResponseEntity<byte[]> downloadPdf() throws IOException {
        String resultFilePath = RESULT_FILE_PATH;
        File file = new File(resultFilePath);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", file.getName());

        Path path = Paths.get(file.getPath());
        byte[] fileContent = Files.readAllBytes(path);

        return ResponseEntity.ok()
                .headers(headers)
                .body(fileContent);
    }
    @GetMapping("/isAlive")
    public String isAlive() {
        return "api is alive~";
    }

}

