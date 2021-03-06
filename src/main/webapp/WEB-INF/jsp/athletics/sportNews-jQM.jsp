<%--

    Licensed to Jasig under one or more contributor license
    agreements. See the NOTICE file distributed with this work
    for additional information regarding copyright ownership.
    Jasig licenses this file to you under the Apache License,
    Version 2.0 (the "License"); you may not use this file
    except in compliance with the License. You may obtain a
    copy of the License at:

    http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing,
    software distributed under the License is distributed on
    an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
    KIND, either express or implied. See the License for the
    specific language governing permissions and limitations
    under the License.

--%>

<jsp:directive.include file="/WEB-INF/jsp/include.jsp"/>
<style type="text/css">
    .athletic-portlet-news h3 {
        position: relative;
        padding-left: 60px;
    }
    .athletic-portlet-news h3,
    .athletic-portlet-news p {
        font-size: 12px;
    }
    .sports-news-date {
        position: absolute;
        left: 0;
        top: 0;
        font-weight: normal;
        color: #D3D3D3;
        display: inline-block;
        width: 60px;
    }
</style>

<div class="portlet">
    <div data-role="header" class="titlebar portlet-titlebar">
        <a href="<portlet:renderURL/>" data-role="button" data-icon="back" data-inline="true">All Sports</a>
        <h2>${ sport.name }</h2>
        <portlet:renderURL var="scoresUrl">
            <portlet:param name="action" value="sportScores"/>
            <portlet:param name="sport" value="${ sport.name }"/>
        </portlet:renderURL>
        <a data-role="button" href="${ scoresUrl }">Scores</a>
    </div>
    
    <div data-role="content" class="portlet-content athletic-portlet-news">
        <ul data-role="listview" class="feed">
            <c:forEach items="${ sport.newsItem }" var="item">
                <li>
                    <a href="${ item.storyUrl }">
                        <h3>
                            <span class="sports-news-date">${ item.date }</span>    
                            ${ item.title }
                        </h3>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</div>
