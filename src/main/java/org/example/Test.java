package org.example;

import java.io.*;
import java.util.Base64;
import java.awt.Desktop;

public class Test {
    public static void main(String[] args) throws IOException {
        // Чтение файла и кодирование в Base64
        File img = new File("src/main/resources/image/example.webp");
        InputStream bis = new FileInputStream(img);

        byte[] allBytes = bis.readAllBytes();
        bis.close();

        final String encoded = Base64.getEncoder().encodeToString(allBytes);
        System.out.println("Encoded image: " + encoded);

        // Декодирование Base64 обратно в байты
        byte[] decodedBytes = Base64.getDecoder().decode(encoded);

        // Создание нового файла для декодированного изображения
        File outputFile = new File("src/main/resources/image/decoded_example.webp");
        try (OutputStream os = new FileOutputStream(outputFile)) {
            os.write(decodedBytes);
        }

        // Открытие файла с использованием стандартной программы
        if (Desktop.isDesktopSupported()) {
            Desktop desktop = Desktop.getDesktop();
            if (outputFile.exists()) {
                desktop.open(outputFile);
            } else {
                System.out.println("File does not exist.");
            }
        } else {
            System.out.println("Desktop is not supported on this system.");
        }
    }
}
