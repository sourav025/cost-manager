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
3. Go inside `cost-manager` directory using `cd cost-manager`
4. Now there are two steps to execute application main function
    1. Execute the command below to build `cost-manager`
    ```bash
    sudo chmod +x build.sh run.sh
    sh build.sh
    ```
    2. Run `cost-manager` using the below command
    ```
    sh run $NAMES_LIST_FILE_FULL_PATH EXPENSES_LIST_FULL_PATH$
    ```
    #### Example
    ```bash
    sh run /user/ubuntu/input/names.txt /user/ubuntu/input/expenses.txt
    ```

5. To execute unit tests goto `cost-manager` directory and execute `mvn surefire:test`
