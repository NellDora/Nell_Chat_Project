package com.nellchat.ncproject.fileSystem.controller;

import com.nellchat.ncproject.fileSystem.service.FileExtractionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/file")
@Slf4j
public class FileController {

    private final FileExtractionService fileExtractionService;

    private  FileController(FileExtractionService fileExtractionService){
        this.fileExtractionService = fileExtractionService;
    }

    @GetMapping("/excel")
    public String exelExtract(){
        String title = "테스트다2";
        fileExtractionService.ExcelExtract(title);
        return null;
    }

}
