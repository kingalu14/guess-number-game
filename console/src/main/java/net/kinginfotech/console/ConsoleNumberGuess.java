package net.kinginfotech.console;

import lombok.extern.slf4j.Slf4j;
import net.kinginfotech.Game;
import net.kinginfotech.MessageGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;
@Component
@Slf4j
public class ConsoleNumberGuess {

    //===fields===
    private final Game game;
    private final MessageGenerator messageGenerator;


    public ConsoleNumberGuess(Game game, MessageGenerator messageGenerator) {
        this.game = game;
        this.messageGenerator = messageGenerator;
    }

    //===Event==
    @EventListener(ContextRefreshedEvent.class)
    public void start(){
        log.info("start()--> container ready for use");
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.println(messageGenerator.getMainMessage());
            System.out.println(messageGenerator.getResultMessage());

            int guess = scanner.nextInt();
            scanner.nextLine();
            game.setGuess(guess);
            game.check();
            if(game.isGameWon() || game.isGameLost()){
                System.out.println(messageGenerator.getResultMessage());
                System.out.println("Play again y/n?");
                String playAgain = scanner.nextLine().trim();
                if(!playAgain.equalsIgnoreCase("y")){
                    break;
                }
                game.reset();
            }


        }

    }
}
