package com.app.tests;

import static org.testng.Assert.*;

import org.testng.annotations.Test;

import com.app.pages.AwsCalculatorPage;
import com.app.utilities.TestBase;



public class AWSCalculatorTests extends TestBase{
	
  AwsCalculatorPage calculator = new AwsCalculatorPage();

  @Test(priority=0, description="Monthly bill should be $0.00 by default")
  public void defaultMonthlyBillTest() {
	  assertTrue(calculator.isAt());
	  assertEquals(0.0 ,calculator.getMonthlyBillAmount());
  }
  
  @Test(priority=1)
  public void addedEc2DefaultValuesTest() throws InterruptedException {
	  calculator.addEc2.click();
	  assertTrue(calculator.description.getAttribute("value").isEmpty());
	  assertEquals(calculator.getIntanceCount(),1);
	  assertEquals(Integer.parseInt(calculator.usageCount.getAttribute("value")),100);
	  assertEquals(calculator.getUsageOption(),"% Utilized/Month");
	  //assert that OS -> Linux on t1.micro
	  assertEquals(calculator.ec2Type.getText(),"Linux on t1.micro");
	  //assert that billing option is On-Demand (No Contract)
	  assertEquals(calculator.billingOption.getText(),"On-Demand (No Contract)");
	  //assert that price is 14.64
	  assertEquals(calculator.getMonthlyCost(),14.64);
	  double servicesTabMonthlyCost = calculator.getMonthlyCost();
	  
	  calculator.billLabel.click();
	  // (//input[@class='gwt-TextBox gwt-TextBox-readonly'])[1]
	  // //a[.='Amazon EC2 Service (US-East)']/../../../td[4]/table/tbody/tr/td/input
	  double billTabMonthlyCost=Double.parseDouble(calculator.monthlyBillCostBeforeDiscounts.getAttribute("value"));
	  
	  assertEquals(servicesTabMonthlyCost,billTabMonthlyCost);
	  
	  calculator.services.click();
	  
  }
  
  @Test(priority=2)
  public void clearFormTest(){
	  //1. Click on Clear Form
	  calculator.clearForm.click();
	  assertTrue(calculator.checkClearAlert());
	  /*
	   * Verify alert is displayed and text is "
		Please Confirm
		Are you sure you want to clear all entries in this service form?"
	   *  
	   */
	  String popupText = calculator.confirmDialog.getText();
	  assertTrue(popupText.contains("Please Confirm") 
			  && popupText.contains("Are you sure you want to clear all entries in this service form?")
			  );
	//3. Click on OK
	  calculator.OK.click();
	 //4. Verify that form is cleared.
	  assertEquals(calculator.isEC2InstancesTableClear() , true);
	    
  }
  
  
}