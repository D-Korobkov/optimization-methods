import NewtonMethods.ClassicNewtonMethod;
import NewtonMethods.LinarySearchNewtonMethod;
import NewtonMethods.MarquardtMethod;
import NewtonMethods.NewtonMethodWithDescentDirection;
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

        System.out.println("run classicNewtonTest");

        testOnFunctions(new ClassicNewtonMethod(), eps);

        System.out.println("classicNewton test passed");

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

        testOnFunctions(new NewtonMethodWithDescentDirection(), eps);

        System.out.println("descentDirectionNewton test passed");
    }

    @Test
    public void firstVariantOfMarquardtTest() throws IOException {
        System.out.println("run firstVariantOfMarquardtTest");

        testOnFunctions(new MarquardtMethod(), eps);

        System.out.println("firstVariantOfMarquardt test passed");
    }
}
