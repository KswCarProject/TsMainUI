package com.hongfans.carmedia.processes;

import android.content.Context;
import android.os.AsyncTask;
import com.hongfans.carmedia.processes.models.AndroidAppProcess;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AndroidAppProcessLoader extends AsyncTask<Void, Void, List<AndroidAppProcess>> {
    /* access modifiers changed from: private */
    public final Context context;
    private final Listener listener;

    public interface Listener {
        void onComplete(List<AndroidAppProcess> list);
    }

    public AndroidAppProcessLoader(Context context2, Listener listener2) {
        this.context = context2.getApplicationContext();
        this.listener = listener2;
    }

    /* access modifiers changed from: protected */
    public List<AndroidAppProcess> doInBackground(Void... params) {
        List<AndroidAppProcess> processes = AndroidProcesses.getRunningAppProcesses();
        Collections.sort(processes, new Comparator<AndroidAppProcess>() {
            public int compare(AndroidAppProcess lhs, AndroidAppProcess rhs) {
                return Utils.getName(AndroidAppProcessLoader.this.context, lhs).compareToIgnoreCase(Utils.getName(AndroidAppProcessLoader.this.context, rhs));
            }
        });
        return processes;
    }

    /* access modifiers changed from: protected */
    public void onPostExecute(List<AndroidAppProcess> androidAppProcesses) {
        this.listener.onComplete(androidAppProcesses);
    }
}
