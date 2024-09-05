package commands;

import java.io.IOException;

import exceptions.InputException;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * Represents a command to unmark a task as not done in the task list.
 * This command handles the unmarking of a task at a specified index, indicating it is not completed.
 */
public class UnmarkCommand extends Command {
    private final int taskIndex;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     * This command is used to unmark a task (mark it as not done) in the task list.
     *
     * @param taskIndex the index of the task to be unmarked, as a string.
     * @throws InputException if the task index is empty, or if the task index is not a valid number.
     */
    public UnmarkCommand(String taskIndex) throws InputException {
        if (taskIndex.trim().isEmpty()) {
            throw new InputException("Please specify which task number to unmark.");
        }
        try {
            this.taskIndex = Integer.parseInt(taskIndex.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InputException("Invalid task number format. Please enter a valid number.");
        }
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            tasks.get(taskIndex).unmark();
            storage.saveTaskList(tasks.getTasks());

            return "OK, I've marked this task as not done yet:"
                    + tasks.get(taskIndex).toString();
        } else {
            return "The specified task does not exist.";
        }
    }
}
