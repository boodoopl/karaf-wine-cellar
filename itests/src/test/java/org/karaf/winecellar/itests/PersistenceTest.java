package org.karaf.winecellar.itests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.karaf.winecellar.dao.GeneralDAO;
import org.karaf.winecellar.model.Wine;
import org.ops4j.pax.exam.junit.ExamReactorStrategy;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import javax.inject.Inject;

@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class PersistenceTest extends WineCellarTestSupport {

    @Inject
    GeneralDAO generalDAO;

    @Test
    public void checkWinesExit() {
        assertEquals(generalDAO.getCount(Wine.class), 1);
    }
}
