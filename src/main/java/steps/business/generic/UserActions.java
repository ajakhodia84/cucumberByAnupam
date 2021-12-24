package steps.business.generic;

import steps.generic.keywords.World;
import steps.generic.keywords.Launch;
import steps.generic.keywords.VerifyTitle;
import steps.generic.keywords.Wait;
import steps.generic.keywords.WindowHandling;

public class UserActions {

    Wait Wait;
    WindowHandling windowHandling;
    World World;
    Launch Launch;
    VerifyTitle VerifyTitle;

    public UserActions(Wait wait, WindowHandling handle, World world, Launch launch, VerifyTitle verifyTitle) {
        this.windowHandling = handle;
        this.Wait = wait;
        this.World = world;
        this.Launch = launch;
        this.VerifyTitle = verifyTitle;
    }
}
