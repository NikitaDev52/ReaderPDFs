package ru.netology;

public class PageEntry implements Comparable<PageEntry>{
    private final String pdfName;
    private final int page;
    private final int count;

    @Override
    public int compareTo(PageEntry o) {
        return 0;
    }
}
