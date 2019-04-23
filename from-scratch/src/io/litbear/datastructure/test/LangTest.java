package io.litbear.datastructure.test;

import org.junit.Test;

import java.lang.ref.SoftReference;

public class LangTest {

    @Test
    public void SoftReferenceTest() {
        String str = "@kdgregory: I agree that it's a bad idea, but mostly you " +
                "have no control at all. But you often can't tell what's the \"useful life\" " +
                "and there's no way how to balance different caches (example: You have to " +
                "decide things like \"should the cache-1 limit be 1000 entries?\" " +
                "rather than \"let cache-1 and cache-2 use together no more than 100 MB\"). " +
                "– maaartinus Feb 6 '14 at 23:59In my case the \"useful life\" is the " +
                "longest one. But if I use strong references a out of memory may occur. " +
                "@kdgregory: Is it a bad thing to have object kept in memory if the " +
                "gc can delete them whenever it needs to ? @ maaartinus: I can always set " +
                "the references manually to null and limit the cache size, am I " +
                "wrong ? – zelus Feb 7 '14 at 1:01";
        SoftReference sf = new SoftReference<>(str);
        str = null;
        System.gc();
        byte[] bytes = new byte[1024*1023 + 1007];
        System.gc();
        System.out.println("Is Collected: "+sf.get());
    }
}
