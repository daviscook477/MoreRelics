package morerelics.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.StartGameSubscriber;
import basemod.interfaces.StartActSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.helpers.Prefs;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class MagicPiggyBank extends CustomRelic implements StartActSubscriber, StartGameSubscriber {
    public static final String ID = "MagicPiggyBank";
    private static final String IMG = "img/relics/MagicPiggyBank.png";
    private static final String OUTLINE = "img/relics/outline/MagicPiggyBank.png";

    public int storedGold = 0;

    public MagicPiggyBank() {
        super(ID, new Texture(Gdx.files.internal(IMG)), new Texture(Gdx.files.internal(OUTLINE)), RelicTier.UNCOMMON, LandingSound.MAGICAL);

        BaseMod.subscribeToStartAct(this);
        BaseMod.subscribeToStartGame(this);
    }

    @Override
    public void receiveStartAct() {
        if (storedGold != 0 && AbstractDungeon.player.relics.indexOf(this) != -1) {
            AbstractDungeon.player.gainGold(storedGold * 2);
            storedGold = 0;
            flash();

            Prefs modPrefs = SaveHelper.getPrefs("MoreRelicsSave");
            modPrefs.putInteger("PiggyBank", 0);
            modPrefs.flush();

            description = getUpdatedDescription();
            tips.clear();
            tips.add(new PowerTip(name, description));
            initializeTips();
        }
    }

    @Override
    public void receiveStartGame() {
        if (AbstractDungeon.player.relics.indexOf(this) != -1) {
            Prefs modPrefs = SaveHelper.getPrefs("MoreRelicsSave");
            storedGold = modPrefs.getInteger("PiggyBank", 0);

            description = getUpdatedDescription();
            tips.clear();
            tips.add(new PowerTip(name, description));
            initializeTips();
        }
    }

    @Override
    public void onEquip() {
        storedGold = AbstractDungeon.player.gold;
        AbstractDungeon.player.loseGold(AbstractDungeon.player.gold);
        flash();

        Prefs modPrefs = SaveHelper.getPrefs("MoreRelicsSave");
        modPrefs.putInteger("PiggyBank", storedGold);
        modPrefs.flush();

        description = getUpdatedDescription();
        tips.clear();
        tips.add(new PowerTip(name, description));
        initializeTips();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + storedGold;
    }

    @Override
    public AbstractRelic makeCopy() {
        return new MagicPiggyBank();
    }
}