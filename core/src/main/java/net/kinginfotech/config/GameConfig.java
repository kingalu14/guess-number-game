package net.kinginfotech.config;

import net.kinginfotech.GuessCount;
import net.kinginfotech.MaxNumber;
import net.kinginfotech.MinNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = "net.kinginfotech")
@PropertySource("classpath:config/game.properties")
public class GameConfig {
    //== fields ==
    @Value("${game.maxNumber}")
    private int maxNumber;

    @Value("${game.minNumber}")
    private int minNumber;

    @Value("${game.guessCount}")
    private int guessCount;

    //==bean methods==
    @Bean
    @MaxNumber
    public int maxNumber(){
        return  maxNumber;
    }

    @Bean
    @MinNumber
    public int minNumber(){
        return  minNumber;
    }

    @Bean
    @GuessCount
    public int guessCount(){
        return  guessCount;
    }

}
