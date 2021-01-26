package com.autochips.camera;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import com.autochips.camera.util.DVRConst;
import com.txznet.sdk.TXZResourceManager;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Vector;

public class StateMachine {
    public static final boolean HANDLED = true;
    public static final boolean NOT_HANDLED = false;
    private static final int SM_INIT_CMD = -2;
    private static final int SM_QUIT_CMD = -1;
    /* access modifiers changed from: private */
    public String mName;
    /* access modifiers changed from: private */
    public SmHandler mSmHandler;
    /* access modifiers changed from: private */
    public HandlerThread mSmThread;

    public static class LogRec {
        private IState mDstState;
        private String mInfo;
        private IState mOrgState;
        private StateMachine mSm;
        private IState mState;
        private long mTime;
        private int mWhat;

        LogRec(StateMachine sm, Message msg, String info, IState state, IState orgState, IState transToState) {
            update(sm, msg, info, state, orgState, transToState);
        }

        public void update(StateMachine sm, Message msg, String info, IState state, IState orgState, IState dstState) {
            this.mSm = sm;
            this.mTime = System.currentTimeMillis();
            this.mWhat = msg != null ? msg.what : 0;
            this.mInfo = info;
            this.mState = state;
            this.mOrgState = orgState;
            this.mDstState = dstState;
        }

        public long getTime() {
            return this.mTime;
        }

        public long getWhat() {
            return (long) this.mWhat;
        }

        public String getInfo() {
            return this.mInfo;
        }

        public IState getState() {
            return this.mState;
        }

        public IState getDestState() {
            return this.mDstState;
        }

        public IState getOriginalState() {
            return this.mOrgState;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("time=");
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(this.mTime);
            sb.append(String.format("%tm-%td %tH:%tM:%tS.%tL", new Object[]{c, c, c, c, c, c}));
            sb.append(" processed=");
            sb.append(this.mState == null ? "<null>" : this.mState.getName());
            sb.append(" org=");
            sb.append(this.mOrgState == null ? "<null>" : this.mOrgState.getName());
            sb.append(" dest=");
            sb.append(this.mDstState == null ? "<null>" : this.mDstState.getName());
            sb.append(" what=");
            String what = this.mSm != null ? this.mSm.getWhatToString(this.mWhat) : TXZResourceManager.STYLE_DEFAULT;
            if (TextUtils.isEmpty(what)) {
                sb.append(this.mWhat);
                sb.append("(0x");
                sb.append(Integer.toHexString(this.mWhat));
                sb.append(")");
            } else {
                sb.append(what);
            }
            if (!TextUtils.isEmpty(this.mInfo)) {
                sb.append(" ");
                sb.append(this.mInfo);
            }
            return sb.toString();
        }
    }

    private static class LogRecords {
        private static final int DEFAULT_SIZE = 20;
        private int mCount;
        private boolean mLogOnlyTransitions;
        /* access modifiers changed from: private */
        public Vector<LogRec> mLogRecVector;
        /* access modifiers changed from: private */
        public int mMaxSize;
        private int mOldestIndex;

        private LogRecords() {
            this.mLogRecVector = new Vector<>();
            this.mMaxSize = 20;
            this.mOldestIndex = 0;
            this.mCount = 0;
            this.mLogOnlyTransitions = false;
        }

        /* synthetic */ LogRecords(LogRecords logRecords) {
            this();
        }

        /* access modifiers changed from: package-private */
        public synchronized void setSize(int maxSize) {
            this.mMaxSize = maxSize;
            this.mOldestIndex = 0;
            this.mCount = 0;
            this.mLogRecVector.clear();
        }

        /* access modifiers changed from: package-private */
        public synchronized void setLogOnlyTransitions(boolean enable) {
            this.mLogOnlyTransitions = enable;
        }

        /* access modifiers changed from: package-private */
        public synchronized boolean logOnlyTransitions() {
            return this.mLogOnlyTransitions;
        }

        /* access modifiers changed from: package-private */
        public synchronized int size() {
            return this.mLogRecVector.size();
        }

        /* access modifiers changed from: package-private */
        public synchronized int count() {
            return this.mCount;
        }

        /* access modifiers changed from: package-private */
        public synchronized void cleanup() {
            this.mLogRecVector.clear();
        }

