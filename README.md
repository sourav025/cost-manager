=== Assumptions - for `Cost-Manager` ===
A1. Given names in input people file are unique and each names are separated by end of line.
Otherwise, names will be skipped the name to register into system.
A2. Expense Transaction files contain lines and each line represents a separate expense transaction made by any one of the people involved. Expense Transaction is valid iff name provided is registered before, amount is not negative.
Each transaction line in file always follows the below format: 
	$PEOPLE_NAME$ paid $AMOUNT_WITH_CURRENCY_SIGN_AS_PREFIX$ for $PURPOSE_DESCRIPTION$.
A3. `No Negative amount` of money is not allowed
A4. `CURRENCY SIGN` is always `$`


=== Instructions to Run Project  ===
Pre-requisitions to start a local development environment for cost-manager.
# Java-8
# Maven
# Git
# Internet Connection ( To down artifacts from Maven repository)

==== Procedures to follow ====
# Open terminal or Portable Git
# Clone from Github repo using `git clone https://github.com/sourav025/cost-manager.git` command
# Go inside `cost-manager` directory
# Execute `mvn clean install` command
# Execute `mvn -Dnames="$NAMES_LIST_FILE_FULL_PATH$" -Dexpenses="$EXPENSES_LIST_FULL_PATH$" exec:java` and please use absolute path for file
  Or Goto `target` directory using `cd target` and execute `java -Dnames="$NAMES_LIST_FILE_FULL_PATH$" -Dexpenses="$EXPENSES_LIST_FULL_PATH$ -jar cost-manager-1.0.0-SNAPSHOT.jar` 
  Note: Please make sure to put
# To execute unit tests goto `cost-manager` directory and execute `mvn surefire:test`
