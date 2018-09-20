package cn.com.sparknet.branchRecord.common.basedao;

import javax.sql.DataSource;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;


public class TransactionManager
{
  private PlatformTransactionManager transactionManager;
  private DefaultTransactionDefinition def;
  private TransactionStatus status;

  public TransactionManager(DataSource ds)
  {
    this.transactionManager = new DataSourceTransactionManager(ds);
    this.def = new DefaultTransactionDefinition();
    this.def.setPropagationBehavior(0);
    this.def.setIsolationLevel(2);
    this.status = this.transactionManager.getTransaction(this.def);
  }

  public void commit() {
    this.transactionManager.commit(this.status);
  }

  public void rollback() {
    this.transactionManager.rollback(this.status);
  }
}
