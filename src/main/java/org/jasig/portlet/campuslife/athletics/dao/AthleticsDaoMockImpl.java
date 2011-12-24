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
package org.jasig.portlet.campuslife.athletics.dao;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jasig.portlet.campuslife.athletics.model.feed.xml.AthleticsFeed;
import org.jasig.portlet.campuslife.athletics.model.feed.xml.Sport;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;

public class AthleticsDaoMockImpl implements InitializingBean, IAthleticsDao {

    protected final Log log = LogFactory.getLog(getClass());

    private AthleticsFeed feed;
    
    private Resource mockData;

    public void setMockData(Resource mockData) {
        this.mockData = mockData;
    }
    
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(AthleticsFeed.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            this.feed = (AthleticsFeed) unmarshaller.unmarshal(mockData.getInputStream());
        } catch (IOException e) {
            log.error("Failed to read mock data", e);
        } catch (JAXBException e) {
            log.error("Failed to unmarshall mock data", e);
        }
    }
    
    /* (non-Javadoc)
     * @see org.jasig.portlet.campuslife.athletics.dao.sample.IAthleticsDao#getFeed()
     */
    @Override
    public AthleticsFeed getFeed() {
        return feed;
    }
    
    /* (non-Javadoc)
     * @see org.jasig.portlet.campuslife.athletics.dao.sample.IAthleticsDao#getSport(java.lang.String)
     */
    @Override
    public Sport getSport(String sport) {
        for (Sport s : feed.getSport()) {
            if (sport.equals(s.getName())) {
                return s;
            }
        }
        return null;
    }

}
