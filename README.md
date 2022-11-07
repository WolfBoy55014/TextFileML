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
The next is the Boolean `useSimpleAlgorithm`, this defines which method the API should use for comparing strings. Setting it to `true` uses a more exact output in smaller datasets, and won't respond if it doesn't find a sufficient match. The Simple Algorithm or `IfBasic` would be better for a smaller Q&A dataset. Setting it to `false` uses Levenshtein Distance calculations to determine the most accurate response to the input. The Levenshtein method is better with larger data sets, and may give inaccurate answers if the dataset is too small because it will always output the *best* (not always *good*) response.

The third parrameter sets debug mode. A boolean of `true` will turn it on, otherwise keep it off with `false`.

The last int `initialTolerance` is the initial tolerance the Levenshtein Calculations use to find the responce, mabye set it to 1-2 for large datasets to improve speed, but otherwise, set it to 0.

### Use the built-in Main file to use the base UI, if you want to use it. or a Discord bot is being worked on.

# Setup
### Setup is a little weird, but it will improve.
First, if you get a outOfBoundsException, try inserting a `_` into var2.txt, and filling out at least 1 input-responce (or *two* lines) in the log.txt, or just use [mine from this repo](../master/log.txt)...
