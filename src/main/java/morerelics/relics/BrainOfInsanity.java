package morerelics.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import basemod.interfaces.PostDungeonInitializeSubscriber;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.CardLibrary;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.unlock.UnlockTracker;

import java.util.ArrayList;
import java.util.Map;

public class BrainOfInsanity extends CustomRelic implements PostDungeonInitializeSubscriber {
    public static final String ID = "BrainOfInsanity";
    private static final String IMG = "img/relics/BrainOfInsanity.png";
    private static final String OUTLINE = "img/relics/outline/BrainOfInsanity.png";

    public BrainOfInsanity() {
        super(ID, new Texture(Gdx.files.internal(IMG)), new Texture(Gdx.files.internal(OUTLINE)), RelicTier.RARE, LandingSound.MAGICAL);

        BaseMod.subscribeToPostDungeonInitialize(this);
    }

    private void addCardsToPool() {
        ArrayList<AbstractCard> cardsToAdd = new ArrayList<>();

        switch (AbstractDungeon.player.chosenClass) {
            case IRONCLAD: {
                for (Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
                    AbstractCard card = c.getValue();
                    if (card.color != AbstractCard.CardColor.GREEN || card.rarity == AbstractCard.CardRarity.BASIC || UnlockTracker.isCardLocked(c.getKey()) && !Settings.isDailyRun) continue;
                    cardsToAdd.add(card);
                }
                break;
            }
            case THE_SILENT: {
                for (Map.Entry<String, AbstractCard> c : CardLibrary.cards.entrySet()) {
                    AbstractCard card = c.getValue();
                    if (card.color != AbstractCard.CardColor.RED || card.rarity == AbstractCard.CardRarity.BASIC || UnlockTracker.isCardLocked(c.getKey()) && !Settings.isDailyRun) continue;
                    cardsToAdd.add(card);
                }
                break;
            }
        }

        for (AbstractCard c : cardsToAdd) {
            switch (c.rarity) {
                case COMMON: {
                    AbstractDungeon.commonCardPool.addToTop(c);
                    AbstractDungeon.srcCommonCardPool.addToTop(c);
                    break;
                }
                case UNCOMMON: {
                    AbstractDungeon.uncommonCardPool.addToTop(c);
                    AbstractDungeon.srcUncommonCardPool.addToTop(c);
                    break;
                }
                case RARE: {
                    AbstractDungeon.rareCardPool.addToTop(c);
                    AbstractDungeon.srcRareCardPool.addToTop(c);
                    break;
                }
                case CURSE: {
                    AbstractDungeon.curseCardPool.addToTop(c);
                    AbstractDungeon.srcCurseCardPool.addToTop(c);
                    break;
                }
            }
        }
    }

    @Override
    public void receivePostDungeonInitialize() {
        if (AbstractDungeon.player.relics.indexOf(this) != -1) {
            addCardsToPool();
        }
    }

    @Override
    public void onEquip() {
        addCardsToPool();
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new BrainOfInsanity();
    }
}