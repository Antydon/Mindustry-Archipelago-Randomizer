package mindustry.randomizer;

import mindustry.content.Blocks;
import mindustry.content.Items;
import mindustry.content.Liquids;
import mindustry.content.SectorPresets;
import mindustry.content.UnitTypes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mindustry.ctype.UnlockableContent;


import static mindustry.randomizer.Shared.MINDUSTRY_BASE_ID;

/**
 * Contain and update information about what has been done within this World.
 *
 * @author John Mahglass
 * @version 1.0.0 2024-05-26
 */
public class WorldState {

    /**
     * Name of the file containing locations that has been checked.
     */
    public String locationCheckedFile = "RandomizerLocationChecked.txt";

    /**
     * Name of the file containing locations that are pending.
     */
    public String checkPendingFile = "RandomizerCheckPending.txt";

    /**
     * Name of the file containing item that are queued for unlock.
     */
    public String itemToBeReceivedFile = "RandomizerItemToBeReceived.txt";


    /**
     * List of all locations used in the randomisation.
     */
    public Map<Integer, String> locations;

    /**
     * List of locations that have been checked and successfully sent.
     */
    public ArrayList<Long> locationsChecked;

    /**
     * List of checked locations that have not been successfully sent.
     */
    public ArrayList<Long> checkPending;

    /**
     * List of item that are queued to be received.
     */
    public ArrayList<Long> itemToBeReceived;

    /**
     * All UnlockableContent with their matching id
     */
    public Map<Long, UnlockableContent> items;

    /**
     * Randomized items that have been received.
     */
    public Long[] unlockedItems;

    /**
     * Contains the options of the generated game.
     */
    public MindustryOptions options;


