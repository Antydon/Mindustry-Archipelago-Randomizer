package mindustry.randomizer;

import dev.koifysh.archipelago.helper.DeathLink;
import mindustry.content.Blocks;
import mindustry.content.Planets;
import mindustry.content.SectorPresets;
import mindustry.content.UnitTypes;
import mindustry.randomizer.client.SlotData;
import mindustry.world.blocks.power.ThermalGenerator;
import mindustry.world.blocks.production.BeamDrill;
import mindustry.world.blocks.production.Drill;
import mindustry.world.blocks.production.GenericCrafter;
import mindustry.world.blocks.production.Pump;
import mindustry.world.blocks.production.Separator;
import mindustry.world.blocks.production.WallCrafter;

import static arc.Core.settings;
import static mindustry.Vars.randomizer;
import static mindustry.randomizer.enums.SettingStrings.*;

/**
 * Class containing the options for the generated Mindustry game.
 *
 * @author John Mahglass
 */
public class MindustryOptions {

    /**
     * If the options has been filled and are not default.
     */
    private boolean optionsFilled;

    /**
     * If the tutorial is to be skipped. Also unlock tutorial-related tech.
     */
    private boolean tutorialSkip;

    /**
     * The selected campaign.
     */
    private int campaign;

    /**
     * If death link is activated
     */
    private boolean deathLink;

    /**
     * Disable death link even if it was chosen as an option for generation.
     */
    private boolean forceDisableDeathLink;

    /**
     * Disable invasions.
     */
    private boolean disableInvasions;

    /**
     * Increase the rate at which resource are harvested and increase production output.
     */
    private boolean fasterProduction;

    public boolean getTutorialSkip() {
        return this.tutorialSkip;
    }

    public boolean getDisableInvasions() {
        return this.disableInvasions;
    }

    public boolean getFasterProduction() {
        return this.fasterProduction;
    }

    /**
     * Verify if death link is enable but does not verify if it has been disabled on client side.
     * This should only be used to enable DeathLink tags in the client upon connection.
     * @return If the death link option was chosen for game generation.
     */
    public boolean getTrueDeathLink(){
        return this.deathLink;
    }

    /**
     * Verifiy if death link is enable and also check if force disable has been enable.
     * @return Return true if death link is activated.
     */
    public boolean getDeathLink() {
        return this.deathLink && !this.forceDisableDeathLink;
    }

    public boolean getForceDisableDeathLink() {
        return this.forceDisableDeathLink;
    }

    /**
     * Set forceDisableDeathLink and save the variable in settings.
     * @param state The new state of forceDisableDeathLink.
     */
    public void setForceDisableDeathLink(boolean state) {
        this.forceDisableDeathLink = state;
        settings.put(FORCE_DISABLE_DEATH_LINK.value, state);
        if (forceDisableDeathLink != state) { // Setting has been changed.
            if (forceDisableDeathLink) {
                DeathLink.setDeathLinkEnabled(false);
            } else {
                DeathLink.setDeathLinkEnabled(true);
            }
        }

    }

    public int getCampaign() {
        return this.campaign;
    }

    public boolean getOptionsFilled() {
        boolean filled;
        if (this.optionsFilled || randomizer.hasConnectedPreviously) {
            filled = true;
        } else {
            filled = false;
        }
        return filled;
    }


    /**
     * Fill the options with the options received from AP
     * @param slotData slot data containing the player's options.
     */
    public void fillOptions (SlotData slotData) {
        if (slotData != null) {
            this.deathLink = slotData.getDeathlink();
            this.tutorialSkip = slotData.getTutorialSkip();
            this.disableInvasions = slotData.getDisableInvasions();
            this.fasterProduction = slotData.getFasterProduction();
            this.campaign = slotData.getCampaignChoice();

            this.optionsFilled = true;
            saveOptions();
            settings.put(HAS_CONNECTED.value, true);
            if (tutorialSkip) {
                if (campaign == 0) {
                    settings.put(FREE_LAUNCH_SERPULO.value, true);
                } else if (campaign == 1) {
                    settings.put(FREE_LAUNCH_EREKIR.value, true);
                } else if (campaign == 2) {
                    settings.put(FREE_LAUNCH_SERPULO.value, true);
                    settings.put(FREE_LAUNCH_EREKIR.value, true);
                }
            }
            if (fasterProduction) {
                settings.put(FASTER_PRODUCTION.value, true);
            }
            if (disableInvasions) {
                settings.put(DISABLE_INVASIONS.value, true);
            }
            if (deathLink) {
                settings.put(DEATH_LINK.value, true);
            }
        }
    }


