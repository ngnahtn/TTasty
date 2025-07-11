/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 *
 * @author tung
 */
public class ImgUrl {

    public boolean isImageUrlExists(String imgUrl) {
        try {
            URL url = new URL(imgUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD"); // Chỉ kiểm tra header
            connection.setConnectTimeout(3000);
            connection.connect();

            int responseCode = connection.getResponseCode();
            String contentType = connection.getContentType();

            // Kiểm tra status OK và định dạng MIME là hình ảnh
            return (responseCode >= 200 && responseCode < 400)
                    && (contentType != null && contentType.startsWith("image/"));
        } catch (IOException e) {
            return false;
        }
    }
}
