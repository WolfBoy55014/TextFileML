# TurAI-API
This is a simple chatbot API for conversations and QA. Please read the whole `README` before using.

# Usage
Usage is fairly simple. Here is an example:
```java
public static void main(String[] args) throws IOException {
  net.wolfboy.tfml.SetUpFiles.MakeFiles();
  Scanner inputScanner = new Scanner(System.in);
  while (true) {
    System.out.print("[YOU] ");
    input = inputScanner.nextLine();
    String output = net.wolfboy.tfml.TurAIAPI.IOHandler(input, false, false, 0);
    System.out.println("[BOT] " + output);
  }
}
```
Calling `net.wolfboy.tfml.SetUpFiles.MakeFiles()` sets up `var.txt`, `var2.txt`, and `log.txt` to make sure that the files are present, if not, it will automatically generate them.

The `net.wolfboy.tfml.TurAIAPI.IOHandler()` method is the main entrypoint for the API. There are currently 4 parameters to be passed to the method, ***all 4 are required***.
The first parameter is the input string, such as,
```java
"Hello!"
``` 
or a variable,
```java
String thisIsAString = "Hello!";
```
  
