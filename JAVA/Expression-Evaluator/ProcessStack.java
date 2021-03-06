package calculation;




//This Message class is just to used as a return type to show whether the stored items of the stack
//can be executable or valid. There are two data member involved, the first one "success" is
//to indicate whether the expression is valid, 0 - empty expression; 1 - invalid expression; 2 - valid expression 
//Actually only when success == 2, the second parameter "result" is meaningful. It shows the 
//result of the expression. Otherwise for the case success == 0 or success == 1, we can assume
//result = 0.
class Message
{
	private int success;  //valid indicator 0 - empty expression; 1 - invalid expression; 2 - valid expression 
	private double result;
	private String expression;
	
	
	//Constructor 1
	public Message()
	{
		success = 0; //by default, assume the expression is 0
		result = 0.0;
	}
	
	public Message(int s, double r)
	{
		success = s;
		result = r;
	}
	

	//below are basic setter and getter
	public int getSuccess() {
		return success;
	}
	
	public void setSuccess(int success) {
		this.success = success;
	}

	public double getResult() {
		return result;
	}

	public void setResult(double result) {
		this.result = result;
	}

}

/*This is a display class that is used to show message. In other word, for the class ProcessStack below, it use
* this Display class to print out message information. 
* 
* The reason I design it in this way is to let you practice implementing different tasks in 
* different modules. So this Display class is to take care of the output message. The ProcessStack
* class is to take care the logic*/
class Display{
	public static void showMessage(Message m)
	{
		if(m.getSuccess() == 0)
		{
			System.out.println("\nNo any input from the stack");
		}
		else if(m.getSuccess() == 1)
		{
			System.out.println("\nThe expression is wrong!");
		}
		else
		{
			System.out.println("The expression result is: " + m.getResult());
		}
	}
}


/*The most important class in this file, which is used to process */
public class ProcessStack {

	public ProcessStack()
	{
		
	}
	
	
	/*This method call the the calculate() and print out the corresponding message*/
	public static void parseMyStack(MyStack s)
	{
		Message m = calculate(s);
		Display.showMessage(m);
	}
	
	
	
	/*Step 5: implement the following function 
	 * This is the most important function of this file. The description for this function
	 * is already defined in the instructions. Basecially, you will check the stored item of 
	 * the MyStack input parameter. There are three possible cases: empty expression, invalid expression,
	 * and valid expression. If it is a valid expression, calculate it. According to the three
	 * cases, return different messages.
	 * */
	public static Message calculate(MyStack ms)
	{
		
			
		//if ms stores correct expression, return new Message(2, result);
		//if ms has empty expression, return new Message(0, 0.0);
		//if ms has invalid expression, return new Message(1, 0.0)
		String num;
		char flag;
		Double sum=0.0;
		Double checkNum=0.0;
		int status = 0 ;
		boolean numFound = false;
		
		
		if(ms.stack.isEmpty())
			return (new Message(0,0.0));
		else if (ms.getSize()<2)
			return (new Message(1,0.0));
		else
		{
			while(!ms.stack.isEmpty())
			{
				status = 2;
				num = ms.stack.pop();
				flag = num.charAt(0);
				try
				{
					checkNum=Double.parseDouble(num);
					sum=checkNum;
				}
				catch(NumberFormatException nfe)
				{
					
    		  	}
				
				if(((java.util.regex.Pattern.matches("(\\d)+\\.(\\d)+", num) || 
	    				java.util.regex.Pattern.matches("(\\d)+", num)) && !ms.stack.isEmpty()) && numFound == false)
	    		{
	    			num = ms.stack.pop();
	    			flag = num.charAt(0);
					if(java.util.regex.Pattern.matches("(\\d)+\\.(\\d)+", num) 
	    					|| java.util.regex.Pattern.matches("(\\d)+", num) && !ms.stack.isEmpty() )
	    			{
	    				return (new Message(1,0.0));
	    			}
	    			else if( flag == '+')
	    			{
	    				num = ms.stack.pop();
	    				numFound = true;
	    				try
	    				{
	    					sum = sum +Double.parseDouble(num);
	    				}
	    				catch(NumberFormatException nfe)
	    				{
	    					return (new Message(1,0.0));
	    			  	}
	    			}
	    			else if( flag == '-')
	    			{
	    				num = ms.stack.pop();
	    				numFound = true;
	    				try
	    				{
	    					Double.parseDouble(num);
	    				}
	    				catch(NumberFormatException nfe)
	    				{
	    					return (new Message(1,0.0));
	    			  	}
	    				if(Double.parseDouble(num)>sum)
			 				sum = Double.parseDouble(num)-sum ;
	    				else 
	    					 sum=sum -Double.parseDouble(num);
	    			}
	    			else if( flag == '*')
	    			{
	    				num = ms.stack.pop();
	    				numFound = true;
	    				try
	    				{
	    					sum = sum *Double.parseDouble(num);
	    				}
	    				catch(NumberFormatException nfe)
	    				{
	    					return (new Message(1,0.0));
	    			  	}
	    			}
	    			else if( flag == '/')
	    			{
	    				num = ms.stack.pop();
	    				numFound = true;
	    				try
	    				{
	    					Double.parseDouble(num);
	    				}
	    				catch(NumberFormatException nfe)
	    				{
	    					return (new Message(1,0.0));
	    			  	}
	    				
	    				if(Double.parseDouble(num)!=0)
	    					sum = sum /Double.parseDouble(num);
	    				else
	    				{
	    					System.out.println("Cannot divide by 0");
	    				}
	    			}
	    			
	    			else if(checkNum ==0.0)
	    			{
	    				
	    			}
	    			else
	    				return (new Message(1,0.0));
	    		}
				
				else if( flag == '+')
    			{
    				num = ms.stack.pop();
    				numFound = true;
    				try
    				{
    					sum = sum +Double.parseDouble(num);
    				}
    				catch(NumberFormatException nfe)
    				{
    					return (new Message(1,0.0));
    			  	}
    			}
    			else if( flag == '-')
    			{
    				num = ms.stack.pop();
    				numFound = true;
    				try
    				{
    					Double.parseDouble(num);
    				}
    				catch(NumberFormatException nfe)
    				{
    					return (new Message(1,0.0));
    			  	}
    				if(Double.parseDouble(num)>sum)
		 				sum = Double.parseDouble(num)-sum ;
    				else 
    					 sum=sum-Double.parseDouble(num);
    			}
    			else if( flag == '*')
    			{
    				num = ms.stack.pop();
    				numFound = true;
    				try
    				{
    					sum = sum *Double.parseDouble(num);
    				}
    				catch(NumberFormatException nfe)
    				{
    					return (new Message(1,0.0));
    			  	}
    			}
    			else if( flag == '/')
    			{
    				num = ms.stack.pop();
    				numFound = true;
    				try
    				{
    					Double.parseDouble(num);
    				}
    				catch(NumberFormatException nfe)
    				{
    					return (new Message(1,0.0));
    			  	}
    				if(Double.parseDouble(num)!=0)
    					sum = sum /Double.parseDouble(num);
    				else
    				{
    					System.out.println("Cannot divide by 0");
    				}
    			}
    			
    			else if(checkNum ==0.0)
    			{
    				
    			}
    			else
    				return (new Message(1,0.0));
				
			}
			return (new Message(status,sum));
		}
	}
	
}
