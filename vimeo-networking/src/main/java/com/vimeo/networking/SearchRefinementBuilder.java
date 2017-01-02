/*
 * Copyright (c) 2015 Vimeo (https://vimeo.com)
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.vimeo.networking;

import com.vimeo.networking.callbacks.ModelCallback;

import java.util.HashMap;
import java.util.Map;

/**
 * A builder for constructing the parameter map that should be sent along with {@link VimeoClient#search(String, String, ModelCallback, Map, String)}.
 * This is used to refine any of your searches. It returns a map so you can add additional parameters to it.
 * <p/>
 * Created by kylevenn on 7/9/15.
 */
public class SearchRefinementBuilder {

    private final static int FIVE_MINUTES = 60 * 5; // 60 seconds * 5 minutes = 300 seconds
    Map<String, String> parameterMap;

    public SearchRefinementBuilder() {
        parameterMap = new HashMap<>();
    }

    public SearchRefinementBuilder(Vimeo.RefineSort sort) {
        parameterMap = new HashMap<>();
        this.setSort(sort);
    }

    public SearchRefinementBuilder setSort(Vimeo.RefineSort sort) {
        switch (sort) {
            case AZ:
            case ZA:
                parameterMap.put(Vimeo.PARAMETER_GET_SORT, Vimeo.SORT_ALPHABETICAL);
                parameterMap.put(Vimeo.PARAMETER_GET_DIRECTION, sort.getText());
                break;
            default:
                parameterMap.put(Vimeo.PARAMETER_GET_SORT, sort.getText());
                break;
        }
        return this;
    }

    public SearchRefinementBuilder setMinDuration(int duration) {
        parameterMap.put(Vimeo.PARAMETER_GET_LENGTH_MIN_DURATION, String.valueOf(duration));
        return this;
    }

    public SearchRefinementBuilder setMaxDuration(int duration) {
        parameterMap.put(Vimeo.PARAMETER_GET_LENGTH_MAX_DURATION, String.valueOf(duration));
        return this;
    }

    public SearchRefinementBuilder setDurationUnderFiveMinutes() {
        return setMaxDuration(FIVE_MINUTES);
    }

    public SearchRefinementBuilder setDurationOverFiveMinutes() {
        return setMinDuration(FIVE_MINUTES);
    }

    // Example: ?filter=upload_date&filter_upload_date=day
    public SearchRefinementBuilder setUploadDateFilter(Vimeo.RefineUploadDate uploadDateFilter) {
        // Only include in refinement parameters if it's not ANYTIME
        if (uploadDateFilter != Vimeo.RefineUploadDate.ANYTIME) {
            parameterMap.put(Vimeo.PARAMETER_GET_FILTER, Vimeo.FILTER_UPLOAD);
            parameterMap.put(Vimeo.PARAMETER_GET_UPLOAD_DATE_FILTER, uploadDateFilter.getText());
        }
        return this;
    }

    public Map<String, String> build() {
        return parameterMap;
    }
}
