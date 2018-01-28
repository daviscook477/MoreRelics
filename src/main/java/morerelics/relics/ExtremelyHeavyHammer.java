package morerelics.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.relics.AbstractRelic;

public class ExtremelyHeavyHammer extends CustomRelic {
    public static final String ID = "ExtremelyHeavyHammer";
    private static final String IMG = "img/relics/ExtremelyHeavyHammer.png";
    private static final String OUTLINE = "img/relics/outline/ExtremelyHeavyHammer.png";

    private boolean activate;

    public ExtremelyHeavyHammer() {
        super(ID, new Texture(Gdx.files.internal(IMG)), new Texture(Gdx.files.internal(OUTLINE)), RelicTier.RARE, LandingSound.HEAVY);
    }

    @Override
    public void atTurnStart() {
        activate = true;
    }

    @Override
    public void onPlayerEndTurn() {
        if (activate) {
            AbstractPlayer p = AbstractDungeon.player;

            flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(p, this));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(AbstractDungeon.player, 4), 4));
        }
    }

    @Override
    public void onPlayCard(AbstractCard c, AbstractMonster m) {
        activate = false;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

    @Override
    public AbstractRelic makeCopy() {
        return new ExtremelyHeavyHammer();
    }
}