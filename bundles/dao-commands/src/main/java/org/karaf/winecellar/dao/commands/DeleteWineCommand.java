package org.karaf.winecellar.dao.commands;

import org.apache.felix.service.command.CommandSession;
import org.apache.karaf.shell.commands.Argument;
import org.apache.karaf.shell.commands.Command;
import org.karaf.winecellar.model.Wine;;

@Command(scope = "dao", name = "deleteWine", description = "Delete Wine using DAO")
public class DeleteWineCommand extends DAOCommand {
    @Argument(index=0, name="Wine Id", required=true, description="Wine Id", multiValued=false)
    long wineId;

    @Override
    public Object execute(CommandSession commandSession) throws Exception {
        generalDAO.removeById(Wine.class, wineId);
        return null;
    }
}