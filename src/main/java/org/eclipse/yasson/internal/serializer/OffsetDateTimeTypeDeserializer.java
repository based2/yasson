/*******************************************************************************
 * Copyright (c) 2016, 2017 Oracle and/or its affiliates. All rights reserved.
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License v1.0 and Eclipse Distribution License v. 1.0
 * which accompanies this distribution.
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 * Roman Grigoriadi
 ******************************************************************************/

package org.eclipse.yasson.internal.serializer;

import org.eclipse.yasson.internal.properties.MessageKeys;
import org.eclipse.yasson.internal.properties.Messages;
import org.eclipse.yasson.internal.model.JsonBindingModel;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.logging.Logger;

/**
 * Deserializer for {@link OffsetDateTime} type.
 *
 * @author David Kral
 */
public class OffsetDateTimeTypeDeserializer extends AbstractDateTimeDeserializer<OffsetDateTime> {
    private static final Logger log = Logger.getLogger(OffsetDateTimeTypeDeserializer.class.getName());

    /**
     * Creates a new instance.
     *
     * @param model Binding model.
     */
    public OffsetDateTimeTypeDeserializer(JsonBindingModel model) {
        super(OffsetDateTime.class, model);
    }

    /**
     * fromInstant is called only in case {@link javax.json.bind.annotation.JsonbDateFormat} is TIME_IN_MILLIS,
     * which doesn't make much sense for usage with OffsetDateTime.
     */
    @Override
    protected OffsetDateTime fromInstant(Instant instant) {
        log.warning(Messages.getMessage(MessageKeys.OFFSET_DATE_TIME_FROM_MILLIS, OffsetDateTime.class.getSimpleName(), UTC));
        return OffsetDateTime.ofInstant(instant, UTC);
    }

    @Override
    protected OffsetDateTime parseDefault(String jsonValue, Locale locale) {
        return OffsetDateTime.parse(jsonValue, DateTimeFormatter.ISO_OFFSET_DATE_TIME.withLocale(locale));
    }

    @Override
    protected OffsetDateTime parseWithFormatter(String jsonValue, DateTimeFormatter formatter) {
        return OffsetDateTime.parse(jsonValue, formatter);
    }
}
