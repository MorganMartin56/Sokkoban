package com.mycompany.sokkoban;

public class Map_Element {
    
    private String Symbol;
    private String ImgFileName;
    
    public Map_Element(String Symbol, String ImgFileName) {
        this.Symbol = Symbol;
        this.ImgFileName = ImgFileName;
    }
    
    public String getSymbol() {
        return Symbol;
    }
    
    public void setSymbol(String Symbol) {
        this.Symbol = Symbol;
    }
    
    public String getImgFileName() {
        return ImgFileName;
    }
    
    public void setImgFileName(String ImgFileName) {
        this.ImgFileName = ImgFileName;
    }
}