    /**
     * Constructor for MindustryOptions, if the user has never connected to a game, load default
     * options in the meantime.
     */
    public MindustryOptions() {
        //Do not use randomizer.hasConnectedPreviously since Vars.randomizer is null
        if (settings != null && settings.getBool(HAS_CONNECTED.value)) { //Player has connected to the game before
            loadOptions();
        } else { //Player never connected to the game and has not received options information.
            this.optionsFilled = false;
            this.tutorialSkip = false;
            this.campaign = -1;
            this.disableInvasions = false;
            this.fasterProduction = false;
            this.deathLink = false;
            if (settings != null) { //Local settings
                this.forceDisableDeathLink = settings.getBool(FORCE_DISABLE_DEATH_LINK.value);
            }
        }
    }

    /**
     * Unlock Serpulo's tutorial research and unlock Frozen Forest.
     */
    protected static void unlockSerpuloTutorialItems() {
        Blocks.conveyor.quietUnlock();
        Blocks.duo.quietUnlock();
        Blocks.scatter.quietUnlock();
        Blocks.mechanicalDrill.quietUnlock();
        Blocks.copperWall.quietUnlock();
        SectorPresets.groundZero.quietUnlock();
        SectorPresets.frozenForest.quietUnlock();
        SectorPresets.frozenForest.alwaysUnlocked = true;
    }

    /**
     * Unlock Erekir's tutorial research and unlock Aegis.
     */
    protected static void unlockErekirTutorialItems() {
        Blocks.duct.quietUnlock();
        Blocks.plasmaBore.quietUnlock();
        Blocks.turbineCondenser.quietUnlock();
        Blocks.beamNode.quietUnlock();
        Blocks.cliffCrusher.quietUnlock();
        Blocks.siliconArcFurnace.quietUnlock();
        Blocks.breach.quietUnlock();
        Blocks.berylliumWall.quietUnlock();
        Blocks.tankFabricator.quietUnlock();
        UnitTypes.stell.unlock();
        SectorPresets.aegis.quietUnlock();
        SectorPresets.aegis.alwaysUnlocked = true;
    }

    /**
     * Apply the faster production option to the selected campaign.
     * @param campaign The selected campaign.
     */
    protected static void applyFasterProduction(int campaign){
        if (campaign == 0) { //Serpulo
            applySerpuloFasterProduction();
        } else if (campaign == 1) { //Erekir
            applyErekirFasterProduction();
        } else if (campaign == 2) { //All
            applySerpuloFasterProduction();
            applyErekirFasterProduction();
        }
    }

    /**
     * Apply the faster production option on Erekir's research.
     */
    private static void applyErekirFasterProduction() {
        doubleOutputItem(((GenericCrafter) Blocks.siliconArcFurnace));
        doubleOutputLiquids(((GenericCrafter) Blocks.electrolyzer));
        doubleOutputLiquid(((GenericCrafter) Blocks.atmosphericConcentrator));
        doubleOutputItem(((GenericCrafter) Blocks.oxidationChamber));
        doubleOutputItem(((GenericCrafter) Blocks.carbideCrucible));
        doubleOutputItem(((GenericCrafter) Blocks.surgeCrucible));
        doubleOutputLiquid(((GenericCrafter) Blocks.cyanogenSynthesizer));
        doubleOutputItem(((GenericCrafter) Blocks.phaseSynthesizer));
        halfWallCrafterDrillTime(((WallCrafter) Blocks.cliffCrusher));
        halfBeamDrillTime(((BeamDrill) Blocks.plasmaBore));
        halfBeamDrillTime(((BeamDrill) Blocks.largePlasmaBore));
        halfDrillTime(((Drill) Blocks.impactDrill));
        halfDrillTime(((Drill) Blocks.eruptionDrill));
        doublePumpAmount(((Pump)Blocks.reinforcedPump));
    }



