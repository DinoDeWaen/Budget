import org.junit.Test;

import cashFlow.CashFlowDTO;


public class AddCashFlow {
	
	private static final double amount = 155.47;
	private static CashFlowDTO cashFlowDTO;
	
	
	@Test
	public void CreateCashFlow(){
		cashFlowDTO = CashFlowDTO.newBuilder()
				      .withValue(amount)
				      .build();	
	}

}
