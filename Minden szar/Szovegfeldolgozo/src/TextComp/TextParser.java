package TextComp;

import org.w3c.dom.Text;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class TextParser {
    private ArrayList<Parancs> lista;
    public void addParancs(Parancs p){
        lista.add(p);
    }
    public TextParser(){
        lista=new ArrayList<Parancs>();
    }
    public void Parse(String path) throws FileNotFoundException {
        File myObj = new File(path);
        Scanner myReader =new Scanner(myObj);
        while(myReader.hasNextLine()){
            String data = myReader.nextLine();
            String[] line=data.split(" ");
            Parancs parancs =new Parancs();
            parancs.setTipus(line[0]);
            parancs.setNev(line[1]);
            parancs.setFuggvenynev(line[2]);
            int length=line.length;
            ArrayList<String>p=new ArrayList<String>();
            ArrayList<String>pt=new ArrayList<String>();
            int size=(length-2)/2;
            String[] params= new String[size];
            String[] paramTypes = new String[size];
            System.out.println("length-3/2 = "+(length-2)/2);
            if(size>0) {
                parancs.setHasParam(true);
                int seq=3;
                int cnt=0;
                for (int i = 0; i<size;i++){
                    pt.add(line[3+(i*2)+0]);
                    paramTypes[cnt]=line[3+(i*2)+0];
                    p.add(line[3+(i*2)+1]);
                    params[cnt]=line[3+(i*2)+1];
                    cnt++;
                }
            }
            else{
                parancs.setHasParam(false);
            }
            for(int i=0;i<p.size();i++){
                params[i]=p.get(i);
                System.out.print(p.get(i)+" ");
                paramTypes[i]=pt.get(i);
                System.out.println(pt.get(i)+" ");
            }
            parancs.setParams(params);
            parancs.setParamTypes(paramTypes);
            this.addParancs(parancs);
        }
        myReader.close();
    }
    public ArrayList<Parancs> getLista(){
        return lista;
    }
    public void printAllCommand(){
        for(int i=0; i<lista.size();i++){
            System.out.print(lista.get(i).getTipus()+" "+lista.get(i).getNev()+" "+lista.get(i).getFuggvenynev());
            String[] pLine=lista.get(i).getParams();
            String[] ptLine=lista.get(i).getParamTypes();
            int length=pLine.length;
            for(int j=0;j<length;j++){
                System.out.print(" "+ptLine[j]);
                System.out.print(" "+pLine[j]);
            }
            System.out.println();
        }
    }

}
