package common;

import office.OfficeController;
import office.*;
import user.FestivalController;
import user.UIFestivalInformation;

public class CommonController {
    public void startPublic() {    	
    	UIFestivalInformation ui = new UIFestivalInformation();
      	FestivalController festival = new FestivalController(ui);
    }

    public void startOffice() {
    	OfficeController office = new OfficeController();
    	UIOfficeMain ui = new UIOfficeMain();
    }
}
