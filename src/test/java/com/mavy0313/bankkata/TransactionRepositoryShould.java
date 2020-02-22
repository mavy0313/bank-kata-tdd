package com.mavy0313.bankkata;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;

import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class TransactionRepositoryShould {

  private static final String TODAY = "12/05/2015";

  @Mock private Clock clock;

  private TransactionRepository transactionRepository;

  @Before
  public void setUp() {
    transactionRepository = new TransactionRepository(clock);
  }

  //should be integration test with real db
  @Test
  public void create_and_store_a_deposit_transaction() {
    given(clock.todayAsString()).willReturn(TODAY);
    transactionRepository.addDeposit(100);

    List<Transaction> transactions = transactionRepository.allTransactions();

    assertThat(transactions.size(), is(1));
    assertThat(transactions.get(0), is(transaction(TODAY, 100)));
  }

  @Test
  public void create_and_store_a_withdrawal_transaction() {
    given(clock.todayAsString()).willReturn(TODAY);
    transactionRepository.addWithdrawal(100);

    List<Transaction> transactions = transactionRepository.allTransactions();

    assertThat(transactions.size(), is(1));
    assertThat(transactions.get(0), is(transaction(TODAY, -100)));
  }

  private Transaction transaction(String date, int amount) {
    return new Transaction(date, amount);
  }
}