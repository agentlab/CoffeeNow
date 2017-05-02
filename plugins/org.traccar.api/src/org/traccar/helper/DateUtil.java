/*
 * Copyright 2016 Anton Tananaev (anton@traccar.org)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.traccar.helper;

import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

public final class DateUtil {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

    private DateUtil() {
    }

    public static Date correctDay(Date guess) {
        return correctDate(new Date(), guess, Calendar.DAY_OF_MONTH);
    }

    public static Date correctYear(Date guess) {
        return correctDate(new Date(), guess, Calendar.YEAR);
    }

    public static Date correctDate(Date now, Date guess, int field) {

        if (guess.getTime() > now.getTime()) {
            Date previous = dateAdd(guess, field, -1);
            if (now.getTime() - previous.getTime() < guess.getTime() - now.getTime()) {
                return previous;
            }
        } else if (guess.getTime() < now.getTime()) {
            Date next = dateAdd(guess, field, 1);
            if (next.getTime() - now.getTime() < now.getTime() - guess.getTime()) {
                return next;
            }
        }

        return guess;
    }

    private static Date dateAdd(Date guess, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(guess);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    public static Date parseDate(String value) {
        //TODO: fixme type
        return (Date)DATE_FORMAT.parse(value);
    }
}
