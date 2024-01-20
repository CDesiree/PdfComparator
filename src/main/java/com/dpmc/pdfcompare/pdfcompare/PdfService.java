package com.dpmc.pdfcompare.pdfcompare;

import ch.qos.logback.core.net.SyslogOutputStream;
import de.redsix.pdfcompare.PdfComparator;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class PdfService {

    public String comparePdfs(PdfRequest pdfRequest) throws IOException {
        String path1 = pdfRequest.getPath1();
        String path2 = pdfRequest.getPath2();
        String resultFile = "D:\\pdfcompareapp\\files\\Compared";

        PdfComparator pdfComparator = new PdfComparator(path1, path2);
        boolean isEquals = pdfComparator.compare().writeTo(resultFile);

        System.out.println("Is Equal: " + isEquals);
        System.out.println("Process complete");

        return resultFile;
    }
}