        /* access modifiers changed from: package-private */
        public synchronized LogRec get(int index) {
            LogRec logRec;
            int nextIndex = this.mOldestIndex + index;
            if (nextIndex >= this.mMaxSize) {
                nextIndex -= this.mMaxSize;
            }
            if (nextIndex >= size()) {
                logRec = null;
            } else {
                logRec = this.mLogRecVector.get(nextIndex);
            }
            return logRec;
        }

        /* access modifiers changed from: package-private */
        public synchronized void add(StateMachine sm, Message msg, String messageInfo, IState state, IState orgState, IState transToState) {
            this.mCount++;
            if (this.mLogRecVector.size() < this.mMaxSize) {
                this.mLogRecVector.add(new LogRec(sm, msg, messageInfo, state, orgState, transToState));
            } else {
                LogRec pmi = this.mLogRecVector.get(this.mOldestIndex);
                this.mOldestIndex++;
                if (this.mOldestIndex >= this.mMaxSize) {
                    this.mOldestIndex = 0;
                }
                pmi.update(sm, msg, messageInfo, state, orgState, transToState);
            }
        }
    }

    private static class SmHandler extends Handler {
        private static final Object mSmHandlerObj = new Object();
        /* access modifiers changed from: private */
        public boolean mDbg;
        /* access modifiers changed from: private */
        public ArrayList<Message> mDeferredMessages;
        /* access modifiers changed from: private */
        public State mDestState;
        /* access modifiers changed from: private */
        public HaltingState mHaltingState;
        private boolean mHasQuit;
        private State mInitialState;
        private boolean mIsConstructionCompleted;
        /* access modifiers changed from: private */
        public LogRecords mLogRecords;
        private Message mMsg;
        private QuittingState mQuittingState;
        /* access modifiers changed from: private */
        public StateMachine mSm;
        private HashMap<State, StateInfo> mStateInfo;
        /* access modifiers changed from: private */
        public StateInfo[] mStateStack;
        /* access modifiers changed from: private */
        public int mStateStackTopIndex;
        private StateInfo[] mTempStateStack;
        private int mTempStateStackCount;
        private boolean mTransitionInProgress;

        private class StateInfo {
            boolean active;
            StateInfo parentStateInfo;
            State state;

            private StateInfo() {
            }

            /* synthetic */ StateInfo(SmHandler smHandler, StateInfo stateInfo) {
                this();
            }

            public String toString() {
                return "state=" + this.state.getName() + ",active=" + this.active + ",parent=" + (this.parentStateInfo == null ? DVRConst.UNKOWN_CAMERA_ID : this.parentStateInfo.state.getName());
            }
        }

        private class HaltingState extends State {
            private HaltingState() {
            }

            /* synthetic */ HaltingState(SmHandler smHandler, HaltingState haltingState) {
                this();
            }

            public boolean processMessage(Message msg) {
                SmHandler.this.mSm.haltedProcessMessage(msg);
                return true;
            }
        }

        private class QuittingState extends State {
            private QuittingState() {
            }

            /* synthetic */ QuittingState(SmHandler smHandler, QuittingState quittingState) {
                this();
            }

            public boolean processMessage(Message msg) {
                return false;
            }
        }

        public final void handleMessage(Message msg) {
            if (!this.mHasQuit) {
                if (!(this.mSm == null || msg.what == -2 || msg.what == -1)) {
                    this.mSm.onPreHandleMessage(msg);
                }
                if (this.mDbg) {
                    this.mSm.log("handleMessage: E msg.what=" + msg.what);
                }
                this.mMsg = msg;
                State msgProcessedState = null;
                if (this.mIsConstructionCompleted) {
                    msgProcessedState = processMsg(msg);
                } else if (!this.mIsConstructionCompleted && this.mMsg.what == -2 && this.mMsg.obj == mSmHandlerObj) {
                    this.mIsConstructionCompleted = true;
                    invokeEnterMethods(0);
                } else {
                    throw new RuntimeException("StateMachine.handleMessage: The start method not called, received msg: " + msg);
                }
                performTransitions(msgProcessedState, msg);
                if (this.mDbg && this.mSm != null) {
                    this.mSm.log("handleMessage: X");
                }
                if (this.mSm != null && msg.what != -2 && msg.what != -1) {
                    this.mSm.onPostHandleMessage(msg);
                }
            }
        }

