package org.karaf.winecellar.dao.commands;

import org.apache.felix.service.command.CommandSession;
import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.karaf.winecellar.model.Wine;;

@Command(scope = "dao", name = "addWine", description = "Add Wine using DAO")
public class AddWineCommand extends DAOCommand {
    @Argument(index=0, name="Wine Name", required=true, description="Wine Name", multiValued=false)
    String wineName;
    @Argument(index=1, name="Wine Country", required=true, description="Wine Country", multiValued=false)
    String wineCountry;
    @Argument(index=2, name="Wine Picture", required=true, description="Wine Picture", multiValued=false)
    String winePicture;
    @Argument(index=3, name="Wine Description", required=true, description="Wine Description", multiValued=false)
    String wineDescription;

    @Override
    public Object execute(CommandSession commandSession) throws Exception {
        generalDAO.add(new Wine(wineName, wineCountry, winePicture, wineDescription));
        return null;
    }
}