    /**
     * True if there is a check waiting to be sent to the server.
     * @return True if a check is pending.
     */
    public boolean hasCheckPending(){
        if (checkPending.isEmpty() || checkPending == null) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * Constrcutor of the world state.
     */
    public WorldState() {
        this.options = new MindustryOptions();
        this.items = new HashMap<>();
        this.unlockedItems = null;
        this.locations = new HashMap<>();
        this.locationsChecked = new ArrayList<>();
        this.checkPending = new ArrayList<>();
        this.itemToBeReceived = new ArrayList<>();
    }

    /**
     * Initialize world state.
     */
    public void initialize(){
         loadStates();
    }

    /**
     * Add a check to an array. Note that the state should be saved after using this method by
     * calling saveState().
     * @param stateArray
     * @param newCheck
     */
    public void addCheck(ArrayList<Long> stateArray, Long newCheck){
        stateArray.add(newCheck);
    }

    /**
     * Save current state to the save file.
     */
    public void saveState(String fileName,ArrayList<Long> state){
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            if (state != null) {
                oos.writeObject(state);
            }

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveStates(){
        saveState(checkPendingFile, checkPending);
        saveState(locationCheckedFile, locationsChecked);
        saveState(itemToBeReceivedFile, itemToBeReceived);
    }

    /**
     * Load every saved state
     */
    private void loadStates(){
        checkPending = loadState(checkPendingFile);
        locationsChecked = loadState(locationCheckedFile);
        itemToBeReceived = loadState(itemToBeReceivedFile);
    }

    /**
     * Load the saved state at the start of the game.
     */
    private ArrayList<Long> loadState(String fileName) {
        ArrayList<Long> loadedState;
        try {
            FileInputStream fis = new FileInputStream(fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);

            loadedState = (ArrayList<Long>) ois.readObject();
            if (loadedState == null) {
                loadedState = new ArrayList<>();
            }

            ois.close();
            fis.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            loadedState = new ArrayList<>();
        }
        return loadedState;
    }

    private void wipeState(String fileName) {
        ArrayList<Long> state = new ArrayList<>();
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

                oos.writeObject(state);

            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void wipeStates() {
        wipeState(locationCheckedFile);
        wipeState(checkPendingFile);
        wipeState(itemToBeReceivedFile);
    }

    /**
     * Initialize items for all campaigns.
     */
    protected void initializeAllItems() {
        //Method not implemented
    }

    /**
     * Initialize locations for all campaigns
     */
    protected void initializeAllLocations() {
        //Method not implemented
    }

    /**
     * Initialize item from Erekir campaign.
     */
    protected void initializeErekirItems() {
        //Method not implemented
    }

    /**
     * Initialize locations from Erekir campaign
     */
    protected void initializeErekirLocations() {
        //Method not implemented
    }

    /**
     * Initialize item from Serpulo campaign.
     */
    protected void initializeSerpuloItems() {
        //items.put(MINDUSTRY_BASE_ID + 0, Blocks.conveyor);
        items.put(MINDUSTRY_BASE_ID + 1, Blocks.junction);
        items.put(MINDUSTRY_BASE_ID + 2, Blocks.router);
        items.put(MINDUSTRY_BASE_ID + 3, Blocks.launchPad);
        items.put(MINDUSTRY_BASE_ID + 4, Blocks.distributor);
        items.put(MINDUSTRY_BASE_ID + 5, Blocks.sorter);
        items.put(MINDUSTRY_BASE_ID + 6, Blocks.invertedSorter);
        items.put(MINDUSTRY_BASE_ID + 7, Blocks.overflowGate);
        items.put(MINDUSTRY_BASE_ID + 8, Blocks.underflowGate);
        items.put(MINDUSTRY_BASE_ID + 9, Blocks.container);
        items.put(MINDUSTRY_BASE_ID + 10, Blocks.unloader);
        items.put(MINDUSTRY_BASE_ID + 11, Blocks.vault);
        items.put(MINDUSTRY_BASE_ID + 12, Blocks.itemBridge);
        items.put(MINDUSTRY_BASE_ID + 13, Blocks.titaniumConveyor);
        items.put(MINDUSTRY_BASE_ID + 14, Blocks.phaseConveyor);
        items.put(MINDUSTRY_BASE_ID + 15, Blocks.massDriver);
        items.put(MINDUSTRY_BASE_ID + 16, Blocks.payloadConveyor);
        items.put(MINDUSTRY_BASE_ID + 17, Blocks.payloadRouter);
        items.put(MINDUSTRY_BASE_ID + 18, Blocks.armoredConveyor);
        items.put(MINDUSTRY_BASE_ID + 19, Blocks.plastaniumConveyor);
        items.put(MINDUSTRY_BASE_ID + 20, Blocks.coreFoundation);
        items.put(MINDUSTRY_BASE_ID + 21, Blocks.coreNucleus);
        //items.put(MINDUSTRY_BASE_ID + 22, Blocks.mechanicalDrill);
        items.put(MINDUSTRY_BASE_ID + 23, Blocks.mechanicalPump);
        items.put(MINDUSTRY_BASE_ID + 24, Blocks.conduit);
        items.put(MINDUSTRY_BASE_ID + 25, Blocks.liquidJunction);
        items.put(MINDUSTRY_BASE_ID + 26, Blocks.liquidRouter);
        items.put(MINDUSTRY_BASE_ID + 27, Blocks.liquidContainer);
        items.put(MINDUSTRY_BASE_ID + 28, Blocks.liquidTank);
        items.put(MINDUSTRY_BASE_ID + 29, Blocks.bridgeConduit);
        items.put(MINDUSTRY_BASE_ID + 30, Blocks.pulseConduit);
        items.put(MINDUSTRY_BASE_ID + 31, Blocks.phaseConduit);
        items.put(MINDUSTRY_BASE_ID + 32, Blocks.platedConduit);
        items.put(MINDUSTRY_BASE_ID + 33, Blocks.rotaryPump);
        items.put(MINDUSTRY_BASE_ID + 34, Blocks.impulsePump);
        items.put(MINDUSTRY_BASE_ID + 35, Blocks.graphitePress);
        items.put(MINDUSTRY_BASE_ID + 36, Blocks.pneumaticDrill);
        items.put(MINDUSTRY_BASE_ID + 37, Blocks.cultivator);
        items.put(MINDUSTRY_BASE_ID + 38, Blocks.laserDrill);
        items.put(MINDUSTRY_BASE_ID + 39, Blocks.blastDrill);
        items.put(MINDUSTRY_BASE_ID + 40, Blocks.waterExtractor);
        items.put(MINDUSTRY_BASE_ID + 41, Blocks.oilExtractor);
        items.put(MINDUSTRY_BASE_ID + 42, Blocks.pyratiteMixer);
        items.put(MINDUSTRY_BASE_ID + 43, Blocks.blastMixer);
        items.put(MINDUSTRY_BASE_ID + 44, Blocks.siliconSmelter);
        items.put(MINDUSTRY_BASE_ID + 45, Blocks.sporePress);
        items.put(MINDUSTRY_BASE_ID + 46, Blocks.coalCentrifuge);
        items.put(MINDUSTRY_BASE_ID + 47, Blocks.multiPress);
        items.put(MINDUSTRY_BASE_ID + 48, Blocks.siliconCrucible);
        items.put(MINDUSTRY_BASE_ID + 49, Blocks.plastaniumCompressor);
        items.put(MINDUSTRY_BASE_ID + 50, Blocks.phaseWeaver);
        items.put(MINDUSTRY_BASE_ID + 51, Blocks.kiln);
        items.put(MINDUSTRY_BASE_ID + 52, Blocks.pulverizer);
        items.put(MINDUSTRY_BASE_ID + 53, Blocks.incinerator);
        items.put(MINDUSTRY_BASE_ID + 54, Blocks.melter);
        items.put(MINDUSTRY_BASE_ID + 55, Blocks.surgeSmelter);
        items.put(MINDUSTRY_BASE_ID + 56, Blocks.separator);
        items.put(MINDUSTRY_BASE_ID + 57, Blocks.disassembler);
        items.put(MINDUSTRY_BASE_ID + 58, Blocks.cryofluidMixer);
        items.put(MINDUSTRY_BASE_ID + 59, Blocks.microProcessor);
        items.put(MINDUSTRY_BASE_ID + 60, Blocks.switchBlock);
        items.put(MINDUSTRY_BASE_ID + 61, Blocks.message);
        items.put(MINDUSTRY_BASE_ID + 62, Blocks.logicDisplay);
        items.put(MINDUSTRY_BASE_ID + 63, Blocks.largeLogicDisplay);
        items.put(MINDUSTRY_BASE_ID + 64, Blocks.memoryCell);
        items.put(MINDUSTRY_BASE_ID + 65, Blocks.memoryBank);
        items.put(MINDUSTRY_BASE_ID + 66, Blocks.logicProcessor);
        items.put(MINDUSTRY_BASE_ID + 67, Blocks.hyperProcessor);
        items.put(MINDUSTRY_BASE_ID + 68, Blocks.illuminator);
        items.put(MINDUSTRY_BASE_ID + 69, Blocks.combustionGenerator);
        items.put(MINDUSTRY_BASE_ID + 70, Blocks.powerNode);
        items.put(MINDUSTRY_BASE_ID + 71, Blocks.powerNodeLarge);
        items.put(MINDUSTRY_BASE_ID + 72, Blocks.diode);
        items.put(MINDUSTRY_BASE_ID + 73, Blocks.surgeTower);
        items.put(MINDUSTRY_BASE_ID + 74, Blocks.battery);
        items.put(MINDUSTRY_BASE_ID + 75, Blocks.batteryLarge);
        items.put(MINDUSTRY_BASE_ID + 76, Blocks.mender);
        items.put(MINDUSTRY_BASE_ID + 77, Blocks.mendProjector);
        items.put(MINDUSTRY_BASE_ID + 78, Blocks.forceProjector);
        items.put(MINDUSTRY_BASE_ID + 79, Blocks.overdriveProjector);
        items.put(MINDUSTRY_BASE_ID + 80, Blocks.overdriveDome);
        items.put(MINDUSTRY_BASE_ID + 81, Blocks.repairPoint);
        items.put(MINDUSTRY_BASE_ID + 82, Blocks.repairTurret);
        items.put(MINDUSTRY_BASE_ID + 83, Blocks.steamGenerator);
        items.put(MINDUSTRY_BASE_ID + 84, Blocks.thermalGenerator);
        items.put(MINDUSTRY_BASE_ID + 85, Blocks.differentialGenerator);
        items.put(MINDUSTRY_BASE_ID + 86, Blocks.thoriumReactor);
        items.put(MINDUSTRY_BASE_ID + 87, Blocks.impactReactor);
        items.put(MINDUSTRY_BASE_ID + 88, Blocks.rtgGenerator);
        items.put(MINDUSTRY_BASE_ID + 89, Blocks.solarPanel);
        items.put(MINDUSTRY_BASE_ID + 90, Blocks.largeSolarPanel);
        //items.put(MINDUSTRY_BASE_ID + 91, Blocks.duo);
        //items.put(MINDUSTRY_BASE_ID + 92, Blocks.copperWall);
        items.put(MINDUSTRY_BASE_ID + 93, Blocks.copperWallLarge);
        items.put(MINDUSTRY_BASE_ID + 94, Blocks.titaniumWall);
        items.put(MINDUSTRY_BASE_ID + 95, Blocks.titaniumWallLarge);
        items.put(MINDUSTRY_BASE_ID + 96, Blocks.door);
        items.put(MINDUSTRY_BASE_ID + 97, Blocks.doorLarge);
        items.put(MINDUSTRY_BASE_ID + 98, Blocks.plastaniumWall);
        items.put(MINDUSTRY_BASE_ID + 99, Blocks.plastaniumWallLarge);
        items.put(MINDUSTRY_BASE_ID + 100, Blocks.thoriumWall);
        items.put(MINDUSTRY_BASE_ID + 101, Blocks.thoriumWallLarge);
        items.put(MINDUSTRY_BASE_ID + 102, Blocks.surgeWall);
        items.put(MINDUSTRY_BASE_ID + 103, Blocks.surgeWallLarge);
        items.put(MINDUSTRY_BASE_ID + 104, Blocks.phaseWall);
        items.put(MINDUSTRY_BASE_ID + 105, Blocks.phaseWallLarge);
        //items.put(MINDUSTRY_BASE_ID + 106, Blocks.scatter);
        items.put(MINDUSTRY_BASE_ID + 107, Blocks.hail);
        items.put(MINDUSTRY_BASE_ID + 108, Blocks.salvo);
        items.put(MINDUSTRY_BASE_ID + 109, Blocks.swarmer);
        items.put(MINDUSTRY_BASE_ID + 110, Blocks.cyclone);
        items.put(MINDUSTRY_BASE_ID + 111, Blocks.spectre);
        items.put(MINDUSTRY_BASE_ID + 112, Blocks.ripple);
        items.put(MINDUSTRY_BASE_ID + 113, Blocks.fuse);
        items.put(MINDUSTRY_BASE_ID + 114, Blocks.scorch);
        items.put(MINDUSTRY_BASE_ID + 115, Blocks.arc);
        items.put(MINDUSTRY_BASE_ID + 116, Blocks.wave);
        items.put(MINDUSTRY_BASE_ID + 117, Blocks.parallax);
        items.put(MINDUSTRY_BASE_ID + 118, Blocks.segment);
        items.put(MINDUSTRY_BASE_ID + 119, Blocks.tsunami);
        items.put(MINDUSTRY_BASE_ID + 120, Blocks.lancer);
        items.put(MINDUSTRY_BASE_ID + 121, Blocks.meltdown);
        items.put(MINDUSTRY_BASE_ID + 122, Blocks.foreshadow);
        items.put(MINDUSTRY_BASE_ID + 123, Blocks.shockMine);
        items.put(MINDUSTRY_BASE_ID + 124, Blocks.groundFactory);
        items.put(MINDUSTRY_BASE_ID + 125, UnitTypes.dagger);
        items.put(MINDUSTRY_BASE_ID + 126, UnitTypes.mace);
        items.put(MINDUSTRY_BASE_ID + 127, UnitTypes.fortress);
        items.put(MINDUSTRY_BASE_ID + 128, UnitTypes.scepter);
        items.put(MINDUSTRY_BASE_ID + 129, UnitTypes.reign);
        items.put(MINDUSTRY_BASE_ID + 130, UnitTypes.nova);
        items.put(MINDUSTRY_BASE_ID + 131, UnitTypes.pulsar);
        items.put(MINDUSTRY_BASE_ID + 132, UnitTypes.quasar);
        items.put(MINDUSTRY_BASE_ID + 133, UnitTypes.vela);
        items.put(MINDUSTRY_BASE_ID + 134, UnitTypes.corvus);
        items.put(MINDUSTRY_BASE_ID + 135, UnitTypes.crawler);
        items.put(MINDUSTRY_BASE_ID + 136, UnitTypes.atrax);
        items.put(MINDUSTRY_BASE_ID + 137, UnitTypes.spiroct);
        items.put(MINDUSTRY_BASE_ID + 138, UnitTypes.arkyid);
        items.put(MINDUSTRY_BASE_ID + 139, UnitTypes.toxopid);
        items.put(MINDUSTRY_BASE_ID + 140, Blocks.airFactory);
        items.put(MINDUSTRY_BASE_ID + 141, UnitTypes.flare);
        items.put(MINDUSTRY_BASE_ID + 142, UnitTypes.horizon);
        items.put(MINDUSTRY_BASE_ID + 143, UnitTypes.zenith);
        items.put(MINDUSTRY_BASE_ID + 144, UnitTypes.antumbra);
        items.put(MINDUSTRY_BASE_ID + 145, UnitTypes.eclipse);
        items.put(MINDUSTRY_BASE_ID + 146, UnitTypes.mono);
        items.put(MINDUSTRY_BASE_ID + 147, UnitTypes.poly);
        items.put(MINDUSTRY_BASE_ID + 148, UnitTypes.mega);
        items.put(MINDUSTRY_BASE_ID + 149, UnitTypes.quad);
        items.put(MINDUSTRY_BASE_ID + 150, UnitTypes.oct);
        items.put(MINDUSTRY_BASE_ID + 151, Blocks.navalFactory);
        items.put(MINDUSTRY_BASE_ID + 152, UnitTypes.risso);
        items.put(MINDUSTRY_BASE_ID + 153, UnitTypes.minke);
        items.put(MINDUSTRY_BASE_ID + 154, UnitTypes.bryde);
        items.put(MINDUSTRY_BASE_ID + 155, UnitTypes.sei);
        items.put(MINDUSTRY_BASE_ID + 156, UnitTypes.omura);
        items.put(MINDUSTRY_BASE_ID + 157, UnitTypes.retusa);
        items.put(MINDUSTRY_BASE_ID + 158, UnitTypes.oxynoe);
        items.put(MINDUSTRY_BASE_ID + 159, UnitTypes.cyerce);
        items.put(MINDUSTRY_BASE_ID + 160, UnitTypes.aegires);
        items.put(MINDUSTRY_BASE_ID + 161, UnitTypes.navanax);
        items.put(MINDUSTRY_BASE_ID + 162, Blocks.additiveReconstructor);
        items.put(MINDUSTRY_BASE_ID + 163, Blocks.multiplicativeReconstructor);
        items.put(MINDUSTRY_BASE_ID + 164, Blocks.exponentialReconstructor);
        items.put(MINDUSTRY_BASE_ID + 165, Blocks.tetrativeReconstructor);
        items.put(MINDUSTRY_BASE_ID + 166, SectorPresets.frozenForest);
        items.put(MINDUSTRY_BASE_ID + 167, SectorPresets.craters);
        items.put(MINDUSTRY_BASE_ID + 168, SectorPresets.ruinousShores);
        items.put(MINDUSTRY_BASE_ID + 169, SectorPresets.windsweptIslands);
        items.put(MINDUSTRY_BASE_ID + 170, SectorPresets.tarFields);
        items.put(MINDUSTRY_BASE_ID + 171, SectorPresets.impact0078);
        items.put(MINDUSTRY_BASE_ID + 172, SectorPresets.desolateRift);
        items.put(MINDUSTRY_BASE_ID + 173, SectorPresets.planetaryTerminal);
        items.put(MINDUSTRY_BASE_ID + 174, SectorPresets.extractionOutpost);
        items.put(MINDUSTRY_BASE_ID + 175, SectorPresets.saltFlats);
        items.put(MINDUSTRY_BASE_ID + 176, SectorPresets.coastline);
        items.put(MINDUSTRY_BASE_ID + 177, SectorPresets.navalFortress);
        items.put(MINDUSTRY_BASE_ID + 178, SectorPresets.overgrowth);
        items.put(MINDUSTRY_BASE_ID + 179, SectorPresets.biomassFacility);
        items.put(MINDUSTRY_BASE_ID + 180, SectorPresets.stainedMountains);
        items.put(MINDUSTRY_BASE_ID + 181, SectorPresets.fungalPass);
        items.put(MINDUSTRY_BASE_ID + 182, SectorPresets.nuclearComplex);
        items.put(MINDUSTRY_BASE_ID + 183, Items.lead);
        items.put(MINDUSTRY_BASE_ID + 184, Items.titanium);
        items.put(MINDUSTRY_BASE_ID + 185, Liquids.cryofluid);
        items.put(MINDUSTRY_BASE_ID + 186, Items.thorium);
        items.put(MINDUSTRY_BASE_ID + 187, Items.surgeAlloy);
        items.put(MINDUSTRY_BASE_ID + 188, Items.phaseFabric);
        items.put(MINDUSTRY_BASE_ID + 189, Items.metaglass);
        items.put(MINDUSTRY_BASE_ID + 190, Items.scrap);
        items.put(MINDUSTRY_BASE_ID + 191, Liquids.slag);
        items.put(MINDUSTRY_BASE_ID + 192, Items.coal);
        items.put(MINDUSTRY_BASE_ID + 193, Items.graphite);
        items.put(MINDUSTRY_BASE_ID + 194, Items.silicon);
        items.put(MINDUSTRY_BASE_ID + 195, Items.pyratite);
        items.put(MINDUSTRY_BASE_ID + 196, Items.blastCompound);
        items.put(MINDUSTRY_BASE_ID + 197, Items.sporePod);
        items.put(MINDUSTRY_BASE_ID + 198, Liquids.oil);
        items.put(MINDUSTRY_BASE_ID + 199, Items.plastanium);
    }

    /**
     * Initialise location for Serpulo campaign.
     */
    protected void initializeSerpuloLocations() { //Might not be useful
        /*
        locations.put(MINDUSTRY_BASE_ID + 0, "Node Conveyor");
        locations.put(MINDUSTRY_BASE_ID + 1, "Node Junction");
        locations.put(MINDUSTRY_BASE_ID + 2, "Node Router");
        locations.put(MINDUSTRY_BASE_ID + 3, "Node Launch Pad");
        locations.put(MINDUSTRY_BASE_ID + 4, "Node Distributor");
        locations.put(MINDUSTRY_BASE_ID + 5, "Node Sorter");
        locations.put(MINDUSTRY_BASE_ID + 6, "Node Inverted Sorter");
        locations.put(MINDUSTRY_BASE_ID + 7, "Node Overflow");
        locations.put(MINDUSTRY_BASE_ID + 8, );
        locations.put(MINDUSTRY_BASE_ID + 9, );
        locations.put(MINDUSTRY_BASE_ID + 10, );
        locations.put(MINDUSTRY_BASE_ID + 11, );
        locations.put(MINDUSTRY_BASE_ID + 12, );
        locations.put(MINDUSTRY_BASE_ID + 13, );
        locations.put(MINDUSTRY_BASE_ID + 14, );
        locations.put(MINDUSTRY_BASE_ID + 15, );
        locations.put(MINDUSTRY_BASE_ID + 16, );
        locations.put(MINDUSTRY_BASE_ID + 17, );
        locations.put(MINDUSTRY_BASE_ID + 18, );
        locations.put(MINDUSTRY_BASE_ID + 19, );
        locations.put(MINDUSTRY_BASE_ID + 20, );
        locations.put(MINDUSTRY_BASE_ID + 21, );
        locations.put(MINDUSTRY_BASE_ID + 22, );
        locations.put(MINDUSTRY_BASE_ID + 23, );
        locations.put(MINDUSTRY_BASE_ID + 24, );
        locations.put(MINDUSTRY_BASE_ID + 25, );
        locations.put(MINDUSTRY_BASE_ID + 26, );
        locations.put(MINDUSTRY_BASE_ID + 27, );
        locations.put(MINDUSTRY_BASE_ID + 28, );
        locations.put(MINDUSTRY_BASE_ID + 29, );
        locations.put(MINDUSTRY_BASE_ID + 30, );
        locations.put(MINDUSTRY_BASE_ID + 31, );
        locations.put(MINDUSTRY_BASE_ID + 32, );
        locations.put(MINDUSTRY_BASE_ID + 33, );
        locations.put(MINDUSTRY_BASE_ID + 34, );
        locations.put(MINDUSTRY_BASE_ID + 35, );
        locations.put(MINDUSTRY_BASE_ID + 36, );
        locations.put(MINDUSTRY_BASE_ID + 37, );
        locations.put(MINDUSTRY_BASE_ID + 38, );
        locations.put(MINDUSTRY_BASE_ID + 39, );
        locations.put(MINDUSTRY_BASE_ID + 40, );
        locations.put(MINDUSTRY_BASE_ID + 41, );
        locations.put(MINDUSTRY_BASE_ID + 42, );
        locations.put(MINDUSTRY_BASE_ID + 43, );
        locations.put(MINDUSTRY_BASE_ID + 44, );
        locations.put(MINDUSTRY_BASE_ID + 45, );
        locations.put(MINDUSTRY_BASE_ID + 46, );
        locations.put(MINDUSTRY_BASE_ID + 47, );
        locations.put(MINDUSTRY_BASE_ID + 48, );
        locations.put(MINDUSTRY_BASE_ID + 49, );
        locations.put(MINDUSTRY_BASE_ID + 50, );
        locations.put(MINDUSTRY_BASE_ID + 51, );
        locations.put(MINDUSTRY_BASE_ID + 52, );
        locations.put(MINDUSTRY_BASE_ID + 53, );
        locations.put(MINDUSTRY_BASE_ID + 54, );
        locations.put(MINDUSTRY_BASE_ID + 55, );
        locations.put(MINDUSTRY_BASE_ID + 56, );
        locations.put(MINDUSTRY_BASE_ID + 57, );
        locations.put(MINDUSTRY_BASE_ID + 58, );
        locations.put(MINDUSTRY_BASE_ID + 59, );
        locations.put(MINDUSTRY_BASE_ID + 60, );
        locations.put(MINDUSTRY_BASE_ID + 61, );
        locations.put(MINDUSTRY_BASE_ID + 62, );
        locations.put(MINDUSTRY_BASE_ID + 63, );
        locations.put(MINDUSTRY_BASE_ID + 64, );
        locations.put(MINDUSTRY_BASE_ID + 65, );
        locations.put(MINDUSTRY_BASE_ID + 66, );
        locations.put(MINDUSTRY_BASE_ID + 67, );
        locations.put(MINDUSTRY_BASE_ID + 68, );
        locations.put(MINDUSTRY_BASE_ID + 69, );
        locations.put(MINDUSTRY_BASE_ID + 70, );
        locations.put(MINDUSTRY_BASE_ID + 71, );
        locations.put(MINDUSTRY_BASE_ID + 72, );
        locations.put(MINDUSTRY_BASE_ID + 73, );
        locations.put(MINDUSTRY_BASE_ID + 74, );
        locations.put(MINDUSTRY_BASE_ID + 75, );
        locations.put(MINDUSTRY_BASE_ID + 76, );
        locations.put(MINDUSTRY_BASE_ID + 77, );
        locations.put(MINDUSTRY_BASE_ID + 78, );
        locations.put(MINDUSTRY_BASE_ID + 79, );
        locations.put(MINDUSTRY_BASE_ID + 80, );
        locations.put(MINDUSTRY_BASE_ID + 81, );
        locations.put(MINDUSTRY_BASE_ID + 82, );
        locations.put(MINDUSTRY_BASE_ID + 83, );
        locations.put(MINDUSTRY_BASE_ID + 84, );
        locations.put(MINDUSTRY_BASE_ID + 85, );
        locations.put(MINDUSTRY_BASE_ID + 86, );
        locations.put(MINDUSTRY_BASE_ID + 87, );
        locations.put(MINDUSTRY_BASE_ID + 88, );
        locations.put(MINDUSTRY_BASE_ID + 89, );
        locations.put(MINDUSTRY_BASE_ID + 90, );
        locations.put(MINDUSTRY_BASE_ID + 91, );
        locations.put(MINDUSTRY_BASE_ID + 92, );
        locations.put(MINDUSTRY_BASE_ID + 93, );
        locations.put(MINDUSTRY_BASE_ID + 94, );
        locations.put(MINDUSTRY_BASE_ID + 95, );
        locations.put(MINDUSTRY_BASE_ID + 96, );
        locations.put(MINDUSTRY_BASE_ID + 97, );
        locations.put(MINDUSTRY_BASE_ID + 98, );
        locations.put(MINDUSTRY_BASE_ID + 99, );
        locations.put(MINDUSTRY_BASE_ID + 100, );
        locations.put(MINDUSTRY_BASE_ID + 101, );
        locations.put(MINDUSTRY_BASE_ID + 102, );
        locations.put(MINDUSTRY_BASE_ID + 103, );
        locations.put(MINDUSTRY_BASE_ID + 104, );
        locations.put(MINDUSTRY_BASE_ID + 105, );
        locations.put(MINDUSTRY_BASE_ID + 106, );
        locations.put(MINDUSTRY_BASE_ID + 107, );
        locations.put(MINDUSTRY_BASE_ID + 108, );
        locations.put(MINDUSTRY_BASE_ID + 109, );
        locations.put(MINDUSTRY_BASE_ID + 110, );
        locations.put(MINDUSTRY_BASE_ID + 111, );
        locations.put(MINDUSTRY_BASE_ID + 112, );
        locations.put(MINDUSTRY_BASE_ID + 113, );
        locations.put(MINDUSTRY_BASE_ID + 114, );
        locations.put(MINDUSTRY_BASE_ID + 115, );
        locations.put(MINDUSTRY_BASE_ID + 116, );
        locations.put(MINDUSTRY_BASE_ID + 117, );
        locations.put(MINDUSTRY_BASE_ID + 118, );
        locations.put(MINDUSTRY_BASE_ID + 119, );
        locations.put(MINDUSTRY_BASE_ID + 120, );
        locations.put(MINDUSTRY_BASE_ID + 121, );
        locations.put(MINDUSTRY_BASE_ID + 122, );
        locations.put(MINDUSTRY_BASE_ID + 123, );
        locations.put(MINDUSTRY_BASE_ID + 124, );
        locations.put(MINDUSTRY_BASE_ID + 125, );
        locations.put(MINDUSTRY_BASE_ID + 126, );
        locations.put(MINDUSTRY_BASE_ID + 127, );
        locations.put(MINDUSTRY_BASE_ID + 128, );
        locations.put(MINDUSTRY_BASE_ID + 129, );
        locations.put(MINDUSTRY_BASE_ID + 130, );
        locations.put(MINDUSTRY_BASE_ID + 131, );
        locations.put(MINDUSTRY_BASE_ID + 132, );
        locations.put(MINDUSTRY_BASE_ID + 133, );
        locations.put(MINDUSTRY_BASE_ID + 134, );
        locations.put(MINDUSTRY_BASE_ID + 135, );
        locations.put(MINDUSTRY_BASE_ID + 136, );
        locations.put(MINDUSTRY_BASE_ID + 137, );
        locations.put(MINDUSTRY_BASE_ID + 138, );
        locations.put(MINDUSTRY_BASE_ID + 139, );
        locations.put(MINDUSTRY_BASE_ID + 140, );
        locations.put(MINDUSTRY_BASE_ID + 141, );
        locations.put(MINDUSTRY_BASE_ID + 142, );
        locations.put(MINDUSTRY_BASE_ID + 143, );
        locations.put(MINDUSTRY_BASE_ID + 144, );
        locations.put(MINDUSTRY_BASE_ID + 145, );
        locations.put(MINDUSTRY_BASE_ID + 146, );
        locations.put(MINDUSTRY_BASE_ID + 147, );
        locations.put(MINDUSTRY_BASE_ID + 148, );
        locations.put(MINDUSTRY_BASE_ID + 149, );
        locations.put(MINDUSTRY_BASE_ID + 150, );
        locations.put(MINDUSTRY_BASE_ID + 151, );
        locations.put(MINDUSTRY_BASE_ID + 152, );
        locations.put(MINDUSTRY_BASE_ID + 153, );
        locations.put(MINDUSTRY_BASE_ID + 154, );
        locations.put(MINDUSTRY_BASE_ID + 155, );
        locations.put(MINDUSTRY_BASE_ID + 156, );
        locations.put(MINDUSTRY_BASE_ID + 157, );
        locations.put(MINDUSTRY_BASE_ID + 158, );
        locations.put(MINDUSTRY_BASE_ID + 159, );
        locations.put(MINDUSTRY_BASE_ID + 160, );
        locations.put(MINDUSTRY_BASE_ID + 161, );
        locations.put(MINDUSTRY_BASE_ID + 162, );
        locations.put(MINDUSTRY_BASE_ID + 163, );
        locations.put(MINDUSTRY_BASE_ID + 164, );
        locations.put(MINDUSTRY_BASE_ID + 165, );
        locations.put(MINDUSTRY_BASE_ID + 166, );
        locations.put(MINDUSTRY_BASE_ID + 167, );
        locations.put(MINDUSTRY_BASE_ID + 168, );
        locations.put(MINDUSTRY_BASE_ID + 169, );
        locations.put(MINDUSTRY_BASE_ID + 170, );
        locations.put(MINDUSTRY_BASE_ID + 171, );
        locations.put(MINDUSTRY_BASE_ID + 172, );
        locations.put(MINDUSTRY_BASE_ID + 173, );
        locations.put(MINDUSTRY_BASE_ID + 174, );
        locations.put(MINDUSTRY_BASE_ID + 175, );
        locations.put(MINDUSTRY_BASE_ID + 176, );
        locations.put(MINDUSTRY_BASE_ID + 177, );
        locations.put(MINDUSTRY_BASE_ID + 178, );
        locations.put(MINDUSTRY_BASE_ID + 179, );
        locations.put(MINDUSTRY_BASE_ID + 180, );
        locations.put(MINDUSTRY_BASE_ID + 181, );
        locations.put(MINDUSTRY_BASE_ID + 182, );
        locations.put(MINDUSTRY_BASE_ID + 183, );
        locations.put(MINDUSTRY_BASE_ID + 184, );
        locations.put(MINDUSTRY_BASE_ID + 185, );
        locations.put(MINDUSTRY_BASE_ID + 186, );
        locations.put(MINDUSTRY_BASE_ID + 187, );
        locations.put(MINDUSTRY_BASE_ID + 188, );
        locations.put(MINDUSTRY_BASE_ID + 189, );
        locations.put(MINDUSTRY_BASE_ID + 190, );
        locations.put(MINDUSTRY_BASE_ID + 191, );
        locations.put(MINDUSTRY_BASE_ID + 192, );
        locations.put(MINDUSTRY_BASE_ID + 193, );
        locations.put(MINDUSTRY_BASE_ID + 194, );
        locations.put(MINDUSTRY_BASE_ID + 195, );
        locations.put(MINDUSTRY_BASE_ID + 196, );
        locations.put(MINDUSTRY_BASE_ID + 197, );
        locations.put(MINDUSTRY_BASE_ID + 198, );
        locations.put(MINDUSTRY_BASE_ID + 199, );
        */
    }

}
