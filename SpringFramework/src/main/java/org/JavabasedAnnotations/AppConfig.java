package org.JavabasedAnnotations;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;

@Configuration
@ComponentScan("org.JavabasedAnnotations")
public class AppConfig {

//    @Bean(name = {"com1","com2","desk"})
//    //@Scope("prototype")
//    public Desktop desktop(){
//        return new Desktop();
//    }
//
//    @Bean
//    @Primary
//    public Laptop laptop(){
//        return new Laptop();
//    }
//
//    @Bean
//    public Alien alien(@Autowired Computer com){
//        Alien alien = new Alien();
//        alien.setCom(com);
//        return alien;
//    }


}
