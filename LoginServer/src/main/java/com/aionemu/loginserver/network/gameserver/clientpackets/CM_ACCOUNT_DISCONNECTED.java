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

package com.aionemu.loginserver.network.gameserver.clientpackets;

import com.aionemu.commons.network.netty.packet.AbstractClientPacket;
import com.aionemu.loginserver.controller.AccountTimeController;
import com.aionemu.loginserver.model.Account;
import com.aionemu.loginserver.network.gameserver.GameServerChannelHandler;

/**
 * In this packet GameServer is informing LoginServer that some account is no
 * longer on GameServer [ie was disconencted]
 * 
 * @author -Nemesiss-
 * 
 */
public class CM_ACCOUNT_DISCONNECTED extends AbstractClientPacket<GameServerChannelHandler>
{
	/**
	 * AccountId of account that was disconnected form GameServer.
	 */
	private int	accountId;

	/**
	 * Constructor.
	 * 
	 * @param buf
	 * @param client
	 */
	public CM_ACCOUNT_DISCONNECTED(int opcode)
	{
		super(opcode);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void readImpl()
	{
		accountId = readD();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected void runImpl()
	{
		Account account = getChannelHandler().getGameServerInfo().removeAccountFromGameServer(accountId);

		/**
		 * account can be null if a player logged out from gs
		 * {@link CM_ACCOUNT_RECONNECT_KEY 
		 */
		if (account != null)
		{
			AccountTimeController.updateOnLogout(account);
		}
	}
}
