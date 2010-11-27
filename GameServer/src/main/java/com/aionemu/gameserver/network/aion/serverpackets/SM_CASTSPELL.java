/*
 * This file is part of aion-unique <aionunique.smfnew.com>.
 *
 * aion-unique is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * aion-unique is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with aion-unique.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.network.aion.AbstractAionServerPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;

/**
 * @author Lyahim, alexa026
 * @author Lyahim, rhys2002
 */
public class SM_CASTSPELL extends AbstractAionServerPacket<AionChannelHandler>
{
	private int		attackerObjectId;
	private int		spellId;
	private int		level;
	private int		targetType;
	private int		duration;

	private int		targetObjectId;

	private float	x;
	private float	y;
	private float	z;

	public SM_CASTSPELL(int attackerObjectId, int spellId, int level, int targetType, int targetObjectId, int duration)
	{
		this.attackerObjectId = attackerObjectId;
		this.spellId = spellId;
		this.level = level;
		this.targetType = targetType;
		this.targetObjectId = targetObjectId;
		this.duration = duration;
	}

	public SM_CASTSPELL(int attackerObjectId, int spellId, int level, int targetType, float x, float y, float z, int duration)
	{
		this(attackerObjectId, spellId, level, targetType, 0, duration);
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionChannelHandler cHandler)
	{
		writeD(attackerObjectId);
		writeH(spellId);
		writeC(level);

		writeC(targetType);
		switch (targetType)
		{
			case 0:
				writeD(targetObjectId);
				break;
			case 1:
				writeF(x);
				writeF(y);
				writeF(z);
				break;
		}

		writeH(duration);
		writeD(0);
	}
}
