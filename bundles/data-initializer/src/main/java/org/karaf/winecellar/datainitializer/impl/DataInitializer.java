package org.karaf.winecellar.datainitializer.impl;

import org.karaf.winecellar.dao.GeneralDAO;
import org.karaf.winecellar.model.Image;
import org.karaf.winecellar.model.Wine;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataInitializer {

    private GeneralDAO generalDAO;

    public void setGeneralDAO(GeneralDAO generalDAO) {
        this.generalDAO = generalDAO;
    }

    public void init() {
        long wineCount = generalDAO.getCount(Wine.class);
        if (wineCount == 0) {
            generalDAO.add(new Wine("CHATEAU DE SAINT COSME",
									"2009",
									"Grenache / Syrah",
									"France",
									"Southern Rhone / Gigondas",
									"The aromas of fruit and spice give one a hint of the light drinkability of " +
                                            "this lovely wine, which makes an excellent complement to fish dishes.",
									addImageFromFile("saint_cosme.jpg")));
            generalDAO.add(new Wine("LAN RIOJA CRIANZA",
									"2006",
									"Tempranillo",
									"Spain",
									"Rioja",
									"A resurgence of interest in boutique vineyards has opened the door for this " +
                                            "excellent foray into the dessert wine market. Light and bouncy, with a " +
                                            "hint of black truffle, this wine will not fail to tickle the taste buds.",
									addImageFromFile("lan_rioja.jpg")));
            generalDAO.add(new Wine("MARGERUM SYBARITE",
									"2010",
									"Sauvignon Blanc",
									"USA",
									"California Central Cosat",
									"The cache of a fine Cabernet in ones wine cellar can now be replaced with a " +
                                            "childishly playful wine bubbling over with tempting tastes of\nblack " +
                                            "cherry and licorice. This is a taste sure to transport you back in time.",
									addImageFromFile("margerum.jpg")));
            generalDAO.add(new Wine("OWEN ROE \"EX UMBRIS\"",
									"2009",
									"Syrah",
									"USA",
									"Washington",
									"A one-two punch of black pepper and jalapeno will send your senses reeling," +
                                            " as the orange essence snaps you back to reality. Don\"t miss\nthis " +
                                            "award-winning taste sensation.",
									addImageFromFile("ex_umbris.jpg")));
            generalDAO.add(new Wine("REX HILL",
									"2009",
									"Pinot Noir",
									"USA",
									"Oregon",
									"One cannot doubt that this will be the wine served at the Hollywood award shows, " +
                                            "because it has undeniable star power. Be the first to catch\nthe debut " +
                                            "that everyone will be talking about tomorrow.",
									addImageFromFile("rex_hill.jpg")));
            generalDAO.add(new Wine("VITICCIO CLASSICO RISERVA",
									"2007",
									"Sangiovese Merlot",
									"Italy",
									"Tuscany",
									"Though soft and rounded in texture, the body of this wine is full and rich and" +
                                            " oh-so-appealing. This delivery is even more impressive when one takes " +
                                            "note of the tender tannins that leave the taste buds wholly satisfied.",
									addImageFromFile("viticcio.jpg")));
            generalDAO.add(new Wine("CHATEAU LE DOYENNE",
									"2005",
									"Merlot",
									"France",
									"Bordeaux",
									"Though dense and chewy, this wine does not overpower with its finely balanced" +
                                            " depth and structure. It is a truly luxurious experience for the\nsenses.",
									addImageFromFile("le_doyenne.jpg")));
            generalDAO.add(new Wine("DOMAINE DU BOUSCAT",
									"2009",
									"Merlot",
									"France",
									"Bordeaux",
									"The light golden color of this wine belies the bright flavor it holds. " +
                                            "A true summer wine, it begs for a picnic lunch in a sun-soaked vineyard.",
									addImageFromFile("bouscat.jpg")));
            generalDAO.add(new Wine("BLOCK NINE",
									"2009",
									"Pinot Noir",
									"USA",
									"California",
									"With hints of ginger and spice, this wine makes an excellent complement to light " +
                                            "appetizer and dessert fare for a holiday gathering.",
									addImageFromFile("block_nine.jpg")));
            generalDAO.add(new Wine("DOMAINE SERENE",
									"2007",
									"Pinot Noir",
									"USA",
									"Oregon",
									"Though subtle in its complexities, this wine is sure to please a wide range " +
                                            "of enthusiasts. Notes of pomegranate will delight as the nutty finish " +
                                            "completes the picture of a fine sipping experience.",
									addImageFromFile("domaine_serene.jpg")));
            generalDAO.add(new Wine("BODEGA LURTON",
									"2011",
									"Pinot Gris",
									"Argentina",
									"Mendoza",
									"Solid notes of black currant blended with a light citrus make this wine an easy " +
                                            "pour for varied palates.",
									addImageFromFile("bodega_lurton.jpg")));
            generalDAO.add(new Wine("LES MORIZOTTES",
									"2009",
									"Chardonnay",
									"France",
									"Burgundy",
									"Breaking the mold of the classics, this offering will surprise and undoubtedly " +
                                            "get tongues wagging with the hints of coffee and tobacco in\nperfect " +
                                            "alignment with more traditional notes. Breaking the mold of the classics, " +
                                            "this offering will surprise and\nundoubtedly get tongues wagging with the" +
                                            " hints of coffee and tobacco in\nperfect alignment with more traditional " +
                                            "notes. Sure to please the late-night crowd with the slight " +
                                            "jolt of adrenaline it brings.",
									addImageFromFile("morizottes.jpg")));
        }
    }

    /**
     * Add image from resources by GeneralDAO and return Image id
     */
    private long addImageFromFile(String fileName) {
		InputStream inputStream = getClass().getResourceAsStream("/images/" + fileName);
		byte[] data = new byte[0];
		try {
			data = new byte[inputStream.available()];
		} catch (IOException e) {
			throw new RuntimeException("File read error", e);
		}
		try {
			inputStream.read(data);
		} catch (IOException e) {
			throw new RuntimeException("File read error", e);
		}
		Image image = new Image(data);
        generalDAO.add(image);
        return image.getId(); 
    }
}
