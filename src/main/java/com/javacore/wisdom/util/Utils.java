package com.javacore.wisdom.util;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.FileVisitOption;

import com.google.protobuf.ByteString;
import com.google.protobuf.Timestamp;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.UUID;

import java.util.Comparator;


import static org.apache.commons.codec.binary.Hex.encodeHexString;

public final class Utils {


    /**
     * Read the contents a file.
     *
     * @param input source file to read.
     * @return contents of the file.
     * @throws IOException
     */
    public static byte[] readFile(File input) throws IOException {
        return Files.readAllBytes(Paths.get(input.getAbsolutePath()));
    }

    /**
     * Generate a v4 UUID
     *
     * @return String representation of {@link UUID}
     */
    public static String generateUUID() {
        return UUID.randomUUID().toString();
    }


    private static final SecureRandom RANDOM = new SecureRandom();

    public static byte[] generateNonce(final int NONCE_LENGTH) {

        byte[] values = new byte[NONCE_LENGTH];
        RANDOM.nextBytes(values);

        return values;
    }

    public static String toHexString(ByteString byteString) {
        if (byteString == null) {
            return null;
        }

        return encodeHexString(byteString.toByteArray());

    }

    public static String toHexString(byte[] bytes) {
        if (bytes == null) {
            return null;
        }

        return encodeHexString(bytes);

    }


    /**
     * Delete a file or directory
     *
     * @param file {@link File} representing file or directory
     * @throws IOException
     */
    public static void deleteFileOrDirectory(File file) throws IOException {
        if (file.exists()) {
            if (file.isDirectory()) {
                Path rootPath = Paths.get(file.getAbsolutePath());

                Files.walk(rootPath, FileVisitOption.FOLLOW_LINKS)
                        .sorted(Comparator.reverseOrder())
                        .map(Path::toFile)
                        .forEach(File::delete);
            } else {
                file.delete();
            }
        } else {
            throw new RuntimeException("File or directory does not exist");
        }
    }


}
