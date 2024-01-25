package com.dpmc.pdfcompare.pdfcompare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("api/pdf")
@CrossOrigin(origins = "http://localhost:3000")
public class PdfController {
    private final PdfService pdfService;

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
    @GetMapping("/isAlive")
    public String isAlive() {
        return "api is alive~";
    }

}
