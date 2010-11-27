/*
 * This file is part of aion-unique <aion-unique.smfnew.com>.
 *
 *  aion-unique is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  aion-unique is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AbstractAionServerPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;

/**
 * @author Lyahim, Lyahim
 *
 */
public class SM_SHOW_NPC_ON_MAP extends AbstractAionServerPacket<AionChannelHandler>
{
	private int	npcid, worldid;
	private float	x, y, z;

	public SM_SHOW_NPC_ON_MAP(int npcid, int worldid, float x, float y, float z)
	{
		this.npcid = npcid;
		this.worldid = worldid;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	@Override
	protected void writeImpl(AionChannelHandler cHandler)
	{
		writeD(this.npcid);
		writeD(this.worldid);
		writeD(this.worldid);
		writeF(this.x);
		writeF(this.y);
		writeF(this.z);
	}
}
