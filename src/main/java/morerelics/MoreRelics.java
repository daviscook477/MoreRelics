package morerelics;

import basemod.BaseMod;
import basemod.ModPanel;
import basemod.helpers.RelicType;
import basemod.interfaces.PostInitializeSubscriber;
import basemod.interfaces.EditStringsSubscriber;
import basemod.interfaces.EditRelicsSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.megacrit.cardcrawl.helpers.RelicLibrary;
import com.megacrit.cardcrawl.localization.RelicStrings;
import morerelics.relics.*;
import java.nio.charset.StandardCharsets;

@SpireInitializer
public class MoreRelics implements PostInitializeSubscriber, EditStringsSubscriber, EditRelicsSubscriber {
    private static final String MODNAME = "More Relics";
    private static final String AUTHOR = "t-larson, StreptoFire, Velken, cogumush, fiiiiilth";
    private static final String DESCRIPTION = "v1.2.1 NL Adds 11 new relics.";
    
    public MoreRelics() {
        BaseMod.subscribeToPostInitialize(this);
        BaseMod.subscribeToEditStrings(this);
        BaseMod.subscribeToEditRelics(this);
    }
    
    public static void initialize() {
        MoreRelics mr = new MoreRelics();
    }
    
    @Override
    public void receiveEditStrings() {
      String relicStrings = Gdx.files.internal("localization/MoreRelics-RelicStrings.json").readString(
        		String.valueOf(StandardCharsets.UTF_8));
        BaseMod.loadCustomStrings(RelicStrings.class, relicStrings);
    }
    
    @Override
    public void receiveEditRelics() {
      BaseMod.addRelic(new TwinDoll(), RelicType.SHARED);
      BaseMod.addRelic(new DoorKnob(), RelicType.SHARED);
      BaseMod.addRelic(new TrainingArmor(), RelicType.SHARED);
      BaseMod.addRelic(new UtilityBelt(), RelicType.SHARED);
      BaseMod.addRelic(new RustyGear(), RelicType.SHARED);
      BaseMod.addRelic(new MagicKindling(), RelicType.SHARED);
      BaseMod.addRelic(new NemesisMask(), RelicType.SHARED);
      BaseMod.addRelic(new BrainOfInsanity(), RelicType.SHARED);
      BaseMod.addRelic(new ExtremelyHeavyHammer(), RelicType.SHARED);
      BaseMod.addRelic(new GreedyCoin(), RelicType.SHARED);
      BaseMod.addRelic(new MagicPiggyBank(), RelicType.SHARED);
    }
    
    @Override
    public void receivePostInitialize() {
        // Mod badge
        Texture badgeTexture = new Texture(Gdx.files.internal("img/MoreRelicsBadge.png"));
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addLabel("This mod does not have any settings", 400.0f, 700.0f, (me) -> {});  
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
    }
}