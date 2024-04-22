#1. Delete the directory
    rm -rf target && rm -rf lib
#2. Create the lib directory and target
    mkdir target && mkdir lib
#3. Loading command line libraries JCommander and JCDP
    curl https://repo1.maven.org/maven2/com/beust/jcommander/1.82/jcommander-1.82.jar -o lib/jcommander-1.82.jar
    curl https://repo1.maven.org/maven2/com/diogonunes/JCDP/4.0.2/JCDP-4.0.2.jar -o lib/JCDP-4.0.2.jar
#4  Extract files from library
    cd target
    jar xf ../lib/jcommander-1.82.jar
    jar xf ../lib/JCDP-4.0.2.jar
    rm -rf META-INF
    cd ..
#5  Compile lib and files
    javac -d target -cp lib/\* src/java/edu/school21/printer/*/*.java
#6  Copy resources
    cp -r src/resources ./target/resources
#7  Create a jar-file
    jar -cfm target/images-to-chars-printer.jar src/manifest.mf -C target .
#8  Run program
    java -jar target/images-to-chars-printer.jar --white=BLUE --black=GREEN