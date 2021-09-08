package com.blured.ecomerce.product.messages.listener;

import com.blured.ecomerce.product.domain.entity.Product;
import com.blured.ecomerce.product.domain.messages.ProductUpdMsg;
import com.blured.ecomerce.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProductUpdListener {
    @Autowired
    ProductService productService;

    @JmsListener(destination = "${jms.ProductTopic}", subscription = "productSearchListener")
    public void receiveMessage(ProductUpdMsg msg) {
        Product product = msg.getProduct();
        boolean isDelete = msg.isDelete();

        if (isDelete) {
            productService.deleteProduct(product);
            log.info("deleted {}", product.getId());
        } else {
            productService.insertUpdateProduct(product);
            log.info("upserted {}", product.getId());
        }
    }

    @Bean
    public MessageConverter jacksonJmsMessageConverter() {
        MappingJackson2MessageConverter converter = new MappingJackson2MessageConverter();
        converter.setTargetType(MessageType.BYTES);
        converter.setTypeIdPropertyName("_type");
        return converter;
    }

}
