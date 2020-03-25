package com.company;

public class BuvarruhaVisitor implements TargyVisitor{
    public boolean visit(Kotel k){
        return false;
    };
    public boolean visit(Lapat l){
        return false;
    };
    public boolean visit(Buvarruha b){
        return true;
    };
    public boolean visit(Elelem e){
        return false;
    };
    public boolean visit(Alkatresz a){
        return false;
    };
}
