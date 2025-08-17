package com.scheduler.springorderscheduler.schedulers;

import com.scheduler.springorderscheduler.entity.Order;
import com.scheduler.springorderscheduler.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

// this service class will get the pending orders from db and process them
@Service
public class OrderScheduler {
    @Autowired
    OrderRepository repository;
//    @Scheduled(fixedRate = 5000)
//    @Scheduled(initialDelay = 10000, fixedDelay = 3000)
    @Scheduled(cron = "* * * * * *")// cron expression
    public void processPendingOrders(){
        System.out.println("Processing Orders");
        List<Order> orders = repository.findByStatus("PENDING");
        orders.forEach(order ->{
            order.setStatus("COMPLETED");
            repository.save(order);
            System.out.println("Order Processing completed for email address "+order.getCustomerEmail());
        });

        System.out.printf("Processed {%d} pending orders \n",orders.size());
    }
}
