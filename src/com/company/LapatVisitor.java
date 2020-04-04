package com.company;

public class LapatVisitor implements TargyVisitor{
    public boolean visit(Kotel k){
        return false;
    };
    public boolean visit(Lapat l){
        return true;
    };
    public boolean visit(Buvarruha b){
        return false;
    };
    public boolean visit(Elelem e){
        return false;
    };
    public boolean visit(Alkatresz a){
        return false;
    };
    public boolean visit(Sator s) { return false;}
}
