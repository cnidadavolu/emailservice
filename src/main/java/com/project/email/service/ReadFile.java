package com.project.email.service;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class ReadFile {

    public Map<String, String> recipientList() {

        Map<String, String> recipientMap = new HashMap<String, String>();
        String[] mapValues;
        BufferedReader reader;

        try {
            File emailFile = new ClassPathResource("emailList.txt").getFile();
            reader = new BufferedReader(new FileReader(emailFile));
            String line = "";
            while ((line = reader.readLine()) != null) {
                mapValues = line.split(",");
                System.out.println(mapValues.length);
                for (int i = 0; i < mapValues.length - 1; i++) {
                    recipientMap.put(mapValues[i], mapValues[i + 1]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipientMap;
    }
}
