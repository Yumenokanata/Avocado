package indi.yume.tools.avocado;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import indi.yume.tools.avocado.model.DayDate;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
//    @Test
//    public void addition_isCorrect() throws Exception {
//        assertEquals(4, 2 + 2);
//    }

    @Test
    public void testArrayList() {
        String preFix = "item";

        List<DayDate> list = new ArrayList<>();

        DayDate tempDay = new DayDate();
        for (int i = 0; i < 365; i++) {
            list.add(new DayDate(tempDay));
            tempDay.addDay(1);
        }

        long time = System.currentTimeMillis();
        for (int n = 0; n < 4; n++) {
            List<DayDate> tempList = new ArrayList<>();
            int start = 100;
            tempDay = new DayDate();
            tempDay.addDay(start);

            for (int i = start; i < start + 42; i++) {
//                int itemIndex = list.indexOf(tempDay);
//                DayDate item = list.get(itemIndex);

                if(list.contains(tempDay)) {
                    tempList.add(new DayDate(tempDay));
                }

                tempDay.addDay(1);
            }

            System.out.println("result= " + tempList.size());
        }
        System.out.println("ArrayList: spend time= " + (System.currentTimeMillis() - time));
    }

    @Test
    public void testHashSet() {
        String preFix = "item";

        TreeSet<DayDate> set = new TreeSet<>();

        DayDate tempDay = new DayDate();
        for (int i = 0; i < 365; i++) {
            set.add(new DayDate(tempDay));
            tempDay.addDay(1);
        }

        long time = System.currentTimeMillis();
        for (int n = 0; n < 4; n++) {
            List<DayDate> tempList = new ArrayList<>();
            int start = 100;
            tempDay = new DayDate();
            tempDay.addDay(start);

            for (int i = start; i < start + 42; i++) {
//                int itemIndex = set.indexOf(tempDay);
                if(set.contains(tempDay)) {
                    tempList.add(new DayDate(tempDay));
                }

                tempDay.addDay(1);
            }

            System.out.println("result= " + tempList.size());
        }
        System.out.println("HashSet: spend time= " + (System.currentTimeMillis() - time));
    }
}