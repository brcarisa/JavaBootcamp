package edu.school21.app;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;

import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException, InterruptedException {
        Game game = Game.getInstance();
        try {
            JCommander.newBuilder().addObject(game).build().parse(args);
        } catch (ParameterException e) {
            throw new IllegalParametersException(e.getMessage());
        }
        game.start();
    }
}
