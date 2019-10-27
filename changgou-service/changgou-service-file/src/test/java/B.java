import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public  class B {
    @Test
    public static void main(){
        ArrayList list = new ArrayList<>(Arrays.asList("a", "b", "c", "d", "e", "f"));
        Iterator<String> itr = list.iterator();
        while (itr.hasNext()) {
            boolean s = itr.hasNext();
            System.out.println(s);
            itr.remove();
        }
    }
}
