package jp.co.kin.db.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(locations = { "classpath:common-context.xml", "classpath:db-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class BaseDbTest {

}
