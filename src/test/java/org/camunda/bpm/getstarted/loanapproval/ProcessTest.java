package org.camunda.bpm.getstarted.loanapproval;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.test.Deployment;
import org.camunda.community.process_test_coverage.junit5.platform7.ProcessEngineCoverageExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.ProcessEngineTests.*;

@ExtendWith(ProcessEngineCoverageExtension.class)
public class ProcessTest {

    @Test
    @Deployment(resources = "payment.bpmn")
    public void testHappyPath() {
        Map<String, Object> variables = new HashMap<>();
        variables.put("orderTotal", 30.5);
        variables.put("customerId", "cust20");
        variables.put("cardNumber", "1234 5678");
        variables.put("cvc", "789");
        variables.put("expiryDate", "09/24");

        ProcessInstance processInstance = runtimeService().startProcessInstanceByKey("PaymentProcess", "order1", variables);

        assertThat(processInstance).isStarted();

        execute(job());

        assertThat(processInstance).isEnded()
                .hasNotPassed(findId("Charge credit card"))
                .hasPassed(findId("Payment completed"));
    }
}