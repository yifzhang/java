package peiliping.versioncompare;

import org.apache.maven.artifact.versioning.DefaultArtifactVersion;

public class Main {

	public static void main(String[] args) {
		
		DefaultArtifactVersion minVersion = new DefaultArtifactVersion("1.0.1");
		DefaultArtifactVersion maxVersion = new DefaultArtifactVersion("1.10");

		DefaultArtifactVersion version = new DefaultArtifactVersion("1.11");

		if (version.compareTo(minVersion) == -1 || version.compareTo(maxVersion) == 1) {
		    System.out.println("Sorry, your version is unsupported");
		}
		

	}

}
