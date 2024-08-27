package org.example;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpStatusImageDownloader {

    private HttpStatusChecker checker;

    public HttpStatusImageDownloader() {
        this.checker = new HttpStatusChecker();
    }

    public void downloadStatusImage(int code) throws Exception {
        String imageUrl = checker.getStatusImage(code);
        String folderPath = "src/main/resources/image";
        File folder = new File(folderPath);

        // Перевіряємо чи існує папка, якщо ні — створюємо її
        if (!folder.exists()) {
            folder.mkdirs();
        }

        // Формуємо шлях для збереження файлу
        String filePath = folderPath + "/" + code + ".jpg";

        try (InputStream in = new BufferedInputStream(new URL(imageUrl).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(filePath)) {

            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }

            System.out.println("Image downloaded: " + filePath);
        } catch (IOException e) {
            throw new Exception("Failed to download image: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader();

        try {
            downloader.downloadStatusImage(200); // Should download https://http.cat/200.jpg
            downloader.downloadStatusImage(404); // Should download https://http.cat/404.jpg
            downloader.downloadStatusImage(10000); // Should throw Exception
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
