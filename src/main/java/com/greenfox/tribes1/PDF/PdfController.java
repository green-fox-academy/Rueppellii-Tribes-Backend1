package com.greenfox.tribes1.PDF;

import com.greenfox.tribes1.Kingdom.KingdomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.context.Context;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@Controller
public class PdfController {
  private PdfService pdfService;
  private KingdomService kingdomService;

  @Autowired
  public PdfController(PdfService pdfService, KingdomService kingdomService) {
    this.pdfService = pdfService;
    this.kingdomService = kingdomService;
  }

  @GetMapping(value = "/kingdoms/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
  public ResponseEntity<InputStreamResource> kingdomsReport() throws IOException {
    Context context = new Context();
    context.setVariable("kingdoms", kingdomService.findAll());
    ByteArrayInputStream inputStream = pdfService.createPdf(
            pdfService.parsePdfTemplate(context, "kingdom_report"));
    return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(inputStream));
  }
}
