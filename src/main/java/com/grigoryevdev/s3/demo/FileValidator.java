package com.grigoryevdev.s3.demo;

import org.springframework.util.CollectionUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class FileValidator implements Validator {

    private final long maxFileSize;

    public FileValidator(long maxFileSize) {
        this.maxFileSize = maxFileSize;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FileContainer.class.isAssignableFrom( clazz );
    }

    @Override
    public void validate(Object target, Errors errors) {

        FileContainer fileContainer = (FileContainer) target;
        if (fileContainer.getFile() == null) {
            errors.reject("file");
        }

        if (fileContainer.getFile().getSize() > maxFileSize ) {
            errors.reject("file");
        }

    }
}
