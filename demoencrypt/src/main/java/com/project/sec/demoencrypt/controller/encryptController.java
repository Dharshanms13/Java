package com.project.sec.demoencrypt.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class encryptController {

    @GetMapping("/sample")
    public String getString() throws IOException{
        
        ProcessBuilder builder = new ProcessBuilder("python", "D://NewWorkspace//Start//demoencrypt//src//main//java//com//project//sec//demoencrypt//controller//sample2.py", "1", "4");
        
        Process process = builder.start();

        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

        BufferedReader errorReader = new BufferedReader(new InputStreamReader(process.getErrorStream()));

        String lines = null;

        while ((lines = reader.readLine()) != null) {
            System.out.println(lines);
        }

        while ((lines = errorReader.readLine()) != null) {
            System.out.println(lines);
        }

        return "SampleString";
    }
}
