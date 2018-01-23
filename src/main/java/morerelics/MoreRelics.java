package morerelics;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import morerelics.relics.*;
import java.nio.charset.StandardCharsets;

public class MoreRelics implements PostInitializeSubscriber {
    private static final String MODNAME = "More Relics";
    private static final String AUTHOR = "t-larson";
    private static final String DESCRIPTION = "v1.0.0 NL Adds 5 new relics.";
    
    public MoreRelics() {
        BaseMod.subscribeToPostInitialize(this);
    }
    
    public static void initialize() {
        MoreRelics mr = new MoreRelics();
    }
    
    public void receivePostInitialize() {
        // Mod badge
        Texture badgeTexture = new Texture(Gdx.files.internal("img/MoreRelicsBadge.png"));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addLabel("This mod does not have any settings", 400.0f, 700.0f, (me) -> {});  
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        // RelicStrings
        String jsonString = Gdx.files.internal("localization/MoreRelics-RelicStrings.json").readString(String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomRelicStrings(jsonString);
        
        // Add relics
        RelicLibrary.add(new TwinDoll());
        RelicLibrary.add(new DoorKnob());
        RelicLibrary.add(new TrainingArmor());
        RelicLibrary.add(new UtilityBelt());
        RelicLibrary.add(new RustyGear());
    }
}