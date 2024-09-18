package org.camunda.bpm.getstarted.loanapproval.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

public class SendPaymentCompletionDelegate implements JavaDelegate {

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    System.out.println("Sending message back to order process");

    execution
    .getProcessEngineServices()
    .getRuntimeService()
    .createMessageCorrelation("paymentCompletedMessage")
    .processInstanceBusinessKey(execution.getBusinessKey())
    .correlate();
  }
}