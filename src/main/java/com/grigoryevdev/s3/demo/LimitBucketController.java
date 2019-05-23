package com.grigoryevdev.s3.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/limit-storage/")
public class LimitBucketController {

    // 1MB restriction
    private static final long MAX_FILE_SIZE = 1 * 1024 * 1024;

    @InitBinder
    public void initBinder(final DataBinder binder) {
        binder.setValidator(new FileValidator(MAX_FILE_SIZE));
    }

    @Autowired
    private AmazonClient amazonClient;

    @PostMapping("/uploadFile")
    public String uploadFile(@Valid @ModelAttribute("fileContainer") FileContainer fileContainer) {
        return this.amazonClient.uploadFile(fileContainer.getFile());
    }

    @DeleteMapping("/deleteFile")
    public String deleteFile(@RequestPart(value = "url") String fileUrl) {
        return this.amazonClient.deleteFileFromS3Bucket(fileUrl);
    }



}
