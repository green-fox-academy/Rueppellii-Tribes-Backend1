package com.greenfox.tribes1.PDF;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;

@Service
public class PdfService {

  public ByteArrayInputStream generatePDF() {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      PdfPTable table = new PdfPTable(3);
      table.setWidthPercentage(80);
      table.setWidths(new int[]{1, 3, 3});

      Font headFont = FontFactory.getFont(FontFactory.HELVETICA);

      PdfPCell hcell = new PdfPCell(new Phrase("Id", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Name", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Valami", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      Document document = new Document();
      PdfWriter.getInstance(document, outputStream);
      document.open();
      document.add(table);
      document.close();
    } catch (DocumentException e) {
      e.printStackTrace();
    }
    return new ByteArrayInputStream(outputStream.toByteArray());
  }

  public ByteArrayInputStream createPdf(String content) throws IOException {
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    try {
      ITextRenderer renderer = new ITextRenderer();
      String baseUrl = FileSystems
              .getDefault()
              .getPath("src", "main", "resources")
              .toUri()
              .toString();
      renderer.setDocumentFromString(content, baseUrl);
      renderer.layout();
      renderer.createPDF(outputStream);
    } catch (DocumentException e) {
      System.out.println("Something went wrong with document creation!");
    }
    return new ByteArrayInputStream(outputStream.toByteArray());
  }

  public String parsePdfTemplate(Context context, String templateName) {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("/templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode("HTML");
    templateResolver.setCharacterEncoding("UTF-8");
    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    return templateEngine.process(templateName, context);
  }
}
