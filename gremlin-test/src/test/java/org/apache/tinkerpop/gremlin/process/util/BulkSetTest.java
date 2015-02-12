/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.tinkerpop.gremlin.process.util;

import org.junit.Test;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class BulkSetTest {

    @Test
    public void shouldHaveProperCountAndNotOutOfMemoryException() {
        final Set<Boolean> list = new BulkSet<>();
        final Random random = new Random();
        for (int i = 0; i < 10000000; i++) {
            list.add(random.nextBoolean());
        }
        assertEquals(10000000, list.size());
    }

    @Test
    public void shouldHaveCorrectBulkCounts() {
        final BulkSet<String> set = new BulkSet<>();
        set.add("marko");
        set.add("matthias");
        set.add("marko", 7);
        set.add("stephen");
        set.add("stephen");
        assertEquals(8, set.get("marko"));
        assertEquals(1, set.get("matthias"));
        assertEquals(2, set.get("stephen"));
        final Iterator<String> iterator = set.iterator();
        for (int i = 0; i < 11; i++) {
            if (i < 8)
                assertEquals("marko", iterator.next());
            else if (i < 9)
                assertEquals("matthias", iterator.next());
            else
                assertEquals("stephen", iterator.next());
        }
        assertEquals(11, set.size());
    }
}
