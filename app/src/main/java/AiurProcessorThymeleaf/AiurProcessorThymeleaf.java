package AiurProcessorThymeleaf;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#template-resolvers
 * return new FileInputStream(new File(template));
 */

public class AiurProcessorThymeleaf {

    private String prefix;

    AiurProcessorThymeleaf(String prefix) {
        if (!prefix.endsWith(File.separator)) {
            prefix = prefix + File.separator;
        }
        this.prefix = prefix;
    }

    private TemplateEngine getFileEngine() {
        FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setPrefix(this.prefix);

        // templateResolver.setSuffix(".html");

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.addTemplateResolver(templateResolver);
        templateEngine.addDialect(new LayoutDialect());

        return templateEngine;
    }

    public String processFile(String path, String dataString) {
        TemplateEngine engine = getFileEngine();
        Context ctx = new Context();

        // this is not JavaScript
        Type t = new TypeToken<HashMap<String, Object>>(){}.getType();
        Map<String, Object> data = new Gson().fromJson(dataString, t);

        ctx.setVariables(data);

        return engine.process(path, ctx);
    }

    /**
     * Someday, create a mix between FileTemplateResolver and StringTemplateResolver.
     * StringTemplateResolver cannot be configured (prefix, etc ...)
     * and therefore cannot reference other files.
     * For Aiur though, we want to primarily process a string from the code fence.
     * But ideally also want to reference layouts and fragments. 🦊
     */
    // public static String processString(String templateString, String dataString) {
    //     return ...;
    // }
}
