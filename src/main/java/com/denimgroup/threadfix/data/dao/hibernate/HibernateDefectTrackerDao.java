////////////////////////////////////////////////////////////////////////
//
//     Copyright (c) 2009-2011 Denim Group, Ltd.
//
//     The contents of this file are subject to the Mozilla Public License
//     Version 1.1 (the "License"); you may not use this file except in
//     compliance with the License. You may obtain a copy of the License at
//     http://www.mozilla.org/MPL/
//
//     Software distributed under the License is distributed on an "AS IS"
//     basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
//     License for the specific language governing rights and limitations
//     under the License.
//
//     The Original Code is Vulnerability Manager.
//
//     The Initial Developer of the Original Code is Denim Group, Ltd.
//     Portions created by Denim Group, Ltd. are Copyright (C)
//     Denim Group, Ltd. All Rights Reserved.
//
//     Contributor(s): Denim Group, Ltd.
//
////////////////////////////////////////////////////////////////////////
package com.denimgroup.threadfix.data.dao.hibernate;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.denimgroup.threadfix.data.dao.DefectTrackerDao;
import com.denimgroup.threadfix.data.entities.DefectTracker;

/**
 * Hibernate DefectTracker DAO implementation. Most basic methods are
 * implemented in the AbstractGenericDao
 * 
 * @author mcollins, dwolf
 * @see AbstractGenericDao
 */
@Repository
public class HibernateDefectTrackerDao implements DefectTrackerDao {

	private SessionFactory sessionFactory;

	@Autowired
	public HibernateDefectTrackerDao(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void deleteById(int id) {
		sessionFactory.getCurrentSession().delete(retrieveById(id));
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<DefectTracker> retrieveAll() {
		return sessionFactory.getCurrentSession().createCriteria(DefectTracker.class, "def")
				.createAlias("defectTrackerType", "dtt")
				.addOrder(Order.asc("dtt.name"))
				.addOrder(Order.asc("def.name")).list();
	}

	@Override
	public DefectTracker retrieveById(int id) {
		return (DefectTracker) sessionFactory.getCurrentSession().get(DefectTracker.class, id);
	}

	@Override
	public DefectTracker retrieveByName(String name) {
		return (DefectTracker) sessionFactory.getCurrentSession()
				.createQuery("from DefectTracker defectTracker where defectTracker.name = :name")
				.setString("name", name).uniqueResult();
	}

	@Override
	public void saveOrUpdate(DefectTracker defectTracker) {
		if (defectTracker != null && defectTracker.getId() != null) {
			sessionFactory.getCurrentSession().merge(defectTracker);
		} else {
			sessionFactory.getCurrentSession().saveOrUpdate(defectTracker);
		}
	}
}
