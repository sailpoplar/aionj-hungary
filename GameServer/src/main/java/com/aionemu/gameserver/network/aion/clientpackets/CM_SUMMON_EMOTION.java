/*
 * This file is part of aion-unique <aion-unique.org>.
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

import org.apache.log4j.Logger;

import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.gameobjects.instance.Summon;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.state.CreatureState;
import com.aionemu.commons.network.netty.packet.AbstractClientPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author Lyahim, ATracer
 *
 */
public class CM_SUMMON_EMOTION extends AbstractClientPacket<AionChannelHandler>
{
	private static final Logger	log	= Logger.getLogger(CM_SUMMON_EMOTION.class);

	@SuppressWarnings("unused")
	private int					objId;

	private int					emotionTypeId;

	public CM_SUMMON_EMOTION(int opcode)
	{
		super(opcode);
	}

	@Override
	protected void readImpl()
	{
		objId = readD();
		emotionTypeId = readC();
	}

	@Override
	protected void runImpl()
	{
		EmotionType emotionType = EmotionType.getEmotionTypeById(emotionTypeId);

		// Unknown Summon Emotion Type
		if (emotionType == EmotionType.UNK)
			log.error("Unknown emotion type? 0x" + Integer.toHexString(emotionTypeId).toUpperCase());

		Player activePlayer = getChannelHandler().getActivePlayer();
		if (activePlayer == null)
			return;

		Summon summon = activePlayer.getSummon();
		if (summon == null)
			return;

		switch (emotionType)
		{
			case FLY:
			case LAND:
				PacketSendUtility.broadcastPacket(summon, new SM_EMOTION(summon, emotionType));
				break;
			case ATTACKMODE: //start attacking
				summon.setState(CreatureState.WEAPON_EQUIPPED);
				PacketSendUtility.broadcastPacket(summon, new SM_EMOTION(summon, emotionType));
				break;
			case NEUTRALMODE: //stop attacking
				summon.unsetState(CreatureState.WEAPON_EQUIPPED);
				PacketSendUtility.broadcastPacket(summon, new SM_EMOTION(summon, emotionType));
				break;
		}
	}
}
