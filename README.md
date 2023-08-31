# Running the project build
- Install java if it's not installed (https://www.java.com/en/download/help/download_options.html)
- In the command terminal navigate to the "VintedHomework/target" directory
- In the terminal run "java -jar "VintedHomework-1.0.0.jar" command

# Running the tests
- Install maven if it's not installed (https://maven.apache.org/install.html)
- In the comman terminal navigate to the "VintedHomework" directory
- In the terminal run "mvn test" command

# Decisions
To make sure the code is clean and maintanable I've tried to follow Clean code principles as explained by Robert Cecil Martin.
Main points would be:
- Give meaningful names to variables functions classes
- Methods should be small and do only one thing
- Classes only have related data
- Methods named as verbs
- Classes named as nouns

Due to these principles one can add new rules just by creating a new method of it and calling it in "discountCalculator" as well as modifying old rules by simply changing repsective methods

For coding style I've based in on Google Java Style Guide: https://google.github.io/styleguide/javaguide.html