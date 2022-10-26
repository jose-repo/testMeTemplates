import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class VelocityStartGenerator {

    static String inputTemplate = "/testMeTests/HlagJUnitMappers.java";
    static String className = "VelocityExample";
    static String message = "Hello World!";
    static String outputFile = className + ".java";

    public static void main(String[] args) throws IOException {
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.getTemplate(inputTemplate);

        VelocityContext context = new VelocityContext();
        context.put("className", className);
        context.put("message", message);

        Writer writer = new FileWriter(new File(outputFile));
        velocityEngine.init();
        Velocity.mergeTemplate(inputTemplate, "UTF-8", context, writer);
        writer.flush();
        writer.close();

        System.out.println("Generated " + outputFile);
    }
}