package ru.netology;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.canvas.parser.PdfTextExtractor;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class BooleanSearchEngine implements SearchEngine{
    private Map<String, List<PageEntry>> wordsFromPdf = new HashMap<>();

    public BooleanSearchEngine(File pdfsDir) throws IOException {
        if (pdfsDir.exists() && pdfsDir.isDirectory() && Objects.requireNonNull(pdfsDir.listFiles()).length > 0) {
            File[] pdsf = pdfsDir.listFiles();
            for (File pdf : pdsf) {
                PdfDocument doc = new PdfDocument(new PdfReader(pdf));
                for (int i = 1; i <= doc.getNumberOfPages(); i++) {
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
                    for (Map.Entry<String, Integer> entry : freqs.entrySet()) {
                        String word = entry.getKey();
                        int count = entry.getValue();
                        int pageFromDoc = i;
                        PageEntry pageEntry = new PageEntry(pdf.getName(), pageFromDoc, count);

                        wordsFromPdf.computeIfAbsent(word, k -> new ArrayList<>()).add(pageEntry);
                    }
                }
            }
        }
    }


    @Override
    public List<PageEntry> search(String word) {
        return wordsFromPdf.getOrDefault(word.toLowerCase(), new ArrayList<>());
    }
}
