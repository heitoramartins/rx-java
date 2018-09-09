package com.contribuidor.cma.util.validate;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FileValidator<T> {

    private MultipartFile file;
    private List<T> erros = Collections.EMPTY_LIST;

    private FileValidator(MultipartFile file) {
        this.file = file;
        erros = new ArrayList<T>();
    }

    public static FileValidator ensureThat(MultipartFile file) {
        return new FileValidator(file);
    }

    public FileValidator assertMaxKiloBytes(Integer kiloBytes, T errorMessage) {
        if (hasFile() && file.getSize() > (kiloBytes * 1000)) {
            erros.add(errorMessage);
        }

        return this;
    }

    public FileValidator assertMaxMegaBytes(Integer megaBytes, T errorMessage) {
        if (hasFile() && file.getSize() > (megaBytes * 1000000)) {
            erros.add(errorMessage);
        }

        return this;
    }

    public FileValidator assertContentType(String[] contentTypes, T errorMessage) {
        if (hasFile() && !Arrays.stream(contentTypes).anyMatch(type -> type.equals(file.getContentType()))) {
            erros.add(errorMessage);
        }

        return this;
    }

    public FileValidator assertImageWidth(Integer width, T errorMessage) {
        if (!hasFile()) {
            return this;
        }

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());

            if (image != null && image.getWidth() != width) {
                erros.add(errorMessage);
            }
        } catch (IOException e) {
            return this;
        }

        return this;
    }

    public FileValidator assertImageHeigth(Integer heigth, T errorMessage) {
        if (!hasFile()) {
            return this;
        }

        try {
            BufferedImage image = ImageIO.read(file.getInputStream());

            if (image != null && image.getHeight() != heigth) {
                erros.add(errorMessage);
            }
        } catch (IOException e) {
            return this;
        }

        return this;
    }

    public FileValidator assertNotNull(T errorMessage) {
        if (!hasFile()) {
            erros.add(errorMessage);
        }

        return this;
    }

    private boolean hasFile() {
        return file != null;
    }

    public boolean hasErrors() {
        return !erros.isEmpty();
    }

    public List<T> errors() {
        return erros;
    }
}
