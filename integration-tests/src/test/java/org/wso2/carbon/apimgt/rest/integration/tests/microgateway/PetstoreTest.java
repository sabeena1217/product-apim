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

import io.swagger.client.model.Category;
import io.swagger.client.model.Pet;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeTest;
import org.wso2.carbon.apimgt.core.configuration.models.KeyMgtConfigurations;
import org.wso2.carbon.apimgt.core.exception.APIManagementException;
import org.wso2.carbon.apimgt.core.internal.ServiceReferenceHolder;
import org.wso2.carbon.apimgt.core.util.AMSSLSocketFactory;

public class PetstoreTest {

    PetstoreClient client;

    @BeforeTest
    public void setup() throws APIManagementException {
        String url = "https://localhost:9092/v2";
        KeyMgtConfigurations keyManagerConfigs = ServiceReferenceHolder.getInstance().getAPIMConfiguration()
                .getKeyManagerConfigs();
        String kmCertAlias = keyManagerConfigs.getKeyManagerCertAlias();
        client = Feign.builder().client(new Client.Default(AMSSLSocketFactory.getSSLSocketFactory(kmCertAlias),
                (hostname, sslSession) -> true)).target(PetstoreClient.class, url);
    }

    @Test
    public void testCreateAndGetPet() throws Exception {
        Pet pet = createRandomPet();
        client.addPet(pet);

        Pet fetched = client.getPetById(pet.getId());
        Assert.assertNotNull(fetched);
        System.out.println(fetched.getId());
        Assert.assertEquals(pet.getId(), fetched.getId());
        System.out.println(fetched.getId());
//        Assert.assertNotNull(fetched.getCategory());
//        Assert.assertEquals(fetched.getCategory().getName(), pet.getCategory().getName());
    }

    private Pet createRandomPet() {
        Pet pet = new Pet();
        pet.setId(new Long(123));
        pet.setName("ollie");

        Category category = new Category();
        category.setName("really-happy");

        pet.setCategory(category);
        pet.setStatus(Pet.StatusEnum.AVAILABLE);
//        List<String> photos = Arrays.asList("http://foo.bar.com/1", "http://foo.bar.com/2");
//        pet.setPhotoUrls(photos);

        return pet;
    }

}
