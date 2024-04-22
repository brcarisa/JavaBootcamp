#1. Delete the directory
    rm -rf target
#2. Create directory:
    mkdir target
#3. Compile files to the 'target' directory:
    javac -d ./target src/java/edu/school21/printer/*/*.java
#4. Run program:
    java -cp target/ edu.school21.printer.app.Program . 0 <path_to_image>