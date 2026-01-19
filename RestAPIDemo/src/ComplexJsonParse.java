import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {

	public static void main(String args[]) {
	
	JsonPath js = new JsonPath(payLoad.coursePrice());
	//1. Print No of courses returned by API
	int courseCount = js.getInt("courses.size()");
    System.out.println(courseCount);
    
    //2.Print Purchase Amount
    int purchaseAmount = js.getInt("dashboard.purchaseAmount");
    System.out.println(purchaseAmount);
    
    
    //3. Print Title of the first course
    String titleCourse1 = js.getString("courses[0].title");
    System.out.println(titleCourse1);
    
    //4. Print All course titles and their respective Prices
    for(int i =0,sum = 0; i<=courseCount-1; i++)
    {
    	String title =js.getString("courses["+i+"].title");
    	System.out.println(title);
    	
    	String price =js.getString("courses["+i+"].price");
    	System.out.println(price);
    	
    	int copies = js.getInt("courses[" + i + "].copies");
    	System.out.println(copies);
    	
        //5. Print no of copies sold by RPA Course
        if(js.getString("courses["+i+"].title").equalsIgnoreCase("RPA"))
        {
        	String numOfCopies =js.getString("courses["+i+"].copies");
        	System.out.println("Num of copies for RPA:");
        	System.out.println(numOfCopies);
        	
        }
        
        
        //6. Verify if Sum of all Course prices matches with Purchase Amount
        sum = sum + (Integer.parseInt(price) * copies);
        if(purchaseAmount == sum)
        {
        	System.out.println("Sum of all Course prices matches with Purchase Amount");
        }
        
    }  
    
    
	}
}
