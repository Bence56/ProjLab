package com.company;

public class BuvarruhaVisitor implements TargyVisitor{

    public BuvarruhaVisitor(){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("BuvarruhaVisitor()");
        Tab.tab--;
    }
    public boolean visit(Kotel k){
        return false;
    }
    public boolean visit(Lapat l){
        return false;
    };
    public boolean visit(Buvarruha b){
        Tab.tab++;
        for(int j=0; j<Tab.tab; j++)System.out.print("\t");
        System.out.println("BuvarruhaVisitor.visit(Buvarruha b)");
        Tab.tab--;
        return true;
    }
    public boolean visit(Elelem e){
        return false;
    }
    public boolean visit(Alkatresz a){
        return false;
    }
}
