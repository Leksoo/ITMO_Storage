package ru.ifmo.rain.feponov.walk;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Walk {
    private static final int FNV_INIT = 0x811c9dc5;
    private static final int FNV_PRIME = 16777619;
    private static final int FNV_STEP = 0xff;
    private static final int BUFFER_SIZE = 4096;
    private final Path inputPath;
    private final Path outputPath;

    public static void main(String[] args) {
        try {
            Walk walker = new Walk(args);
            walker.walk();
        } catch (IllegalArgumentException | WalkException e) {
            System.err.println(e.getMessage());
        }
    }

    private Walk(String[] paths) throws IllegalArgumentException, WalkException {
        if (paths == null || paths.length != 2 || paths[0] == null || paths[1] == null) {
            throw new IllegalArgumentException("Invalid input arguments");
        }
        try {
            inputPath = Paths.get(paths[0]);
        } catch (InvalidPathException e) {
            throw new WalkException("Invalid input file path", e.getMessage());
        }
        try {
            outputPath = Paths.get(paths[1]);
            if (Files.notExists(outputPath)) {
                if (outputPath.getParent() != null) {
                    Files.createDirectories(outputPath.getParent());
                }
            }
        } catch (IOException e) {
            throw new WalkException("unable to create directories", e.getMessage());
        } catch (InvalidPathException e) {
            throw new WalkException("Invalid output file path", e.getMessage());
        }
    }

    private void walk() throws WalkException {
        try (BufferedReader reader = Files.newBufferedReader(inputPath)) {
            try (BufferedWriter writer = Files.newBufferedWriter(outputPath)) {
                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        try {
                            Path path = Paths.get(line);
                            writeResult(writer, calculateHashSum(path), line);
                        } catch (InvalidPathException | IOException e) {
                            writeResult(writer, 0, line);
                        }
                    }
                } catch (IOException e) {
                    throw new WalkException("Error happened while reading from input file", e.getMessage());
                }
            } catch (FileNotFoundException e) {
                throw new WalkException("Error happened while opening output file", e.getMessage());
            } catch (IllegalArgumentException e) {
                throw new WalkException("Incorrect options passed", e.getMessage());
            } catch (UnsupportedOperationException e) {
                throw new WalkException("Unsupported operation", e.getMessage());
            } catch (SecurityException e) {
                throw new WalkException("Security error happened while working with input file", e.getMessage());
            } catch (IOException e) {
                throw new WalkException("Something wrong happened while working with output file", e.getMessage());
            }
        } catch (FileNotFoundException e) {
            throw new WalkException("Error happened while opening input file", e.getMessage());
        } catch (SecurityException e) {
            throw new WalkException("Security error happened while working with input file", e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new WalkException("buffer size <= 0", e.getMessage());
        } catch (NullPointerException e) {
            throw new WalkException("lock is null", e.getMessage());
        } catch (IOException e) {
            throw new WalkException("Something wrong happened while working with input file", e.getMessage());
        }
    }

    private int calculateHashSum(Path path) throws IOException {
        BufferedInputStream inputStream = new BufferedInputStream(Files.newInputStream(path));
        byte[] bytes = new byte[BUFFER_SIZE];
        int hash = FNV_INIT;
        int cnt;
        while ((cnt = inputStream.read(bytes)) != -1) {
            for (int i = 0; i < cnt; i++) {
                hash *= FNV_PRIME;
                hash ^= (bytes[i] & FNV_STEP);
            }
        }
        return hash;
    }

    private void writeResult(BufferedWriter writer, int hashSum, String path) throws IOException {
        writer.write(String.format("%08x", hashSum) + " " + path);
        writer.newLine();
    }
}
