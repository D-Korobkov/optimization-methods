import NewtonMethods.ClassicNewtonMethod;
import NewtonMethods.LinarySearchNewtonMethod;
import NewtonMethods.NewtoneMethodWithDescentDirection;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;


public class NewtonTests extends MethodTests {


    @BeforeClass
    public static void prepare() {

        System.out.println("prepare");
    }

    @Before
    public void preTest() {

        System.out.println("run test");
    }

    @Test
    public void classicNewtoneTest() throws IOException {

        System.out.println("run classicNewtoneTest");

        testOnFunctions(new ClassicNewtonMethod(), eps);

        System.out.println("classicNewtone test passed");

    }

    @Test
    public void linearSearchNewtonTest() throws IOException {
        System.out.println("run linearSearchNewtonTest");

        testOnFunctions(new LinarySearchNewtonMethod(), eps);

        System.out.println("classicNewtone test passed");
    }

    @Test
    public void descentDirectionNewtonTest() throws IOException {
        System.out.println("run descentDirectionNewtonTest");

        testOnFunctions(new NewtoneMethodWithDescentDirection(), eps);

        System.out.println("descentDirectionNewtonTest test passed");
    }
}
