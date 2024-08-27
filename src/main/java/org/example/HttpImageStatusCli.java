package org.example;

import java.util.Scanner;

public class HttpImageStatusCli {

    private HttpStatusImageDownloader downloader;

    public HttpImageStatusCli() {
        this.downloader = new HttpStatusImageDownloader();
    }

    public void askStatus() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter HTTP status code: ");
        String input = scanner.nextLine();

        try {
            int statusCode = Integer.parseInt(input);
            downloader.downloadStatusImage(statusCode);
        } catch (NumberFormatException e) {
            System.out.println("Please enter valid number");
        } catch (Exception e) {
            System.out.println("There is not image for HTTP status " + input);
        }
    }

    public static void main(String[] args) {
        HttpImageStatusCli cli = new HttpImageStatusCli();
        cli.askStatus();
    }
}
