/*-
 * ---license-start
 * Corona-Warn-App
 * ---
 * Copyright (C) 2020 SAP SE and all other contributors
 * All modifications are copyright (c) 2020 Devside SRL.
 * ---
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ---license-end
 */

package app.coronawarn.server.common.persistence.domain;

import app.coronawarn.server.common.protocols.external.exposurenotification.TemporaryExposureKey;
import java.time.LocalDate;

/**
 * This interface bundles interfaces that are used for the implementation of {@link DiagnosisKeyBuilder}.
 */
interface DiagnosisKeyBuilders {

  interface Builder {

    /**
     * Adds the specified key data to this builder.
     *
     * @param keyData generated diagnosis key.
     * @return this Builder instance.
     */
    RollingStartIntervalNumberBuilder withKeyData(byte[] keyData);

    /**
     * Adds the data contained in the specified protocol buffers key object to this builder.
     *
     * @param protoBufObject ProtocolBuffer object associated with the temporary exposure key.
     * @return this Builder instance.
     */
    FinalBuilder fromProtoBuf(TemporaryExposureKey protoBufObject);
  }

  interface RollingStartIntervalNumberBuilder {

    /**
     * Adds the specified rolling start interval number to this builder.
     *
     * @param rollingStartIntervalNumber number describing when a key starts. It is equal to
     *                                   startTimeOfKeySinceEpochInSecs / (60 * 10).
     * @return this Builder instance.
     */
    TransmissionRiskLevelBuilder withRollingStartIntervalNumber(int rollingStartIntervalNumber);
  }

  interface TransmissionRiskLevelBuilder {

    /**
     * Adds the specified transmission risk level to this builder.
     *
     * @param transmissionRiskLevel risk of transmission associated with the person this key came from.
     * @return this Builder instance.
     */
    FinalBuilder withTransmissionRiskLevel(int transmissionRiskLevel);
  }

  interface FinalBuilder {

    /**
     * Adds the specified submission timestamp that is expected to represent hours since epoch.
     *
     * @param submissionTimestamp timestamp in hours since epoch.
     * @return this Builder instance.
     */
    FinalBuilder withSubmissionTimestamp(long submissionTimestamp);

    /**
     * Adds the specified rolling period to this builder. If not specified, the rolling period defaults to {@link
     * DiagnosisKey#EXPECTED_ROLLING_PERIOD}
     *
     * @param rollingPeriod Number describing how long a key is valid. It is expressed in increments of 10 minutes (e.g.
     *                      144 for 24 hours).
     * @return this Builder instance.
     */
    FinalBuilder withRollingPeriod(int rollingPeriod);

    /**
     * Adds the country to this builder.
     *
     * @param country the country associated with the key).
     * @return this Builder instance.
     */
    FinalBuilder withCountry(String country);

    /**
     * Adds the mobileTestId to this builder.
     *
     * @param mobileTestId the mobileTestId associated with the key).
     * @return this Builder instance.
     */
    FinalBuilder withMobileTestId(String mobileTestId);

    /**
     * Adds the datePatientInfectious to this builder.
     *
     * @param datePatientInfectious the datePatientInfectious associated with the key).
     * @return this Builder instance.
     */
    FinalBuilder withDatePatientInfectious(LocalDate datePatientInfectious);

    /**
     * Adds the dateTestCommunicated to this builder.
     *
     * @param dateTestCommunicated the dateTestCommunicated associated with the key).
     * @return this Builder instance.
     */
    FinalBuilder withDateTestCommunicated(LocalDate dateTestCommunicated);

    /**
     * Adds the resultChannel to this builder.
     *
     * @param resultChannel the resultChannel associated with the key).
     * @return this Builder instance.
     */
    FinalBuilder withResultChannel(int resultChannel);

    /**
     * Adds the verified flag to this builder.
     *
     * @param verified the verified flag associated with the key).
     * @return this Builder instance.
     */
    FinalBuilder withVerified(boolean verified);

    /**
     * Builds a {@link DiagnosisKey} instance. If no submission timestamp has been specified it will be set to "now" as
     * hours since epoch.
     */
    DiagnosisKey build();
  }
}
