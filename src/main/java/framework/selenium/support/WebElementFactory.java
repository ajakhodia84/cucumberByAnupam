package framework.selenium.support;


import framework.exceptions.ObjectNotFoundInORException;
import framework.shared.FrameworkConstants;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import steps.generic.keywords.World;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;


public class WebElementFactory {

    Properties objectRepository;
    int iActualXCoordinate, iActualYCoordinate, iActualHeight, iActualWidth, iRelativeXCoordinate, iRelativeYCoordinate,
            iRelativeHeight, iRelativeWidth;

    public WebDriver driver;
    private WebDriverWait waitDriver;
    public String orName;


    public WebElementFactory(WebDriver driver, String orName) {
        this.driver = driver;
        this.orName = orName;
    }


    public By getByLocator(String object) {
        By byProperty = null;
        //object = odr.ack.prodct.email
        try {
            String strLocatorValue = ObjectRepository.getLocatorValue(object, this.orName);
            String sLocator = strLocatorValue.replaceAll("\\=.*", "");
            String sLocator_Property = strLocatorValue.substring(sLocator.length() + 1);
            switch (sLocator) {
                case "id":
                    byProperty = By.id(sLocator_Property);
                    break;
                case "cssSelector":
                case "css":
                    byProperty = By.cssSelector(sLocator_Property);
                    break;
                case "xpath":
                    byProperty = By.xpath(sLocator_Property);
                    break;
                case "linkText":
                    byProperty = By.linkText(sLocator_Property);
                    break;
                case "name":
                    byProperty = By.name(sLocator_Property);
                    break;
                case "partialLinkText":
                    byProperty = By.partialLinkText(sLocator_Property);
                    break;
                case "tagName":
                    byProperty = By.tagName(sLocator_Property);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Error while getting object locator");
        }
        return byProperty;
    }


    public String getLocatorValue(String object) {
        String strLocatorValue = null;
        String sLocator = null;
        try {
            strLocatorValue = ObjectRepository.getLocatorValue(object, this.orName);
        } catch (ObjectNotFoundInORException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (strLocatorValue.contains("css=")) {
            sLocator = strLocatorValue.replaceAll("css=", "");
        } else if (strLocatorValue.contains("xpath=")) {
            sLocator = strLocatorValue.replaceAll("xpath=", "");
        }
        return sLocator;
    }


    public WebElement getElement(String Object) {
        //object = odr.ack.prodct.email
        By locatorBy = getByLocator(Object);
        return getElement(locatorBy);
    }


    public WebElement getElement(String Object, boolean highlight) {
        By locatorBy = getByLocator(Object);
        return getElement(locatorBy, highlight);
    }

    public WebElement getElement(By property, boolean highlight) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.presenceOfElementLocated(property));
            if (highlight) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println(World.RED + "Element is not present :: " + property + World.RESET);
        }
        return element;
    }

    public WebElement getElementIfVisible(By property, boolean highlight) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(property));
            if (highlight) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            System.out.println("Element is not present :: " + property);
        }
        return element;
    }

    public WebElement getElementIfVisible(String objectName, boolean highlight) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.visibilityOfElementLocated(getByLocator(objectName)));
            if (highlight) {
                Thread.sleep(100);

            }
        } catch (Exception e) {
            System.out.println("Element is not present :: " + objectName);
        }
        return element;
    }

    public WebElement getClickableElement(By property, boolean highlight) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.elementToBeClickable(property));
            if (highlight) {
                Thread.sleep(100);

            }
        } catch (Exception e) {
            System.out.println("Element is not clickable :: " + property);
        }
        return element;
    }


    public WebElement getClickableElement(String objectName, boolean highlight) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.elementToBeClickable(getByLocator(objectName)));
            if (highlight) {
                Thread.sleep(100);

            }
        } catch (Exception e) {
            System.out.println("Element is not clickable :: " + objectName);
        }
        return element;
    }

    private WebDriverWait getWaitDriver() {
        if (this.waitDriver == null) {
            this.waitDriver = new WebDriverWait(this.driver, FrameworkConstants.SMALL_WAIT);
        }
        return this.waitDriver;
    }


    public WebElement getElement(By byProperties) {
        return getElement(byProperties, true);
    }

    public WebElement getElement(By property, int maxWaitTime) {
        WebElement element = null;
        try {
            WebDriverWait waitDriver = new WebDriverWait(driver, maxWaitTime);
            element = waitDriver.until(ExpectedConditions.presenceOfElementLocated(property));

        } catch (Exception e) {
            System.out.println("Element is not present :: " + property);
        }
        return element;
    }

    public WebElement getElement(String name, int maxWaitTime) {
        WebElement element = null;
        try {
            WebDriverWait waitDriver = new WebDriverWait(driver, maxWaitTime);
            element = waitDriver.until(ExpectedConditions.presenceOfElementLocated(getByLocator(name)));

        } catch (Exception e) {
            System.out.println("Element is not present :: " + name);
        }
        return element;
    }

    public WebElement getElementWithInParent(By parentProperty, By childProperty) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentProperty, childProperty));

        } catch (Exception e) {
            System.out.println("Parent element with property :: " + parentProperty + " is not having child with property :: " + childProperty);
        }
        return element;
    }

    public WebElement getElementWithInParent(By parentProperty, String childObjectName) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentProperty, getByLocator(childObjectName)));

        } catch (Exception e) {
            System.out.println("Parent element with property :: " + parentProperty + " is not having child with name :: " + childObjectName);
        }
        return element;
    }

    public WebElement getElementWithInParent(String parentObjectName, String childObjectName) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.presenceOfNestedElementLocatedBy(getByLocator(parentObjectName), getByLocator(childObjectName)));

        } catch (Exception e) {
            System.out.println("Parent element with name :: " + parentObjectName + " is not having child with name :: " + childObjectName);
        }
        return element;
    }


    public WebElement getElementWithInParent(WebElement parentElement, By childProperty) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.presenceOfNestedElementLocatedBy(parentElement, childProperty));

        } catch (Exception e) {
            System.out.println("Parent element with property :: " + parentElement.toString() + " is not having child with property :: " + childProperty);
        }
        return element;
    }

    public List<WebElement> getElements(By byProperties) {
        List<WebElement> elements = new ArrayList<WebElement>();
        try {
            elements = this.getWaitDriver().until(ExpectedConditions.presenceOfAllElementsLocatedBy(byProperties));
        } catch (StaleElementReferenceException ex) {
            int tryCounter = 0;
            do {
                try {
                    elements = getWaitDriver().until(ExpectedConditions.presenceOfAllElementsLocatedBy(byProperties));
                } catch (Exception e) {
                    tryCounter++;
                }
            } while (tryCounter < 3 && elements == null);
        } catch (TimeoutException e) {
            System.out.println("Timeout - Elements couldn't be found :: " + byProperties);
        } catch (Exception e) {
            System.out.println("Elements are not present :: " + byProperties);
        }
        return elements;
    }

    public List<WebElement> getElements(String orObjectName) {
        List<WebElement> elements = new ArrayList<WebElement>();
        By locatorBy = getByLocator(orObjectName);
        try {
            elements = this.getWaitDriver().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorBy));
        } catch (StaleElementReferenceException ex) {
            int tryCounter = 0;
            do {
                try {
                    elements = getWaitDriver().until(ExpectedConditions.presenceOfAllElementsLocatedBy(locatorBy));
                } catch (Exception e) {
                    tryCounter++;
                }
            } while (tryCounter < 3 && elements == null);
        } catch (TimeoutException e) {
            System.out.println("Timeout - Elements couldn't be found :: " + orObjectName);
        } catch (Exception e) {
            System.out.println("Elements are not present by locator mentioned with object :: " + orObjectName);
        }
        return elements;
    }

    public boolean isElementPresentOnPage(By name) {
        WebElement element = getElement(name);
        return element != null ? true : false;
    }


    public boolean isElementVisibleOnPage(By name) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.visibilityOf(getElement(name)));
        } catch (Exception ex) {
            element = null;
        }
        return element != null ? true : false;
    }

    public boolean isElementPresentOnPage(By name, boolean highlight) {
        WebElement element = getElement(name, highlight);
        return element != null ? true : false;
    }

    public WebElement findElement(By locator) {
        WebElement element = null;
        try {
            element = driver.findElement(locator);
        } catch (Exception ex) {
            element = null;
        }
        return element;
    }

    public WebElement findElement(String object) {
        WebElement element = null;
        try {
            element = driver.findElement(getByLocator(object));
        } catch (Exception ex) {
            element = null;
        }
        return element;
    }

    public boolean isElementPresentOnPage(By name, int maxTime) {
        WebElement element = null;
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, maxTime);
            element = wdWait.until(ExpectedConditions.presenceOfElementLocated(name));
        } catch (Exception ex) {
            element = null;
        }
        return element != null ? true : false;
    }

    public boolean isElementVisibleOnPage(By name, boolean highlight) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.visibilityOf(getElement(name, highlight)));
        } catch (Exception ex) {
            element = null;
        }
        return element != null ? true : false;
    }

    public boolean isElementVisibleOnPage(By name, int maxTime) {
        WebElement element = null;
        try {
            WebDriverWait wdWait = new WebDriverWait(driver, maxTime);
            element = wdWait.until(ExpectedConditions.visibilityOf(getElement(name, true)));
        } catch (Exception ex) {
            element = null;
        }
        return element != null ? true : false;
    }

    public int getElementCount(String Object) throws ObjectNotFoundInORException {
        return getElementCount(getByLocator(Object));

    }

    public int getElementCount(By byProperty) {
        List<WebElement> elements = new ArrayList<WebElement>();
        try {
            elements = this.getWaitDriver().until(ExpectedConditions.presenceOfAllElementsLocatedBy(byProperty));

        } catch (TimeoutException e) {
            System.out.println("Timeout - Elements couldn't be found :: " + byProperty);
        }
        return elements.size();
    }


    public WebElement getNestedElement(By parentProperty, By childProperty) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.presenceOfNestedElementLocatedBy
                    (parentProperty, childProperty));
        } catch (StaleElementReferenceException ex) {
            int tryCounter = 0;
            do {
                try {
                    element = this.getWaitDriver().until(ExpectedConditions.presenceOfNestedElementLocatedBy
                            (parentProperty, childProperty));
                } catch (Exception e) {
                    tryCounter++;
                }
            } while (tryCounter < 3 && element == null);
        } catch (TimeoutException e) {
            System.out.println("Timeout - Nested elements couldn't be found :: " + childProperty);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        return element;
    }

    public WebElement getNestedElement(WebElement parentElement, By childProperty) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.presenceOfNestedElementLocatedBy
                    (parentElement, childProperty));
        } catch (StaleElementReferenceException ex) {
            int tryCounter = 0;
            do {
                try {
                    element = this.getWaitDriver().until(ExpectedConditions.presenceOfNestedElementLocatedBy
                            (parentElement, childProperty));
                } catch (Exception e) {
                    tryCounter++;
                }
            } while (tryCounter < 3 && element == null);
        } catch (TimeoutException e) {
            System.out.println("Timeout - Nested elements couldn't be found :: " + childProperty);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        return element;
    }

    public List<WebElement> getNestedElements(By parentProperty, By childProperty) {
        List<WebElement> nestedElements = null;
        try {
            nestedElements = this.getWaitDriver().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy
                    (parentProperty, childProperty));
        } catch (StaleElementReferenceException ex) {
            int tryCounter = 0;
            do {
                try {
                    nestedElements = this.getWaitDriver().until(ExpectedConditions.visibilityOfNestedElementsLocatedBy
                            (parentProperty, childProperty));
                } catch (Exception e) {
                    tryCounter++;
                }
            } while (tryCounter < 3 && nestedElements == null);
        } catch (TimeoutException e) {
            System.out.println("Timeout - Nested elements couldn't be found :: " + childProperty);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        return nestedElements;
    }

    public List<WebElement> getNestedElements(WebElement parentElement, By childProperty) {
        List<WebElement> nestedElements = null;
        try {
            nestedElements = parentElement.findElements(childProperty);
        } catch (StaleElementReferenceException ex) {
            int tryCounter = 0;
            do {
                try {
                    nestedElements = parentElement.findElements(childProperty);
                } catch (Exception e) {
                    tryCounter++;
                }
            } while (tryCounter < 3 && nestedElements == null);
        } catch (TimeoutException e) {
            System.out.println("Timeout - Nested elements couldn't be found :: " + childProperty);
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
        return nestedElements;
    }

    public int getYOffsetOfPage() {
        JavascriptExecutor js = (JavascriptExecutor) this.driver;
        return ((Long) js.executeScript("return window.pageYOffset;")).intValue();
    }


    public boolean isElementVisibleOnPage(String name) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.visibilityOf(getElement(name)));
        } catch (Exception ex) {
            element = null;
        }
        return element != null ? true : false;
    }


    public boolean isElementPresentOnPage(String name, boolean highlight) throws ObjectNotFoundInORException {
        WebElement element = getElement(name, highlight);
        return element != null ? true : false;
    }


    public boolean isElementVisibleOnPage(String name, boolean highlight) {
        WebElement element = null;
        try {
            element = this.getWaitDriver().until(ExpectedConditions.visibilityOf(getElement(name, highlight)));
        } catch (Exception ex) {
            element = null;
        }
        return element != null ? true : false;
    }

    public void setOrName(String orName) {
        this.orName = orName;

    }


    public List<WebElement> getElementsWithInParent(By parentProperty, By childProperty) {
        List<WebElement> elements = null;
        try {
            elements = this.getWaitDriver().until(ExpectedConditions.presenceOfNestedElementsLocatedBy(parentProperty, childProperty));

        } catch (Exception e) {
            System.out.println("Parent element with property :: " + parentProperty + " is not having child with property :: " + childProperty);
        }
        return elements;
    }

    public List<WebElement> getElementsWithInParent(By parentProperty, String childObjectName) {
        List<WebElement> elements = null;
        try {
            elements = this.getWaitDriver().until(ExpectedConditions.presenceOfNestedElementsLocatedBy(parentProperty, getByLocator(childObjectName)));

        } catch (Exception e) {
            System.out.println("Parent element with property :: " + parentProperty + " is not having child with name :: " + childObjectName);
        }
        return elements;
    }

    public List<WebElement> getElementsWithInParent(String parentObjectName, String childObjectName) {
        List<WebElement> elements = null;
        try {
            elements = this.getWaitDriver().until(ExpectedConditions.presenceOfNestedElementsLocatedBy(getByLocator(parentObjectName), getByLocator(childObjectName)));

        } catch (Exception e) {
            System.out.println("Parent element with name :: " + parentObjectName + " is not having child with name :: " + childObjectName);
        }
        return elements;
    }


    public List<WebElement> getElementsWithInParent(WebElement parentElement, By childProperty) {
        List<WebElement> elements = null;
        try {
            elements = parentElement.findElements(childProperty);
        } catch (Exception e) {
            System.out.println("Parent element with property :: " + parentElement.toString() + " is not having child with property :: " + childProperty);
        }
        return elements;
    }


}
