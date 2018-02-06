## Assumptions for `cost-manager`
1. Names will be separated by lines in input name list file. All names are unique. Duplicate names in the files will be <b>skipped</b>.
2. Each line in expense transaction file represents a separate transaction made by any one of the people involved. Expense Transaction is valid <b>iff</b> name provided is presented in the namelist file. Each transaction line in file always follows the below format: 
```
$PEOPLE_NAME$ paid $AMOUNT_WITH_CURRENCY_SIGN_AS_PREFIX$ for $PURPOSE_DESCRIPTION$.
```

3. <b>No Negative amount</b> of money is not allowed
4. <b>CURRENCY SIGN</b> is always <b>$</b>

## Instructions to Run `cost-manager`
### Pre-requisitions
```code
1. Java-8
2. Maven
3. Git
4. Internet Connection ( To down artifacts from Maven repository)
```

### Procedures to follow
1. Open terminal or Portable Git
2. Clone from Github repo using following command
```
git clone https://github.com/sourav025/cost-manager.git
```
3. Go inside `cost-manager` directory
4. Execute `mvn clean install` command
5. Now there are 2 ways, application main function can be executed
    1. Execute command. Please use absolute path for file.
    ```
    mvn -Dnames="$NAMES_LIST_FILE_FULL_PATH$" -Dexpenses="$EXPENSES_LIST_FULL_PATH$" exec:java
    ```
    2. Goto `target` directory using `cd target` and execute 
    ```
    java -Dnames="$NAMES_LIST_FILE_FULL_PATH$" -Dexpenses="$EXPENSES_LIST_FULL_PATH$ -jar cost-manager-1.0.0-SNAPSHOT.jar
    ``` 

6. To execute unit tests goto `cost-manager` directory and execute `mvn surefire:test`
