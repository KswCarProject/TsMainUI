package com.hongfans.carmedia;

import com.hongfans.rearview.services.api.ProgramDigtalModel;
import java.util.List;

public interface OnPlayChangedListener {
    void OnPlayListChanged(List<ProgramDigtalModel> list);

    void OnPlayMusicChanged(ProgramDigtalModel programDigtalModel);

    void OnPlayStateChanged(int i);
}
