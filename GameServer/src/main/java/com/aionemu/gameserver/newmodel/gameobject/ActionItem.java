/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, 
 * MA  02110-1301, USA.
 *
 * http://www.gnu.org/copyleft/gpl.html
 */
package com.aionemu.gameserver.newmodel.gameobject;

import com.aionemu.gameserver.newmodel.gameobject.interfaces.IDialogRequest;
import com.aionemu.gameserver.newmodel.gameobject.interfaces.IReward;
import com.aionemu.gameserver.newmodel.gameobject.player.Player;

/**
 * @author lyahim
 *
 */
public final class ActionItem extends SpawnedObject<ActionItem> implements IDialogRequest, IReward
{
	
	private Player lastActor = null;

	public ActionItem(Integer objId)
	{
		super(objId, null);
	}

	@Override
	public void doReward(com.aionemu.gameserver.newmodel.gameobject.player.Player player)
	{
		if (lastActor == null)
			return;
		
//		DropService.getInstance().registerDrop(getOwner() , lastActor, lastActor.getLevel());
//		DropService.getInstance().requestDropList(lastActor, getOwner().getObjectId());
		
		lastActor = null;
	}

	@Override
	public void onDialogRequest(com.aionemu.gameserver.newmodel.gameobject.player.Player player)
	{
	}
}