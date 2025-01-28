# Hoja de trabajo 2 - Stack Structure

### Compilación

`javac -d out src/main/java/com/api/*.java src/test/java/com/api/*.java -cp "lib/junit-platform-console-standalone-1.11.0 jar"`
`or`
`javac -d bin src/main/java/com/api/Stack.java src/main/java/com/api/PostfixVector.java src/main/java/com/api/Main.java`

### Ejecutar

`java -cp out com.api.Main -f <file.txt>`
`or`
`java -cp bin com.api.Main -f "D:/ruta/especifica/Test.csv"`


### Test

`java -jar lib/junit-platform-console-standalone-1.11.0.jar --class-path out --scan-class-path`