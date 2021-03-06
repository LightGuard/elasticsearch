/*
 * Licensed to ElasticSearch and Shay Banon under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. ElasticSearch licenses this
 * file to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.elasticsearch.action.admin.indices.close;

import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.support.IgnoreIndices;
import org.elasticsearch.action.support.master.AcknowledgedRequestBuilder;
import org.elasticsearch.client.IndicesAdminClient;
import org.elasticsearch.client.internal.InternalIndicesAdminClient;

/**
 * Builder for close index request
 */
public class CloseIndexRequestBuilder extends AcknowledgedRequestBuilder<CloseIndexRequest, CloseIndexResponse, CloseIndexRequestBuilder> {

    public CloseIndexRequestBuilder(IndicesAdminClient indicesClient) {
        super((InternalIndicesAdminClient) indicesClient, new CloseIndexRequest());
    }

    public CloseIndexRequestBuilder(IndicesAdminClient indicesClient, String... indices) {
        super((InternalIndicesAdminClient) indicesClient, new CloseIndexRequest(indices));
    }

    /**
     * Sets the indices to be closed
     * @param indices the indices to be closed
     * @return the request itself
     */
    public CloseIndexRequestBuilder setIndices(String... indices) {
        request.indices(indices);
        return this;
    }

    /**
     * Specifies what type of requested indices to ignore. For example indices that don't exist.
     * @param ignoreIndices the desired behaviour regarding indices to ignore
     * @return the request itself
     */
    public CloseIndexRequestBuilder setIgnoreIndices(IgnoreIndices ignoreIndices) {
        request.ignoreIndices(ignoreIndices);
        return this;
    }

    @Override
    protected void doExecute(ActionListener<CloseIndexResponse> listener) {
        ((IndicesAdminClient) client).close(request, listener);
    }
}
