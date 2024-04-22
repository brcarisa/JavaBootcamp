package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;
import edu.school21.printer.logic.CommandLine;
import com.beust.jcommander.JCommander;
import java.io.IOException;

public class Program {
    public static void main(String[] args) throws IOException, IllegalArgumentException {
        try {
            CommandLine commandLine = new CommandLine();
            JCommander jCommander = new JCommander(commandLine);
            jCommander.parse(args);
            Logic logic = new Logic("/resources/it.bmp", commandLine);
            logic.OutputImage();
        } catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
