/**
 * This file is part of aion-emu <aion-emu.com>.
 *
 *  aion-emu is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-emu is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-emu.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AbstractAionServerPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;

/**
 * @author Lyahim, Sweetkr
 */
public class SM_TARGET_SELECTED extends AbstractAionServerPacket<AionChannelHandler>
{
	@SuppressWarnings("unused")
	private Player	player;
	private int		level;
	private int		maxHp;
	private int		currentHp;
	private int		targetObjId;

	public SM_TARGET_SELECTED(Player player)
	{
		this.player = player;
		if (player.getTarget() instanceof Creature)
		{
			this.level = ((Creature) player.getTarget()).getLevel();
			this.maxHp = ((Creature) player.getTarget()).getLifeStats().getMaxHp();
			this.currentHp = ((Creature) player.getTarget()).getLifeStats().getCurrentHp();
		}
		else
		{
			//TODO: check various gather on retail
			this.level = 1;
			this.maxHp = 1;
			this.currentHp = 1;
		}

		if (player.getTarget() != null)
			targetObjId = player.getTarget().getObjectId();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionChannelHandler cHandler)
	{
		writeD(targetObjId);
		writeH(level);
		writeD(maxHp);
		writeD(currentHp);
	}
}
