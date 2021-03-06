/*
 * This file is part of aion-unique <aion-unique.com>.
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

import com.aionemu.commons.network.netty.packet.AbstractClientPacket;
import com.aionemu.gameserver.model.gameobjects.AionObject;
import com.aionemu.gameserver.model.gameobjects.interfaces.IDialogSelect;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.network.aion.AionChannelHandler;
import com.aionemu.gameserver.questEngine.QuestEngine;
import com.aionemu.gameserver.questEngine.model.QuestEnv;
import com.aionemu.gameserver.world.World;

/**
 * 
 * @author Lyahim, KKnD , orz, avol
 * 
 */
public class CM_DIALOG_SELECT extends AbstractClientPacket<AionChannelHandler>
{
	/**
	 * Target object id that client wants to TALK WITH or 0 if wants to unselect
	 */
	private int					targetObjectId;
	private int					dialogId;
	@SuppressWarnings("unused")
	private int					unk1;
	@SuppressWarnings("unused")
	private int					lastPage;
	private int					questId;

	@SuppressWarnings("unused")
	private static final Logger	log	= Logger.getLogger(CM_DIALOG_SELECT.class);

	/**
	 * Constructs new instance of <tt>CM_CM_REQUEST_DIALOG </tt> packet
	 * 
	 * @param opcode
	 */
	public CM_DIALOG_SELECT(int opcode)
	{
		super(opcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void readImpl()
	{
		targetObjectId = readD();// empty
		dialogId = readH(); // total no of choice
		unk1 = readH();
		lastPage = readH();
		questId = readD();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runImpl()
	{
		final Player player = getChannelHandler().getActivePlayer();
		if (player == null)
			return;

		if (targetObjectId == 0)
		{
			if (QuestEngine.getInstance().onDialog(new QuestEnv(null, player, questId, dialogId)))
				return;
		}

		AionObject object = World.getInstance().findAionObject(targetObjectId);

		if (object instanceof IDialogSelect)
			((IDialogSelect) object).onDialogSelect(dialogId, player, questId);
		//log.info("id: "+targetObjectId+" dialogId: " + dialogId +" unk1: " + unk1 + " questId: "+questId);
	}
}