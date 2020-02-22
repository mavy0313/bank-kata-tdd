package com.mavy0313.bankkata;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class StatementPrinter {

  private static final String STATEMENT_HEADER = "DATE | AMOUNT | BALANCE";
  private Console console;
  private DecimalFormat decimalFormatter = new DecimalFormat("#.00");

  public StatementPrinter(Console console) {
    this.console = console;
  }

  public void print(List<Transaction> transactions) {
    console.printLine(STATEMENT_HEADER);
    printStatementLines(transactions);
  }

  private void printStatementLines(List<Transaction> transactions) {
    AtomicInteger runningBalance = new AtomicInteger(0);
    transactions.stream()
        .map(transaction -> statementLine(transaction, runningBalance))
        .collect(Collectors.toCollection(LinkedList::new))
        .descendingIterator()
        .forEachRemaining(console::printLine);
  }

  private String statementLine(Transaction transaction, AtomicInteger runningBalance) {
    return transaction.date()
        + " | "
        + decimalFormatter.format(transaction.amount())
        + " | "
        + decimalFormatter.format(runningBalance.addAndGet(transaction.amount()));
  }
}
