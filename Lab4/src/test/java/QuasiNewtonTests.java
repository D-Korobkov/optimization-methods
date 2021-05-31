import QuasiNewton.BFGS;
import QuasiNewton.PowellMethod;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import java.io.IOException;

public class QuasiNewtonTests extends MethodTests {

    @BeforeClass
    public static void prepare() {

        System.out.println("prepare");
    }

    @Before
    public void preTest() {

        System.out.println("run test");
    }

    @Test
    public void BFGSTest() throws IOException {

        System.out.println("run BFGSTest");

        testOnFunctions(new BFGS(eps), eps);

        System.out.println("BFGS test passed");

    }

    @Test
    public void PowellTest() throws IOException {

        System.out.println("run PowellTest");

        testOnFunctions(new PowellMethod(eps), eps);

        System.out.println("Powell's test passed");

    }

}
