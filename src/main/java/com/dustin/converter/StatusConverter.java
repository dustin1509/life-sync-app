/*******************************************************************************
 * Copyright (c) 2020 iXchange Pte. Ltd. All rights reserved.
 *
 *  This software is the confidential and proprietary information of iXchange Pte
 *  Ltd ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the license
 *   agreement you entered into with iXchange.
 ******************************************************************************/

package com.dustin.converter;

import com.dustin.constants.Status;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StatusConverter implements Converter<String, Status> {

    @Override
    public Status convert(String source) {
        try {
            return Status.findByValue(source);
        } catch (Exception e) {
            return null;
        }
    }
}
