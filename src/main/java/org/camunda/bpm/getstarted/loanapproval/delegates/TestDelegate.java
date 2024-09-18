package org.camunda.bpm.getstarted.loanapproval.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.services.CustomerService;

public class TestDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("In delegate: " + delegateExecution.getProcessInstanceId());

        Boolean shouldFail = (Boolean) delegateExecution.getVariable("shouldFail");

        if (shouldFail) {
            throw new IllegalArgumentException("Fehlgeschlagen");
        }
    }
}