    /**
     * Apply the faster production option on Serpulo's research.
     */
    private static void applySerpuloFasterProduction() {
        doubleOutputItem(((GenericCrafter) Blocks.graphitePress));
        doubleOutputItem(((GenericCrafter) Blocks.multiPress));
        doubleOutputItem(((GenericCrafter) Blocks.siliconSmelter));
        doubleOutputItem(((GenericCrafter) Blocks.siliconCrucible));
        doubleOutputItem(((GenericCrafter) Blocks.kiln));
        doubleOutputItem(((GenericCrafter) Blocks.plastaniumCompressor));
        doubleOutputItem(((GenericCrafter) Blocks.phaseWeaver));
        doubleOutputItem(((GenericCrafter) Blocks.surgeSmelter));
        doubleOutputLiquid(((GenericCrafter) Blocks.cryofluidMixer));
        doubleOutputItem(((GenericCrafter) Blocks.pyratiteMixer));
        doubleOutputItem(((GenericCrafter) Blocks.blastMixer));
        doubleOutputLiquid(((GenericCrafter) Blocks.melter));
        doubleSeparatorOutput(((Separator) Blocks.separator));
        doubleSeparatorOutput(((Separator) Blocks.disassembler));
        doubleOutputLiquid(((GenericCrafter) Blocks.sporePress));
        doubleOutputItem(((GenericCrafter) Blocks.pulverizer));
        doubleOutputItem(((GenericCrafter) Blocks.coalCentrifuge));
        doublePumpAmount(((Pump) Blocks.mechanicalPump));
        doublePumpAmount(((Pump) Blocks.rotaryPump));
        doublePumpAmount(((Pump) Blocks.impulsePump));
        halfDrillTime(((Drill) Blocks.mechanicalDrill));
        halfDrillTime(((Drill) Blocks.pneumaticDrill));
        halfDrillTime(((Drill) Blocks.laserDrill));
        halfDrillTime(((Drill) Blocks.blastDrill));
        doublePumpAmount(((Pump) Blocks.waterExtractor));
        doublePumpAmount(((Pump) Blocks.oilExtractor));
        doubleOutputItem(((GenericCrafter) Blocks.cultivator));
    }

    private static void doubleOutputLiquids(GenericCrafter crafter) {
        for (int i = 0; i < crafter.outputLiquids.length; i++) {
            crafter.outputLiquids[i].amount = crafter.outputLiquids[i].amount * 2;
        }
    }

    /**
     * Reduce time required by the miner to extract ressources by half.
     * @param miner the miner to have the extract time reduced by half
     */
    private static void halfWallCrafterDrillTime(WallCrafter miner) {
        miner.drillTime = miner.drillTime / 2;
    }

    /**
     * Reduce time required by the drill to extract ressources by half.
     * @param drill the drill to have the extract time reduced by half
     */
    private static void halfBeamDrillTime(BeamDrill drill) {
        drill.drillTime = drill.drillTime / 2;
    }


    /**
     * Reduce time required by the drill to extract ressources by half.
     * @param drill the drill to have the extract time reduced by half
     */
    private static void halfDrillTime(Drill drill) {
        drill.drillTime = drill.drillTime / 2;
    }

    /**
     * Double the output item of a GenericCrafter
     * @param crafter The crafter to have the output doubled.
     */
    private static void doubleOutputItem(GenericCrafter crafter){
        crafter.outputItem.amount = crafter.outputItem.amount * 2;
    }

    /**
     * Double the output liquid of a ThermalGenerator
     * @param generator The generator to have the output doubled.
     */
    private static void doubleGeneratorOutputLiquid(ThermalGenerator generator){
        generator.outputLiquid.amount = generator.outputLiquid.amount * 2;
    }

    /**
     * Double the output liquid of a GenericCrafter
     * @param crafter The crafter to have the output doubled.
     */
    private static void doubleOutputLiquid(GenericCrafter crafter){
        crafter.outputLiquid.amount = crafter.outputLiquid.amount * 2;
    }

    /**
     * Double the output item of a Separator.
     * @param separator The separator to have the output doubled.
     */
    private static void doubleSeparatorOutput(Separator separator){
        for (int i = 0; i < separator.results.length; i++) {
            separator.results[i].amount = separator.results[i]. amount * 2;
        }
    }

    /**
     * Double the amount extracted by the Pump
     * @param pump The pump to have the amount doubled.
     */
    private static void doublePumpAmount(Pump pump){
        pump.pumpAmount = pump.pumpAmount * 2;
    }


    /**
     * Save options locally
     */
    private void saveOptions() {
        settings.put(DEATH_LINK.value, getDeathLink());
        settings.put(TUTORIAL_SKIP.value, getTutorialSkip());
        settings.put(DISABLE_INVASIONS.value, getDisableInvasions());
        settings.put(FASTER_PRODUCTION.value, getFasterProduction());
        settings.put(CAMPAIGN_CHOICE.value, getCampaign());
    }

    /**
     * Load options locally
     */
    private void loadOptions() {
        this.deathLink = settings.getBool(DEATH_LINK.value);
        this.forceDisableDeathLink = settings.getBool(FORCE_DISABLE_DEATH_LINK.value);
        this.tutorialSkip = settings.getBool(TUTORIAL_SKIP.value);
        this.disableInvasions = settings.getBool(DISABLE_INVASIONS.value);
        this.fasterProduction = settings.getBool(FASTER_PRODUCTION.value);
        this.campaign = settings.getInt(CAMPAIGN_CHOICE.value);
        this.optionsFilled = true;
    }

}
