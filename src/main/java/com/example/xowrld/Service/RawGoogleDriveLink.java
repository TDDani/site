package com.example.xowrld.Service;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class RawGoogleDriveLink {


    public String getRawLink(String link) throws IOException {
        String fileId = extractFileId(link);
        String rawLink = "https://drive.google.com/u/0/uc?id=" + fileId + "&export=download";
        URLConnection con = new URL(rawLink).openConnection();
        con.connect();
        String location = con.getHeaderField("Location");
        if (location != null && location.startsWith("https://drive.google.com/")) {
            rawLink = location.replace("export=download", "export=raw");
        }
        return rawLink;
    }

    public String extractFileId(String link) {
        int startIndex = link.indexOf("/d/") + 3;
        int endIndex = link.indexOf("/view");
        if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
            return link.substring(startIndex, endIndex);
        }
        throw new IllegalArgumentException("Invalid Google Drive file link");
    }
}
