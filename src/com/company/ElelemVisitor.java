package com.company;

public class ElelemVisitor implements TargyVisitor{
    public boolean visit(Kotel k){
        return false;
    };
    public boolean visit(Lapat l){
        return false;
    };
    public boolean visit(Buvarruha b){ return false;};
    public boolean visit(Elelem e){
        return true;
    };
    public boolean visit(Alkatresz a){
        return false;
    }

    @Override
    public boolean visit(Sator s) {
        return false;
    }

    ;
}
