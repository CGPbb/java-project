package changgou.order.listener;

import com.alibaba.fastjson.JSON;
import com.changgou.order.config.RabbitMQConfig;
import com.changgou.order.service.OrderService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @RabbitListener 可以标注在类上面，需配合 @RabbitHandler 注解一起使用
 @RabbitListener 标注在类上面表示当有收到消息的时候，就交给 @RabbitHandler 的方法处理，具体使用哪个方法处理，根据 MessageConverter 转换后的参数类型

 */
/*
@Component
@RabbitListener(queues = "order_pay")
public class OrderPayListener {
    private OrderService orderService;
    @RabbitHandler
    public void updatePayStatus(String message){
        System.out.println("接收到消息:"+message);
        Map map = JSON.parseObject(message,Map.class);
        orderService.updatePayStatus((String)map.get("orderId"),(String)map.get("transactionId"));
    }
}*/
@Component
public class OrderPayListener {

    @Autowired
    private OrderService orderService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_PAY)
    public void receivePayMessage(String message){
        System.out.println("接收到了订单支付的消息:"+message);

        Map map = JSON.parseObject(message, Map.class);

        //调用业务层,完成订单数据库的修改
        orderService.updatePayStatus((String)map.get("orderId"),(String)map.get("transactionId"));
    }
}
