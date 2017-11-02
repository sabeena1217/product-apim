/*
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  WSO2 Inc. licenses this file to you under the Apache License,
 *  Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing,
 *  software distributed under the License is distributed on an
 *  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *  KIND, either express or implied.  See the License for the
 *  specific language governing permissions and limitations
 *  under the License.
 */
package org.wso2.carbon.apimgt.rest.integration.tests.microgateway;

import feign.Client;
import feign.Feign;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.wso2.carbon.apimgt.core.configuration.models.KeyMgtConfigurations;
import org.wso2.carbon.apimgt.core.exception.APIManagementException;
import org.wso2.carbon.apimgt.core.internal.ServiceReferenceHolder;
import org.wso2.carbon.apimgt.core.util.AMSSLSocketFactory;
public class MockyTest {

    MockyClient client = null;

    @BeforeTest
    public void setup() throws APIManagementException{
        String url = "https://localhost:9092/api";
        KeyMgtConfigurations keyManagerConfigs = ServiceReferenceHolder.getInstance().getAPIMConfiguration()
                .getKeyManagerConfigs();
        String kmCertAlias = keyManagerConfigs.getKeyManagerCertAlias();
        client = Feign.builder().client(new Client.Default(AMSSLSocketFactory.getSSLSocketFactory(kmCertAlias),
                (hostname, sslSession) -> true)).target(MockyClient.class, url);
    }


    @Test(enabled = true)
    public void apisGetTest() {
        String response = client.testValidApikey();
        System.out.println(response);
        //String response = apiClient.get();
        Assert.assertEquals(response, "{ \"abc\": \"This is a test\" }", "API name mismatch");

        String res = client.testInvalidApikey();
        System.out.println(res);
        Assert.assertEquals(res, "{\"code\":900903,\"message\":\"subscription not found\"}", "API name mismatch");

//        String rr = client.addUser("sabeena","sabeena");
//        System.out.println(rr);

       // client.addUser("sabeena","sabeena");
    }




}
