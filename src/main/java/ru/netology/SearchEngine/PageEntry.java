package ru.netology;

public class PageEntry implements Comparable<PageEntry>{
    private String pdfName;
    private int page;
    private int count;

    public PageEntry (String pdfName, int page, int count) {
        this.pdfName = pdfName;
        this.count = count;
        this.page = page;
    }

    public void setPdfName(String pdfName) {
        this.pdfName = pdfName;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPdfName() {
        return pdfName;
    }

    public int getCount() {
        return count;
    }

    public int getPage() {
        return page;
    }

    @Override
    public String toString() {
        return "PageEntry{" +
                "pdfName='" + pdfName + '\'' +
                ", page=" + page +
                ", count=" + count +
                '}';
    }

    @Override
    public int compareTo(PageEntry o) {
        return Integer.compare(o.getCount(), count);
    }
}
