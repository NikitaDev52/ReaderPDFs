package ru.netology;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BooleanSearchEngine implements SearchEngine{
    private Map<String, PageEntry> wordsFromPdf = new HashMap<>();


    public BooleanSearchEngine(File pdfsDir) throws IOException {
        if (pdfsDir.exists() && pdfsDir.isDirectory() && pdfsDir.length() > 0) {
            File[] pdsf = pdfsDir.listFiles();
            for (File pdf : pdsf) {
                PdfDocument doc = new PdfDocument(new PdfReader(pdf));
                for (int i = 0; i < doc.getNumberOfPages(); i++) {
                    PdfPage page = doc.getPage(i);
                    String text = PdfTextExtractor.getTextFromPage(page);
                    String[] words = text.split("\\P{IsAlphabetic}+");
                    Map<String, Integer> freqs = new HashMap<>();
                    for (var word : words) {
                        if (word.isEmpty()) {
                            continue;
                        }
                        word = word.toLowerCase();
                        freqs.put(word, freqs.getOrDefault(word, 0) + 1);
                    }

                }
            }
        }
    }


    @Override
    public List<PageEntry> search(String word) {
        return null;
    }
}
