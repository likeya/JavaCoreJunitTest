package junit.test;

import junit.test.domain.Caculator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.runners.Parameterized.*;

/**
 * Created by Likeya on 2017/2/27.
 */
@RunWith(value = Parameterized.class
)
public class CaculatorTest {
    /*
        @Test
        public void testAdd() {
            Caculator caculator = new Caculator();
            double result = caculator.add(10, 50);
    //        assertEquals("是否相等",60, result, 0);
            assertNotNull(result);
        }
    */
    private double expact;
    private double valueOne;
    private double valueTwo;

    @Parameters
    public static Collection<Integer[]> getTestParameters() {
        return Arrays.asList(new Integer[][]{
                {2 , 1 , 1} ,
                {3 , 2 , 1} ,
                {4 , 3 , 1} ,
        });
    }

    public CaculatorTest(double expact, double valueOne, double valueTwo) {
        this.expact = expact;
        this.valueOne = valueOne;
        this.valueTwo = valueTwo;
    }

    @Test
    public void sum() {
        Caculator caculator = new Caculator();
        Assert.assertEquals(expact, caculator.add(valueOne, valueTwo), 0);
    }

}
