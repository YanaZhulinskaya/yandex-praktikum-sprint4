import dropdownText.OrderScooter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@RunWith(Parameterized.class)
public class OrderScooterTest {
    OrderScooter objOrderScooter;
    WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String phone;

    public OrderScooterTest(String name, String surname, String address, String phone) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phone = phone;
    }

    @Parameterized.Parameters
    public static String[][] gerOrderData() {
        return new String[][]{
                {"Яна", "Калинова", "Внуково", "89691234567"},
                {"Саша", "Смирнов", "Новгород", "89884563423"}
        };
    }

    @Before
    public void before() {
        //драйвер для браузера Chrome
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--headless", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        // переход на страницу приложения
        driver.get("https://qa-scooter.praktikum-services.ru");
        //объект класса главной страницы
        objOrderScooter = new OrderScooter(driver);
    }

    @Test
    public void orderPositiveOne() {
        objOrderScooter.clickOrderButtonHeader();
        createOrder();
    }

    @Test
    public void orderPositiveMiddleButton() {
        objOrderScooter.scrollToMiddleButton();
        objOrderScooter.clickOrderButtonMiddle();
        createOrder();
    }

    private void createOrder() {
        objOrderScooter.confirmCookies();
        objOrderScooter.setName(name);
        objOrderScooter.setSurname(surname);
        objOrderScooter.setAdress(address);
        objOrderScooter.setMetro();
        objOrderScooter.setPhoneNumber(phone);
        objOrderScooter.clickNextButton();
        objOrderScooter.setDeliveryDate();
        objOrderScooter.setDurationOrder();
        objOrderScooter.clickOrderScooterButton();
        objOrderScooter.clickYesInDialog();
        objOrderScooter.isPanelVisible();
    }

    // Закрыть браузер
    @After
    public void after() {
        driver.quit();
    }
}