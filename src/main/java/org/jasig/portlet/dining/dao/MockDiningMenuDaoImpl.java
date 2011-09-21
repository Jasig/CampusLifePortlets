/**
 * Licensed to Jasig under one or more contributor license
 * agreements. See the NOTICE file distributed with this work
 * for additional information regarding copyright ownership.
 * Jasig licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file
 * except in compliance with the License. You may obtain a
 * copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.jasig.portlet.dining.dao;

import java.io.IOException;
import java.util.List;

import javax.portlet.PortletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.portlet.dining.model.menu.xml.DiningHall;
import org.jasig.portlet.dining.model.menu.xml.Menu;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class MockDiningMenuDaoImpl implements IDiningMenuDao, InitializingBean {

    protected final Log log = LogFactory.getLog(getClass());

    private Menu menu;
    
    private Resource mockData;

    public void setMockData(Resource mockData) {
        this.mockData = mockData;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Menu.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            this.menu = (Menu) unmarshaller.unmarshal(mockData.getInputStream());
        } catch (IOException e) {
            log.error("Failed to read mock data", e);
        } catch (JAXBException e) {
            log.error("Failed to unmarshall mock data", e);
        }
    }

    @Override
    public DiningHall getMenu(PortletRequest request, String diningHall) {
        return this.menu.getDiningHall().get(0);
    }

    @Override
    public List<String> getDiningHalls(PortletRequest request) {
        // TODO Auto-generated method stub
        return null;
    }

}
