#1. Delete the directory
    rm -rf target
#2. Create directory:
    mkdir target
#3. Compile files to the 'target' directory:
    javac -d target src/java/edu/school21/printer/*/*.java
#4. Copy resources:
    cp -r src/resources ./target/resources
#5. Create jar file:
    jar -cfm target/images-to-chars-printer.jar src/manifest.mf -C target .
#6. Run program:
    java -jar target/images-to-chars-printer.jar . 0