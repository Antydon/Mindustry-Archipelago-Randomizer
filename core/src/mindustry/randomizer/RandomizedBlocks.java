package mindustry.randomizer;

import mindustry.content.Blocks;
import mindustry.world.Block;

import java.util.ArrayList;
import java.util.List;

public class RandomizedBlocks {

    /**
     * List of unspecified blocks to randomize for Serpulo.
     */
    private static List<Block> blocksSerpulo = new ArrayList<>();

    /**
     * List of unspecified blocks to randomize for Erekir.
     */
    private static List<Block> blocksErekir = new ArrayList<>();

    public static List<Block> getBlocksSerpulo() {
        if (blocksSerpulo.isEmpty()) {
            fillBlocksSerpuloList();
        }
        return new ArrayList<>(blocksSerpulo);
    }

    public static List<Block> getBlocksErekir() {
        if (blocksSerpulo.isEmpty()) {
            fillNormalBlocksErekirList();
        }
        return new ArrayList<>(blocksErekir);
    }

    /**
     * Add all the unspecified blocks to the Serpulo randomizer list.
     */
    private static void fillBlocksSerpuloList(){
        //Core -- Maybe
//        blocksSerpulo.add(Blocks.coreShard);
//        blocksSerpulo.add(Blocks.coreFoundation);
//        blocksSerpulo.add(Blocks.coreNucleus);
        //Rooting -- maybe
//        blocksSerpulo.add(Blocks.junction);
//        blocksSerpulo.add(Blocks.phaseConveyor);
//        blocksSerpulo.add(Blocks.sorter);
//        blocksSerpulo.add(Blocks.invertedSorter);
//        blocksSerpulo.add(Blocks.overflowGate);
//        blocksSerpulo.add(Blocks.underflowGate);
        blocksSerpulo.add(Blocks.massDriver);
        //Drills
        blocksSerpulo.add(Blocks.mechanicalDrill);
        blocksSerpulo.add(Blocks.pneumaticDrill);
        blocksSerpulo.add(Blocks.laserDrill);
        blocksSerpulo.add(Blocks.blastDrill);
        blocksSerpulo.add(Blocks.waterExtractor);
        blocksSerpulo.add(Blocks.cultivator);
        blocksSerpulo.add(Blocks.oilExtractor);
        //GenericCrafter
        blocksSerpulo.add(Blocks.graphitePress);
        blocksSerpulo.add(Blocks.multiPress);
        blocksSerpulo.add(Blocks.siliconSmelter);
        blocksSerpulo.add(Blocks.siliconCrucible);
        blocksSerpulo.add(Blocks.kiln);
        blocksSerpulo.add(Blocks.plastaniumCompressor);
        blocksSerpulo.add(Blocks.phaseWeaver);
        blocksSerpulo.add(Blocks.surgeSmelter);
        blocksSerpulo.add(Blocks.pulverizer);
        blocksSerpulo.add(Blocks.melter);
        blocksSerpulo.add(Blocks.separator);
        blocksSerpulo.add(Blocks.disassembler);
        blocksSerpulo.add(Blocks.sporePress);
        blocksSerpulo.add(Blocks.coalCentrifuge);
        blocksSerpulo.add(Blocks.cryofluidMixer);
        blocksSerpulo.add(Blocks.pyratiteMixer);
        blocksSerpulo.add(Blocks.blastMixer);
        blocksSerpulo.add(Blocks.incinerator);
        //Liquids
//        blocksSerpulo.add(Blocks.liquidRouter);// maybe
//        blocksSerpulo.add(Blocks.liquidJunction);// maybe
//        blocksSerpulo.add(Blocks.phaseConduit);// maybe
        blocksSerpulo.add(Blocks.liquidContainer);
        blocksSerpulo.add(Blocks.liquidTank);
        blocksSerpulo.add(Blocks.mechanicalPump);
        blocksSerpulo.add(Blocks.rotaryPump);
        blocksSerpulo.add(Blocks.impulsePump);
        //Power
        blocksSerpulo.add(Blocks.battery);
        blocksSerpulo.add(Blocks.batteryLarge);
        blocksSerpulo.add(Blocks.powerNode);
        blocksSerpulo.add(Blocks.powerNodeLarge);
        blocksSerpulo.add(Blocks.surgeTower);
        blocksSerpulo.add(Blocks.diode);
        blocksSerpulo.add(Blocks.combustionGenerator);
        blocksSerpulo.add(Blocks.thermalGenerator);
        blocksSerpulo.add(Blocks.steamGenerator);
        blocksSerpulo.add(Blocks.differentialGenerator);
        blocksSerpulo.add(Blocks.rtgGenerator);
        blocksSerpulo.add(Blocks.solarPanel);
        blocksSerpulo.add(Blocks.largeSolarPanel);
        blocksSerpulo.add(Blocks.thoriumReactor);
        blocksSerpulo.add(Blocks.impactReactor);
        //Walls
        blocksSerpulo.add(Blocks.copperWall);
        blocksSerpulo.add(Blocks.copperWallLarge);
        blocksSerpulo.add(Blocks.thoriumWall);
        blocksSerpulo.add(Blocks.thoriumWallLarge);
        blocksSerpulo.add(Blocks.plastaniumWall);
        blocksSerpulo.add(Blocks.plastaniumWallLarge);
        blocksSerpulo.add(Blocks.phaseWall);
        blocksSerpulo.add(Blocks.phaseWallLarge);
        blocksSerpulo.add(Blocks.surgeWall);
        blocksSerpulo.add(Blocks.surgeWallLarge);
        blocksSerpulo.add(Blocks.door);
        blocksSerpulo.add(Blocks.doorLarge);
        //Unobtanable Wall for giggles
//        normalBlocksSerpulo.add(Blocks.scrapWall);
//        normalBlocksSerpulo.add(Blocks.scrapWallLarge);
//        normalBlocksSerpulo.add(Blocks.scrapWallHuge);
//        normalBlocksSerpulo.add(Blocks.scrapWallGigantic);
//        normalBlocksSerpulo.add(Blocks.titaniumWall);
        //turrets
        blocksSerpulo.add(Blocks.duo);
        blocksSerpulo.add(Blocks.scatter);
        blocksSerpulo.add(Blocks.scorch);
        blocksSerpulo.add(Blocks.hail);
        blocksSerpulo.add(Blocks.wave);
        blocksSerpulo.add(Blocks.lancer);
        blocksSerpulo.add(Blocks.arc);
        blocksSerpulo.add(Blocks.parallax);
        blocksSerpulo.add(Blocks.swarmer);
        blocksSerpulo.add(Blocks.salvo);
        blocksSerpulo.add(Blocks.segment);
        blocksSerpulo.add(Blocks.tsunami);
        blocksSerpulo.add(Blocks.fuse);
        blocksSerpulo.add(Blocks.ripple);
        blocksSerpulo.add(Blocks.cyclone);
        blocksSerpulo.add(Blocks.foreshadow);
        blocksSerpulo.add(Blocks.spectre);
        blocksSerpulo.add(Blocks.meltdown);
        //Factories
        blocksSerpulo.add(Blocks.groundFactory);
        blocksSerpulo.add(Blocks.airFactory);
        blocksSerpulo.add(Blocks.navalFactory);
        blocksSerpulo.add(Blocks.additiveReconstructor);
        blocksSerpulo.add(Blocks.multiplicativeReconstructor);
        blocksSerpulo.add(Blocks.exponentialReconstructor);
        blocksSerpulo.add(Blocks.tetrativeReconstructor);
        blocksSerpulo.add(Blocks.repairPoint);
        blocksSerpulo.add(Blocks.repairTurret);
        //Logic
        blocksSerpulo.add(Blocks.microProcessor);
        blocksSerpulo.add(Blocks.logicProcessor);
        blocksSerpulo.add(Blocks.hyperProcessor);
        blocksSerpulo.add(Blocks.message);
        blocksSerpulo.add(Blocks.switchBlock);
        blocksSerpulo.add(Blocks.memoryCell);
        blocksSerpulo.add(Blocks.memoryBank);
        blocksSerpulo.add(Blocks.logicDisplay);
        blocksSerpulo.add(Blocks.largeLogicDisplay);
        //Miscellaneous
        blocksSerpulo.add(Blocks.mender);
        blocksSerpulo.add(Blocks.mendProjector);
        blocksSerpulo.add(Blocks.overdriveProjector);
        blocksSerpulo.add(Blocks.overdriveDome);
        blocksSerpulo.add(Blocks.forceProjector);
        blocksSerpulo.add(Blocks.container);
        blocksSerpulo.add(Blocks.vault);
        blocksSerpulo.add(Blocks.unloader);//maybe
        blocksSerpulo.add(Blocks.shockMine);
        blocksSerpulo.add(Blocks.illuminator);
        blocksSerpulo.add(Blocks.launchPad);
        //Payload Conveyor -- Maybe
//        blocksSerpulo.add(Blocks.payloadConveyor);
//        blocksSerpulo.add(Blocks.payloadRouter);
    }

    /**
     * Add all the unspecified blocks to the Erekir randomizer list.
     */
    private static void fillNormalBlocksErekirList() {
        blocksErekir.add(Blocks.berylliumWall);
    }
}
