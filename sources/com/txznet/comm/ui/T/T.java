package com.txznet.comm.ui.T;

import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import com.txznet.comm.Tr.Tr.Tn;
import com.txznet.comm.ui.TE.Ty;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* compiled from: Proguard */
public class T extends BaseAdapter {

    /* renamed from: T  reason: collision with root package name */
    List<View> f421T = new ArrayList();
    private View Tn;
    private Animation Tr = Ty.Tr();
    private Map<Integer, Boolean> Ty = new HashMap();

    public int getCount() {
        return this.f421T.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = this.f421T.get(position);
        boolean animation = com.txznet.comm.ui.TE.T.Tr(true);
        boolean animationFirst = com.txznet.comm.ui.TE.T.Ty(true);
        if (position == 0) {
            if (animationFirst) {
                T(position, view);
            }
            Tn.T("chat_list_animation_first::" + animationFirst);
        } else {
            if (animation) {
                T(position, view);
            }
            Tn.T("chat_list_animation::" + animation);
        }
        return view;
    }

    public void T(View view) {
        this.f421T.add(view);
        notifyDataSetChanged();
    }

    public void T() {
        this.f421T = new ArrayList();
        notifyDataSetChanged();
        this.Ty.clear();
    }

    private void T(int position, View convertView) {
        if (this.Tn != null) {
            this.Tn.getAnimation().cancel();
        }
        if ((this.Ty.get(Integer.valueOf(position)) == null || this.Ty.get(Integer.valueOf(position)).booleanValue()) && position == this.f421T.size() - 1) {
            convertView.startAnimation(this.Tr);
            this.Ty.put(Integer.valueOf(position), false);
        }
    }

    public void Tr() {
        if (this.f421T.size() > 0) {
            this.f421T.remove(this.f421T.size() - 1);
            notifyDataSetChanged();
        }
    }
}
