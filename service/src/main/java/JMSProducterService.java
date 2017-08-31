import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.*;

/**
 * Created by rongbin.xie on 2017/8/25.
 */
@Service
public class JMSProducterService {

    @Autowired
    private JMSServiceHelper jMSServiceHelper;

    public  void createMessage() {
        //连接工厂

        try {
            //通过连接工厂获取连接
            Connection connection = jMSServiceHelper.createConnection();
            //启动连接
            connection.start();
            //创建session
            Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
            //创建一个名称为HelloWorld的消息队列
            Destination destination = session.createQueue("HelloWorld");

            //创建消息生产者
            MessageProducer messageProducer = session.createProducer(destination);
            //发送消息
            sendMessage(session, messageProducer);

            session.commit();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 发送消息
     *
     * @param session
     * @param messageProducer 消息生产者
     * @throws Exception
     */
    private static void sendMessage(Session session, MessageProducer messageProducer) throws Exception {
        for (int i = 0; i < 10; i++) {
            //创建一条文本消息
            TextMessage message = session.createTextMessage("ActiveMQ 发送消息" + i);
            System.out.println("发送消息：Activemq 发送消息" + i);
            //通过消息生产者发出消息
            messageProducer.send(message);
        }

    }
}
