package AiurProcessorThymeleaf;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.apache.commons.io.FileUtils;

public class AiurProcessorThymeleafTest {
    @Test
    public void testThymeleafProcessFile() {
        String prefix = System.getProperty("user.dir");

        AiurProcessorThymeleaf thl = new AiurProcessorThymeleaf(prefix);

        String filePath = FileUtils.getFile("src", "test", "resources", "test-simple.html").toString();

        String expectedString = "<div>yep</div>\n";
        Assertions.assertEquals(expectedString, thl.processFile(filePath, "{test: \"yep\"}"));

        String expectedStringNoData = "<div></div>\n";
        Assertions.assertEquals(expectedStringNoData, thl.processFile(filePath, ""));
    }

    @Test
    public void testThymeleafFragment() {
        String prefix = System.getProperty("user.dir");

        AiurProcessorThymeleaf thl = new AiurProcessorThymeleaf(prefix);

        String filePath = FileUtils.getFile("src", "test", "resources", "test-use-fragment.html").toString();

        String expectedString = "<h1>Hello</h1>\n<div>\n    <span>Thyme Fragment</span>\n</div>\n";
        Assertions.assertEquals(expectedString, thl.processFile(filePath, "{title: \"Hello\", text: \"Thyme Fragment\"}"));
    }

    @Test
    public void testThymeleafLayoutFragment() {
        String prefix = System.getProperty("user.dir");

        AiurProcessorThymeleaf thl = new AiurProcessorThymeleaf(prefix);

        String filePath = FileUtils.getFile("src", "test", "resources", "test-use-layout-fragment.html").toString();

        String expectedString = "<div class=\"parent\"><div>\n    <h2>Hello Layout</h2>\n    <p>\n        Lorem ipsum dolor sit amet.\n    </p>\n</div></div>\n";
        Assertions.assertEquals(expectedString, thl.processFile(filePath, "{title: \"Hello Layout\"}"));
    }
}
