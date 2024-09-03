package commands;

import java.io.IOException;

import exceptions.InputException;
import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;


/**
 * Represents a command to delete a task from the task list.
 * This command handles the removal of a task at a specified index in the task list.
 */
public class DeleteCommand implements Command {
    private final int taskIndex;

    /**
     * Constructs a DeleteCommand with the specified task index.
     * The index is derived from the user input and validated to ensure it is a valid number.
     *
     * @param taskIndex the index of the task to be deleted, as provided by the user.
     * @throws InputException if the task index is invalid, missing, or cannot be parsed into an integer.
     */
    public DeleteCommand(String taskIndex) throws InputException {
        if (taskIndex.trim().isEmpty()) {
            throw new InputException("Please specify which task number to delete.");
        }
        try {
            this.taskIndex = Integer.parseInt(taskIndex.trim()) - 1;
        } catch (NumberFormatException e) {
            throw new InputException("Invalid task number format. Please enter a valid number.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (taskIndex >= 0 && taskIndex < tasks.size()) {
            Task removedTask = tasks.remove(taskIndex);
            storage.saveTaskList(tasks.getTasks());

            ui.printLine();
            ui.show("Noted. I've removed this task:");
            ui.show(removedTask.toString());
            ui.show("Now you have " + tasks.size() + " tasks in the list.");
            ui.printLine();
        } else {
            ui.show("The specified task does not exist.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
