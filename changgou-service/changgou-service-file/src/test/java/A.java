import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class A {
    @Test
    public static void main(String[] args) {
        ArrayList list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f"));
        for (Object s : list) {
            if(s.equals("a")){
                System.out.println(s);
                list.remove(s);
            }
        }
    }

    @Test
    public static void main(){
        ArrayList list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f"));
        Iterator itr = list.iterator();
        while (itr.hasNext()) {
            boolean s = itr.hasNext();
            System.out.println(s);
            itr.remove();
        }
    }
}

