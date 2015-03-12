/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.cds.connecta.presenter.domain;

/**
 *
 * @author nataniel
 */
public enum FileExtensionEnum {
    JPG(FileTypeEnum.IMAGE, "image/jpeg"),
    PNG(FileTypeEnum.IMAGE, "image/png" ),
    MP4(FileTypeEnum.VIDEO, "video/mp4"),
    XLS(FileTypeEnum.BINARY, "application/excel"),
    DOC(FileTypeEnum.BINARY, "application/msword"),
    DOCX(FileTypeEnum.BINARY, "application/vnd.openxmlformats-officedocument.wordprocessingml.document"),
    PPT(FileTypeEnum.BINARY, ""),
    PPTX(FileTypeEnum.BINARY, "");
    
    private FileTypeEnum fileTypeEnum;
    private String mimeTypes;

    private FileExtensionEnum(FileTypeEnum fileTypeEnum, String mimeTypes) {
        this.fileTypeEnum = fileTypeEnum;
        this.mimeTypes = mimeTypes;
    }

    public FileTypeEnum getFileTypeEnum() {
        return fileTypeEnum;
    }

    public void setFileTypeEnum(FileTypeEnum fileTypeEnum) {
        this.fileTypeEnum = fileTypeEnum;
    }

    public String getMimeTypes() {
        return mimeTypes;
    }

    public void setMimeTypes(String mimeTypes) {
        this.mimeTypes = mimeTypes;
    }
    
}