        private void performTransitions(State msgProcessedState, Message msg) {
            State orgState = this.mStateStack[this.mStateStackTopIndex].state;
            boolean recordLogMsg = this.mSm.recordLogRec(this.mMsg) && msg.obj != mSmHandlerObj;
            if (this.mLogRecords.logOnlyTransitions()) {
                if (this.mDestState != null) {
                    this.mLogRecords.add(this.mSm, this.mMsg, this.mSm.getLogRecString(this.mMsg), msgProcessedState, orgState, this.mDestState);
                }
            } else if (recordLogMsg) {
                this.mLogRecords.add(this.mSm, this.mMsg, this.mSm.getLogRecString(this.mMsg), msgProcessedState, orgState, this.mDestState);
            }
            State destState = this.mDestState;
            if (destState != null) {
                while (true) {
                    if (this.mDbg) {
                        this.mSm.log("handleMessage: new destination call exit/enter");
                    }
                    StateInfo commonStateInfo = setupTempStateStackWithStatesToEnter(destState);
                    this.mTransitionInProgress = true;
                    invokeExitMethods(commonStateInfo);
                    invokeEnterMethods(moveTempStateStackToStateStack());
                    moveDeferredMessageAtFrontOfQueue();
                    if (destState == this.mDestState) {
                        break;
                    }
                    destState = this.mDestState;
                }
                this.mDestState = null;
            }
            if (destState == null) {
                return;
            }
            if (destState == this.mQuittingState) {
                this.mSm.onQuitting();
                cleanupAfterQuitting();
            } else if (destState == this.mHaltingState) {
                this.mSm.onHalting();
            }
        }

        private final void cleanupAfterQuitting() {
            if (this.mSm.mSmThread != null) {
                getLooper().quit();
                this.mSm.mSmThread = null;
            }
            this.mSm.mSmHandler = null;
            this.mSm = null;
            this.mMsg = null;
            this.mLogRecords.cleanup();
            this.mStateStack = null;
            this.mTempStateStack = null;
            this.mStateInfo.clear();
            this.mInitialState = null;
            this.mDestState = null;
            this.mDeferredMessages.clear();
            this.mHasQuit = true;
        }

        /* access modifiers changed from: private */
        public final void completeConstruction() {
            if (this.mDbg) {
                this.mSm.log("completeConstruction: E");
            }
            int maxDepth = 0;
            for (StateInfo i : this.mStateInfo.values()) {
                int depth = 0;
                while (i != null) {
                    i = i.parentStateInfo;
                    depth++;
                }
                if (maxDepth < depth) {
                    maxDepth = depth;
                }
            }
            if (this.mDbg) {
                this.mSm.log("completeConstruction: maxDepth=" + maxDepth);
            }
            this.mStateStack = new StateInfo[maxDepth];
            this.mTempStateStack = new StateInfo[maxDepth];
            setupInitialStateStack();
            sendMessageAtFrontOfQueue(obtainMessage(-2, mSmHandlerObj));
            if (this.mDbg) {
                this.mSm.log("completeConstruction: X");
            }
        }

