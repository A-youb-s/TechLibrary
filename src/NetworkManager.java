package src;

import javax.swing.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class NetworkManager {
    public void checkUrl(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                JOptionPane.showMessageDialog(null, "URL is reachable.", "URL Check", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "URL is not reachable. Response code: " + responseCode, "URL Check", JOptionPane.WARNING_MESSAGE);
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error checking URL: " + e.getMessage(), "URL Check", JOptionPane.ERROR_MESSAGE);
        }
    }
}
