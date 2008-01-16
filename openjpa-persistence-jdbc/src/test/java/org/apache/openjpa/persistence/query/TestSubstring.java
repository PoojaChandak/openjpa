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
package org.apache.openjpa.persistence.query;

import javax.persistence.EntityManager;

import org.apache.openjpa.persistence.test.SingleEMTestCase;

public class TestSubstring extends SingleEMTestCase {

    public void setUp() {
        super.setUp(SimpleEntity.class, CLEAR_TABLES, "openjpa.Log", "SQL=TRACE");

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(new SimpleEntity("foo", "bar"));
        em.getTransaction().commit();
        em.close();
    }

    public void testSingleCharacterSubstringInWhere() {
        assertEquals((long) 1, em.createQuery("select count(o) from simple o " +
            "where substring(o.value, 1, 1) = 'b'").getSingleResult());
        assertEquals((long) 1, em.createQuery("select count(o) from simple o " +
            "where substring(o.value, 2, 1) = 'a'").getSingleResult());
        assertEquals((long) 1, em.createQuery("select count(o) from simple o " +
            "where substring(o.value, 3, 1) = 'r'").getSingleResult());
    }

    public void testMultiCharacterSubstringInWhere() {
        assertEquals((long) 1, em.createQuery("select count(o) from simple o " +
            "where substring(o.value, 1, 2) = 'ba'").getSingleResult());
        assertEquals((long) 1, em.createQuery("select count(o) from simple o " +
            "where substring(o.value, 2, 2) = 'ar'").getSingleResult());
    }

    public void testSubstringInSelect() {
        assertEquals("b", em.createQuery("select substring(o.value, 1, 1) " +
            "from simple o").getSingleResult());
        assertEquals("a", em.createQuery("select substring(o.value, 2, 1) " +
            "from simple o").getSingleResult());
        assertEquals("r", em.createQuery("select substring(o.value, 3, 1) " +
            "from simple o").getSingleResult());
    }
}