        /* JADX WARNING: CFG modification limit reached, blocks count: 122 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private final com.autochips.camera.State processMsg(android.os.Message r5) {
            /*
                r4 = this;
                com.autochips.camera.StateMachine$SmHandler$StateInfo[] r1 = r4.mStateStack
                int r2 = r4.mStateStackTopIndex
                r0 = r1[r2]
                boolean r1 = r4.mDbg
                if (r1 == 0) goto L_0x0025
                com.autochips.camera.StateMachine r1 = r4.mSm
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "processMsg: "
                r2.<init>(r3)
                com.autochips.camera.State r3 = r0.state
                java.lang.String r3 = r3.getName()
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                r1.log(r2)
            L_0x0025:
                boolean r1 = r4.isQuit(r5)
                if (r1 == 0) goto L_0x005e
                com.autochips.camera.StateMachine$SmHandler$QuittingState r1 = r4.mQuittingState
                r4.transitionTo(r1)
            L_0x0030:
                if (r0 == 0) goto L_0x0067
                com.autochips.camera.State r1 = r0.state
            L_0x0034:
                return r1
            L_0x0035:
                com.autochips.camera.StateMachine$SmHandler$StateInfo r0 = r0.parentStateInfo
                if (r0 != 0) goto L_0x003f
                com.autochips.camera.StateMachine r1 = r4.mSm
                r1.unhandledMessage(r5)
                goto L_0x0030
            L_0x003f:
                boolean r1 = r4.mDbg
                if (r1 == 0) goto L_0x005e
                com.autochips.camera.StateMachine r1 = r4.mSm
                java.lang.StringBuilder r2 = new java.lang.StringBuilder
                java.lang.String r3 = "processMsg: "
                r2.<init>(r3)
                com.autochips.camera.State r3 = r0.state
                java.lang.String r3 = r3.getName()
                java.lang.StringBuilder r2 = r2.append(r3)
                java.lang.String r2 = r2.toString()
                r1.log(r2)
            L_0x005e:
                com.autochips.camera.State r1 = r0.state
                boolean r1 = r1.processMessage(r5)
                if (r1 == 0) goto L_0x0035
                goto L_0x0030
            L_0x0067:
                r1 = 0
                goto L_0x0034
            */
            throw new UnsupportedOperationException("Method not decompiled: com.autochips.camera.StateMachine.SmHandler.processMsg(android.os.Message):com.autochips.camera.State");
        }

        private final void invokeExitMethods(StateInfo commonStateInfo) {
            while (this.mStateStackTopIndex >= 0 && this.mStateStack[this.mStateStackTopIndex] != commonStateInfo) {
                State curState = this.mStateStack[this.mStateStackTopIndex].state;
                if (this.mDbg) {
                    this.mSm.log("invokeExitMethods: " + curState.getName());
                }
                curState.exit();
                this.mStateStack[this.mStateStackTopIndex].active = false;
                this.mStateStackTopIndex--;
            }
        }

        private final void invokeEnterMethods(int stateStackEnteringIndex) {
            for (int i = stateStackEnteringIndex; i <= this.mStateStackTopIndex; i++) {
                if (stateStackEnteringIndex == this.mStateStackTopIndex) {
                    this.mTransitionInProgress = false;
                }
                if (this.mDbg) {
                    this.mSm.log("invokeEnterMethods: " + this.mStateStack[i].state.getName());
                }
                this.mStateStack[i].state.enter();
                this.mStateStack[i].active = true;
            }
            this.mTransitionInProgress = false;
        }

        private final void moveDeferredMessageAtFrontOfQueue() {
            for (int i = this.mDeferredMessages.size() - 1; i >= 0; i--) {
                Message curMsg = this.mDeferredMessages.get(i);
                if (this.mDbg) {
                    this.mSm.log("moveDeferredMessageAtFrontOfQueue; what=" + curMsg.what);
                }
                sendMessageAtFrontOfQueue(curMsg);
            }
            this.mDeferredMessages.clear();
        }

        private final int moveTempStateStackToStateStack() {
            int startingIndex = this.mStateStackTopIndex + 1;
            int j = startingIndex;
            for (int i = this.mTempStateStackCount - 1; i >= 0; i--) {
                if (this.mDbg) {
                    this.mSm.log("moveTempStackToStateStack: i=" + i + ",j=" + j);
                }
                this.mStateStack[j] = this.mTempStateStack[i];
                j++;
            }
            this.mStateStackTopIndex = j - 1;
            if (this.mDbg) {
                this.mSm.log("moveTempStackToStateStack: X mStateStackTop=" + this.mStateStackTopIndex + ",startingIndex=" + startingIndex + ",Top=" + this.mStateStack[this.mStateStackTopIndex].state.getName());
            }
            return startingIndex;
        }

