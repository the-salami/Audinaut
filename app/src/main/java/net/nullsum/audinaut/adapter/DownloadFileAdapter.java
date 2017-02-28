/*
  This file is part of Subsonic.
	Subsonic is free software: you can redistribute it and/or modify
	it under the terms of the GNU General Public License as published by
	the Free Software Foundation, either version 3 of the License, or
	(at your option) any later version.
	Subsonic is distributed in the hope that it will be useful,
	but WITHOUT ANY WARRANTY; without even the implied warranty of
	MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
	GNU General Public License for more details.
	You should have received a copy of the GNU General Public License
	along with Subsonic. If not, see <http://www.gnu.org/licenses/>.
	Copyright 2014 (C) Scott Jackson
*/

package net.nullsum.audinaut.adapter;

import android.content.Context;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.List;

import net.nullsum.audinaut.R;
import net.nullsum.audinaut.service.DownloadFile;
import net.nullsum.audinaut.util.Util;
import net.nullsum.audinaut.view.FastScroller;
import net.nullsum.audinaut.view.SongView;
import net.nullsum.audinaut.view.UpdateView;

public class DownloadFileAdapter extends SectionAdapter<DownloadFile> implements FastScroller.BubbleTextGetter {
	public static int VIEW_TYPE_DOWNLOAD_FILE = 1;

	public DownloadFileAdapter(Context context, List<DownloadFile> entries, OnItemClickedListener onItemClickedListener) {
		super(context, entries);
		this.onItemClickedListener = onItemClickedListener;
		this.checkable = true;
	}

	@Override
	public UpdateView.UpdateViewHolder onCreateSectionViewHolder(ViewGroup parent, int viewType) {
		return new UpdateView.UpdateViewHolder(new SongView(context));
	}

	@Override
	public void onBindViewHolder(UpdateView.UpdateViewHolder holder, DownloadFile item, int viewType) {
		SongView songView = (SongView) holder.getUpdateView();
		songView.setObject(item.getSong(), Util.isBatchMode(context));
		songView.setDownloadFile(item);
	}

	@Override
	public int getItemViewType(DownloadFile item) {
		return VIEW_TYPE_DOWNLOAD_FILE;
	}

	@Override
	public String getTextToShowInBubble(int position) {
		return null;
	}

	@Override
	public void onCreateActionModeMenu(Menu menu, MenuInflater menuInflater) {
		if(Util.isOffline(context)) {
			menuInflater.inflate(R.menu.multiselect_nowplaying_offline, menu);
		} else {
			menuInflater.inflate(R.menu.multiselect_nowplaying, menu);
		}
	}
}