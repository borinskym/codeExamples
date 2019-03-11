package integrationTestWithClasses;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {EventsFetcherTestConfiguration.class})
public class IntegrationTestWithClasses {
}
