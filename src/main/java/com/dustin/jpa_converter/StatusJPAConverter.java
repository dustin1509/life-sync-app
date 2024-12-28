/*******************************************************************************
 * Copyright (c) 2020 iXchange Pte. Ltd. All rights reserved.
 *
 *  This software is the confidential and proprietary information of iXchange Pte
 *  Ltd ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the license
 *   agreement you entered into with iXchange.
 ******************************************************************************/

package com.dustin.jpa_converter;

import com.dustin.constants.Status;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;


@Converter(autoApply = true)
public class StatusJPAConverter implements AttributeConverter<Status, String> {
    @Override
    public String convertToDatabaseColumn(Status paymentBatchStatus) {
        return paymentBatchStatus != null ? paymentBatchStatus.getValue() : null;
    }

    @Override
    public Status convertToEntityAttribute(String s) {
        return Status.findByValue(s);
    }
}
