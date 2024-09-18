package org.camunda.bpm.getstarted.loanapproval.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.services.CustomerService;

public class CreditDeductionDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("In delegate: " + delegateExecution.getProcessInstanceId());

        CustomerService service = new CustomerService();

        // Extract variables from process instance
        String customerId = (String) delegateExecution.getVariable("customerId");
        Double amount = (Double) delegateExecution.getVariable("orderTotal");

        // Execute business logic using the variables
        Double openAmount = service.deductCredit(customerId, amount);
        Double customerCredit = service.getCustomerCredit(customerId);

        // Save the results to the process instance
        delegateExecution.setVariable("openAmount", openAmount);
        delegateExecution.setVariable("customerCredit", customerCredit);
    }
}
