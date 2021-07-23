package setup;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface IPageObject {

    WebElement getElement(String elementName) throws NoSuchFieldException, IllegalAccessException;

    List<WebElement> getElements(String elementsListName) throws NoSuchFieldException, IllegalAccessException;

}
