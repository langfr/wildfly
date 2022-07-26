/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.test.integration.jpa.epcpropagation.unsync;

import jakarta.ejb.Stateful;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceContextType;
import jakarta.persistence.PersistenceProperty;

/**
 * CMT stateful bean
 *
 * @author Scott Marlow
 */
@Stateful
public class CMTPCStatefulBean {
    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "mypc")
    private EntityManager em;

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "mypc", properties={@PersistenceProperty(name="wildfly.jpa.allowjoinedunsync", value="true")})
    private EntityManager allowjoinedunsyncEm;

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "allowjoinedunsyncPU")
    private EntityManager allowjoinedunsyncEmViaPersistenceXml;


    public Employee getEmp(int id) {
        return em.find(Employee.class, id);
    }

    public Employee getEmpAllowJoinedUnsync(int id) {
        return allowjoinedunsyncEm.find(Employee.class, id);
    }

    public Employee getEmpAllowJoinedUnsyncPersistenceXML(int id) {
        return allowjoinedunsyncEmViaPersistenceXml.find(Employee.class, id);
    }

}
