package logger;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

/**
 * MathLogger for organized logging of fields that you gave.
 * Root directory: /logger/
 * All your log files will be in: /log/yourPath/fieldName.
 * You can name fields that you want to log.
 * Logs to /logger/innerPath/fieldName.log
 * Field name "general" is reserved by logger.
 * general.log - for general logging information.
 *
 * Each log separates by "\n".
 * Rewrites all data that were in files.
 */
public class FieldLogger implements AutoCloseable {



    static final String prePath = "/logger/";

    /**
     * inner path that you gave
     */
    Path path;

    /**
     * Map for fields: contains pairs: fieldName -> writer to field's file
     */
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

    /**
     * Creates new Logger that logs to /logger/innerPath/fieldName.log
     *
     * @param innerPath - inner path for storing field's files
     * @param fieldNames - list of field names
     */
    public FieldLogger(String innerPath, List<String> fieldNames) {

        this.path = Path.of(prePath, innerPath);

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

        for (String fileName : fieldNames) {
            writers.putIfAbsent(fileName, createWriter(new File(path + File.separator + fileName + ".log")));
        }
        writers.putIfAbsent("general", createWriter(new File(path + File.separator + "general" + ".log")));



    }

    /**
     * Creates new Logger that logs to /logger/innerPath/fieldName.log
     * Have only one field.
     *
     * @param innerPath - inner path for storing field's files
     * @param fieldName - fieldName
     */
    public FieldLogger(String innerPath, String fieldName) {
        this(innerPath, List.of(fieldName));
    }

    /**
     * Logs only into  /logger/innerPath/general.log
     *
     * @param innerPath - inner path for storing field's files
     */
    public FieldLogger(String innerPath) {
        this(innerPath, List.of());
    }


    /**
     * Logs your message into  /logger/innerPath/general.log
     *
     * @param msg - log message
     */
    public void log(String msg) {
        log("general", msg);
    }

    /**
     * Logs your message into  specified field file: /logger/innerPath/fieldName.log
     *
     * @param fieldName - field name
     * @param msg - - log message
     */
    public void log(String fieldName, String msg) {
        Writer w = writers.get(fieldName);
        try {
            w.write(msg);
            w.write("\n");
        } catch (IOException suppressed){
            RuntimeException e = new RuntimeException("Troubles with logging");
            e.addSuppressed(suppressed);
            throw e;
        }
    }

    public void flush(){
        List<Exception> suppressedExceptions = new LinkedList<>();
        writers.values().forEach((w) -> {
            try {
                w.flush();
            } catch (IOException e) {
                suppressedExceptions.add(e);
            }
        });

        if (!suppressedExceptions.isEmpty()) {
            RuntimeException e = new RuntimeException("Troubles with flushing some Writers");
            for (Exception suppressedException : suppressedExceptions) {
                e.addSuppressed(suppressedException);
            }
            throw e;
        }
    }

    @Override
    public void close() throws Exception {
        List<Exception> suppressedExceptions = new LinkedList<>();
        writers.values().forEach((w) -> {
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
