package peiliping;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import eu.bitwalker.useragentutils.Browser;




public class Test {

    public static void main(String[] args) {        
        
        ArrayList<Browser> l = new ArrayList<Browser>();
        
        
        for (Browser b : Browser.values()){
            l.add(b);
        }
        
        Collections.sort(l, new Comparator<Browser>() {
            @Override
            public int compare(Browser o1, Browser o2) {
                // TODO Auto-generated method stub
                return 0;
            }
        });
        
        
        
    }
}
