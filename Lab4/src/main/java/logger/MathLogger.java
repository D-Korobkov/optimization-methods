package logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class MathLogger implements AutoCloseable {

    Path path;
    Map<String, Writer> writers;

    private Writer createWriter(File file) {

        try {

            Writer writer = new BufferedWriter(new FileWriter(file));
            return writer;

        } catch (IOException e) {

            RuntimeException exc = new RuntimeException("Troubles with creating Writer for file: " + file.toString());
            exc.addSuppressed(e);

            try {
                this.close();
            } catch (Exception suppressed) {
                exc.addSuppressed(suppressed);
            }


            throw exc;
        }

    }


    public MathLogger(Path path, List<String> fileNames) {

        this.path = path;

        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException suppressed) {
                RuntimeException e = new RuntimeException("Troubles with creating directory: " + path);
                e.addSuppressed(suppressed);
                throw e;
            }
        }

        writers = new HashMap<>();

        for (String fileName : fileNames) {
            writers.putIfAbsent(fileName, createWriter(new File(path + File.separator + fileName + ".log")));
        }
        writers.putIfAbsent("general", createWriter(new File(path + File.separator + "general" + ".log")));



    }

    public MathLogger(Path path, String fileName) {
        this(path, List.of(fileName));
    }

    public MathLogger(Path path) {
        this(path, List.of());

    }

    public void log(String msg) {
        log("general", msg);
    }

    public void log(String name, String msg) {
        Writer w = writers.get(name);
        try {
            w.write(msg);
            w.write("\n");
        } catch (IOException suppressed){
            RuntimeException e = new RuntimeException("Troubles with logging");
            e.addSuppressed(suppressed);
            throw e;
        }
    }

    @Override
    public void close() throws Exception {
        List<Exception> suppressedExceptions = new LinkedList<>();
        writers.entrySet().stream().map(Map.Entry::getValue).forEach((w) -> {
            try {
                w.close();
            } catch (IOException e) {
                suppressedExceptions.add(e);
            }
        });

        if (!suppressedExceptions.isEmpty()) {
            Exception e = new Exception("Troubles with closing some Writers");
            for (Exception suppressedException : suppressedExceptions) {
                e.addSuppressed(suppressedException);
            }
            throw e;
        }
    }
}
