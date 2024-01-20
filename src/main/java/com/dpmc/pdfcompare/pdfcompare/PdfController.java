package com.dpmc.pdfcompare.pdfcompare;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("api/pdf")
public class PdfController {
    private final PdfService pdfService;

    @Autowired
    public PdfController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @PostMapping("/compare")
    public String comparePdf(@RequestBody PdfRequest pdfRequest) throws IOException {
        return pdfService.comparePdfs(pdfRequest);
    }
    @GetMapping("/isAlive")
    public String isAlive() {
        return "api is alive~";
    }

}
