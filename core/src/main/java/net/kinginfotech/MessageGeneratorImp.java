package net.kinginfotech;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component@Slf4j
public class MessageGeneratorImp implements  MessageGenerator{

    private final Game game;

    public MessageGeneratorImp(Game game) {
        this.game = game;
    }

    @PostConstruct
    public void init(){
        log.info("game = {}", game);
    }

    @Override
    public String getMainMessage() {
        return "Number is in between " + game.getSmallest() +
                " and " + game.getBiggest() +
                ". can you guess it?";
    }

    @Override
    public String getResultMessage() {
        if(game.isGameWon())
            return "You guess it! The number was"+ game.getNumber();
        else if(game.isGameLost())
            return "You lost! The number was "+ game.getNumber();
        else if(!game.isValidNumberRange())
            return "Invalid number range";
        else if(game.getRemainingGuesses()==game.getGuessCount())
            return "What is your first guess?";
        else {
            String direction="Lower";
            if(game.getGuess() < game.getNumber())
                direction = "Higher";
             return  direction +"! You have "+ game.getRemainingGuesses() + " guesses left";
        }
    }

    public int getRandomNumberMessage(){
         return 9;
    }
}
