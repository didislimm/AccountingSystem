package com.mironov.controller.command;

import com.mironov.controller.command.impl.CheckValuesCommand;
import com.mironov.controller.command.impl.CreateHouseCommand;
import com.mironov.controller.command.impl.MenuCommand;
import com.mironov.controller.command.impl.OutputHouseCommand;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {
    private Map<ParameterName, Command> commands = new HashMap<>();

    public CommandProvider() {
        commands.put(ParameterName.CREATE_HOUSE,new CreateHouseCommand());
        commands.put(ParameterName.CHECK_VALUES,new CheckValuesCommand());
        commands.put(ParameterName.OUTPUT_HOUSE,new OutputHouseCommand());
        commands.put(ParameterName.MENU,new MenuCommand());

    }

    public Command getCommand(String commandName) {
        Command command;
        ParameterName valueName;

        commandName = commandName.toUpperCase();
        valueName = ParameterName.valueOf(commandName);

        command = commands.get(valueName);

        return command;
    }
}
