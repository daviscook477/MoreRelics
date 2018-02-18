package morerelics;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.interfaces.PostInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;
import morerelics.relics.*;
import java.nio.charset.StandardCharsets;

@SpireInitializer
public class MoreRelics implements PostInitializeSubscriber {
    private static final String MODNAME = "More Relics";
    private static final String AUTHOR = "t-larson, StreptoFire, Velken, cogumush, fiiiiilth";
    private static final String DESCRIPTION = "v1.2.0 NL Adds 11 new relics.";
    
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
        BaseMod.loadCustomStrings(RelicStrings.class, jsonString);
        
        // Add relics
        RelicLibrary.add(new TwinDoll());
        RelicLibrary.add(new DoorKnob());
        RelicLibrary.add(new TrainingArmor());
        RelicLibrary.add(new UtilityBelt());
        RelicLibrary.add(new RustyGear());
        RelicLibrary.add(new MagicKindling());
        RelicLibrary.add(new NemesisMask());
        RelicLibrary.add(new BrainOfInsanity());
        RelicLibrary.add(new ExtremelyHeavyHammer());
        RelicLibrary.add(new GreedyCoin());
        RelicLibrary.add(new MagicPiggyBank());
    }
}