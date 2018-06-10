import com.ovixeu.core.DriverFactory;
import com.thoughtworks.gauge.Step;
import org.junit.Assert;

public class StepImplementation {


    @Step("test step")
    public void implementation1() {
        DriverFactory driverFactory = new DriverFactory();
        Assert.assertFalse(false);
    }


}
