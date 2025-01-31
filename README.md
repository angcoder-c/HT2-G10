# Hoja de trabajo 2 - Stack Structure

## Diagrama UML de clases

![image](https://github.com/user-attachments/assets/6cb4ebe2-32ff-4708-9726-f84570c956e5)

![image](https://github.com/user-attachments/assets/9762bcb0-69c6-4464-9b46-a5977a2502c3)



### Compilación

`javac -d out src/main/java/com/api/*.java src/test/java/com/api/*.java -cp "lib/junit-platform-console-standalone-1.11.0.jar"`

### Ejecutar

`java -cp out com.api.Main -f <file.txt>`


### Test

`java -jar lib/junit-platform-console-standalone-1.11.0.jar --class-path out --scan-class-path`
