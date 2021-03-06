/**
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

import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.network.aion.AbstractAionServerPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;

/**
 * 
 * @author Lyahim, alexa026
 * @author Lyahim, ATracer
 * 
 */
public class SM_ATTACK_STATUS extends AbstractAionServerPacket<AionChannelHandler>
{
	private Creature	creature;
	private TYPE		type;
	private int			skillId;
	private int			value;

	public static enum TYPE
	{
		NATURAL_HP(3), REGULAR(5), DAMAGE(7), HP(7), MP(21), NATURAL_MP(22), FP_RINGS(23), FP(25), NATURAL_FP(26);

		private int	value;

		private TYPE(int value)
		{
			this.value = value;
		}

		public int getValue()
		{
			return this.value;
		}
	}

	public SM_ATTACK_STATUS(Creature creature, TYPE type, int skillId, int value)
	{
		this.creature = creature;
		this.type = type;
		this.skillId = skillId;
		this.value = value;
	}

	public SM_ATTACK_STATUS(Creature creature, int value)
	{
		this.creature = creature;
		this.type = TYPE.REGULAR;
		this.skillId = 0;
	}

	/**
	 * {@inheritDoc} ddchcc
	 */

	@Override
	protected void writeImpl(AionChannelHandler cHandler)
	{
		writeD(creature.getObjectId());
		switch (type)
		{
			case DAMAGE:
				writeD(-value);
				break;
			default:
				writeD(value);
		}
		writeC(type.getValue());
		writeC(creature.getLifeStats().getHpPercentage());
		writeH(skillId);
		writeH(0xA6);
	}
}
