package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features= "src/test/resources/features",
        glue={"hooks","steps"},
        tags="@tables001", //@dropdown001 and @login001 and - mvn clean test '-Dcucumber.tags=@tables001'
        plugin={"pretty", "html:src/test/resources/report/report.html"}
)



public class Runner {
}
