public class Deadline extends Task{

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public Task createTask(String input) {
        String[] details = input.substring(9).split(" /by ");
        if (details.length == 2) {
            String description = details[0].trim();
            String by = details[1].trim();
            return new Deadline(description, by);
        } else {
            throw new IllegalArgumentException("Invalid format. Use: deadline <description> /by <date>");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
