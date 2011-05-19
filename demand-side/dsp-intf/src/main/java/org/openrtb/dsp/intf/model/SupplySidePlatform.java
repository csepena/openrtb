/*
 * Copyright (c) 2010, The OpenRTB Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   1. Redistributions of source code must retain the above copyright notice,
 *      this list of conditions and the following disclaimer.
 *
 *   2. Redistributions in binary form must reproduce the above copyright
 *      notice, this list of conditions and the following disclaimer in the
 *      documentation and/or other materials provided with the distribution.
 *
 *   3. Neither the name of the OpenRTB nor the names of its contributors
 *      may be used to endorse or promote products derived from this
 *      software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.openrtb.dsp.intf.model;

/**
 * A value object representing the required information for sending a request to a supply-side platform (exchange).
 */
public class SupplySidePlatform {

	String organization;
	String advertiserBatchServiceUrl;
	String publisherBatchServiceUrl;
	String urlGroupsBatchServiceUrl;
	String demandSideName;
	byte[] sharedSecret;

    /**
     * @deprecated Please refer to
     *             {@link #SupplySidePlatform(String, String, String, byte[])}
     *             instead; each supply-side platform is capable of specifying a
     *             unique name for the demand-side partner.
     */
    @Deprecated
    public SupplySidePlatform(String organization, String batchServiceUrl, byte[] sharedSecret) {
    	this(organization, batchServiceUrl, null, null, "", sharedSecret);
    }

	/**
	 * Constructor for a supply-side platform object. Organization and sharedSecret are required to be non- <tt>null</tt>.
	 * Either advertiserBatchServiceUrl or publisherBatchServiceUrl is required to be non-<tt>null</tt>. If above
	 * conditions are not met, an {@link IllegalArgumentException} is thrown to alert the caller of the condition.
	 */
	public SupplySidePlatform(String organization, 
							  String advertiserBatchServiceUrl, 
							  String publisherBatchServiceUrl, 
							  String urlGroupsBatchServiceUrl, 
							  String demandSideName, byte[] sharedSecret) {
		if (organization == null || demandSideName == null || sharedSecret == null || (publisherBatchServiceUrl == null && advertiserBatchServiceUrl == null)) {
			throw new IllegalArgumentException("organization [" + organization + "], " + 
											   "demand side name ["+demandSideName+"], and " +
											   "secret [" + sharedSecret + "] are required and " + 
											   "either advertiser service url [" + advertiserBatchServiceUrl + "] or " + 
											   "publisher service url [" + publisherBatchServiceUrl + "] is required " + 
											   "for valid supply-side platform definitions");
		}
		this.organization = organization;
		this.demandSideName = ("".equals(demandSideName))?(null):(demandSideName);
		this.sharedSecret = sharedSecret;
		this.advertiserBatchServiceUrl = advertiserBatchServiceUrl;
		this.publisherBatchServiceUrl = publisherBatchServiceUrl;
		this.urlGroupsBatchServiceUrl = urlGroupsBatchServiceUrl;
	}

	public String getOrganization() {
		return organization;
	}

	public String getDemandSideName() {
		return demandSideName;
	}
	
	public byte[] getSharedSecret() {
		return sharedSecret;
	}

	@Deprecated
	public String getBatchServiceUrl() {
		return getAdvertiserBatchServiceUrl();
	}

	public String getAdvertiserBatchServiceUrl() {
		return advertiserBatchServiceUrl;
	}

	public String getPublisherBatchServiceUrl() {
		return publisherBatchServiceUrl;
	}

	public String getUrlGroupsBatchServiceUrl() {
		return urlGroupsBatchServiceUrl;
	}
}
