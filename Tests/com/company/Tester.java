package com.company;

import org.junit.platform.launcher.Launcher;
import org.junit.platform.launcher.LauncherDiscoveryRequest;
import org.junit.platform.launcher.TestPlan;
import org.junit.platform.launcher.core.LauncherDiscoveryRequestBuilder;
import org.junit.platform.launcher.core.LauncherFactory;
import org.junit.platform.launcher.listeners.SummaryGeneratingListener;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

import static org.junit.platform.engine.discovery.DiscoverySelectors.selectClass;

public class Tester {
    SummaryGeneratingListener listener = new SummaryGeneratingListener();

    public void runJatekosTest() {
        LauncherDiscoveryRequest request = LauncherDiscoveryRequestBuilder.request()
                .selectors(selectClass(JatekosTest.class))
                .build();
        Launcher launcher = LauncherFactory.create();
        TestPlan testPlan = launcher.discover(request);
        launcher.registerTestExecutionListeners(listener);
        launcher.execute(request);
    }

    /**
     * Egymás után futtatja a parancsokat
     */
    public void test() {
        Executer e = new Executer();
        Parser parser = new Parser();
        ArrayList<Parancs> parancsok = null;
        try {

            parancsok = parser.parse("Resources/parancsok.txt");

            for (int i = 0; i < parancsok.size(); i++) {
                Parancs p = parancsok.get(i);
                String tipus = p.getTipus();
                String nev = p.getNev();
                String fuggveny = p.getFuggvenynev();
                Class<?>[] paramTypes = e.argClasses(p.getParamTypes());
                String[] params = p.getParams();

                if (fuggveny.equals("create")) {
                    Object obj = e.construct(tipus, paramTypes, params);
                    e.put(nev, obj);
                } else {
                    Object o = e.get(nev);
                    Object ret = e.runTheMethod(o, fuggveny, paramTypes, params);
                    e.put("ret" + String.valueOf(i), ret);
                }
            }
        } catch (FileNotFoundException | NoSuchMethodException | InstantiationException |
                IllegalAccessException | InvocationTargetException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    public void testfromfile(String filename) {
        Executer e = new Executer();
        Parser parser = new Parser();
        ArrayList<Parancs> parancsok = null;
        try {

            parancsok = parser.parse("Resources/" + filename);

            for (int i = 0; i < parancsok.size(); i++) {
                Parancs p = parancsok.get(i);
                String tipus = p.getTipus();
                String nev = p.getNev();
                String fuggveny = p.getFuggvenynev();
                Class<?>[] paramTypes = e.argClasses(p.getParamTypes());
                String[] params = p.getParams();

                if (fuggveny.equals("create")) {
                    Object obj = e.construct(tipus, paramTypes, params);
                    e.put(nev, obj);
                } else {
                    Object o = e.get(nev);
                    Object ret = e.runTheMethod(o, fuggveny, paramTypes, params);
                    e.put("ret" + String.valueOf(i), ret);
                }
            }
        } catch (FileNotFoundException | NoSuchMethodException | InstantiationException |
                IllegalAccessException | InvocationTargetException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
}