package org.karaf.winecellar.dao.commands;

import org.apache.felix.service.command.CommandSession;
import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.karaf.winecellar.model.Wine;;

@Command(scope = "dao", name = "updateWine", description = "Update Wine using DAO")
public class UpdateWineCommand extends DAOCommand {
    @Argument(index=0, name="Wine Id", required=true, description="Wine Id", multiValued=false)
    long wineId;
    @Argument(index=1, name="Wine Name", required=true, description="Wine Name", multiValued=false)
    String wineName;
    @Argument(index=2, name="Wine Country", required=true, description="Wine Country", multiValued=false)
    String wineCountry;
    @Argument(index=3, name="Wine Picture", required=true, description="Wine Picture", multiValued=false)
    String winePicture;
    @Argument(index=4, name="Wine Description", required=true, description="Wine Description", multiValued=false)
    String wineDescription;

    @Override
    public Object execute(CommandSession commandSession) throws Exception {
        Wine existingWine = generalDAO.getById(Wine.class, wineId);
//        existingWine.setName(wineName);
//        existingWine.setCountry(wineCountry);
//        existingWine.setDescription(wineDescription);
//        existingWine.setPicture(winePicture);
//        generalDAO.update(existingWine);
        return null;
    }
}