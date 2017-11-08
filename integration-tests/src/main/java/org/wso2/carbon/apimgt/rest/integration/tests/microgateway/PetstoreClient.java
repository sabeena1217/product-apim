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
import feign.Body;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
import feign.Response;
import io.swagger.client.api.PetApi;
import io.swagger.client.model.Pet;

public interface PetstoreClient extends PetApi{

    /**
     * Add a new pet to the store
     *
     * @param body Pet object that needs to be added to the store (required)
     */
//    @RequestLine("POST /pet")
//    @Headers({
//            "Content-Type: application/json",
//            "Accept: application/json",
//    })
//    void addPet(Pet body);

    @RequestLine("POST /pet")
    @Headers({
            "apikey: 111111",
            "Content-Type: application/json",
            "Accept: application/json",
    })
    void addPet(Pet body);
}
