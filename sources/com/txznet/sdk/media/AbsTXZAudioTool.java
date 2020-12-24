package com.txznet.sdk.media;

import com.txznet.comm.Tr.Tn;
import com.txznet.comm.Ty.Tr;

/* compiled from: Proguard */
public abstract class AbsTXZAudioTool implements ITXZMediaTool {
    public final void onPlayerStatusChanged(PlayerStatus status) {
        Tr builder = new Tr();
        builder.T("status", (Object) status.toStatusString());
        Tn.Tr().T("com.txznet.txz", "txz.audio.sdk.status_notify", builder.Ty(), (Tn.Tr) null);
    }

    public final void onPlayingModelChanged(TXZMediaModel model) {
        Tr builder = new Tr();
        builder.T("model", (Object) model.toJsonObject());
        Tn.Tr().T("com.txznet.txz", "txz.audio.sdk.playing_model", builder.Ty(), (Tn.Tr) null);
    }
}
