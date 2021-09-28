package net.ibxnjadev.kruby.core.template;

import net.ibxnjadev.kruby.helper.io.StreamHelper;

import java.io.*;

public class TemplateUtil {

    public static void setupTemplateEnvironment(Template template, String dockerfileNameDirectory) {

        File directory = new File("dockerfiles/" + dockerfileNameDirectory);

        System.out.println(directory);
        File dockerfile = new File(directory, "docker/java_8_template/Dockerfile");
        File entrypoint = new File(directory, "docker/java_8_template/entrypoint.sh");

        File directoryTemplate = template.getDirectory();
        File dockerfileTemplate = new File(directoryTemplate, "docker/java_8_template/Dockerfile");
        File entrypointTemplate = new File(directoryTemplate, "docker/java_8_template/entrypoint.sh");

        try {

            dockerfileTemplate.createNewFile();
            entrypointTemplate.createNewFile();

            InputStream dockerInput = new FileInputStream(dockerfile);
            InputStream entrypointInput = new FileInputStream(entrypoint);

            OutputStream dockerOutputStream = new FileOutputStream(dockerfileTemplate);
            OutputStream entrypointOutputStream = new FileOutputStream(entrypointTemplate);

            StreamHelper.copyStream(dockerInput, dockerOutputStream);
            StreamHelper.copyStream(entrypointInput, entrypointOutputStream);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
