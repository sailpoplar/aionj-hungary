/*
 * This file is part of aion-unique <aionu-unique.org>.
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
import com.aionemu.gameserver.questEngine.model.QuestStatus;

/**
 * @author Lyahim, MrPoke
 * 
 */
public class SM_QUEST_STEP extends AbstractAionServerPacket<AionChannelHandler>
{
	private int	questId;
	private int	status;
	private int	vars;

	public SM_QUEST_STEP(int questId, QuestStatus status, int vars)
	{
		this.questId = questId;
		this.status = status.value();
		this.vars = vars;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void writeImpl(AionChannelHandler cHandler)
	{
		writeH(questId);
		writeC(status);
		writeD(vars);
		writeC(0);
	}

}
