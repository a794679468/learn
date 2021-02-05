package com.dgq.consumer.util;
 
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
 
public class ServerData {
 
    public static String getServerTopCommandContent() {
 
        Process process;
        String allcontent = "";
        try {
            process = Runtime.getRuntime().exec   ("top -b -n 1");
             InputStreamReader   ir= new InputStreamReader(process.getInputStream());
                LineNumberReader   input   =   new   LineNumberReader   (ir);    
 
                String   line;
                while   ((line   =   input.readLine   ())   !=   null){
                  //System.out.println(line); 
                    line = line + "<br />";
                    allcontent += line;
                }
                
        } catch (IOException e) {
 
            e.printStackTrace();
        }   //   表示只执行一次就自动退出，   
		return allcontent;
      }
 
}