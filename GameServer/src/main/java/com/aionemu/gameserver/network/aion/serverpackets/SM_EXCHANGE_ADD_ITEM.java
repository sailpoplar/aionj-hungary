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
package com.aionemu.gameserver.network.aion.serverpackets;

import com.aionemu.gameserver.model.gameobjects.Item;
import com.aionemu.gameserver.model.items.ItemId;
import com.aionemu.gameserver.model.templates.item.ItemTemplate;
import com.aionemu.gameserver.network.aion.AionChannelHandler;

/**
 * @author Lyahim, Avol
 * @author Lyahim, ATracer
 * 
 */
public class SM_EXCHANGE_ADD_ITEM extends _InventoryPacket
{
	private int		action;
	private Item	item;

	public SM_EXCHANGE_ADD_ITEM(int action, Item item)
	{
		this.action = action;
		this.item = item;
	}

	@Override
	protected void writeImpl(AionChannelHandler cHandler)
	{

		writeC(action); // 0 -self 1-other

		writeGeneralInfo(item);

		ItemTemplate itemTemplate = item.getItemTemplate();

		if (itemTemplate.getTemplateId() == ItemId.KINAH.value())
		{
			writeKinah(item, true);
		}
		else if (itemTemplate.isWeapon())
		{
			writeWeaponInfo(item, true);
		}
		else if (itemTemplate.isArmor())
		{
			writeArmorInfo(item, true, false, false);
		}
		else
		{
			writeGeneralItemInfo(item, false, false);
			writeC(0);
		}
	}

	@Override
	protected void writeGeneralInfo(Item item)
	{
		ItemTemplate itemTemplate = item.getItemTemplate();
		writeD(itemTemplate.getTemplateId());
		writeD(item.getObjectId());
		writeH(0x24);
		writeD(itemTemplate.getNameId());
		writeH(0);
	}
}