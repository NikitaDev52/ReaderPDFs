import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.itextpdf.layout.element.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.SearchEngine.BooleanSearchEngine;
import ru.netology.SearchEngine.PageEntry;
import ru.netology.SearchEngine.SearchEngine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BooleanSearchEngineTest {
    SearchEngine searchEngine;


    @BeforeEach
    void createNewSearchEngine() throws IOException {
    searchEngine = new BooleanSearchEngine(new File("pdfs"));
    }

    @Test
    @DisplayName("Тест валиден только для такого набора pdf-файлов, как в pdfs/ и для такого файла text/resources/ExceptForTests")
    void Const_BooleanSearhEngine() {
        Path filePath = Path.of("src/test/resources/ExceptForTests");
        try {
            String except = Files.readString(filePath);
            Gson gson = new Gson();
            java.util.List<PageEntry> exceptColl = gson.fromJson(except, new com.google.gson.reflect.TypeToken<java.util.List<PageEntry>>() {}.getType());
            Collections.sort(exceptColl);
            java.util.List<PageEntry> actualColl = searchEngine.search("Что");
            Collections.sort(actualColl);
            assertEquals(exceptColl, actualColl);
        } catch (IOException e) {
            throw new RuntimeException("Файл не удалось найти или открыть, относительный путь: " + filePath.toString());
        }
    }
}
