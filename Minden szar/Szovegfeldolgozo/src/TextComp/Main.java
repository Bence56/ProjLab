package TextComp;

import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("You gonna do a flip nigga?");
        TextParser parser =new TextParser();
        parser.Parse("C:\\Users\\bsfcb\\Desktop\\BME\\4. Félév\\Szoft proj lab\\Minden szar\\Szovegfeldolgozo\\src\\TextComp\\Nigga.txt");
        parser.printAllCommand();
    }
}
