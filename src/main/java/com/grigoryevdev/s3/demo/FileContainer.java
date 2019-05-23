package com.grigoryevdev.s3.demo;

import org.springframework.web.multipart.MultipartFile;

public class FileContainer {

    private MultipartFile file;

    public FileContainer() {
    }

    public FileContainer(MultipartFile file) {
        this.file = file;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }
}
