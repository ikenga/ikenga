package me.ikenga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;


@Component
@EnableAutoConfiguration
@ComponentScan
public class IkengaBoot {

    /**
     * spring configuration main method to build context
     *
     * @param args
     */
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(IkengaBoot.class, args);
    }
}
