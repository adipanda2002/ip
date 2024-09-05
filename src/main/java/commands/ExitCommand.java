package commands;

import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * Represents a command to exit the application.
 * This command signals the application to terminate, with no additional actions required.
 */
public class ExitCommand extends Command {

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return Ui.showGoodbye();
    }
}
