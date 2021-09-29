package name.baskanov;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

//import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.converter.ConvertWith;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;
/*
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assume.

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
*/
import org.junit.jupiter.api.TestFactory;


public class myMathTest 
{
    private static int count=0;
    
    @BeforeAll
    public static void HelloClass()
    {
       System.out.println("Hello, World!");
    }
    @BeforeEach
    public void Hello()
    {
        myMathTest.count++;
        System.out.println( String.format("Hello %d-th test",myMathTest.count));
    }
    
    @Test
    @Timeout (value = 100, unit = TimeUnit.MILLISECONDS)
    @DisplayName("Тест с таймаутом") 
    public void shouldAnswerWithTrue()
    {
        try {
            Thread.sleep(90);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue( true );
    }
    
    @Test
    public void TestMyMathMod1()
    {
        assertAll(
            () -> assertEquals(MyMath.myMod(10, 5),0),
            () -> assertEquals(MyMath.myMod(11, 5),1),
            () -> assertEquals(MyMath.myMod(12, 5),2),
            () -> assertEquals(MyMath.myMod(13, 5),3),
            () -> assertEquals(MyMath.myMod(14, 5),4),
            () -> assertEquals(MyMath.myMod(15, 5),0)
        );
    }

    @RepeatedTest(20)
    public void TestMyMathSqr1()
    {
        assertEquals( MyMath.MySqr(3),9);
    }
    
    @Test
    public void TestMyMathSqr2()
    {
        assertEquals( MyMath.MySqr(0),0);
    }

    @Test
    public void TestThrowEx1()
    {
        assertEquals( MyMath.throwEx(1),1);
    }
    @Test
    public void TestThrowEx2()
    {
        final int a = 0;
        Executable exec = () -> {MyMath.throwEx(a);};
      /*  {
            @Override
            public void execute()
            {
                MyMath.throwEx(a);
            }
        };*/
        //() -> {MyMath.throwEx(0);}
        assertThrows(NullPointerException.class, exec);
    }
    static int[][] data = new int[][] { { 2, 4 }, { 4, 16 }, { 0, 0 } , { 12, 144 },{ 13, 169 }};
    @TestFactory
    Stream<DynamicTest> testFactoryFOrMySqr() {
        
        return Arrays.stream(data).map(entry -> {
            int m1 = entry[0];
            int expected = entry[1];
            return dynamicTest(m1 + " * " + m1 + " = " + expected, () -> {
                assertEquals(expected, MyMath.MySqr(m1));
            });
        });
    }
     public static int[][] dataForParametrs() {
         return data;
    }

    @ParameterizedTest
    @MethodSource("dataForParametrs")
    void testWithParameters(int[] data) {
        int m1 = data[0];
        int expected = data[1];
        assertEquals(expected, MyMath.MySqr(m1));
    }
    @ParameterizedTest
    @ValueSource(ints = {2, 4,0, 12, 13 })
    void testWithConverter(@ConvertWith(returnTwoInts.class) int[] data) {
        int m1 = data[0];
        int expected = data[1];
        assertEquals(expected, MyMath.MySqr(m1));
    }
    
    static class returnTwoInts extends SimpleArgumentConverter {
        @Override
        protected Object convert(Object source, Class<?> targetType) {
            assertEquals(Integer.class, source.getClass(), "Can only convert from Integers.");
            assertEquals(int[].class, targetType, "Can only convert to Integers");
            int [] data = new int[2];
            data[0]= (Integer) source;
            data[1] =(Integer) source*(Integer) source;
            System.out.println(data.toString());
            return data;
        }
    }

    @AfterEach
    public void GoodBye()
    {
       System.out.println( String.format("GoodBye, %d-th test",myMathTest.count));
    }
    @AfterAll
    public static void GoodbyeClass()
    {
       System.out.println("Goodbye, World!");
    }
}
