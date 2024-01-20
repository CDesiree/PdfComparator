package com.dpmc.pdfcompare.pdfcompare;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/pdf")
public class PdfController {
    @GetMapping("/isAlive")
    public String isAlive() {
        return "api is alive~";
    }
}
