package jikan.command;

import jikan.activity.Activity;
import jikan.activity.ActivityList;
import jikan.exception.EmptyNameException;
import jikan.exception.InvalidTimeFrameException;
import jikan.storage.Storage;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DeleteCommandTest {

    ActivityList activities = new ActivityList();
    HashSet<String> tags = new HashSet<>();

    void populateActivityList() throws InvalidTimeFrameException {
        activities.storage = new Storage("data/activityList_test.txt");
        try {
            activities.storage.clearFile();
        } catch (FileNotFoundException e) {
            System.out.println("Could not find file.");
        }
        tags.add("tag1");
        tags.add("tag2");
        LocalDateTime startTime = LocalDateTime.parse("2020-01-01T08:00:00");
        LocalDateTime endTime =  LocalDateTime.parse("2020-01-01T10:00:00");
        Duration duration = Duration.between(startTime, endTime);
        Duration allocatedTime = Duration.parse("PT0S");
        Activity activity1 = new Activity("Activity1", startTime, endTime, duration, tags, allocatedTime);
        Activity activity2 = new Activity("Activity2", startTime, endTime, duration, tags, allocatedTime);
        Activity activity3 = new Activity("Activity3", startTime, endTime, duration, tags, allocatedTime);
        activities.add(activity1);
        activities.add(activity2);
        activities.add(activity3);
    }

    @Test
    void executeDelete() {
        try {
            populateActivityList();
        } catch (InvalidTimeFrameException e) {
            System.out.println("Invalid time frame.");
        }
        String parameters = "Activity2";

        Command command = new DeleteCommand(parameters);
        try {
            command.executeCommand(activities);
        } catch (EmptyNameException | InvalidTimeFrameException e) {
            System.out.println("Field error.");
        }

        assertEquals(activities.get(1).getName(), "Activity3");
        assertEquals(activities.getSize(), 2);
    }

}