package nova.wrapper.mc1710.forward.block;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import nova.core.block.Block;
import nova.core.util.components.Storable;
import nova.core.util.components.Updater;
import nova.core.util.transform.Vector3i;
import nova.wrapper.mc1710.backward.world.WorldWrapper;

import java.util.HashMap;
import java.util.Map;

/**
 * A Minecraft TileEntity to Nova block wrapper
 * @author Calclavia
 */
public class TileWrapper extends TileEntity {

	private Block block;

	@Override
	public void validate() {
		super.validate();
		block = ((BlockWrapper) getBlockType()).newBlockInstance(new WorldWrapper(worldObj), new Vector3i(xCoord, yCoord, zCoord));
	}

	/**
	 * Updates the block.
	 */
	@Override
	public void updateEntity() {
		((Updater) block).update(0.05);
	}

	/**
	 * Only register tile updates if the block is an instance of Tickable.
	 * @return
	 */
	@Override
	public boolean canUpdate() {
		return block instanceof Updater;
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);

		if (block instanceof Storable) {
			Map<String, Object> data = new HashMap<>();
			((Storable) block).saveToStore(data);
			//TODO: Actually write to NBT
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);

		if (block instanceof Storable) {
			Map<String, Object> data = new HashMap<>();
			((Storable) block).loadFromStore(data);
			//TODO: Actually read from NBT
		}
	}
}