/*
 * This file is part of aion-unique <www.aion-unique.com>.
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

import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.commons.network.netty.packet.AbstractClientPacket;
import com.aionemu.gameserver.network.aion.AionChannelHandler;
import com.aionemu.gameserver.services.ExchangeService;

/**
 * 
 * @author Lyahim, Avol
 * 
 */
public class CM_EXCHANGE_ADD_KINAH extends AbstractClientPacket<AionChannelHandler>
{
	public int	unk;
	public int	itemCount;

	public CM_EXCHANGE_ADD_KINAH(int opcode)
	{
		super(opcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void readImpl()
	{
		itemCount = readD();
		unk = readD();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runImpl()
	{
		final Player activePlayer = getChannelHandler().getActivePlayer();
		ExchangeService.getInstance().addKinah(activePlayer, itemCount);
	}
}
