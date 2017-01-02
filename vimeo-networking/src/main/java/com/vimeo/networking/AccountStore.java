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

import com.vimeo.networking.model.VimeoAccount;

/**
 * Interface responsible for handling the creation, deletion, and loading of Vimeo accounts on the client.
 * <p/>
 * Created by hanssena on 4/27/15.
 */
public interface AccountStore {

    VimeoAccount loadAccount();

    /**
     * @deprecated use {@link #saveAccount(VimeoAccount, String)} instead
     * <p/>
     * We find no use in storing the password when you can persist the {@link VimeoAccount} across
     * application sessions.
     */
    @Deprecated
    void saveAccount(VimeoAccount vimeoAccount, String email, String password);

    void saveAccount(VimeoAccount vimeoAccount, String email);

    void deleteAccount(VimeoAccount vimeoAccount);
}
