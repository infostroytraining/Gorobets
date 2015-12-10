package Task1.Analyzer.Implementations;

import Task1.Analyzer.Inrefaces.Command;

/**
 * Created by invincible_g_d on 12/10/15.
 */
public class CommandSwitcher {
    private Command frequencyCommand;
    private Command lengthCommand;
    private Command duplicatesCommand;

    public CommandSwitcher(Command frequencyCommand, Command lengthCommand, Command duplicatesCommand) {
        this.frequencyCommand = frequencyCommand;
        this.lengthCommand = lengthCommand;
        this.duplicatesCommand = duplicatesCommand;
    }

    public void frequencyDo(){
        frequencyCommand.executeCommand();
    }

    public void lengthDo(){
        lengthCommand.executeCommand();
    }

    public void duplicatesDo(){
        duplicatesCommand.executeCommand();
    }
}
