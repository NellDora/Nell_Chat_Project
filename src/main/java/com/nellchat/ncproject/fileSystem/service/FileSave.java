package com.nellchat.ncproject.fileSystem.service;


import com.nellchat.ncproject.fileSystem.domain.UploadFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class FileSave {


    private String fileDir;

    public String getFullPath(String fileName) {
        return fileDir + fileName;
    }

    public String extraExt(String originalFileName){
        int pos = originalFileName.lastIndexOf(".");
        String ext = originalFileName.substring(pos+1);
        return ext;
    }

    public String nameConvertor(String originalFileName){
        String ext = extraExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid+"."+ext;
    }

    public UploadFile saveFile(MultipartFile multipartFile) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        String serverFileName = nameConvertor(originalFileName);
        multipartFile.transferTo(new File(getFullPath(serverFileName)));
        return new UploadFile(originalFileName, serverFileName);
    }

    public List<UploadFile> saveFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadFile> uploadFiles = new ArrayList<>();
        for(MultipartFile multipartFile : multipartFiles){
            if(!multipartFile.isEmpty()){
                uploadFiles.add(saveFile(multipartFile));
            }
        }
        return uploadFiles;
    }

}