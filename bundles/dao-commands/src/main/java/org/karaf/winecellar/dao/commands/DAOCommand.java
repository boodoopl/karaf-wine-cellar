package org.karaf.winecellar.dao.commands;

import org.apache.felix.service.command.CommandSession;
import org.apache.karaf.shell.commands.Action;
import org.karaf.winecellar.dao.GeneralDAO;

abstract public class DAOCommand implements Action {

    protected GeneralDAO generalDAO;

    public void setGeneralDAO(GeneralDAO generalDAO) {
        this.generalDAO = generalDAO;
    }

    @Override
    public abstract Object execute(CommandSession commandSession) throws Exception;
}
