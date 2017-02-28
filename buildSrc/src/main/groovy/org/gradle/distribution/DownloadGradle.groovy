package org.gradle.distribution

import org.gradle.api.DefaultTask
import org.gradle.api.tasks.*

class DownloadGradle extends DefaultTask {
    @Input 
    String gradleVersion
    
    @InputDirectory 
    File destinationDir
    
    @Input 
    String gradleDownloadBase = 'http://services.gradle.org/distributions'

    @TaskAction 
    void doDownloadGradle() {
		
		//download only if exists
		if( !getDestinationFile().exists() ) {
	        destinationFile.bytes = new URL(downloadUrl).bytes
		}
    }

    String getDownloadUrl() {
        "$gradleDownloadBase/$downloadFileName"
    }

    String getDistributionNameBase() {
        "gradle-$gradleVersion"
    }

    String getDownloadFileName() {
        "$distributionNameBase-bin.zip"
    }

    @OutputFile 
    File getDestinationFile() {
        new File(destinationDir, downloadFileName)
    }
}