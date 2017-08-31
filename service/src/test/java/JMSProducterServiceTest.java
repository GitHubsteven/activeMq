import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by rongbin.xie on 2017/8/28.
 */
public class JMSProducterServiceTest {
    @Autowired
    JMSProducterService  jMSProducterService;
    @Test
    public void createMessage() throws Exception {
        jMSProducterService.createMessage();
    }

}