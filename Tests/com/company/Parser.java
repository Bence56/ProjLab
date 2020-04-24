package com.company;

import org.json.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {

    private ArrayList<Parancs> lista;

    public Parser() {
        lista = new ArrayList<Parancs>();
    }

    public void addParancs(Parancs p) {
        lista.add(p);
    }

    public ArrayList<Parancs> parse(String path) throws FileNotFoundException {
        File myObj = new File(path);
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()) {

            String data = myReader.nextLine();
            String[] line = data.split(" ");

            Parancs parancs = new Parancs();
            parancs.setTipus(line[0]);
            parancs.setNev(line[1]);
            parancs.setFuggvenynev(line[2]);

            int length = line.length;
            int size = (length - 2) / 2;

            ArrayList<String> p = new ArrayList<String>();
            ArrayList<String> pt = new ArrayList<String>();


            String[] params = new String[size];
            String[] paramTypes = new String[size];

            if (size > 0) {

                parancs.setHasParam(true);
                int seq = 3;
                int cnt = 0;

                for (int i = 0; i < size; i++) {

                    pt.add(line[3 + (i * 2) + 0]);
                    paramTypes[cnt] = line[3 + (i * 2) + 0];
                    p.add(line[3 + (i * 2) + 1]);
                    params[cnt] = line[3 + (i * 2) + 1];
                    cnt++;
                }
            }
            else {
                parancs.setHasParam(false);
            }

            for (int i = 0; i < p.size(); i++) {
                params[i] = p.get(i);
                paramTypes[i] = pt.get(i);
            }

            parancs.setParams(params);
            parancs.setParamTypes(paramTypes);
            this.addParancs(parancs);
        }

        myReader.close();
        return this.lista;
    }

    public ArrayList<Parancs> getLista() {
        return lista;
    }

    public void palyaParse(Kontroller kontroller) throws IOException {
        String jsonString = new String(Files.readAllBytes(Paths.get("Resources/palya.json")));
        JSONObject obj = new JSONObject(jsonString);
        JSONObject palya = obj.getJSONObject("palya");
        JSONArray mezok = palya.getJSONArray("mezok");
        for (int i = 0; i < mezok.length(); i++)
        {
            String id = mezok.getJSONObject(i).getString("id");
            Mezo mezo;
            if(id.charAt(0) == 'J'){
                String targyString = mezok.getJSONObject(i).getString("targy");
                Targy targy =
                        // 0-3 között random teherbírás
                mezo = new Jegtabla((int)(Math.random() * (3+1)),0,targy);
                kontroller.addMezo(mezo);
            }
            else if (id.charAt(0) == 'Y'){
                mezo = new Lyuk(0);
                kontroller.addMezo(mezo);
            }
        }
        for (int i=0;i<mezok.length();i++){
            String tmp = mezok.getJSONObject(i).getString("Fel");
            if (tmp.length()>1){
                int cnt=tmp.charAt(1);
                kontroller.getPalya(i).addSzomszedok(Irany.Fel,kontroller.getPalya(cnt));
            }
            tmp = mezok.getJSONObject(i).getString("JobbFel");
            if (tmp.length()>1){
                int cnt=tmp.charAt(1);
                kontroller.getPalya(i).addSzomszedok(Irany.Fel,kontroller.getPalya(cnt));
            }
            tmp = mezok.getJSONObject(i).getString("Jobb");
            if (tmp.length()>1){
                int cnt=tmp.charAt(1);
                kontroller.getPalya(i).addSzomszedok(Irany.Fel,kontroller.getPalya(cnt));
            }
            tmp = mezok.getJSONObject(i).getString("JobbLe");
            if (tmp.length()>1){
                int cnt=tmp.charAt(1);
                kontroller.getPalya(i).addSzomszedok(Irany.Fel,kontroller.getPalya(cnt));
            }
            tmp = mezok.getJSONObject(i).getString("Le");
            if (tmp.length()>1){
                int cnt=tmp.charAt(1);
                kontroller.getPalya(i).addSzomszedok(Irany.Fel,kontroller.getPalya(cnt));
            }
            tmp = mezok.getJSONObject(i).getString("BalLe");
            if (tmp.length()>1){
                int cnt=tmp.charAt(1);
                kontroller.getPalya(i).addSzomszedok(Irany.Fel,kontroller.getPalya(cnt));
            }
            tmp = mezok.getJSONObject(i).getString("Bal");
            if (tmp.length()>1){
                int cnt=tmp.charAt(1);
                kontroller.getPalya(i).addSzomszedok(Irany.Fel,kontroller.getPalya(cnt));
            }
            tmp = mezok.getJSONObject(i).getString("BalFel");
            if (tmp.length()>1){
                int cnt=tmp.charAt(1);
                kontroller.getPalya(i).addSzomszedok(Irany.Fel,kontroller.getPalya(cnt));
            }
        }
    }

    public void printAllCommand() {
        for (int i = 0; i < lista.size(); i++) {
            System.out.print(lista.get(i).getTipus() + " " + lista.get(i).getNev() + " " + lista.get(i).getFuggvenynev());
            String[] pLine = lista.get(i).getParams();
            String[] ptLine = lista.get(i).getParamTypes();
            int length = pLine.length;
            for (int j = 0; j < length; j++) {
                System.out.print(" " + ptLine[j]);
                System.out.print(" " + pLine[j]);
            }
            System.out.println();
        }
    }



}
