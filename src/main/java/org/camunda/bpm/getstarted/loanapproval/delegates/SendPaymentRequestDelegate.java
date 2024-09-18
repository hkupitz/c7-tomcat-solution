package org.camunda.bpm.getstarted.loanapproval.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.ProcessInstance;

public class SendPaymentRequestDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        System.out.println("Sending message to payment process");

        ProcessInstance processInstance = execution
                .getProcessEngineServices()
                .getRuntimeService()
                .createMessageCorrelation("paymentRequestMessage")
                .setVariables(execution.getVariables())
                .processInstanceBusinessKey(execution.getProcessBusinessKey())
                .correlateStartMessage();
        execution.setVariable("paymentProcessInstanceId", processInstance.getId());
    }
}