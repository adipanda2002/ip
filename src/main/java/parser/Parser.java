package parser;

// used '*' import as all items are imported

import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.UnmarkCommand;
import exceptions.InputException;

/**
 * Parses user input into commands that can be executed by the application.
 * This class interprets the user's input string and returns the corresponding command object.
 */
public class Parser {
    /**
     * Parses the full command input string and returns the corresponding Command object.
     * The method splits the input into the command and its arguments, then creates the appropriate Command object.
     *
     * @param fullCommand the full user input string.
     * @return the corresponding Command object.
     * @throws InputException if the command is invalid or the input is incomplete.
     */
    public static Command parse(String fullCommand) throws InputException {
        String[] commandParts = fullCommand.trim().split(" ", 2);
        String command = commandParts[0].toLowerCase();
        return switch (command) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(commandParts.length > 1 ? commandParts[1].trim() : "");
            case "unmark" -> new UnmarkCommand(commandParts.length > 1 ? commandParts[1].trim() : "");
            case "delete" -> new DeleteCommand(commandParts.length > 1 ? commandParts[1].trim() : "");
            case "todo" -> new AddCommand("todo", commandParts.length > 1 ? commandParts[1].trim() : "");
            case "deadline" -> new AddCommand("deadline", commandParts.length > 1 ? commandParts[1].trim() : "");
            case "event" -> new AddCommand("event", commandParts.length > 1 ? commandParts[1].trim() : "");
            case "find" -> new FindCommand(commandParts.length > 1 ? commandParts[1].trim() : "");
            case "help" -> new HelpCommand();
            default -> throw new InputException("Invalid command. Type 'help' for assistance.");
        };
    }
}
