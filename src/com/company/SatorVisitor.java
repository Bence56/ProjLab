package com.company;

public class SatorVisitor implements TargyVisitor {
    @Override
    public boolean visit(Kotel k) { return false; }
    public boolean visit(Lapat l) { return false; }
    public boolean visit(Buvarruha b) { return false; }
    public boolean visit(Elelem e) { return false; }
    public boolean visit(Alkatresz a) { return false; }
    public boolean visit(Sator s) { return true; }

    
}
