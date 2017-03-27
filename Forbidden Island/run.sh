echo '> Compiling files...'
javac -cp 'lib/*:main/tester.jar' main/*.java
echo '\n> Adding to jar...'
jar uvf run.jar main/*.class main/tester.jar lib/javalib.jar
echo '\n> Running tester...'
cd main
java -cp '../run.jar:.' com.simontuffs.onejar.Boot Island
