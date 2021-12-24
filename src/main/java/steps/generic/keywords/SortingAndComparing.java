package steps.generic.keywords;

import org.openqa.selenium.WebElement;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class SortingAndComparing {

    World world;
    public SortingAndComparing(World world) {
        this.world = world;
    }

    public boolean sortBydateDescending(String sObject) throws ParseException {
        boolean fail = false;
        int count = 0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        List<Date> ActualDate = new ArrayList<>();
        List<Date> SortedDateDescending = new ArrayList<>();
        List<WebElement> OrderList = this.world.getWebElementFactory().getElements(this.world.getWebElementFactory().getByLocator("homepage.orderHistory.date"));
        count = OrderList.size();

        for (WebElement e : OrderList) {
            SortedDateDescending.add(sdf.parse(e.getText()));
            ActualDate.add(sdf.parse(e.getText()));
        }

        // Collections.sort(SortedDateDescending);
        Collections.sort(SortedDateDescending, Collections.reverseOrder());
        for (int i = 0; i < count; i++) {
            if (!SortedDateDescending.get(i).equals(ActualDate.get(i))) {
                fail = true;
                World.softAssertMy.assertTrue(false, "Sorting not working properly");
                break;
            }
        }
        return fail;

    }
}