        private final StateInfo setupTempStateStackWithStatesToEnter(State destState) {
            this.mTempStateStackCount = 0;
            StateInfo curStateInfo = this.mStateInfo.get(destState);
            do {
                StateInfo[] stateInfoArr = this.mTempStateStack;
                int i = this.mTempStateStackCount;
                this.mTempStateStackCount = i + 1;
                stateInfoArr[i] = curStateInfo;
                curStateInfo = curStateInfo.parentStateInfo;
                if (curStateInfo == null || curStateInfo.active) {
                }
                StateInfo[] stateInfoArr2 = this.mTempStateStack;
                int i2 = this.mTempStateStackCount;
                this.mTempStateStackCount = i2 + 1;
                stateInfoArr2[i2] = curStateInfo;
                curStateInfo = curStateInfo.parentStateInfo;
                break;
            } while (curStateInfo.active);
            if (this.mDbg) {
                this.mSm.log("setupTempStateStackWithStatesToEnter: X mTempStateStackCount=" + this.mTempStateStackCount + ",curStateInfo: " + curStateInfo);
            }
            return curStateInfo;
        }

        private final void setupInitialStateStack() {
            if (this.mDbg) {
                this.mSm.log("setupInitialStateStack: E mInitialState=" + this.mInitialState.getName());
            }
            StateInfo curStateInfo = this.mStateInfo.get(this.mInitialState);
            this.mTempStateStackCount = 0;
            while (curStateInfo != null) {
                this.mTempStateStack[this.mTempStateStackCount] = curStateInfo;
                curStateInfo = curStateInfo.parentStateInfo;
                this.mTempStateStackCount++;
            }
            this.mStateStackTopIndex = -1;
            moveTempStateStackToStateStack();
        }

        /* access modifiers changed from: private */
        public final Message getCurrentMessage() {
            return this.mMsg;
        }

        /* access modifiers changed from: private */
        public final IState getCurrentState() {
            return this.mStateStack[this.mStateStackTopIndex].state;
        }

        /* access modifiers changed from: private */
        public final StateInfo addState(State state, State parent) {
            if (this.mDbg) {
                this.mSm.log("addStateInternal: E state=" + state.getName() + ",parent=" + (parent == null ? TXZResourceManager.STYLE_DEFAULT : parent.getName()));
            }
            StateInfo parentStateInfo = null;
            if (parent != null && (parentStateInfo = this.mStateInfo.get(parent)) == null) {
                parentStateInfo = addState(parent, (State) null);
            }
            StateInfo stateInfo = this.mStateInfo.get(state);
            if (stateInfo == null) {
                stateInfo = new StateInfo(this, (StateInfo) null);
                this.mStateInfo.put(state, stateInfo);
            }
            if (stateInfo.parentStateInfo == null || stateInfo.parentStateInfo == parentStateInfo) {
                stateInfo.state = state;
                stateInfo.parentStateInfo = parentStateInfo;
                stateInfo.active = false;
                if (this.mDbg) {
                    this.mSm.log("addStateInternal: X stateInfo: " + stateInfo);
                }
                return stateInfo;
            }
            throw new RuntimeException("state already added");
        }

        /* access modifiers changed from: private */
        public void removeState(State state) {
            StateInfo stateInfo = this.mStateInfo.get(state);
            if (stateInfo != null && !stateInfo.active && 0 == 0) {
                this.mStateInfo.remove(state);
            }
        }

        /* synthetic */ SmHandler(Looper looper, StateMachine stateMachine, SmHandler smHandler) {
            this(looper, stateMachine);
        }

        private SmHandler(Looper looper, StateMachine sm) {
            super(looper);
            this.mHasQuit = false;
            this.mDbg = false;
            this.mLogRecords = new LogRecords((LogRecords) null);
            this.mStateStackTopIndex = -1;
            this.mHaltingState = new HaltingState(this, (HaltingState) null);
            this.mQuittingState = new QuittingState(this, (QuittingState) null);
            this.mStateInfo = new HashMap<>();
            this.mTransitionInProgress = false;
            this.mDeferredMessages = new ArrayList<>();
            this.mSm = sm;
            addState(this.mHaltingState, (State) null);
            addState(this.mQuittingState, (State) null);
        }

        /* access modifiers changed from: private */
        public final void setInitialState(State initialState) {
            if (this.mDbg) {
                this.mSm.log("setInitialState: initialState=" + initialState.getName());
            }
            this.mInitialState = initialState;
        }

        /* access modifiers changed from: private */
        public final void transitionTo(IState destState) {
            if (this.mTransitionInProgress) {
                Log.wtf(this.mSm.mName, "transitionTo called while transition already in progress to " + this.mDestState + ", new target state=" + destState);
            }
            this.mDestState = (State) destState;
            if (this.mDbg) {
                this.mSm.log("transitionTo: destState=" + this.mDestState.getName());
            }
        }

