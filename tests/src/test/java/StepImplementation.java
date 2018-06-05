import com.thoughtworks.gauge.Step;
import org.junit.Assert;

public class StepImplementation {


    @Step("test step")
    public void implementation1() {
        Assert.assertFalse(false);
    }
}
