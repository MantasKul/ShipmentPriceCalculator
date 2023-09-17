# Running the project build
- Install java if it's not installed (https://www.java.com/en/download/help/download_options.html)
- In the command terminal navigate to the "VintedHomework/target" directory
- In the terminal run "java -jar VintedHomework-1.0.0.jar" command

# Running the tests
- Install Maven if it's not installed (https://maven.apache.org/install.html)
- In the command terminal navigate to the "VintedHomework" directory
- In the terminal run "mvn test" command

# Task description
Given input such as this (date, package size, shipment provider):
2015-02-01 S MR
2015-02-02 S MR
2015-02-03 L LP
2015-02-05 S LP
2015-02-06 S MR
2015-02-06 L LP
2015-02-07 L MR
2015-02-08 M MR
2015-02-09 L LP
2015-02-10 L LP
2015-02-10 S MR
2015-02-10 S MR
2015-02-11 L LP
2015-02-12 M MR
2015-02-13 M LP
2015-02-15 S MR
2015-02-17 L LP
2015-02-17 S MR
2015-02-24 L LP
2015-02-29 CUSPS
2015-03-01 S MR

Calculate the discount and price of shipment following these rules:
  - All S shipments should always match the lowest S package price among the providers.
  - The third L shipment via LP should be free, but only once a calendar month.
  - Accumulated discounts cannot exceed 10 € in a calendar month. If there are not enough funds to fully cover a discount this calendar month, it should be covered partially.

Output transactions and append reduced shipment price and a shipment discount (or '-' if there is none).
If the line format is wrong, carrier/sizes are unrecognized 'Ignored' should be appended to the line.

Example output of the given example input:
2015-02-01 S MR 1.50 0.50
2015-02-02 S MR 1.50 0.50
2015-02-03 L LP 6.90 -
2015-02-05 S LP 1.50 -
2015-02-06 S MR 1.50 0.50
2015-02-06 L LP 6.90 -
2015-02-07 L MR 4.00 -
2015-02-08 M MR 3.00 -
2015-02-09 L LP 0.00 6.90
2015-02-10 L LP 6.90 -
2015-02-10 S MR 1.50 0.50
2015-02-10 S MR 1.50 0.50
2015-02-11 L LP 6.90 -
2015-02-12 M MR 3.00 -
2015-02-13 M LP 4.90 -
2015-02-15 S MR 1.50 0.50
2015-02-17 L LP 6.90 -
2015-02-17 S MR 1.90 0.10
2015-02-24 L LP 6.90 -
2015-02-29 CUSPS Ignored
2015-03-01 S MR 1.50 0.50