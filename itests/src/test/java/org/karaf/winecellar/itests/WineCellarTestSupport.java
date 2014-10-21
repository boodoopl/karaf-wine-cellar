package org.karaf.winecellar.itests;

import static org.ops4j.pax.exam.CoreOptions.maven;
import static org.ops4j.pax.exam.karaf.options.KarafDistributionOption.karafDistributionConfiguration;

import org.ops4j.pax.exam.MavenUtils;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import java.io.File;

public class WineCellarTestSupport {

    @Configuration
    public Option[] config() {
        return new Option[]{ karafDistributionConfiguration().frameworkUrl(
                maven().groupId("org.karaf.winecellar").artifactId("winecellar-distribution").type("tar.gz").versionAsInProject())
                .karafVersion(MavenUtils.getArtifactVersion("org.apache.karaf", "apache-karaf")).name("Winecellar Karaf").unpackDirectory(new File("target/paxexam/")) };
    }
}