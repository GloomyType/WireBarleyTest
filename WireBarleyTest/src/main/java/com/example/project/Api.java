package com.example.project;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Api {

	
    @RequestMapping(value="/getApi", method= {RequestMethod.GET, RequestMethod.POST})
    public String callapihttp(@RequestParam("source") String source){
  
        StringBuilder result = new StringBuilder();
        try{
            String urlstr = "http://apilayer.net/api/live?access_key=b766a5bae870f9283d28cd70d298bf0a&currencies=KRW,JPY,PHP&source=" + source + "&format=1";
            URL url = new URL(urlstr);
            HttpURLConnection urlconnection = (HttpURLConnection) url.openConnection();
            urlconnection.setRequestMethod("GET");
 
            BufferedReader br = new BufferedReader(new InputStreamReader(urlconnection.getInputStream(),"UTF-8"));
 
            String returnLine;


            while((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n\r");
            }
            urlconnection.disconnect();
 
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
}
    

