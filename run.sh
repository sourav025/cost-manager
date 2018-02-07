#!/bin/bash
namesFilePath=$1
expensesFilepath=$2

: ${namesFilePath:? Names File Path can not be empty }
: ${expensesFilepath:? Names File Path can not be empty }

mvn exec:java -Dnames=$namesFilePath -Dexpenses=$expensesFilepath
