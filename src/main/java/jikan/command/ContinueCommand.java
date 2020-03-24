package jikan.command;

import jikan.Log;
import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.NoSuchActivityException;
import jikan.parser.Parser;
import jikan.ui.Ui;

import java.time.LocalDateTime;

/**
 * Represents a command to continue recording an existing activity.
 */
public class ContinueCommand extends Command {

    /**
     * Constructor to create a new continue command.
     */
    public ContinueCommand(String parameters) {
        super(parameters);
    }

    @Override
    public void executeCommand(ActivityList activityList) {
        try {
            //Parser.parseContinue(activityList);
            int index = activityList.findActivity(parameters);
            if (index != -1) {
                // activity is found
                Parser.activityName = activityList.get(index).getName();
                Parser.startTime = LocalDateTime.now();
                Parser.tags = activityList.get(index).getTags();
                Parser.continuedIndex = index;
                String line = Parser.activityName + " was continued";
                Ui.printDivider(line);
            } else {
                throw new NoSuchActivityException();
            }
        } catch (NoSuchActivityException e) {
            Ui.printDivider("No activity with this name exists!");
            Log.makeInfoLog("Continue command failed as there was no such activity saved.");
        }
    }
}

