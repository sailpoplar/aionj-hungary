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
package com.aionemu.gameserver.network.aion.clientpackets;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.dataholders.QuestsData;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.commons.network.netty.packet.AbstractClientPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;
import com.aionemu.gameserver.network.aion.serverpackets.SM_QUEST_ACCEPTED;
import com.aionemu.gameserver.questEngine.QuestEngine;

public class CM_DELETE_QUEST extends AbstractClientPacket<AionChannelHandler>
{

	static QuestsData	questsData	= DataManager.QUEST_DATA;
	public int			questId;

	public CM_DELETE_QUEST(int opcode)
	{
		super(opcode);
	}

	@Override
	protected void readImpl()
	{
		questId = readH();
	}

	@Override
	protected void runImpl()
	{
		Player player = getChannelHandler().getActivePlayer();
		if (questsData.getQuestById(questId).isTimer())
		{
			player.cancelTask(TaskId.QUEST_TIMER);
			sendPacket(new SM_QUEST_ACCEPTED(questId, 0));
		}
		if (!QuestEngine.getInstance().deleteQuest(player, questId))
			return;
		sendPacket(new SM_QUEST_ACCEPTED(questId));
		player.updateNearbyQuests();
	}
}
