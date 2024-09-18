package org.camunda.bpm.getstarted.loanapproval.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.getstarted.loanapproval.services.CreditCardService;

public class ChargeCreditCardDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        System.out.println("In delegate: " + delegateExecution.getProcessInstanceId());

        CreditCardService service = new CreditCardService();

        // Extract variables from process instance
        String cardNumber = (String) delegateExecution.getVariable("cardNumber");
        String cvc = (String) delegateExecution.getVariable("cvc");
        String expiryDate = (String) delegateExecution.getVariable("expiryDate");
        Double openAmount = (Double) delegateExecution.getVariable("openAmount");

        service.chargeAmount(cardNumber, cvc, expiryDate, openAmount);
    }
}
