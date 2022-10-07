package AutomationProject2;

import org.checkerframework.checker.units.qual.K;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class TestCase {
    public static void main(String[] args) {
//        CREATE A WEB ORDER
//
//
//        1. Launch Chrome browser.
//        2. Navigate to http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx
        System.setProperty("webdriver.chrome.driver", "/Users/zayasaikhanchuluunbaatar/Downloads/BrowserDriver");
        WebDriver driver = new ChromeDriver();
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        driver.manage().window().maximize();

//        3. Login using username Tester and password test
        driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester", Keys.TAB, "test", Keys.ENTER);
//        4. Click on Order link
        driver.findElement(By.linkText("Order")).click();
//        5. Enter a random product quantity between 1 and 100
        int randomNum = (int)(Math.random()*99+1);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(Keys.BACK_SPACE, String.valueOf(randomNum), Keys.ENTER);
//        6. Click on Calculate and verify that the Total value is correct.
        //driver.findElement(By.xpath("//input[@type='submit']"));
//                Price per unit is 100.  The discount of 8 % is applied to quantities of 10+.
//        So for example, if the quantity is 8, the Total should be 800.
//        If the quantity is 20, the Total should be 1840.
//        If the quantity is 77, the Total should be 7084. And so on.
        int totalAfterDiscount = Integer.parseInt(driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtTotal")).getAttribute("value"));
        int totalBeforeDiscount = randomNum * 100;
        Assert.assertTrue(totalBeforeDiscount - totalBeforeDiscount*0.08 == totalAfterDiscount);
//        6. Generate and enter random first name and last name.
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();
        StringBuilder firstName = new StringBuilder(6);
        for (int i = 0; i < 6; i++)
            firstName.append(chars.charAt(rnd.nextInt(chars.length())));
        StringBuilder lastName = new StringBuilder(6);
        for (int i = 0; i < 6; i++)
            lastName.append(chars.charAt(rnd.nextInt(chars.length())));

        String fullName = firstName + " " + lastName;
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys(fullName);
//        7. Generate and Enter random street address
        String address = (int)(Math.random()* 9999 + 1000) + " Main Rd";
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys(address);
//        8. Generate and Enter random city
        StringBuilder city = new StringBuilder(6);
        for (int i = 0; i < 6; i++)
            city.append(chars.charAt(rnd.nextInt(chars.length())));
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys(city);
//        9. Generate and Enter random state
        StringBuilder state = new StringBuilder(2);
        for (int i = 0; i < 2; i++)
            state.append(chars.charAt(rnd.nextInt(chars.length())));
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys(state);
//        10. Generate and Enter a random 5 digit zip code
        String zip = "" + (int)(Math.random() * 99999 + 10000);
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(zip);

//        EXTRA: As an extra challenge, for steps 6-10 download 1000 row of corresponding realistic data from mockaroo.com in a csv format and load it to your program and use the random set of data from there each time.
//
//        11. Select the card type randomly. On each run your script should select a random type.
        List<WebElement> options = driver.findElements(By.xpath("//table[@class='RadioList']")) ;
        System.out.println(options.size());
        Random random = new Random();
        int index = random.nextInt(options.size());
        options.get(index).click();


//        12. Generate and enter the random card number:

//        If Visa is selected, the card number should start with 4.
        WebElement radioButton1 = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0"));
        WebElement radioButton2 = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1"));
        WebElement radioButton3 = driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1"));
        if(radioButton1.isSelected()){
            String visaCardNum = "4" + (long)(Math.random()*1000000000000000l);
            driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(visaCardNum);
        }
//        If MasterCard is selected, card number should start with 5.

        else if(radioButton2.isSelected()){
            String masterCardNum = "5" + (long)(Math.random()*1000000000000000l);
            driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(masterCardNum);
        }
//        If American Express is selected, card number should start with 3.
//        Card numbers should be 16 digits for Visa and MasterCard, 15 for American Express.
        else if(radioButton3.isSelected()){
            String amexCardNum = "3" + (long)(Math.random()*100000000000000l);
            driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(amexCardNum);
        }
//        13. Enter a valid expiration date (newer than the current date)
        driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("12/25");
//        14. Click on Process
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
//        15. Verify that “New order has been successfully added” message appeared on the page.
        boolean result = driver.getPageSource().contains("New order has been successfully added");
        Assert.assertTrue(result);
//        16. Click on View All Orders link.
        driver.findElement(By.linkText("View all orders")).click();
//        17. The placed order details appears on the first row of the orders table. Verify that the entire information contained on the row
//        (Name, Product, Quantity, etc) matches the previously entered information in previous steps.

//        String actualName = driver.findElement(By.xpath("//table[@class='SampleTable']//tr[2]//td[2]")).getText();
//        Assert.assertTrue(Boolean.parseBoolean(actualName), fullName);

//        List<String> expectedProductNames = Arrays.asList(fullName, "MyMoney", address, city, state, zip, );
//        18. Log out of the application.
//
//                Push your code to GitHub, and share the repo link in the given repo.txt file
    }
}
