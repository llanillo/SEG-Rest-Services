package com.seg.domain.enumeration;

public enum DocumentType {
    IMAGE ("Image"),
    PDF ("Pdf"),
    WORD("Word"),
    EXCEL("Excel"),
    AUTOCAD("Autocad"),
    FOLDER("Folder"),
    OTHER ("Other");
    
    private final String documentType;

    private DocumentType(final String documentType) {
        this.documentType = documentType;
    }

    @Override
    public String toString() {
        return documentType;     
    }    
}
