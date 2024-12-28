/*******************************************************************************
 * Copyright (c) 2020 iXchange Pte. Ltd. All rights reserved.
 *
 *  This software is the confidential and proprietary information of iXchange Pte
 *  Ltd ("Confidential Information"). You shall not disclose such Confidential
 *  Information and shall use it only in accordance with the terms of the license
 *   agreement you entered into with iXchange.
 ******************************************************************************/

package com.dustin.service;

import org.mapstruct.Named;

import java.util.List;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <D> - Dto type parameter.
 * @param <E> - Entity type parameter.
 */
public interface EntityMapper<D, E> {

    E toEntity(D dto);

    D toDto(E entity);

    List<E> toEntityList(List<D> dtoList);

    List<D> toDtoList(List<E> entityList);

    @Named("trimText")
    default String trimText(String source){
        return source != null ? source.trim() : null;
    }
}
