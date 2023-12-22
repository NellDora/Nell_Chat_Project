package com.nellchat.ncproject.fileSystem.domain;


import lombok.Data;


public class UploadFile {

    private String originalFileName;

    private String ServerFileName;

    public UploadFile(String originalFileName, String serverFileName) {
        this.originalFileName = originalFileName;
        ServerFileName = serverFileName;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public String getServerFileName() {
        return ServerFileName;
    }
}
