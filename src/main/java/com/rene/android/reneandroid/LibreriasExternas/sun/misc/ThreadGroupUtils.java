package com.rene.android.reneandroid.LibreriasExternas.sun.misc;


public final class ThreadGroupUtils {

            private ThreadGroupUtils() {
            }

            public static ThreadGroup getRootThreadGroup() {
/*  47*/        ThreadGroup threadgroup = Thread.currentThread().getThreadGroup();
/*  48*/        for (ThreadGroup threadgroup1 = threadgroup.getParent(); threadgroup1 != null; threadgroup1 = threadgroup.getParent()) {
/*  50*/            threadgroup = threadgroup1;
                }

/*  53*/        return threadgroup;
            }
}
