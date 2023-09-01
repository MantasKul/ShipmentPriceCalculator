# Running the project build
- Install java if it's not installed (https://www.java.com/en/download/help/download_options.html)
- In the command terminal navigate to the "VintedHomework/target" directory
- In the terminal run "java -jar "VintedHomework-1.0.0.jar" command

# Running the tests
- Install Maven if it's not installed (https://maven.apache.org/install.html)
- In the command terminal navigate to the "VintedHomework" directory
- In the terminal run "mvn test" command

# Decisions
To make sure the code is clean and maintainable I've tried to follow Clean code principles as explained by Robert Cecil Martin.
The main points would be:
- Give meaningful names to variables functions classes
- Methods should be small and do only one thing
- Classes only have related data
- Methods named as verbs
- Classes named as nouns

Following this convention I've tried to make names clear, methods do only one thing and keep them as small as I can, classes have only related methods/variables in them, that's why Line and PriceCalculator are separate classes.

For coding style, I've based it on the Google Java Style Guide: https://google.github.io/styleguide/javaguide.html

I'm using int for money instead of BigDecimal as it takes less space and should be a bit faster technically, or at least faster until we're using 10+ digits.
This is something that my inexperience might be causing, having someone more experienced to discuss this would help me come to a better conclusion about whether to use int or BigDecimal.

While I've tried my best I'm sure there are things that could be improved and I'd love to discuss them and learn.