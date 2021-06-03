package logger;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class MathLogger implements AutoCloseable {

    Path path;
    Map<String, BufferedWriter> writers;

    private Writer createWriter(File file) {

        try {

            Writer writer = new BufferedWriter(new FileWriter(file));
            return writer;

        } catch (IOException e){

            RuntimeException exc = new RuntimeException("Troubles with creating Writer for file: " + file.toString());
            exc.addSuppressed(e);

            try {
                this.close();
            } catch (Exception suppressed){
                exc.addSuppressed(suppressed);
            }


            throw exc;
        }

    }


    public MathLogger(Path path, List<String> fileNames) {
            if(!Files.exists(path)){
                try {
                    Files.createDirectory(path);
                } catch (IOException suppressed){
                    Exception e = new RuntimeException("Troubles with creating directory: " + path);
                }
            }
    }

    public MathLogger(Path path, String fileName) {

    }

    public MathLogger(Path path) {

    }

    public void log(String msg) {

    }

    public void log(String name, String msg) {

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

        if(!suppressedExceptions.isEmpty()){
            Exception e = new Exception("Troubles with closing some Writers");
            for(Exception suppressedException : suppressedExceptions){
                e.addSuppressed(suppressedException);
            }
            throw e;
        }
    }
}
