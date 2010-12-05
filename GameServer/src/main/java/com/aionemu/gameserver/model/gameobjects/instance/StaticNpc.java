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
package com.aionemu.gameserver.model.gameobjects.instance;

import java.util.concurrent.Future;

import com.aionemu.gameserver.dataholders.DataManager;
import com.aionemu.gameserver.model.EmotionType;
import com.aionemu.gameserver.model.TaskId;
import com.aionemu.gameserver.model.gameobjects.Creature;
import com.aionemu.gameserver.model.gameobjects.VisibleObject;
import com.aionemu.gameserver.model.gameobjects.interfaces.IReward;
import com.aionemu.gameserver.model.gameobjects.knownList.StaticObjectKnownList;
import com.aionemu.gameserver.model.gameobjects.player.Player;
import com.aionemu.gameserver.model.gameobjects.state.CreatureState;
import com.aionemu.gameserver.model.gameobjects.state.CreatureVisualState;
import com.aionemu.gameserver.model.gameobjects.stats.StaticNpcStats;
import com.aionemu.gameserver.model.templates.NpcTemplate;
import com.aionemu.gameserver.model.templates.spawn.SpawnTemplate;
import com.aionemu.gameserver.network.aion.serverpackets.SM_EMOTION;
import com.aionemu.gameserver.services.RespawnService;
import com.aionemu.gameserver.utils.PacketSendUtility;

/**
 * @author Mr. Poke
 *
 */
public class StaticNpc extends VisibleObject
{

	private int						state		= CreatureState.ACTIVE.getId();
	private int						visualState	= CreatureVisualState.VISIBLE.getId();
	private final StaticNpcStats	stats		= new StaticNpcStats();

	/**
	 * @param objId
	 * @param spawnTemplate
	 * @param position
	 */
	public StaticNpc(int objId, SpawnTemplate spawnTemplate)
	{
		super(objId, spawnTemplate);
		if (spawnTemplate != null)
			this.objectTemplate = DataManager.NPC_DATA.getNpcTemplate(spawnTemplate.getTemplateId());
		this.setKnownlist(new StaticObjectKnownList(this));

	}

	@Override
	public String getName()
	{
		return objectTemplate.getName();
	}

	@Override
	public NpcTemplate getObjectTemplate()
	{
		return (NpcTemplate) super.getObjectTemplate();
	}

	/**
	 * @return Returns the stats.
	 */
	public StaticNpcStats getStats()
	{
		return stats;
	}

	/**
	 * @return state
	 */
	public int getState()
	{
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(CreatureState state)
	{
		this.state |= state.getId();
	}

	/** 
	 * @param state taken usually from templates
	 */
	public void setState(int state)
	{
		this.state = state;
	}

	public void unsetState(CreatureState state)
	{
		this.state &= ~state.getId();
	}

	public boolean isInState(CreatureState state)
	{
		int isState = this.state & state.getId();

		if (isState == state.getId())
			return true;

		return false;
	}

	/**
	 * @return visualState
	 */
	public int getVisualState()
	{
		return visualState;
	}

	/**
	 * @param visualState the visualState to set
	 */
	public void setVisualState(CreatureVisualState visualState)
	{
		this.visualState |= visualState.getId();
	}

	public void unsetVisualState(CreatureVisualState visualState)
	{
		this.visualState &= ~visualState.getId();
	}

	public boolean isInVisualState(CreatureVisualState visualState)
	{
		int isVisualState = this.visualState & visualState.getId();

		if (isVisualState == visualState.getId())
			return true;

		return false;
	}

	public byte getLevel()
	{
		return this.getObjectTemplate().getLevel();
	}

	/**
	 * Perform tasks on Creature death
	 */
	public void onDie(Creature lastAttacker)
	{
		this.setState(CreatureState.DEAD);

		if (this instanceof IReward)
			((IReward) this).doReward();

		if (this instanceof Player)
			PacketSendUtility.broadcastPacket((Player) this, new SM_EMOTION(this, EmotionType.DIE, 0, lastAttacker == null ? 0 : lastAttacker.getObjectId()),
					true);
		else
			PacketSendUtility.broadcastPacket(this, new SM_EMOTION(this, EmotionType.DIE, 0, lastAttacker == null ? 0 : lastAttacker.getObjectId()));

	}

	/**
	 * Schedule respawn of npc
	 * In instances - no npc respawn
	 */
	public void scheduleRespawn()
	{
		if (isInInstance())
			return;

		if (getSpawn().getInterval() > 0)
		{
			Future<?> respawnTask = RespawnService.scheduleRespawnTask(this);
			addTask(TaskId.RESPAWN, respawnTask);
		}
	}
}
