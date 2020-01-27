package net.kinginfotech;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
@Slf4j
@Getter
@Component
public class GameImpl implements Game {
    @Getter(AccessLevel.NONE)
    private final NumberGenerator numberGenerator;

    private final int guessCount;

    private int number;
    private  int smallest;
    private  int biggest;
    private  int remainingGuesses;
    private boolean validNumberRange = true;

    @Setter
    private  int guess;

    @Autowired
    public GameImpl(NumberGenerator numberGenerator,@GuessCount int guessCount) {
        this.numberGenerator = numberGenerator;
        this.guessCount = guessCount;
    }


    /*    public GameImpl(NumberGenerator numberGenerator){
       this.numberGenerator = numberGenerator;
       number = numberGenerator.next();
    }*/
    //== init ==
    @PostConstruct
    @Override
    public void reset() {
        smallest=numberGenerator.getMinNumber();
        guess = 0;
        remainingGuesses = guessCount;
        biggest = numberGenerator.getMaxNumber();
        number = numberGenerator.next();
        log.trace("the number is {}", number);
    }

    public void preDestroy(){
        log.info("in Game pre Destroy");
    }

    // a setter method so that the Spring container can inject a NumberGenerator
    /*  instead we use Autowired annotation
        public void setNumberGenerator(NumberGenerator numberGenerator) {
        this.numberGenerator = numberGenerator;
         number = numberGenerator.next();
    }*/


    @Override
    public void check() {
        checkValidNumberRange();
        if(validNumberRange){
            if (guess > number){
                biggest = guess-1;
            }
            if(guess < number){
                smallest = guess + 1;
            }
            if(isGameWon()){
            }
        }
        remainingGuesses--;

    }

    @Override
    public boolean isGameWon() {
        return guess == number;
    }

    @Override
    public boolean isGameLost() {
        return !isGameWon() && remainingGuesses <= 0;
    }
    //==private method==
    private void checkValidNumberRange(){
        validNumberRange = (guess >= smallest) && (guess <= biggest);
    }
}