        /* access modifiers changed from: private */
        public final void deferMessage(Message msg) {
            if (this.mDbg) {
                this.mSm.log("deferMessage: msg=" + msg.what);
            }
            Message newMsg = obtainMessage();
            newMsg.copyFrom(msg);
            this.mDeferredMessages.add(newMsg);
        }

        /* access modifiers changed from: private */
        public final void quit() {
            if (this.mDbg) {
                this.mSm.log("quit:");
            }
            sendMessage(obtainMessage(-1, mSmHandlerObj));
        }

        /* access modifiers changed from: private */
        public final void quitNow() {
            if (this.mDbg) {
                this.mSm.log("quitNow:");
            }
            sendMessageAtFrontOfQueue(obtainMessage(-1, mSmHandlerObj));
        }

        /* access modifiers changed from: private */
        public final boolean isQuit(Message msg) {
            return msg.what == -1 && msg.obj == mSmHandlerObj;
        }

        /* access modifiers changed from: private */
        public final boolean isDbg() {
            return this.mDbg;
        }

        /* access modifiers changed from: private */
        public final void setDbg(boolean dbg) {
            this.mDbg = dbg;
        }
    }

    private void initStateMachine(String name, Looper looper) {
        this.mName = name;
        this.mSmHandler = new SmHandler(looper, this, (SmHandler) null);
    }

    protected StateMachine(String name) {
        this.mSmThread = new HandlerThread(name);
        this.mSmThread.start();
        initStateMachine(name, this.mSmThread.getLooper());
    }

    protected StateMachine(String name, Looper looper) {
        initStateMachine(name, looper);
    }

    protected StateMachine(String name, Handler handler) {
        initStateMachine(name, handler.getLooper());
    }

    /* access modifiers changed from: protected */
    public void onPreHandleMessage(Message msg) {
    }

    /* access modifiers changed from: protected */
    public void onPostHandleMessage(Message msg) {
    }

    public final void addState(State state, State parent) {
        SmHandler.StateInfo unused = this.mSmHandler.addState(state, parent);
    }

    public final void addState(State state) {
        SmHandler.StateInfo unused = this.mSmHandler.addState(state, (State) null);
    }

    public final void removeState(State state) {
        this.mSmHandler.removeState(state);
    }

    public final void setInitialState(State initialState) {
        this.mSmHandler.setInitialState(initialState);
    }

    public final Message getCurrentMessage() {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return null;
        }
        return smh.getCurrentMessage();
    }

    public final IState getCurrentState() {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return null;
        }
        return smh.getCurrentState();
    }

    public final void transitionTo(IState destState) {
        this.mSmHandler.transitionTo(destState);
    }

    public final void transitionToHaltingState() {
        this.mSmHandler.transitionTo(this.mSmHandler.mHaltingState);
    }

    public final void deferMessage(Message msg) {
        this.mSmHandler.deferMessage(msg);
    }

    /* access modifiers changed from: protected */
    public void unhandledMessage(Message msg) {
        if (this.mSmHandler.mDbg) {
            loge(" - unhandledMessage: msg.what=" + msg.what);
        }
    }

    /* access modifiers changed from: protected */
    public void haltedProcessMessage(Message msg) {
    }

    /* access modifiers changed from: protected */
    public void onHalting() {
    }

    /* access modifiers changed from: protected */
    public void onQuitting() {
    }

    public final String getName() {
        return this.mName;
    }

    public final void setLogRecSize(int maxSize) {
        this.mSmHandler.mLogRecords.setSize(maxSize);
    }

    public final void setLogOnlyTransitions(boolean enable) {
        this.mSmHandler.mLogRecords.setLogOnlyTransitions(enable);
    }

    public final int getLogRecSize() {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return 0;
        }
        return smh.mLogRecords.size();
    }

    public final int getLogRecMaxSize() {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return 0;
        }
        return smh.mLogRecords.mMaxSize;
    }

    public final int getLogRecCount() {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return 0;
        }
        return smh.mLogRecords.count();
    }

    public final LogRec getLogRec(int index) {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return null;
        }
        return smh.mLogRecords.get(index);
    }

    public final Collection<LogRec> copyLogRecs() {
        Vector<LogRec> vlr = new Vector<>();
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            Iterator it = smh.mLogRecords.mLogRecVector.iterator();
            while (it.hasNext()) {
                vlr.add((LogRec) it.next());
            }
        }
        return vlr;
    }

    public void addLogRec(String string) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.mLogRecords.add(this, smh.getCurrentMessage(), string, smh.getCurrentState(), smh.mStateStack[smh.mStateStackTopIndex].state, smh.mDestState);
        }
    }

    /* access modifiers changed from: protected */
    public boolean recordLogRec(Message msg) {
        return true;
    }

    /* access modifiers changed from: protected */
    public String getLogRecString(Message msg) {
        return TXZResourceManager.STYLE_DEFAULT;
    }

    /* access modifiers changed from: protected */
    public String getWhatToString(int what) {
        return null;
    }

    public final Handler getHandler() {
        return this.mSmHandler;
    }

    public final Message obtainMessage() {
        return Message.obtain(this.mSmHandler);
    }

    public final Message obtainMessage(int what) {
        return Message.obtain(this.mSmHandler, what);
    }

    public final Message obtainMessage(int what, Object obj) {
        return Message.obtain(this.mSmHandler, what, obj);
    }

    public final Message obtainMessage(int what, int arg1) {
        return Message.obtain(this.mSmHandler, what, arg1, 0);
    }

    public final Message obtainMessage(int what, int arg1, int arg2) {
        return Message.obtain(this.mSmHandler, what, arg1, arg2);
    }

    public final Message obtainMessage(int what, int arg1, int arg2, Object obj) {
        return Message.obtain(this.mSmHandler, what, arg1, arg2, obj);
    }

    public void sendMessage(int what) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessage(obtainMessage(what));
        }
    }

    public void sendMessage(int what, Object obj) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessage(obtainMessage(what, obj));
        }
    }

    public void sendMessage(int what, int arg1) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessage(obtainMessage(what, arg1));
        }
    }

    public void sendMessage(int what, int arg1, int arg2) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessage(obtainMessage(what, arg1, arg2));
        }
    }

    public void sendMessage(int what, int arg1, int arg2, Object obj) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessage(obtainMessage(what, arg1, arg2, obj));
        }
    }

    public void sendMessage(Message msg) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessage(msg);
        }
    }

    public void sendMessageDelayed(int what, long delayMillis) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageDelayed(obtainMessage(what), delayMillis);
        }
    }

    public void sendMessageDelayed(int what, Object obj, long delayMillis) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageDelayed(obtainMessage(what, obj), delayMillis);
        }
    }

    public void sendMessageDelayed(int what, int arg1, long delayMillis) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageDelayed(obtainMessage(what, arg1), delayMillis);
        }
    }

    public void sendMessageDelayed(int what, int arg1, int arg2, long delayMillis) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageDelayed(obtainMessage(what, arg1, arg2), delayMillis);
        }
    }

    public void sendMessageDelayed(int what, int arg1, int arg2, Object obj, long delayMillis) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageDelayed(obtainMessage(what, arg1, arg2, obj), delayMillis);
        }
    }

    public void sendMessageDelayed(Message msg, long delayMillis) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageDelayed(msg, delayMillis);
        }
    }

    /* access modifiers changed from: protected */
    public final void sendMessageAtFrontOfQueue(int what) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageAtFrontOfQueue(obtainMessage(what));
        }
    }

    /* access modifiers changed from: protected */
    public final void sendMessageAtFrontOfQueue(int what, Object obj) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageAtFrontOfQueue(obtainMessage(what, obj));
        }
    }

    /* access modifiers changed from: protected */
    public final void sendMessageAtFrontOfQueue(int what, int arg1) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageAtFrontOfQueue(obtainMessage(what, arg1));
        }
    }

    /* access modifiers changed from: protected */
    public final void sendMessageAtFrontOfQueue(int what, int arg1, int arg2) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageAtFrontOfQueue(obtainMessage(what, arg1, arg2));
        }
    }

    /* access modifiers changed from: protected */
    public final void sendMessageAtFrontOfQueue(int what, int arg1, int arg2, Object obj) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageAtFrontOfQueue(obtainMessage(what, arg1, arg2, obj));
        }
    }

    /* access modifiers changed from: protected */
    public final void sendMessageAtFrontOfQueue(Message msg) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.sendMessageAtFrontOfQueue(msg);
        }
    }

    /* access modifiers changed from: protected */
    public final void clearDeferredMessages() {
        if (this.mSmHandler != null) {
            this.mSmHandler.mDeferredMessages.clear();
        }
    }

    /* access modifiers changed from: protected */
    public final void removeMessages(int what) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.removeMessages(what);
        }
    }

    /* access modifiers changed from: protected */
    public final void removeMessages(int what, Object object) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.removeMessages(what, object);
        }
    }

    /* access modifiers changed from: protected */
    public final void removeDeferredMessages(int what) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            Iterator<Message> iterator = smh.mDeferredMessages.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().what == what) {
                    iterator.remove();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final void removeDeferredMessages(int what, Object object) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            Iterator<Message> iterator = smh.mDeferredMessages.iterator();
            while (iterator.hasNext()) {
                Message msg = iterator.next();
                if (msg.what == what && msg.obj == object) {
                    iterator.remove();
                }
            }
        }
    }

    /* access modifiers changed from: protected */
    public final boolean hasDeferredMessages(int what) {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return false;
        }
        Iterator<Message> iterator = smh.mDeferredMessages.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().what == what) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final boolean hasDeferredMessages(int what, Object object) {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return false;
        }
        Iterator<Message> iterator = smh.mDeferredMessages.iterator();
        while (iterator.hasNext()) {
            Message msg = iterator.next();
            if (msg.what == what && msg.obj == object) {
                return true;
            }
        }
        return false;
    }

    /* access modifiers changed from: protected */
    public final boolean hasMessages(int what) {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return false;
        }
        return smh.hasMessages(what);
    }

    /* access modifiers changed from: protected */
    public final boolean hasMessages(int what, Object object) {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return false;
        }
        return smh.hasMessages(what, object);
    }

    /* access modifiers changed from: protected */
    public final boolean isQuit(Message msg) {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return msg.what == -1;
        }
        return smh.isQuit(msg);
    }

    public final void quit() {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.quit();
        }
    }

    public final void quitNow() {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.quitNow();
        }
    }

    public boolean isDbg() {
        SmHandler smh = this.mSmHandler;
        if (smh == null) {
            return false;
        }
        return smh.isDbg();
    }

    public void setDbg(boolean dbg) {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.setDbg(dbg);
        }
    }

    public void start() {
        SmHandler smh = this.mSmHandler;
        if (smh != null) {
            smh.completeConstruction();
        }
    }

    public void dump(FileDescriptor fd, PrintWriter pw, String[] args) {
        pw.println(String.valueOf(getName()) + ":");
        pw.println(" total records=" + getLogRecCount());
        for (int i = 0; i < getLogRecSize(); i++) {
            pw.println(" rec[" + i + "]: " + getLogRec(i).toString());
            pw.flush();
        }
        pw.println("curState=" + getCurrentState().getName());
    }

    public String toString() {
        String name = "(null)";
        String state = "(null)";
        try {
            name = this.mName.toString();
            state = this.mSmHandler.getCurrentState().getName().toString();
        } catch (NullPointerException e) {
        }
        return "name=" + name + " state=" + state;
    }

    /* access modifiers changed from: protected */
    public void logAndAddLogRec(String s) {
        addLogRec(s);
        log(s);
    }

    /* access modifiers changed from: protected */
    public void log(String s) {
        Log.d(this.mName, s);
    }

    /* access modifiers changed from: protected */
    public void logd(String s) {
        Log.d(this.mName, s);
    }

    /* access modifiers changed from: protected */
    public void logv(String s) {
        Log.v(this.mName, s);
    }

    /* access modifiers changed from: protected */
    public void logi(String s) {
        Log.i(this.mName, s);
    }

    /* access modifiers changed from: protected */
    public void logw(String s) {
        Log.w(this.mName, s);
    }

    /* access modifiers changed from: protected */
    public void loge(String s) {
        Log.e(this.mName, s);
    }

    /* access modifiers changed from: protected */
    public void loge(String s, Throwable e) {
        Log.e(this.mName, s, e);
    }
}
