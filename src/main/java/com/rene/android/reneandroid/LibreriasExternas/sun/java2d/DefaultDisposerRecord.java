package com.rene.android.reneandroid.LibreriasExternas.sun.java2d;


// Referenced classes of package com.rene.android.reneandroid.LibreriasExternas.sun.java2d:
//            DisposerRecord

public class DefaultDisposerRecord
    implements DisposerRecord {

            private long dataPointer;
            private long disposerMethodPointer;

            public DefaultDisposerRecord(long l, long l1) {
/*  37*/        disposerMethodPointer = l;
/*  38*/        dataPointer = l1;
            }

            public void dispose() {
/*  42*/        invokeNativeDispose(disposerMethodPointer, dataPointer);
            }

            public long getDataPointer() {
/*  47*/        return dataPointer;
            }

            public long getDisposerMethodPointer() {
/*  51*/        return disposerMethodPointer;
            }

            public static native void invokeNativeDispose(long l, long l1);
}
